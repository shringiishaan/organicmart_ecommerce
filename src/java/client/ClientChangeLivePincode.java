
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

@WebServlet("/ClientChangeLivePincode")
public class ClientChangeLivePincode extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientChangeLivePincode() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            try
            {
                HttpSession session = request.getSession();
                
                if(request.getParameter("livePincode")==null)
                {
                
                }
                else if(session.getAttribute("client")==null)
                {
                    session.setAttribute("message","Please Login to continue !");
                    response.sendRedirect("ClientLogin");
                }
                else
                {
                    session.setAttribute("clientLivePincode",(String)request.getParameter("livePincode"));
                    session.setAttribute("message","Pincode Updated !");
                    response.sendRedirect("Home");
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
