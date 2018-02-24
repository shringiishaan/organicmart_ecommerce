package view.client;

import view.*;
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
import util.Item;
import util.ItemDAO;
import util.MasterPage;

@WebServlet(urlPatterns = {"/ClientPlaceOrder"})
public class ClientPlaceOrder extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientPlaceOrder() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
                Boolean isAdmin = (session.getAttribute("admin")!=null),
                        isClient = (session.getAttribute("client")!=null);
                //session.setAttribute("client",new Client("client1@gmail.com"));
                if(session.getAttribute("client")==null)
                {
                    session.setAttribute("message","Shop to Place an Order !");
                    response.sendRedirect("ClientHome");
                }
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
"    <title>Farmer360 | My Basket</title>\n" +
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
"							<ul class=\"nav navbar-nav\">"
                                                               +"<li><a href=\"Account\"><i class=\"fa fa-user\"></i> Account</a></li>\n" +
"								<li><a href=\"Logout\"><i class=\"fa fa-users\"></i> Logout</a></li>\n" +
"								<li><a href=\"AboutUs\"><i class=\"fa fa-users\"></i> Our Team</a></li>\n"
                                                            +"</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	\n" +
"		<div class=\"header-bottom\">" +
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
                                                      + "<li><a href='ClientBasket'>Basket</a></li>"
                                                     + "<li><a href='ClientPlaceOrder' class='active'>Place Order</a></li>"
                                                     + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\">"
                                                            + (new Client((int)session.getAttribute("client"))).getName() + " <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"ClientAccount\">Account</a></li>\n" +
                                            "                        <li><a href=\"ClientOrdersLive\">Track Orders</a></li>\n" +
                                            "                        <li><a href=\"ClientOrderHistory\">Order History</a></li>\n" +
                                            "                        <li><a href=\"Logout\">Logout</a></li>\n" +
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
"	</header><br>" + mp.getSessionMessage(session)
     + "<section id='cart_items'>\n" +
"		<div class='container'>\n");
                            
                           if(session.getAttribute("clientItemIdList")!=null)
                           { 
                                out.println("<div class='table-responsive cart_info'>\n" +
"				<table class='table table-condensed'>\n" +
"					<thead>\n" +
"						<tr class='cart_menu'>\n" +
"							<td class='image'>Item</td>\n" +
"							<td class='description'></td>\n" +
"							<td class='quantity'>Quantity</td>\n" +
"							<td class='price'>Price</td>\n" +
"							<td class='total'>Total</td>\n" +
"							<td></td>\n" +
"						</tr>\n" +
"					</thead>\n" +
"					<tbody>");

                                ArrayList<Integer> clientItemIdList =   (ArrayList<Integer>)session.getAttribute("clientItemIdList");
                                ArrayList<Integer> clientQuantityList = (ArrayList<Integer>)session.getAttribute("clientQuantityList");
                                int sellPriceTotal=0,discountTotal=0;
                                
                                if(!clientItemIdList.isEmpty() 
                                        && clientItemIdList.size() == clientQuantityList.size())
                                {
                                    int counter,discount,sellPrice,quantity,max=clientItemIdList.size();
                                    for(counter=0;counter<max;counter++)
                                    {
                                        Item item = new Item(clientItemIdList.get(counter));
                                        quantity = clientQuantityList.get(counter);
                                        sellPrice = item.getSellPrice();
                                        discount = item.getDiscount();
                                        sellPriceTotal += sellPrice;
                                        discountTotal += discount;
                                        
                                        out.println("<tr>"
                                                    + "<td class='cart_product'>\n" +
"								<a href='ClientItemDetails?itemId="+item.getItemID()+"'><img style='width:80px;height:80px;' src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=1' alt=''></a>\n" +
"							</td>\n" +
"							<td class='cart_description'>\n" +
"								<h4><a href='ClientItemDetails?itemId="+item.getItemID()+"'>"+item.getName()+"</a></h4>\n" +
"								<p>"+item.getUnit()+ "</p>\n" +
"							</td>\n" +
"							<td class='cart_total'>" + 
"								<p class='cart_total_price'>"+quantity + "</p>\n" +
"							</td>" +
"							<td class='cart_total'>\n" +
"								<p class='cart_total_price'> <i class='fa fa-inr'></i>  "+(sellPrice-discount) + "</p>\n" +
"							</td>\n" 
        +                                               "<td class='cart_total'>\n" +
"								<p class='cart_total_price'> <i class='fa fa-inr'></i>  "+quantity*(sellPrice-discount) + "</p>\n" +
"							</td>\n" +
"							<td class='cart_delete'>\n" +
"								<a class='cart_quantity_delete' href='ClientRemoveFromCart?itemId="+item.getItemID()+"'><i class='fa fa-times'></i></a>\n" +
"							</td>"
                                                        + "</tr>"); 
                                    }
                                }
                                else
                                {
                                    out.println("<tr><td class='text-center' span=5>No Items in Cart</td></tr>");
                                }  
                                out.println("</tbody></table>"
                                        + "</div>"
                                        + "<form action='ClientOrderSubmit' method='post'><div class='row'>" +
"							<div class='col-md-3'>" +
"								<div class=\"order-message\">" 
                                            +                           "<p>Payment Mode</p>\n" +
                "							<i class='text-success'>Cash On Devlivery</i>" +
                "						</div><br><br>"  +
"							</div>\n" +
"							<div class='col-md-5'>\n" +
"								<div class=\"order-message\">" 
                                            +                           "<p>Shipping Address</p>\n" +
                "							<textarea style='height:80px;' name='orderAddress'>"+client.getAddress()+"</textarea>\n" +
                "							<label><input type=\"checkbox\" name='orderAdressStore' value='1'> Save address to account</label>\n" +
                "						</div><br><br>" +
"							</div>\n" +
"							<div class='col-md-4'>\n" +
"								<table class='table'>\n" +
"									<tr>\n" +
"										<td>Total</td>\n" +
"										<td> <i class='fa fa-inr'></i>  "+sellPriceTotal+"</td>\n" +
"									</tr>\n");
                                                                       if(discountTotal>0)
                                                                            out.println("<tr>\n" +
                                                                                            "<td>You Save</td>\n" +
                                                                                            "<td> <i class='fa fa-inr'></i>  "+discountTotal+"</td>\n" +
                                                                                        "</tr>\n");
                                                                        out.println("<tr class=\"shipping-cost\">\n" +
"										<td>Shipping Cost</td>\n" +
"										<td>Free</td>" +
"									</tr>\n" +
"									<tr>\n" +
"										<td>Net Price</td>\n" +
"										<td><span> <i class='fa fa-inr'></i>  "+(sellPriceTotal-discountTotal)+"</span></td>\n" +
"									</tr>\n" +
"								</table>\n" +
"							</div>\n"  
                                        + "</div>"
                                        + "<hr><div class='row'>"
                                            + "<div class='text-right col-sm-12'>" +
                "				<input type=\"checkbox\" name='termsAndConditions' value='1'> Accept Terms and Conditions<br>\n"
                                                + "<a class='btn btn-primary btn-lg' href='ClientHome'>Continue Shopping</a> "
                                                + "<button type='submit' class='btn btn-primary btn-lg' >Confirm Order</button>"
                                            + "</div>"
                                        + "</div></form>");
                            }
                            else
                            {
                                    out.println("<p class='text-center'><i>No Items in Cart</i></p>");
                            }  
                
         out.println("</div>\n" +
                               "</section><hr>"
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
"<script src=\"js/main.js\"></script>" +
"</body></html>");
                
                
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
