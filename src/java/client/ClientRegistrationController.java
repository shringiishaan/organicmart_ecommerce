package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ClientRegistrationController")
public class ClientRegistrationController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientRegistrationController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            HttpSession session = request.getSession(true);
            ClientDAO clientdao = new ClientDAO();

            String registrationemail = (String)request.getParameter("email");
            String registrationpassword = (String)request.getParameter("password");
            String registrationpassword2 = (String)request.getParameter("password2");
            String registrationpincode = (String)request.getParameter("pincode");
            String registrationcontact = (String)request.getParameter("contact");
            String registrationaddress = (String)request.getParameter("address");
            String registrationname = (String)request.getParameter("name");

            session.setAttribute("registrationname",registrationname);
            session.setAttribute("registrationemail",registrationemail);
            session.setAttribute("registrationpincode",registrationpincode);
            session.setAttribute("registrationcontact",registrationcontact);
            session.setAttribute("registrationaddress",registrationaddress);

            if(registrationpassword.equals(registrationpassword2))
            {	
                    if(!clientdao.isClient(registrationemail))
                    {
                            clientdao.createNewClient(registrationname, registrationemail,registrationpassword,
                                    registrationpincode,registrationaddress,registrationcontact);
                            if(clientdao.isClient(registrationemail))
                            {
                                    session.removeAttribute("client");
                                    session.setAttribute("message","Registration successful! Please <a href='Login'>Login</a> to Continue...");
                                    session.removeAttribute("registrationemail");
                                    session.removeAttribute("registrationname");
                                    response.sendRedirect("ClientHome");
                            }
                            else
                            {
                                    session.setAttribute("error","Some Issue !");
                                    response.sendRedirect("ClientLogin");
                            }
                    }
                    else
                    {
                            session.setAttribute("error","Email already Exists !");
                            response.sendRedirect("ClientLogin");
                    }
            }
            else
            {
                    session.setAttribute("error","Passwords don't match !");
                    response.sendRedirect("ClientLogin");
            }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
