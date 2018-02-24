package view.admin;

import view.admin.*;
import admin.Admin;
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
import util.Item;
import util.ItemDAO;
import util.MasterPage;

@WebServlet(urlPatterns = {"/AdminItemDetails"})
public class AdminItemDetails extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminItemDetails() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
                //session.setAttribute("admin",(new Admin("admin1@gmail.com")).getAdminId());
                
                if(request.getParameter("itemId")==null || session.getAttribute("admin")==null)
                {
                    response.sendRedirect("AdminHome");
                }
                else
                {
                Item item = new Item(Integer.parseInt(request.getParameter("itemId")));
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
"    <title>Farmer360 | Shop</title>\n" +
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
                                                  "<li><a href=\"Account\"><i class=\"fa fa-user\"></i> Account</a></li>\n" +
"								<li><a href=\"AboutUs\"><i class=\"fa fa-users\"></i> Our Team</a></li>\n" +
"								<li><a href=\"Logout\"><i class=\"fa fa-users\"></i> Logout</a></li>\n" +
"							</ul>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"		<div class=\"header-bottom\">\n" +
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
                                                            +"<li class='active'><a href='Home'>Home</a></li>" 
                                                            + "<li><a href='AdminHome'>Panel</a></li>\n"
                                                            + "<li><a href='AdminItemList' class='active'>Items</a></li>\n"
                                                            + "<li><a href='AdminClientList'>Clients</a></li>\n"
                                                            + "<li><a href='AdminOrderList'>Orders</a></li>\n"
                                                            + "<li class='dropdown'>\n" +
                                            "                      <a href='#'>"
                                                            + (new Admin((int)session.getAttribute("admin"))).getName() + " <b class='caret'></b></a>\n" +
                                            "                      <ul role='menu' class=\"sub-menu\">\n" +
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
"	</header><br>"
     + "<section>\n" +
"		<div class=\"container\">\n" +
"			<div class=\"row\">\n" + mp.getSessionMessage(session)
                               + "<div class='col-md-12'>"
                                      + "<div class=\"product-details\"><!--product-details-->\n" +
"						<div class=\"col-sm-2\">\n" +
"							<div class=\"view-product text-center \">\n" +
"								<img style='height:150px;width:150px;' src=\"ItemImageDisplay?itemId="+item.getItemID()+"&imageId=2\" alt=\"Awesome Image\">\n"
        +                                                       "<hr>"
                                        +
"                                                           <h2>"+item.getName()+"</h2>" +
                                                            "<h2 class='text-success'><i class='fa fa-inr'></i>"+item.getSellPrice()+"</h2>"
                                                          + "<p>"+item.getUnit()+"</p>"
                                                    +"<hr><b>Categories : </b>");
                                                             
                                        Iterator<String> itr = item.getCategories().iterator();
                                        while(itr.hasNext())
                                        {
                                            String cat = itr.next();
                                            out.println("<br>" + cat);  
                                        }
                
                                        out.println("<hr><b>Available Places : </b>");
                                        
                                        itr = item.getPincodeList().iterator();
                                        while(itr.hasNext())
                                        {
                                            out.println(itr.next()+"<br>");
                                        }
                                                      out.println("<hr></div>\n" +
                                               "</div>\n" +
"						<div class=\"col-sm-7\"><div class='row'><div class=\"col-sm-12\">"
                                        + "<hr>\n<b>Ordered Quantity : </b><i>" + item.getOrderedQty() + "</i><br>"
                                                + "<b>Quantity Limit : </b><i>" + item.getQtyLimit() + "</i><br>" 
                                                + "<b>Discount : </b><i>" + item.getDiscount() + "</i><hr>"
                                            + "<form action='AdminItemInfoEdit' method='post'>"
                + "         <input type='number' value='" + item.getItemID() +
                        "' name='itemId' hidden><input type='submit' value='Edit Info' class='btn btn-sm btn-default'></form>"
                                + "<br><form action='AdminItemQtyEdit' method='post'>"
                                            + "         <input type='number' value='" + item.getItemID() +
                                    "' name='itemId' hidden><input type='submit' value='Edit Qty' class='btn btn-sm btn-default'></form>"
                                            + "<br><a  class='btn btn-sm btn-danger' href='AdminDeleteItemController?itemId="+item.getItemID()+"'>Delete</a><hr>" +
                                                        item.getDescription() + "<hr>" +
"						</div></div></div>\n" +
"						<div class=\"col-sm-3\">\n"
                                                    + "<h4>Other Items</h4><hr>");
                                
                    ArrayList<Item> itemList = (new ItemDAO()).getItems("1=1", "name asc");
                    Iterator<Item> itemitr = itemList.iterator();
                    while(itemitr.hasNext())
                    {
                        Item item1 = itemitr.next();
                        out.println("<img src='ItemImageDisplay?itemId="+item1.getItemID()+"&imageId=1' alt='item' style='width:50px;height:50px;' />"
                        + "&nbsp;&nbsp;<a href='AdminItemDetails?itemId="+item1.getItemID()+"'>" + item1.getName()+"</a><br><br>");
                    }
                                        out.println("</div>\n" +
"					</div>" 
                                            + "</div></div>");
                                        
                                    
                                
                                        
                                        
                                            out.println("</div></div></section><hr>" +
"<script src=\"js/jquery.js\"></script>\n" +
"<script src=\"js/custom.js\"></script>\n" +
"<script src=\"js/bootstrap.min.js\"></script>\n" +
"<script src=\"js/jquery.scrollUp.min.js\"></script>\n" +
"<script src=\"js/price-range.js\"></script>\n" +
"<script src=\"js/jquery.prettyPhoto.js\"></script>\n" +
"<script src=\"js/main.js\"></script>"
+ "<script type=\"text/javascript\">\n" +
    "function loadItemCost(itemId,quantity) \n" +
    "{ \n" +
    "    document.getElementById('costPrice'+itemId).innerHTML = 0;\n" +
    "    document.getElementById('costPrice'+itemId).innerHTML = (document.getElementById('sellPrice'+itemId).innerHTML*quantity);\n" +
    "}"
+ "</script>"+
"</body></html>");
                
                }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
