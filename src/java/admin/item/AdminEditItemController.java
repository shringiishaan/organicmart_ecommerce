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
@WebServlet("/AdminEditItemController")
public class AdminEditItemController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminEditItemController() 
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
                else if(request.getParameter("UpdateItemName")!=null)
                { 
                    if(request.getParameter("name")==null)
                    {
                        session.setAttribute("error","Enter name !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else if((new ItemDAO()).isItem((String)(request.getParameter("name"))))
                    {
                        session.setAttribute("error","Name already Exists !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else
                    {
                        String name = (String)request.getParameter("name");
                        Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                        item.setName(name);
                        session.setAttribute("message","Name updated !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                }
                else if(request.getParameter("UpdateItemUnit")!=null)
                { 
                    if(request.getParameter("unit")==null)
                    {
                        session.setAttribute("error","Enter unit !");
                    response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else
                    {
                        Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                        item.setUnit((String)request.getParameter("unit"));
                        session.setAttribute("message","Unit updated !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                }
                else if(request.getParameter("UpdateItemDescription")!=null)
                { 
                    if(request.getParameter("description")==null)
                    {
                        session.setAttribute("error","Enter description !");
                    response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else
                    {
                        Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                        item.setDescription((String)request.getParameter("description"));
                        session.setAttribute("message","Description updated !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                }
                else if(request.getParameter("UpdateItemDp")!=null)
                { 
                    if(request.getPart("itemDp")==null)
                    {
                        session.setAttribute("error","Enter Item Display Pic !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else
                    {
                        Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                        InputStream inputStream = null;
                        Part filePart = request.getPart("itemDp");
                        if (filePart != null) 
                        {
                            inputStream = filePart.getInputStream();
                        }
                        item.setItemDp(inputStream);
                        session.setAttribute("message","Item Display Pic updated !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                }
                else if(request.getParameter("UpdateItemPic")!=null)
                { 
                    if(request.getPart("itemPic")==null)
                    {
                        session.setAttribute("error","Enter Item picture !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                    else
                    {
                        Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
                        InputStream inputStream = null;
                        Part filePart = request.getPart("itemPic");
                        if (filePart != null) 
                        {
                            inputStream = filePart.getInputStream();
                        }
                        item.setItemPic(inputStream);
                        session.setAttribute("message","Item Picture updated !");
                        response.sendRedirect("AdminItemInfoEdit?itemId="+request.getParameter("itemId"));
                    }
                }
                else
		{
                    response.sendRedirect("Logout");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
