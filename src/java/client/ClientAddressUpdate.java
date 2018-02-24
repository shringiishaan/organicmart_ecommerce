
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

@WebServlet("/ClientAddressUpdate")
public class ClientAddressUpdate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientAddressUpdate() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            try
            {
                HttpSession session = request.getSession();
                
                if(session.getAttribute("client")==null)
                {
                    session.setAttribute("message","Please Login to continue !");
                    response.sendRedirect("ClientLogin");
                }
                else if(request.getParameter("address")==null)
                {
                    response.sendRedirect("Logout");
                }
                else
                {     
                    Client client = new Client((int)session.getAttribute("client"));
                    client.updateAddress(request.getParameter("address")); 
                    session.setAttribute("message","Address Updated !");
                    response.sendRedirect("ClientAccount");
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
