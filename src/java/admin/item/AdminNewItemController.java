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
import util.ItemDAO;

@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/AdminNewItemController")
public class AdminNewItemController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminNewItemController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            
		if(request.getParameter("name")==null 
                        || request.getParameter("description")==null
                        || request.getPart("itemdp") == null
                        || request.getPart("itempic") == null)
		{
			response.sendRedirect("AdminAddNewItem");
		}
		else
		{
                   try
                   {
			HttpSession session = request.getSession(true);
			ItemDAO itemdao = new ItemDAO();
		
			String name = (String)request.getParameter("name");
			String unit = (String)request.getParameter("unit");
			String description = (String)request.getParameter("description");
                            
			if(itemdao.isItem(name))
			{
                            session.setAttribute("error","Name already Exists !");
                            response.sendRedirect("AdminAddNewItem");
                        }
                        else if(unit.isEmpty())
                        {
                            session.setAttribute("error","Enter proper unit for the Item !");
                            response.sendRedirect("AdminAddNewItem");
                        }
                        else
                        {
                            InputStream inputStream1 = null, inputStream2 = null;
                            Part filePart1 = request.getPart("itemdp");
                            Part filePart2 = request.getPart("itempic");
                            if (filePart1 != null && filePart2 != null) 
                            {
                                inputStream1 = filePart1.getInputStream();
                                inputStream2 = filePart2.getInputStream();
                            }
                            itemdao.createNewItem(name,unit,description,inputStream1,inputStream2);
                            
                            if(itemdao.isItem(name))
                            {
                                    session.setAttribute("message","New Item Created !");
                                    response.sendRedirect("AdminItemList");
                            }
                            else
                            {
                                    session.setAttribute("error","Some Issue !");
                                    response.sendRedirect("AdminAddNewItem");
                            }
			}
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
