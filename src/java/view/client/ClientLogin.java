package view.client;

import admin.Admin;
import client.Client;
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

@WebServlet(urlPatterns = {"/ClientLogin"})
public class ClientLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientLogin() 
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
"    <title>Farmer360 | Home</title>\n" +
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
                                            "                      </ul>\n" +
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
                              "<div class='col-md-4 col-md-offset-1'>\n" +
                                    "<div class='box'>\n" +
                                        "<h2 class='text-uppercase'>Login</h2><hr>\n" +   
                                        "<form action='ClientLoginController' method='post'>\n" +
                                            "<label>Enter Details to Login...</label><br><br>\n" +
                                            "<input ");
                                                if(session.getAttribute("clientloginemail")!=null)
                                                {
                                                    out.println(" value='" + session.getAttribute("clientloginemail") + "' type='email' name='email' placeholder='Registered e-Mail ID' class='form-control' /><br>\n" +
                                        "    				<input autofocus ");
                                                    session.removeAttribute("clientloginemail");
                                                }
                                                else
                                                {
                                                    out.println("autofocus  type='email' name='email' placeholder='Registered e-Mail ID' class='form-control' /><br>\n" +
                                                                    "<input ");
                                                }
                                                    out.println(" type='password' name='password' placeholder='Account Password' class='form-control' /><br>\n" +
                "                           <input type='submit' class=\"btn btn-block btn-md btn-info\" value='Login' />\n" +
                "    			</form>\n" +
                "    		</div>"
                              + "<hr>" +
                "    		</div>\n" +
                "    		<div class='col-md-4 col-md-offset-2'>\n" +
                                    "<div class='box'>\n" +
                                        "<h2 class='text-uppercase'>Register</h2><hr>\n" +
                                            "<form action='ClientRegistrationController' method='post'>\n" +
                                                "<label>Not yet Registered ?</label><br><br>\n" +
                                                "<input ");
						if(session.getAttribute("registrationname")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationname") + "' ");
							session.removeAttribute("registrationname");
						}
						out.println(" type='text' name='name' placeholder='Full Name' class='form-control' /><br>\n" +
                                        "    				<input ");
						if(session.getAttribute("registrationemail")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationemail") + "' ");
							session.removeAttribute("registrationemail");
						}
						out.println(" type='email' name='email' placeholder='e-Mail ID' class='form-control' /><br>\n" +
                                        "    				<input ");
						if(session.getAttribute("registrationpassword")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationpassword") + "' ");
							session.removeAttribute("registrationpassword");
						}
						out.println(" type='password' name='password' placeholder='Account Password' class='form-control' /><br>\n" +
                                        "    				<input type='password' name='password2' placeholder='Re-Enter Account Password' class='form-control' /><br>"
                                                        + "<input ");
						if(session.getAttribute("registrationcontact")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationcontact") + "' ");
							session.removeAttribute("registrationcontact");
						}
						out.println(" type='text' name='contact' placeholder='Contact' class='form-control' /><br>"
                                                        + "<input ");
						if(session.getAttribute("registrationpincode")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationpincode") + "' ");
							session.removeAttribute("registrationpincode");
						}
						out.println(" type='text' name='pincode' placeholder='pincode' class='form-control' /><br>"
                                                        + "<textarea name='address' placeholder='Detailed address' class='form-control'>");
						if(session.getAttribute("registrationaddress")!=null)
						{
							out.println(" value='" + session.getAttribute("registrationaddress") + "' ");
							session.removeAttribute("registrationaddress");
						}
						out.println("</textarea><br>\n" +
                                        "    	<input type='submit' class=\"btn btn-block btn-md btn-info\" value='Register' />\n" +
                                        "</form>\n" +
                                    "</div>"
                                  + "<hr>" +
                                "</div>\n" +
                            "</div>\n" +
                        "</div>\n" +
                  "<section><hr>"
  
+ "<footer id=\"footer\"><!--Footer-->\n" +
"		<div class=\"footer-top\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<div class=\"col-sm-2\">\n" +
"						<div class=\"companyinfo\">\n" +
"							<h2><span>Farmer</span>360</h2>\n" +
"							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"		\n" +
"		<div class=\"footer-widget\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<div class=\"col-sm-2\">\n" +
"						<div class=\"single-widget\">\n" +
"							<h2>Service</h2>\n" +
"							<ul class=\"nav nav-pills nav-stacked\">\n" +
"								<li><a href=\"#\">Online Help</a></li>\n" +
"								<li><a href=\"#\">Contact Us</a></li>\n" +
"								<li><a href=\"#\">Order Status</a></li>\n" +
"								<li><a href=\"#\">Change Location</a></li>\n" +
"								<li><a href=\"#\">FAQ’s</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"					<div class=\"col-sm-2\">\n" +
"						<div class=\"single-widget\">\n" +
"							<h2>Policies</h2>\n" +
"							<ul class=\"nav nav-pills nav-stacked\">\n" +
"								<li><a href=\"#\">Terms of Use</a></li>\n" +
"								<li><a href=\"#\">Privecy Policy</a></li>\n" +
"								<li><a href=\"#\">Refund Policy</a></li>\n" +
"								<li><a href=\"#\">Billing System</a></li>\n" +
"								<li><a href=\"#\">Ticket System</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"					<div class=\"col-sm-2\">\n" +
"						<div class=\"single-widget\">\n" +
"							<h2>About Farmer360</h2>\n" +
"							<ul class=\"nav nav-pills nav-stacked\">\n" +
"								<li><a href=\"#\">Start-Up Information</a></li>\n" +
"								<li><a href=\"#\">Careers</a></li>\n" +
"								<li><a href=\"#\">Store Location</a></li>\n" +
"								<li><a href=\"#\">Affillate Program</a></li>\n" +
"								<li><a href=\"#\">Copyright</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"					<div class=\"col-sm-3 col-sm-offset-3\">\n" +
"						<div class=\"single-widget\">\n" +
"							<h2>About Farmer360</h2>\n" +
"							<form action=\"#\" class=\"searchform\">\n" +
"								<input type=\"text\" placeholder=\"Your email address\" />\n" +
"								<button type=\"submit\" class=\"btn btn-default\"><i class=\"fa fa-arrow-circle-o-right\"></i></button>\n" +
"								<p>Get the most recent updates from <br />our site and be updated your self...</p>\n" +
"							</form>\n" +
"						</div>\n" +
"					</div>\n" +
"					\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"		\n" +
"		<div class=\"footer-bottom\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"row\">\n" +
"					<p class=\"pull-left\">Copyright &copy; 2016 Farmer360. All rights reserved.</p>\n" +
"					<p class=\"pull-right\">Developed by <span>Ishaan Shringi</span></p>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"		\n" +
"	</footer>" +
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
