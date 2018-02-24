package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ItemDAO;

@WebServlet("/ClientAddToCart")
public class ClientAddToCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientAddToCart() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			HttpSession session = request.getSession(true);
		if(session.getAttribute("client")==null)
		{
                    session.setAttribute("error","Please Login to continue !");	
                    response.sendRedirect("ClientLogin");
		}
                else if(request.getParameter("itemId")==null 
                        || request.getParameter("item" + request.getParameter("itemId"))==null)
                {
                    response.sendRedirect("Logout1");
                }
		else
		{
                        int quantity = Integer.parseInt(request.getParameter("item" + request.getParameter("itemId")));
                        ItemDAO itemdao = new ItemDAO();
                        
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			
			if(itemdao.isItem(itemId))
			{
				Client client = new Client((int)session.getAttribute("client"));
				client.addToCart(session,itemId, quantity);
				response.sendRedirect("ClientHome");
			}
			else
			{
                            response.sendRedirect("Logout");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
