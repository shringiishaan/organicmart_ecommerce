package view.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.*;

@WebServlet(urlPatterns = {"/AdminItemInfoEdit"})
public class AdminItemInfoEdit extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminItemInfoEdit() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
                if(session.getAttribute("admin")==null)
                {
                    session.setAttribute("error","Please Login to continue !");
                    response.sendRedirect("AdminLogin");
                }
                else if(request.getParameter("itemId")==null)
                {
                    session.setAttribute("error","Some Issue !");
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
                                                  "<li><a href=\"Account\"><i class=\"fa fa-user\"></i> Account</a></li>\n" +
"								<li><a href=\"AboutUs\"><i class=\"fa fa-users\"></i> Our Team</a></li>\n" +
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
                                        + "<li><a href='AdminHome'>Panel</a></li>\n"
                                        + "<li><a href='AdminItemList' class='active'>Items</a></li>\n"
                                        + "<li><a href='AdminClientList'>Clients</a></li>\n"
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
                "                    <div class='col-md-6 col-md-offset-3'>\n" +
                "                            <h2 class='text-uppercase'>Edit Item</h2><hr>\n" + 
					"                <form action='AdminEditItemController' method='post' enctype='multipart/form-data' role='form'>\n" + 
					"                	<input type='text' name='itemId' value='" + item.getItemID()
                        +                                           "' hidden><div class='form-group'>\n" + 
					"                   	<input value='" + item.getName()
                                + "' name='name' type='text' class='form-control'>\n" + 
	"                                   <input type='submit' class='btn btn-default' value='Update Name' name='UpdateItemName' />\n" + 
									"  	</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<input name='unit' value='" + item.getUnit()
                                        + "' type='text' class='form-control'/>\n" +  
	"                                   <input type='submit' class='btn btn-default' value='Update Unit' name='UpdateItemUnit' />\n" + 
							"</div>\n" + 
					"                	<div class='form-group'>"
                                                + " <div class='col-sm-4 thumbnail'>\n" +
                                                "      <img src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=1' class=''></div><br>\n" + 
					"                   	<label>Display pic with white background</label>"
                                                            + "<input name='itemDp' type='file' class='form-control'/>\n" +  
	"                                   <input type='submit' class='btn btn-default' value='Update Display Pic' name='UpdateItemDp' />\n" + 
							"</div>\n" + 
					"                	<div class='form-group'>\n" 
                                                + " <div class='col-sm-4 thumbnail'>\n" +
                                                "      <img src='ItemImageDisplay?itemId="+item.getItemID()+"&imageId=2' class=''></div><br>\n" + 
					"                   	<label>Original pic with any background</label>"
                                                            + "<input name='itemPic' type='file' class='form-control'/>\n" +  
	"                                   <input type='submit' class='btn btn-default' value='Update Pic 2' name='UpdateItemPic' />\n" + 
							"</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<textarea style='height:300px;' name='description'  class='form-control' placeholder='Item Description text'>"
                                                + item.getDescription() + "</textarea>\n" +  
	"                                   <input type='submit' class='btn btn-default' value='Update Description' name='UpdateItemDescription' />\n" + 
							"</div>\n" + 
	"                                    </form>" +
"                            <hr>\n" +
"                        </div>\n" +
"                    </div></section><hr>" +
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
