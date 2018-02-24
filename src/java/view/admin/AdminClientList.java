																																																																																													package view.admin;

import client.Client;
import client.ClientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.MasterPage;

@WebServlet(urlPatterns = {"/AdminClientList"})
public class AdminClientList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
        public AdminClientList() 
        {
            super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		MasterPage mp = new MasterPage();
		PrintWriter out = response.getWriter();
                if(session.getAttribute("admin")==null)
                {
                    session.setAttribute("Error","Please Login to continue...");
                    response.sendRedirect("AdminLogin");
		}
                else
                {
                
		out.println(
"<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta name=\"description\" content=\"\">\n" +
"    <meta name=\"author\" content=\"\">\n" +
"    <title>Farmer360 | Admin</title>\n" +
"    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/font-awesome.min.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/prettyPhoto.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/price-range.css\" rel=\"stylesheet\">\n" +
"    <link href=\"css/animate.css\" rel=\"stylesheet\">\n" +
"	<link href=\"css/main.css\" rel=\"stylesheet\">\n" +
"	<link href=\"css/responsive.css\" rel=\"stylesheet\">\n" +
"    <!--[if lt IE 9]>\n" +
"    <script src=\"js/html5shiv.js\"></script>\n" +
"    <script src=\"js/respond.min.js\"></script>\n" +
"    <![endif]-->       \n" +
"    <link rel=\"shortcut icon\" href=\"images/ico/favicon.ico\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"images/ico/apple-touch-icon-144-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"images/ico/apple-touch-icon-114-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"images/ico/apple-touch-icon-72-precomposed.png\">\n" +
"    <link rel=\"apple-touch-icon-precomposed\" href=\"images/ico/apple-touch-icon-57-precomposed.png\">\n" +
"</head>\n" +
"<body>\n" +
"	<header id=\"header\">\n" +
"		<div class=\"header-middle\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<div class=\"col-sm-4\">\n" +
"						<div class=\"logo pull-left\">\n" +
"							<ul class=\"nav navbar-nav\">\n" +
"								<li><a href=\"#\" style=\"color:#333333;font-size: 40px; font-family: 'Signika', sans-serif;\">Farmer360</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"					<div class=\"col-sm-8\">\n" +
"						<div class=\"shop-menu pull-right\">\n" +
"							<ul class=\"nav navbar-nav\">\n" +
"								<li><a href=\"AboutUs\"><i class=\"fa fa-users\"></i> Our Team</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	\n" +
"		<div class=\"header-bottom\"><!--header-bottom-->\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<div class=\"col-sm-9\">\n" +
"						<div class=\"navbar-header\">\n" +
"							<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n" +
"								<span class=\"sr-only\">Toggle navigation</span>\n" +
"								<span class=\"icon-bar\"></span>\n" +
"								<span class=\"icon-bar\"></span>\n" +
"								<span class=\"icon-bar\"></span>\n" +
"							</button>\n" +
"						</div>\n" +
"						<div class=\"mainmenu pull-left\">\n" +
"							<ul class=\"nav navbar-nav collapse navbar-collapse\">");
                
                                out.println("<li><a href='Home'>Home</a></li>"
                                        + "<li><a href='AdminHome'>Panel</a></li>\n"
                                        + "<li><a href='AdminItemList'>Items</a></li>\n"
                                        + "<li><a href='AdminClientList' class='active'>Clients</a></li>\n"
                                        + "<li><a href='AdminOrderList'>Orders</a></li>\n"
                                        + "<li><a href='Logout'>Logout</a></li>\n");
                                
			out.println("</ul>\n" +
                                            "                </li>"
                                                    +"</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"					<div class=\"col-sm-3\">\n" +
"						<div class=\"search_box pull-right\">\n" +
"							<input type=\"text\" placeholder=\"Search\"/>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	</header>" +
"	<section><div class=\"container\">\n" +
"			<div class=\"row\">\n" 
                                + mp.getSessionMessage(session) +
"                            <h2 class='text-uppercase'>Clients</h2>" +
                        "</div><hr>"
                        + mp.getSessionMessage(session));
                ClientDAO clientdao = new ClientDAO();
                ArrayList<Client> clients = clientdao.getClients("name asc");
                
                int counter= clients.size()-1;
                if(counter>=0)
                {
                    out.println("<div class='row'><div class='col-md-12'><div class='table-responsive'>\n" +
                                               "<table class='table table-hover'>\n" +
                                                   "<thead>\n" +
                                                       "<tr>\n" +
                                                            "<th>Name</th>\n" +
                                                            "<th>e-Mail</th>\n" +
                                                            "<th>Contact</th>\n" +
                                                            "<th>Address</th>\n" +
                                                            "<th>Pincode</th>\n" +
                                                        "</tr>\n" +
                                                    "</thead>"
                                                  + "<tbody>");
                    Client client; 
                    while(counter>=0)
                    {
                        client = clients.get(counter);
                        out.println("<tr><td>\n" + client.getName() +
                                    "</td><td>\n" + client.getEmail() +
                                    "</td><td>\n" + client.getContact() +
                                    "</td><td>\n" + client.getAddress() +
                                    "</td><td>\n" + client.getPincode() +
                                    "</td></tr>\n");
                        counter--;
                    }
                    out.println("</tbody></table></div></div></div>");
                }
        
            }
            
            out.println(
                  "</div>"
     + "</section><hr>" +
"<script src=\"js/jquery.js\"></script>\n" +
"<script src=\"js/custom.js\"></script>\n" +
"<script src=\"js/bootstrap.min.js\"></script>\n" +
"<script src=\"js/jquery.scrollUp.min.js\"></script>\n" +
"<script src=\"js/price-range.js\"></script>\n" +
"<script src=\"js/jquery.prettyPhoto.js\"></script>\n" +
"<script src=\"js/main.js\"></script>"+
"</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
