																																																																																													package view.client;

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
import util.Item;
import util.ItemDAO;

import util.MasterPage;

@WebServlet(urlPatterns = {"/ClientConfirmCart"})
public class ClientConfirmCart extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
        public ClientConfirmCart() 
        {
            super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(true);
		MasterPage mp = new MasterPage();
		PrintWriter out = response.getWriter();
                if(session.getAttribute("client")==null)
                {
                    session.setAttribute("Error","Please Login to continue...");
                    response.sendRedirect("ClientLogin");
		}
                else
                {
                    Client client = new Client((int)session.getAttribute("client"));
                
                if(session.getAttribute("clientItemIdList")==null)
                {
                    session.setAttribute("Error","Add Items to Basket to continue...");
                    response.sendRedirect("ClientHome");
		}
                
		out.println("<!doctype html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"	<meta charset=\"utf-8\" />\n" +
"	<link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"assets/img/apple-icon.png\">\n" +
"	<link rel=\"icon\" type=\"image/png\" href=\"assets/img/favicon.png\">		\n" +
"	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
"	<title>Farmer360 | Online Store</title>\n" +
"	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />\n" +
"    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
"    <link href=\"bootstrap3/css/bootstrap.css\" rel=\"stylesheet\" />\n" +
"    <link href=\"bootstrap3/css/font-awesome.css\" rel=\"stylesheet\" />\n" +
"	<link href=\"assets/css/gsdk.css\" rel=\"stylesheet\" />   \n" +
"    <link href=\"assets/css/demo.css\" rel=\"stylesheet\" /> \n" +
"    <link href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" rel=\"stylesheet\">\n" +
"    <link href=\"assets/css/mycustom.css\" rel=\"stylesheet\" /> \n" +
"</head>\n" +
"\n" +
"<body>\n" +
"<div id=\"navbar-full\">\n" +
"    <div class=\"container\">\n" +
"        <nav class=\"navbar navbar-ct-green navbar-fixed-top\">\n" +
"			<div class=\"container\">\n" +
"				<div class=\"navbar-header\">\n" +
"				  <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n" +
"				    <span class=\"sr-only\">Toggle navigation</span>\n" +
"				    <span class=\"icon-bar\"></span>\n" +
"				    <span class=\"icon-bar\"></span>\n" +
"				    <span class=\"icon-bar\"></span>\n" +
"				  </button>\n" +
"		            <a href=\"\">\n" +
"		                 <div class=\"logo-container\">\n" +
"		                    <div class=\"brand\">\n" +
"		                        Farmer&nbsp;360\n" +
"		                    </div>\n" +
"		                </div>\n" +
"		            </a>\n" +
"            	</div>\n" +
"            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n" +
"              <ul class=\"nav navbar-nav navbar-right\">"
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
                                                      + "<li><a data-toggle=\"modal\" data-target=\"#basket-modal\" href='#'>Basket</a></li>"
                                 + "<li class='active'><a href='ClientOrders'>Orders</a></li>"
                                        + "<li class=\"dropdown\">\n" +
                        "                      <a href=\"#gsdk\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"
                                        + (new Client((int)session.getAttribute("client"))).getName() + " <b class=\"caret\"></b></a>\n" +
                        "                      <ul class=\"dropdown-menu\">\n" +
                        "                        <li><a href=\"ClientProfile\">Profile</a></li>\n" +
                        "                        <li><a href=\"Logout\">Logout</a></li>\n" +
                        "                      </ul>\n" +
                        "                </li>"
                                                + "</ul></div>\n" +
                            "          </div>\n" +
                            "        </nav>\n" +
                            "    </div>\n"  +
                            "</div> " +
                            "<div class=\"main\">\n" +
                            "    <div class=\"container tim-container\">\n" +
                            "        <div class=\"tim-title\">\n" +
                            "            <h2>Confirm Order</h2>" +
                "                    </div>"
                                    + "<hr>" + mp.getSessionMessage(session) 
                                    + "<div class='row' style='padding:20px;'><div class='col-md-6 col-md-offset-3'>"
                                            + "<table class='table table-hover'>\n");
    if(session.getAttribute("clientItemIdList")!=null)
       {
           
            ArrayList<Integer> clientItemIdList =   (ArrayList<Integer>)session.getAttribute("clientItemIdList");
            ArrayList<Integer> clientQuantityList = (ArrayList<Integer>)session.getAttribute("clientQuantityList");

            if(!clientItemIdList.isEmpty() 
                    && clientItemIdList.size() == clientQuantityList.size())
            {
                int counter;
                for(counter=0;counter<clientItemIdList.size();counter++)
                {
                    Item item = new Item(clientItemIdList.get(counter));
                    out.println("<tr>"
                                + "<td>"+item.getName()+"</td>"
                                + "<td>"+item.getUnit()+"</td>"
                                + "<td>"+clientQuantityList.get(counter) + "</td>"
                                + "<td> <i class='fa fa-inr'></i>  "+(clientQuantityList.get(counter)*item.getSellPrice()) + "</td>"
                                + "<td><a style='color:red;' href='ClientRemoveFromCart?itemId="+clientItemIdList.get(counter)+"' class='btn btn-sm btn default'><b>X</b></a></td>"
                              + "</tr>");
                }
            }
            else
            {
                out.println("<center>No Items in Cart</center>");
            }  
        }
        else
        {
            out.println("<center>No Items in Cart</center>");
        }  
                
         out.println("</table>"
                 + "<hr><form action='ClientOrderSubmit' method='post'>"
                 + "<textarea name='orderAddress' placeholder='Detailed Address' class='form-control'>" + 
                 
                 ((client.getAddress()==null)?"":client.getAddress())
                 
                 + "</textarea><hr>"
                         + "<b>Payment Mode : </b><i>Cash On Delivery</i><hr>"
                         + "<input name='flag'  value='true' type='text' hidden />"
                         + "<input type='submit' value='Confirm Order' class='btn btn-sm btn-success form-control'/><br>"
                 + "</form>"
                         + "</div><hr></div>"
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
"	</footer>"
                         + "</body><script src=\"jquery/jquery-1.10.2.js\" type=\"text/javascript\"></script>\n" +
            "	<script src=\"assets/js/jquery-ui-1.10.4.custom.min.js\" type=\"text/javascript\"></script>\n" +
            "	<script src=\"bootstrap3/js/bootstrap.js\" type=\"text/javascript\"></script>\n" +
            "	<script src=\"assets/js/gsdk-checkbox.js\"></script>\n" +
            "	<script src=\"assets/js/gsdk-radio.js\"></script>\n" +
            "	<script src=\"assets/js/gsdk-bootstrapswitch.js\"></script>\n" +
            "	<script src=\"assets/js/get-shit-done.js\"></script>\n" +
            "    <script src=\"assets/js/custom.js\"></script></html>");
                }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
