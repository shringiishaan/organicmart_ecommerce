package admin.item;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import util.Item;
import util.ItemDAO;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/AdminCategoriesController")
public class AdminCategoriesController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminCategoriesController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
                
                if(session.getAttribute("admin")==null)
                {
                    session.setAttribute("error","Please Login to continue !");
                    response.sendRedirect("AdminLogin");
                }
                else if(request.getParameter("category")==null)
                {
                    session.setAttribute("error","Some Issue !");
                    response.sendRedirect("AdminHome");
                }
                else if(request.getParameter("Add")!=null)
                {
                    ItemDAO itemdao = new ItemDAO();
                    itemdao.createNewCategory(request.getParameter("category"));
                    session.setAttribute("message","Category Added !");
                response.sendRedirect("AdminItemCategories");
                }
                else if(request.getParameter("Remove")!=null)
                {
                    ItemDAO itemdao = new ItemDAO();
                    itemdao.deleteCategory(request.getParameter("category"));
                    session.setAttribute("message","Category Removed !");
                response.sendRedirect("AdminItemCategories");
                }
                
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
