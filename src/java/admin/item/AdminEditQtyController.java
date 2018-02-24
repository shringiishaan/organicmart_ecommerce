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
@WebServlet("/AdminEditQtyController")
public class AdminEditQtyController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminEditQtyController() 
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
                else if(request.getParameter("itemId")==null)
                {
                    session.setAttribute("error","Some Issue !");
                    response.sendRedirect("AdminHome");
                }
                else 
                {
                    Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                    
                    if(request.getParameter("sellPrice")!=null)
                    {
                        item.setSellPrice(Integer.parseInt(request.getParameter("sellPrice")));
                    }
                    if(request.getParameter("discount")!=null)
                    {
                        item.setDiscount(Integer.parseInt(request.getParameter("discount")));
                    }
                    if(request.getParameter("qtyLimit")!=null)
                    {
                        item.setOrderQuantityLimit(Integer.parseInt(request.getParameter("qtyLimit")));
                    }
                    
                    response.sendRedirect("AdminItemQtyEdit?itemId="+request.getParameter("itemId"));
                }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
