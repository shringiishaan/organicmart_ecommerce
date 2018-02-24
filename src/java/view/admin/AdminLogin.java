package view.admin;

import admin.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.ItemDAO;
import util.MasterPage;

@WebServlet(urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminLogin() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		MasterPage mp = new MasterPage();
		PrintWriter out = response.getWriter();
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
"							<ul class=\"nav navbar-nav collapse navbar-collapse\">"
                                                                + "<li><a href='Home'>Home</a></li>"
                                                            + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\">Categories <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href='ClientHome?category=organic'>Organic</a></li>"+
                                            "                        <li><a href='ClientHome?category=InOrganic'>InOrganic</a></li>");
                                                             
                                                    ArrayList<String> arr = (new ItemDAO()).getCategories();
                                                    arr.remove("Organic");
                                                    arr.remove("In-Organic");
                                                    Iterator<String> itr = arr.iterator();
                                                    while(itr.hasNext())
                                                    {
                                                        String cat = itr.next();
                                                        out.println("<li><a href='ClientHome?category="+cat+"'>" + cat+"</a></li>");
                                                    }
                
                                                    out.println("</ul>\n" +
                                        "                </li>"
                                                      + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\" class='active' >Login <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"ClientLogin\">Login</a></li>\n" +
                                            "                        <li><a href=\"ClientRegister\">Register</a></li>\n" +
                                                                   "</ul>\n" +
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
                              "<div class='col-md-4 col-md-offset-4'>\n" +
                                    "<div class='box'>\n" +
                                        "<h2 class='text-uppercase'>Admin Login</h2><hr>\n" +  
"                            <form action='AdminLoginController' method='post'>" + mp.getSessionMessage(session) +
"                                <br><div class='form-group'>\n" +
"                                    <input  ");
			if(session.getAttribute("adminloginemail")!=null)
			{
				out.println(" value='" + session.getAttribute("adminloginemail") + "' type='text' name='email' class='form-control'>\n" +
"                                </div>\n" +
"                                <div class='form-group'>\n" +
"                                    <input autofocus ");
				session.removeAttribute("adminloginemail");
			}
                        else
                        {
				out.println("autofocus type='text' placeholder='email' name='email' class='form-control'>\n" +
"                                </div>\n" +
"                                <div class='form-group'>\n" +
"                                    <input ");
                        }
			out.println(" name='password' type='password' placeholder='password' class='form-control'>\n" +
"                                </div>\n" +
"                                <div class='text-center'>\n" +
"                                    <button type='submit' class='btn btn-template-main'><i class='fa fa-sign-in'></i> Log in</button>\n" +
"                                </div>\n" +
"                            </form>\n" +
                                    "</div>"
                                  + "<hr>" +
                                "</div>\n" +
                            "</div>\n" +
                        "</div>\n" +
                  "<section><hr>" +
"<script src=\"js/jquery.js\"></script>\n" +
"<script src=\"js/custom.js\"></script>\n" +
"<script src=\"js/bootstrap.min.js\"></script>\n" +
"<script src=\"js/jquery.scrollUp.min.js\"></script>\n" +
"<script src=\"js/price-range.js\"></script>\n" +
"<script src=\"js/jquery.prettyPhoto.js\"></script>\n" +
"<script src=\"js/main.js\"></script>"+
"</body></html>");
	}

}
