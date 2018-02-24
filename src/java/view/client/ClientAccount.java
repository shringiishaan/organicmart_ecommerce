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

import util.*;

@WebServlet(urlPatterns = {"/ClientAccount"})
public class ClientAccount extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientAccount() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
                if(session.getAttribute("client")==null)
                {
                    session.setAttribute("error","Please Login to continue !");
                    response.sendRedirect("Logout");
                }
                else 
                {
                    
Client client = new Client((int)session.getAttribute("client"));
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
"								<li><a href=\"Logout\"><i class=\"fa fa-users\"></i> Logout</a></li>\n" +
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
                                                      + "<li><a href='ClientBasket'>Basket</a></li>"
                                                     + "<li><a href='ClientOrder'>Make Order</a></li>"
                                                     + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\" class='active'>"
                                                            + (new Client((int)session.getAttribute("client"))).getName() + " <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"ClientAccount\">Account</a></li>\n" +
                                            "                        <li><a href=\"ClientOrdersLive\">Track Orders</a></li>\n" +
                                            "                        <li><a href=\"ClientOrderHistory\">Order History</a></li>\n" +
                                            "                        <li><a href=\"Logout\">Logout</a></li>\n" +
                                            "                      </ul>\n" +
                                            "                </li>");
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
"	<section>"
             + "<div class=\"container\">\n" +
"			<div class=\"row\">\n" 
                            + mp.getSessionMessage(session) +
                            "<div class='col-md-6 col-md-offset-3'>\n" +
                                "<h2 class='text-uppercase'>Account</h2><hr>\n" + 
                                "<b>Name : </b><i>" + client.getName() + "</i><br>" +
                                "<b>e-Mail : </b><i>" + client.getEmail() + "</i><br><br>"
                              + "<form action='ClientPincodeUpdate' method='post' role='form'>\n" + 
                            "         <input type='text' name='clientId' value='" + client.getClientId() +"' hidden />"
                                    + "<div class='form-group'>"
                                        + "<label>Pincode</label>\n" + 
                            "              <input name='pincode' type='text' value='" + client.getPincode() + "' class='form-control' />" +
                                           "<input type='submit' class='btn btn-default' value='Update Pincode' />\n" + 
                                      "</div>"
                              + "</form>" 
                              + "<form action='ClientAddressUpdate' method='post' role='form'>\n" + 
                            "          <div class='form-group'>"
    +                                      "<label>Address</label>\n" + 
                            "               <textarea name='address' class='form-control'>" +client.getAddress()+ "</textarea>" +
                                           "<input type='submit' class='btn btn-default' value='Update Address' />\n" + 
                                      "</div>\n" + 
                                "</form>" +
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
        }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
