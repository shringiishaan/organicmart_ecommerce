
package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import client.Client;

@WebServlet("/ClientRemoveFromCart")
public class ClientRemoveFromCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientRemoveFromCart() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            try
            {
		if(request.getParameter("itemId")==null)
		{
			response.sendRedirect("Logout");
		}
		else
		{    
                    HttpSession session = request.getSession();
                    
                    if(session.getAttribute("clientItemIdList")==null
                         || session.getAttribute("clientQuantityList")==null)
                    {
			response.sendRedirect("Logout");
                    }
                    else
                    {
                        ArrayList<Integer> clientItemIdList=(ArrayList<Integer>)session.getAttribute("clientItemIdList"),
                                            clientQuantityList=(ArrayList<Integer>)session.getAttribute("clientQuantityList");
                        
                        int index = clientItemIdList.indexOf(Integer.parseInt(request.getParameter("itemId")));
                        
                        clientItemIdList.remove(index);
                        (clientQuantityList).remove(index);
                        
                        session.setAttribute("clientItemIdList",clientItemIdList);
                        session.setAttribute("clientQuantityList",clientQuantityList);
                        session.setAttribute("message","Item removed from Cart !");
                    }

                    response.sendRedirect("ClientHome");
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
