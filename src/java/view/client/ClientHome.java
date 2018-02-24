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
import util.Item;
import util.ItemDAO;
import util.MasterPage;

@WebServlet(urlPatterns = {"/ClientHome"})
public class ClientHome extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public ClientHome() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
            
            HttpSession session = request.getSession();
                Boolean isAdmin = (session.getAttribute("admin")!=null),
                        isClient = (session.getAttribute("client")!=null);
            //session.setAttribute("client",new Client("client1@gmail.com"));
              
            Client client=null;
            if(isClient) client = new Client((int)session.getAttribute("client"));
                
            MasterPage mp = new MasterPage();  
            mp.setClientLivePincode(session);
            
            String sort = request.getParameter("sort"),
                    category = request.getParameter("category");
            
            if(sort == null
                    || !(sort.equals("discount")
                    || sort.equals("new")
                    || sort.equals("pricehigh")
                    || sort.equals("pricelow")))    sort = "discount";
                
            ArrayList<String> categoryList = (new ItemDAO()).getCategories();
            
            if(category == null
                    || !(category.equals("All")
                    || categoryList.contains(category)))    category = "All";
            
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
"							<ul class=\"nav navbar-nav\">\n");
                                                if(isAdmin)
                                                {
                                                    out.println("<li><a href=\"Logout\"><i class=\"fa fa-users\"></i> Logout/a></li>");
                                                }
                                                else if(isClient)
                                                {
                                                    out.println("<li><a href=\"Logout\"><i class=\"fa fa-users\"></i> Logout</a></li>");
                                                }
                                                else 
                                                {
                                                    out.println("<li><a href=\"ClientLogin\"><i class=\"fa fa-user\"></i> Register</a></li>");
                                                }
                                                    out.println("</ul>\n" +
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
"							<ul class=\"nav navbar-nav collapse navbar-collapse\">");
                                                if(isAdmin)
                                                {
                                                    out.println("<li class='active'><a href='Home'>Home</a></li>" 
                                                            + "<li><a href='AdminHome'>Panel</a></li>\n"
                                                            + "<li><a href='AdminItemList'>Items</a></li>\n"
                                                            + "<li><a href='AdminClientList' class='active'>Clients</a></li>\n"
                                                            + "<li><a href='AdminOrderList'>Orders</a></li>\n"
                                                            + "<li class='dropdown'>\n" +
                                            "                      <a href='#'>"
                                                            + (new Admin((int)session.getAttribute("admin"))).getName() + " <b class='caret'></b></a>\n" +
                                            "                      <ul role='menu' class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"Logout\">Logout</a></li>\n" +
                                            "                      </ul>\n" +
                                            "                </li>");
                                                }
                                                else if(isClient)
                                                {
                                             out.println("<li><a href='Home'>Home</a></li>"
                                                            + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\" class='active'>Categories <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href='ClientHome?category=All'>All</a></li>"+
                                            "                        <li><a href='ClientHome?category=Organic'>Organic</a></li>"+
                                            "                        <li><a href='ClientHome?category=InOrganic'>InOrganic</a></li>");
                                             
                                                    Iterator<String> itr = categoryList.iterator();
                                                    while(itr.hasNext())
                                                    {
                                                        String cat = itr.next();
                                                        if(!(cat.equals("Organic") || cat.equals("InOrganic")))
                                                            out.println("<li><a href='ClientHome?category="+cat+"'>" + cat+"</a></li>");
                                                    }
                
                                                    out.println("</ul>\n" +
                                        "                </li>"
                                                      + "<li><a href='ClientBasket'>Basket</a></li>"
                                                     + "<li><a href='ClientPlaceOrder'>Place Order</a></li>"
                                                     + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\">"
                                                            + (new Client((int)session.getAttribute("client"))).getName() + " <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"ClientAccount\">Account</a></li>\n" +
                                            "                        <li><a href=\"ClientOrdersLive\">Track Orders</a></li>\n" +
                                            "                        <li><a href=\"ClientOrderHistory\">Order History</a></li>\n" +
                                            "                        <li><a href=\"Logout\">Logout</a></li>\n" +
                                            "                      </ul>\n" +
                                            "                </li>");
                                                }
                                                else
                                                {
                                                    out.println("<li><a href='Home'>Home</a></li>"
                                                            + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#\" class='active'>Categories <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href='ClientHome?category=All'>All</a></li>"+
                                            "                        <li><a href='ClientHome?category=Organic'>Organic</a></li>"+
                                            "                        <li><a href='ClientHome?category=InOrganic'>InOrganic</a></li>");
                                             
                                                    Iterator<String> itr = categoryList.iterator();
                                                    while(itr.hasNext())
                                                    {
                                                        String cat = itr.next();
                                                        if(!(cat.equals("Organic") || cat.equals("InOrganic")))
                                                            out.println("<li><a href='ClientHome?category="+cat+"'>" + cat+"</a></li>");
                                                    }
                
                                                    out.println("</ul>\n" +
                                        "                </li>"
                                                      + "<li class=\"dropdown\">\n" +
                                            "                      <a href=\"#  \">Login <b class=\"caret\"></b></a>\n" +
                                            "                      <ul role=\"menu\" class=\"sub-menu\">\n" +
                                            "                        <li><a href=\"ClientLogin\">Login</a></li>\n" +
                                            "                        <li><a href=\"ClientRegister\">Register</a></li>\n" +
                                            "                      </ul>\n" +
                                            "                </li>");
                                                }
                                        out.println("</ul>\n" +
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
                                        +"<div class=\"col-sm-3\">\n" +
        "					<div class=\"left-sidebar\">" 
                                                + "<div class=\"brands_products\">" +
"							<div class=\"brands-name text-center\">\n" +
                                                            "<h2>Location</h2>"
                                                        + "Current Location : <i><b>"+session.getAttribute("clientLivePincode")+"</b></i><br><br>"
                                                                + "<form method='post' action='ClientChangeLivePincode'>"
                                                                    + "<div class=\"input-group\">\n" +
                                                                    "    <input type=\"text\" name='livePincode' class=\"form-control\" placeholder=\"Your Location\">\n" +
                                                                    "    <div class=\"input-group-btn\">\n" +
                                                                    "      <button class=\"btn btn-default\" type=\"submit\">Change</button>\n" +
                                                                    "    </div>\n" +
                                                                    "  </div>\n" +
                                                                    "</form><br>" +
                                                        "</div>" +
                                                    "</div>"
                                                + "<div class=\"brands_products\">" +
"							<h2>Sort</h2>\n" +
"							<div class=\"brands-name\">\n" +
                                                            "<div class=\"dropdown\">\n" +
                                                                "  <button class=\"btn btn-default dropdown-toggle\" type=\"button\" data-toggle=\"dropdown\">"
                                                                                + sort +
                                                                "  <span class=\"caret\"></span></button>\n" +
                                                                "  <ul class=\"dropdown-menu\">\n" +
                                                                "    <li><a href='ClientHome?category="+category+"&sort=discount'>Discount</a></li>\n" +
                                                                "    <li><a href='ClientHome?category="+category+"&sort=new'>New</a></li>\n" +
                                                                "    <li><a href='ClientHome?category="+category+"&sort=pricelow'>Price Low to High</a></li>\n" +
                                                                "    <li><a href='ClientHome?category="+category+"&sort=pricehigh'>Price High to Low</a></li>\n" +
                                                                "  </ul>\n" +
                                                                "</div>" +
    "							</div>\n" +
    "						   </div>" 
                            + "</div>\n" +
"				</div>\n" +
"				\n" +
"				<div class=\"col-sm-6 padding-right\">" +
"					<div class=\"features_items\">" +
"						<h2 class=\"title text-center\">"+category+"</h2>");
                
                    ArrayList<Item> itemList;
                    ItemDAO itemdao = new ItemDAO();
                    
                    if(categoryList.contains(category))
                    {
                        switch (sort) {
                            case "pricehigh":
                                itemList = itemdao.getItemsByCategory(category, " sellPrice desc ");
                                break;
                            case "pricelow":
                                itemList = itemdao.getItemsByCategory(category, " sellPrice asc ");
                                break;
                            default:
                                itemList = itemdao.getItemsByCategory(category, " discount,name desc ");
                                break;
                        }
                    }
                    else
                    {
                        if(!category.equals("All"))
                            out.println("<center><i>No Items Found</i></center><hr>");
                            
                        switch (sort) {
                            case "discount":
                                itemList = itemdao.getItems(" 1=1 "," discount desc ");
                                break;
                            case "pricehigh":
                                itemList = itemdao.getItems(" 1=1 "," sellPrice desc ");
                                break;
                            case "pricelow":
                                itemList = itemdao.getItems(" 1=1 "," sellPrice asc ");
                                break;
                            default:
                                itemList = itemdao.getItems(" 1=1 "," discount,name desc ");
                                break;
                        }
                    }
                    
                    Iterator<Item> itemitr = itemList.iterator();

                    while(itemitr.hasNext())
                    {
                        Item item = itemitr.next();
                        out.println();
                        
                                if(session.getAttribute("client")==null || session.getAttribute("clientItemIdList")==null)
                                {
                                        out.println(
                                        "<div class=\"col-sm-4\">" +
                                            "<div class=\"product-image-wrapper\">\n" +
                                                "<div class=\"single-products\">\n" +
                                                    "<div class=\"productinfo text-center\">\n" +
                                                        "<img style='width:80px;height:80px;' src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=1'>\n" 
                                                      + "<form action='ClientAddToCart' method='post' id='itemForm"+item.getItemID()+"'>" +
                                                            "<h2>"+((item.getSellPrice()==0)?"<span> N/A":("<i class='fa fa-inr'></i> <span id=\"costPrice"+item.getItemID()+"\">"+(item.getSellPrice()-item.getDiscount())))+"</span></h2>" + 
                                                          ((item.getDiscount()>0)?"<i class='text-success'>"+((item.getDiscount()*100)/item.getSellPrice())+" % OFF</i>":"")
                                                          + "<span id=\"sellPrice"+item.getItemID()+"\" hidden >"+item.getSellPrice()+"</span>" 
                                                          + "<span id=\"discountOf"+item.getItemID()+"\" hidden >"+item.getDiscount()+"</span>" +
                                                            "<a href='ClientItemDetails?itemId="+item.getItemID()+"'><h4>" 
                                                                + item.getName() + "</h4></a>" +
                                                            "<p>" + item.getUnit() + "</p>" +
                                                          ((item.getDiscount()>0)?"<p class='text-success'>You save <i class='fa fa-inr'></i> <span  id=\"discountPrice"+item.getItemID()+"\">"+item.getDiscount()+"</span></p>":"")
                                                          + "<input type='number' name='itemId' value='"+item.getItemID()+"' hidden />"
                                                          + "<div class=\"input-group col-xs-6 col-xs-offset-3 col-md-12 col-md-offset-0 col-lg-10 col-lg-offset-1\">\n" +
                                                                "<span class=\"input-group-btn\">\n" +
                                                                    "<button type=\"button\" class='btn btn-default btn-number' data-type=\"minus\" data-field='item" + item.getItemID()+"'>\n" +
                                                                            "<span class=\"glyphicon glyphicon-minus\"></span>\n" +
                                                                     "</button>\n" +
                                                                "</span>\n" +
                                                                "<input type=\"text\" name='item"+item.getItemID()+"' id='item"+item.getItemID()+"' onchange=\"loadItemCost("+item.getItemID()+",this.value)\" class='form-control input-number' value=\"1\" min=\"1\" max=\"100\" />\n" +
                                                                "<span class=\"input-group-btn\">\n" +
                                                                    "<button type=\"button\" class=\"btn btn-default btn-number\" data-type='plus' data-field='item" + item.getItemID()+"'>\n" +
                                                                        "<span class=\"glyphicon glyphicon-plus\"></span>\n" +
                                                                     "</button>\n" +
                                                                 "</span>\n" +
                                                           "</div>\n" +
                                                           "<br>\n" +
                                                           "<button type='submit'");
                                            if(!item.hasStock(3) && !item.isAvailableAt((String)session.getAttribute("clientLivePincode"))) out.println(" disabled "); 
                                        out.println("class=\"btn btn-default add-to-cart\">"
                                                            + "<i class=\"fa fa-shopping-cart\"></i> Add to cart"
                                                         + "</button>"
                                        +               "</form>\n");
                                }
                                else
                                {
                                        ArrayList<Integer> clientItemIdList = 
                                                    (ArrayList<Integer>)session.getAttribute("clientItemIdList");


                                    if(clientItemIdList.contains(item.getItemID()))
                                    {
                                            int index = clientItemIdList.indexOf(item.getItemID());
                                            ArrayList<Integer> clientQuantityList = 
                                                    (ArrayList<Integer>)session.getAttribute("clientQuantityList");
                                            int quantity = clientQuantityList.get(index);
                                            out.println("<div class=\"col-sm-4\">" +
                                                           "<div class=\"product-image-wrapper\">\n" +
                                                               "<div class=\"single-products\">\n" +
                                                                   "<div class=\"productinfo text-center\">\n" +
                                                                       "<img style='width:80px;height:80px;' src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=1'>\n" +
                                                                       "<h2><i class='fa fa-inr'></i> "+(quantity*(item.getSellPrice()-item.getDiscount()))+"</h2>\n" +
                                                                        ((item.getDiscount()>0)?"<p><i class='text-success'>"+((item.getDiscount()*100)/item.getSellPrice())+" % OFF</i></p>":"") +
                                                                       "<a href='ClientItemDetails?itemId="+item.getItemID()+"'><h4>" + item.getName() + "</h4></a>" +
                                                                       "<p>" + item.getUnit() + "</p>"   +
                                                                        ((item.getDiscount()>0)?"<p> You saved <i class='fa fa-inr'></i> " + (quantity*(item.getDiscount())) + "</p>":"") 
                                                                     + "<a class='btn btn-info text-center' href='ClientBasket'>" + quantity + " in Basket</a>\n" +
                                                                       "<a class='btn btn-default' href='ClientRemoveFromCart?itemId="+item.getItemID()+"'><i class='fa fa-times'></i></a><div style='height:10px;'></div>\n");
                                       }
                                    else
                                    {
                                        out.println(
                                        "<div class=\"col-sm-4\">" +
                                            "<div class=\"product-image-wrapper\">\n" +
                                                "<div class=\"single-products\">\n" +
                                                    "<div class=\"productinfo text-center\">\n" +
                                                        "<img style='width:80px;height:80px;' src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=1'>\n" 
                                                      + "<form action='ClientAddToCart' method='post' id='itemForm"+item.getItemID()+"'>" +
                                                            "<h2>"+((item.getSellPrice()==0)?"<span> N/A":("<i class='fa fa-inr'></i> <span id=\"costPrice"+item.getItemID()+"\">"+(item.getSellPrice()-item.getDiscount())))+"</span></h2>" + 
                                                          ((item.getDiscount()>0)?"<i class='text-success'>"+((item.getDiscount()*100)/item.getSellPrice())+" % OFF</i>":"")
                                                          + "<span id=\"sellPrice"+item.getItemID()+"\" hidden >"+item.getSellPrice()+"</span>" 
                                                          + "<span id=\"discountOf"+item.getItemID()+"\" hidden >"+item.getDiscount()+"</span>" +
                                                            "<a href='ClientItemDetails?itemId="+item.getItemID()+"'><h4>" 
                                                                + item.getName() + "</h4></a>" +
                                                            "<p>" + item.getUnit() + "</p>" +
                                                          ((item.getDiscount()>0)?"<p class='text-success'>You save <i class='fa fa-inr'></i> <span  id=\"discountPrice"+item.getItemID()+"\">"+item.getDiscount()+"</span></p>":"")
                                                          + "<input type='number' name='itemId' value='"+item.getItemID()+"' hidden />"
                                                          + "<div class=\"input-group col-xs-6 col-xs-offset-3 col-md-12 col-md-offset-0 col-lg-10 col-lg-offset-1\">\n" +
                                                                "<span class=\"input-group-btn\">\n" +
                                                                    "<button type=\"button\" class='btn btn-default btn-number' data-type=\"minus\" data-field='item" + item.getItemID()+"'>\n" +
                                                                            "<span class=\"glyphicon glyphicon-minus\"></span>\n" +
                                                                     "</button>\n" +
                                                                "</span>\n" +
                                                                "<input type=\"text\" name='item"+item.getItemID()+"' id='item"+item.getItemID()+"' onchange=\"loadItemCost("+item.getItemID()+",this.value)\" class='form-control input-number' value=\"1\" min=\"1\" max=\"100\" />\n" +
                                                                "<span class=\"input-group-btn\">\n" +
                                                                    "<button type=\"button\" class=\"btn btn-default btn-number\" data-type='plus' data-field='item" + item.getItemID()+"'>\n" +
                                                                        "<span class=\"glyphicon glyphicon-plus\"></span>\n" +
                                                                     "</button>\n" +
                                                                 "</span>\n" +
                                                           "</div>\n" +
                                                           "<br>\n" +
                                                           "<button type='submit'");
                                            if(!item.hasStock(3) && !item.isAvailableAt((String)session.getAttribute("clientLivePincode"))) out.println(" disabled "); 
                                        out.println("class=\"btn btn-default add-to-cart\">"
                                                            + "<i class=\"fa fa-shopping-cart\"></i> Add to cart"
                                                         + "</button>"
                                        +               "</form>\n");
                                    }
                                }


                                out.println((item.isAvailableAt((String)session.getAttribute("clientLivePincode"))?"<p><i class='text-success'>Available</i></p>":"<p><i class='text-danger'>Not Available</i></p>")
                                        +(item.hasStock(3)?"":"<p><i class='text-danger'>Out Of Stock</i></p>")+ "</div>");
                                if(item.getDiscount()>0)
                                        out.println("<img src=\"images/home/sale.png\" class=\"new\" alt=\"\">");
                                out.println("</div></div>"
                                        + "<hr></div>");
                        }

                            out.println("</div></div>"
                                        +"<div class=\"col-sm-3\">\n" +
        "					<div class=\"left-sidebar\">" 
                                                + "<div class=\"brands_products\">" +
"							<h2>Categories</h2>\n" +
"							<div class=\"brands-name\">\n" +
"								<ul class=\"nav nav-pills nav-stacked\">\n" +
"									<li><a href='ClientHome?category=Organic'> <span class=\"pull-right\">(50)</span>Organic</a></li>" +
"									<li><a href='ClientHome?category=InOrganic'> <span class=\"pull-right\">(50)</span>InOrganic</a></li><br>");
                                             
                                                    Iterator<String> itr = categoryList.iterator();
                                                    while(itr.hasNext())
                                                    {
                                                        String cat = itr.next();
                                                        if(!(cat.equals("Organic") || cat.equals("InOrganic")))
                                                            out.println("<li><a href='ClientHome?category="+cat+"'> <span class=\"pull-right\">(50)</span>"+cat+"</a></li>");
                                                    }
                
                                                    out.println("</ul>\n" +
    "							</div>\n" +
    "						   </div>" 
                            + "</div>\n"
                                    + "</div>\n" +
                                    "</div>\n" +
                               "</section>"
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
"<script src=\"js/main.js\"></script>"
+ "<script type=\"text/javascript\">\n" +
    "function loadItemCost(itemId,quantity) \n" +
    "{ \n" +
    "    document.getElementById('costPrice'+itemId).innerHTML = 0;\n" +
    "    document.getElementById('costPrice'+itemId).innerHTML = ((document.getElementById('sellPrice'+itemId).innerHTML - document.getElementById('discountOf'+itemId).innerHTML)*quantity);\n" +
    "    document.getElementById('discountPrice'+itemId).innerHTML = 0;\n" +
    "    document.getElementById('discountPrice'+itemId).innerHTML = (document.getElementById('discountOf'+itemId).innerHTML*quantity);\n" +
    "}"
+ "</script>"+
"</body></html>");
                
                
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
