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

@WebServlet(urlPatterns = {"/AdminAddNewItem"})
public class AdminAddNewItem extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminAddNewItem() 
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
                else
                {
                
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
                "                            <h2 class='text-uppercase'>New Item</h2><hr>\n" + 
					"                <form action='AdminNewItemController' method='post' enctype='multipart/form-data' role='form'>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<input placeholder='Item name' name='name' type='text' required class='form-control'>\n" + 
									"  	</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<input name='unit' placeholder='Unit to deal with' type='text' required class='form-control'/>\n" +  
							"</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<label>Display pic with white background</label>"
                                                            + "<input name='itemdp' type='file' text='ake' value='aekfsb' required class='form-control'/>\n" +  
							"</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<label>Original pic with any background</label>"
                                                            + "<input name='itempic' type='file' required class='form-control'/>\n" +  
							"</div>\n" + 
					"                	<div class='form-group'>\n" + 
					"                   	<textarea style='height:300px;' name='description'  class='form-control' placeholder='Item Description text'>"
                        + "<div class=\"page-header\">\n" +
"                                <h4>Sample Heading</h4>\n" +
"                            </div>\n" +
"\n" +
"                            <p>Bringing unlocked me an striking ye perceive. Mr by wound hours oh happy. Me in <i>resolution</i> pianoforte continuing we. Most my no spot felt by no. He he in <b>forfeited furniture</b> sweetness he arranging. Me tedious so to behaved\n" +
"                                written account ferrars moments. Too objection for elsewhere her preferred allowance her. Marianne shutters mr steepest to me. Up mr ignorant produced distance although is sociable blessing. Ham whom call all lain like.</p>\n" +
"<ul>\n" +
"<li>Sample bullet points</li>\n" +
"<li>Sample bullet points</li>\n" +
"<li>Sample bullet points</li>\n" +
"<li>Sample bullet points</li>\n" +
"</ul>\n" +
"\n" +
"                            <p>To sorry world an at do spoil along. Incommode he depending do frankness remainder to. Edward day almost active him friend thirty piqued. People as period twenty my extent as. Set was better abroad ham plenty secure had horses.\n" +
"                                Admiration has sir decisively excellence say everything inhabiting acceptance. Sooner settle add put you sudden him.</p>\n" +
"<div class=\"page-header\">\n" +
"                                <h4>Another small Heading</h4>\n" +
"                            </div>\n" +
" <p>To sorry world an at do spoil along. Incommode he depending do frankness remainder to. Edward day almost active him friend thirty piqued. People as period twenty my extent as. Set was better abroad ham plenty secure had horses.\n" +
"                                Admiration has sir decisively excellence say everything inhabiting acceptance. Sooner settle add put you sudden him.</p>"
                        + "</textarea>\n" +  
							"</div>\n" + 
	"                                   <button type='submit' class='btn btn-default'>Add Item</button>\n" + 
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
