package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ClientLoginController")
public class ClientLoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientLoginController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			HttpSession session = request.getSession(true);
		if(request.getParameter("email")==null || request.getParameter("password")==null)
		{
                    session.setAttribute("error","Enter correct Email and Password !");	
                    response.sendRedirect("ClientLogin");
		}
		else
		{
			ClientDAO clientdao = new ClientDAO();
		
			String loginemail = (String)request.getParameter("email");
			String loginpassword = (String)request.getParameter("password");

			session.setAttribute("clientloginemail",loginemail);
			
			if(clientdao.isClient(loginemail, loginpassword))
			{
				Client client = new Client(loginemail);
				session.setAttribute("client",client.getClientId());
				session.setAttribute("message","login successful!");
				session.removeAttribute("clientloginemail");
				response.sendRedirect("ClientHome");
			}
			else
			{
				if(clientdao.isClient(loginemail))
				{
					session.setAttribute("error","Invalid Password");
					response.sendRedirect("ClientLogin");
				}
				else
				{
					session.setAttribute("error","Invalid Email");	
                                        session.removeAttribute("clientloginemail");
					response.sendRedirect("ClientLogin");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
