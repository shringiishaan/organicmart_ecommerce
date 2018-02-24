
package admin.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ItemDAO;

@WebServlet("/AdminDeleteItemController")
public class AdminDeleteItemController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminDeleteItemController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            try
            {
		if(request.getParameter("itemId")==null)
		{
			response.sendRedirect("ErrorPage1");
		}
		else
		{
                        
                    HttpSession session = request.getSession();
                    
                    if(session.getAttribute("admin")==null)
                    {
			response.sendRedirect("Logout");
                    }
                    else
                    {
                        ItemDAO itemdao = new ItemDAO();
                        itemdao.deleteItem(Integer.parseInt(request.getParameter("itemId")));
                        session.setAttribute("message","Item deleted successfully !");
                    }

                    response.sendRedirect("AdminItemList");
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
