
package client;

import client.orders.ClientOrderItem;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Item;

@WebServlet("/ClientOrderSubmit")
public class ClientOrderSubmit extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientOrderSubmit() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            try
            {
                HttpSession session = request.getSession();
                
                if(request.getParameter("termsAndConditions")!=null)
                {
                    
                    if(request.getParameter("orderAddress")==null)
                    {
                        session.setAttribute("error","Please enter detailed address for delivery !");
                        response.sendRedirect("ClientConfirmCart");
                    }
                    
                    String orderAddress = request.getParameter("orderAddress");
                    ArrayList<ClientOrderItem> orderItemList = new ArrayList<>();
                    ArrayList<Integer> clientItemIdList =   (ArrayList<Integer>)session.getAttribute("clientItemIdList");
                    ArrayList<Integer> clientQuantityList = (ArrayList<Integer>)session.getAttribute("clientQuantityList");

                    if(!clientItemIdList.isEmpty() 
                            && clientItemIdList.size() == clientQuantityList.size())
                    {
                        ClientOrderItem clientOrderItem;
                        for(int counter=0;counter<clientItemIdList.size();counter++)
                        {
                            Item item = new Item(clientItemIdList.get(counter));
                            clientOrderItem = new ClientOrderItem(
                                    item,
                                    clientQuantityList.get(counter),
                                    item.getSellPrice(),
                                    item.getDiscount());
                                    orderItemList.add(clientOrderItem);
                        } 
                        
                        (new Client((int)session.getAttribute("client"))).submitNewOrder(orderItemList,orderAddress); 
                        session.setAttribute("message","New Order Created !");
                        response.sendRedirect("ClientOrders");
                    }
                    else
                    {
                        session.setAttribute("error","Issue !");
                        response.sendRedirect("Logout1");
                    }
                    
                        
                }
                else
                {
                    response.sendRedirect("Logout2");
                }
                
            }
            catch(Exception e)
            {
                response.getWriter().println("Error : " + e);
            }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
