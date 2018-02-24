package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginController")
public class AdminLoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminLoginController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("email")==null || request.getParameter("password")==null)
		{
			response.sendRedirect("Logout");
		}
		else
		{
			HttpSession session = request.getSession(true);
			AdminDAO admindao = new AdminDAO();
		
			String loginemail = (String)request.getParameter("email");
			String loginpassword = (String)request.getParameter("password");
                        session.setAttribute("adminloginemail", loginemail);

			try{
			
			if(admindao.isAdmin(loginemail, loginpassword))
			{
                            session.setAttribute("admin",(new Admin(loginemail)).getAdminId());
                            session.removeAttribute("adminloginemail");
                            session.setAttribute("message","login successful!");
                            response.sendRedirect("AdminHome");
			}
			else
			{
                            if(admindao.isAdmin(loginemail))
                            {
                                session.setAttribute("adminloginemail",loginemail);
                                session.setAttribute("error","Invalid Password");
                                response.sendRedirect("AdminLogin");
                            }
                            else
                            {
                                session.removeAttribute("adminloginemail");
                                session.setAttribute("error","Invalid Email");	
                                response.sendRedirect("AdminLogin");
                            }
			}
                        
                        }catch(Exception e){response.getWriter().println(e);}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
