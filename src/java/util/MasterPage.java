package util;

import admin.Admin;
import client.Client;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class MasterPage 
{
	public String getMetaData(String title)
	{
		String ret;
		
		ret="<meta charset='utf-8'>\n" +
                "    <meta name='robots' content='all,follow'>\n" +
                "    <meta name='googlebot' content='index,follow,snippet,archive'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1'>\n" +
                "    <meta name='keywords' content=''>\n" +
                "    <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,500,700,800' rel='stylesheet' type='text/css'>\n" +
                "    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>\n" +
                "    <link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css'>\n" +
                "    <link href='css/animate.css' rel='stylesheet'>\n" +
                "    <link href='css/style.default.css' rel='stylesheet' id='theme-stylesheet'>\n" +
                "    <link rel='shortcut icon' href='img/favicon.ico' type='image/x-icon' />\n" +
                "    <link rel='apple-touch-icon' href='img/apple-touch-icon.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='57x57' href='img/apple-touch-icon-57x57.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='72x72' href='img/apple-touch-icon-72x72.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='76x76' href='img/apple-touch-icon-76x76.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='114x114' href='img/apple-touch-icon-114x114.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='120x120' href='img/apple-touch-icon-120x120.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='144x144' href='img/apple-touch-icon-144x144.png' />\n" +
                "    <link rel='apple-touch-icon' sizes='152x152' href='img/apple-touch-icon-152x152.png' />\n" +
                "    <link rel=\"stylesheet\" href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\">\n" +
                "    <link href='css/custom.css' rel='stylesheet'>" +
                    "   <title>" + title + "</title>\n";
		
		return ret;
	}
	
	public String getNavBar(HttpSession session)
	{
		String navbar;
		navbar="<header>\n" +
            "            <div id='top'>\n" +
            "                <div class='container'>\n" +
            "                    <div class='row'>\n" +
            "                        <div class='col-xs-5 contact'>\n" +
            "                            <a class='hidden-sm hidden-xs is_top' href='Contact'>Contact us</a>\n" +
            "                            <p class='hidden-md hidden-lg'><a href='#' data-animate-hover='pulse'><i class='fa fa-phone'></i></a>  <a href='#' data-animate-hover='pulse'><i class='fa fa-envelope'></i></a>\n" +
            "                            </p>\n" +
            "                        </div>\n" +
            "                        <div class='col-xs-7'>\n" +
            "                            <div class='login'>\n" +
            "                                <a href='login.html'><i class='fa fa-user'></i> <span class='hidden-xs text-uppercase is_top'>Sign up</span></a>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "			<div class='navbar-affixed-top' data-spy='affix' data-offset-top='200'>\n" +
            "                <div class='navbar navbar-default yamm' role='navigation' id='navbar'>\n" +
            "                    <div class='container'>\n" +
            "                        <div class='navbar-header'>\n" +
            "                            <a class='navbar-brand home' href='index.html'>\n" +
            "                            	Organic Mart\n" +
            "                            </a>\n" +
            "                            <div class='navbar-buttons'>\n" +
            "                                <button type='button' class='navbar-toggle btn-template-main' data-toggle='collapse' data-target='#navigation'>\n" +
            "                                    <span class='sr-only'>Menu</span>\n" +
            "                                    <i class='fa fa-align-justify'></i>\n" +
            "                                </button>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <div class='navbar-collapse collapse' id='navigation'>\n" +
            "                            <ul class='nav navbar-nav navbar-right'>";
		if(session.getAttribute("client")!=null)
		{
				navbar += 
				"<li>\n" + 
				"                        <a href='Home'>Home</a>\n" + 
				"                    </li>\n" 
                        + "<li class='dropdown active use-yamm yamm-fw'>\n" +
"                                    <a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown' data-delay='200'>\n" +
"                                    	Categories<b class='caret'></b></a>\n" +
"                                    <ul class='dropdown-menu'>\n" +
"                                        <li>\n" +
"                                            <div class='yamm-content'>\n" +
"                                                <div class='row'>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Vegetables</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a class='btn btn-sm btn-default' href=''>Tomato</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Chilli</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Palak</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coriander</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Methi</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Suran</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Turmeric</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Basil</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Fruits</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Banana</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Litchie</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coconut</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Papaya</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Amla</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Jamfal</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                        <h5>Type</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Organic</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>In-Organic</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </li>\n" +
"                                    </ul>\n" +
"                                </li>\n" +"                    <li><a href=\"#\" data-toggle=\"modal\" data-target=\"#basket-modal\">Basket</a></li>"
                                        + "          <li class='dropdown'>\n" + 
				//"                        <a href='#' class='dropdown-toggle' data-toggle='dropdown'>" + (new Client((int)session.getAttribute("client"))).getName() + " <b class='caret'></b></a>\n" + 
				"                        <ul class='dropdown-menu'>\n" + 
				"                            <li>\n" + 
				"                                <a href='ClientOrders'>My Orders</a>\n" + 
				"                            </li>\n" +
				"                            <li>\n" + 
				"                                <a href='ClientProfile'>Profile</a>\n" + 
				"                            </li>"
                                        + "                            	<li><a href=\"#\" data-toggle=\"modal\" data-target=\"#basket-modal\">Basket</a></li>\n" +
				"                            <li>\n" + 
				"                                <a href='Logout'>Logout</a>\n" + 
				"                            </li>\n" + 
				"                        </ul>\n" + 
				"                    </li>\n";
		}
		else if(session.getAttribute("admin")!=null)
		{
			navbar += 
				"<li>\n" + 
				"                        <a href='Home'>Home</a>\n" + 
				"                    </li>\n" 
                        + "<li class='dropdown use-yamm yamm-fw'>\n" +
"                                    <a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown' data-delay='200'>\n" +
"                                    	Categories<b class='caret'></b></a>\n" +
"                                    <ul class='dropdown-menu'>\n" +
"                                        <li>\n" +
"                                            <div class='yamm-content'>\n" +
"                                                <div class='row'>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Vegetables</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a class='btn btn-sm btn-default' href=''>Tomato</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Chilli</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Palak</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coriander</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Methi</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Suran</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Turmeric</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Basil</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Fruits</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Banana</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Litchie</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coconut</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Papaya</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Amla</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Jamfal</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                        <h5>Type</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Organic</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>In-Organic</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </li>\n" +
"                                    </ul>\n" +
"                                </li>\n" +"                    <li class='active'>\n" + 
				"                        <a href='AdminHome'>Panel</a>\n" + 
				"                    </li>\n" + 
                                "                    <li class='dropdown'>\n" + 
			"                        <a href='#' class='dropdown-toggle' data-toggle='dropdown'>" 
                                + (new Admin((int)session.getAttribute("admin"))).getName() 
                                + " <b class='caret'></b></a>\n" + 
			"                        <ul class='dropdown-menu'>\n" + 
			"                            <li>\n" + 
			"                                <a href='Logout'>Logout</a>\n" + 
			"                            </li>\n" + 
			"                        </ul>\n" + 
			"                    </li>\n";
		}
                else
                {
                    navbar+=    
                                "<li>\n" + 
				"                        <a href='Home'>Home</a>\n" + 
				"                    </li>\n" 
                        + "<li class='dropdown active use-yamm yamm-fw'>\n" +
"                                    <a href='#' class='dropdown-toggle' data-toggle='dropdown' data-hover='dropdown' data-delay='200'>\n" +
"                                    	Categories<b class='caret'></b></a>\n" +
"                                    <ul class='dropdown-menu'>\n" +
"                                        <li>\n" +
"                                            <div class='yamm-content'>\n" +
"                                                <div class='row'>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Vegetables</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a class='btn btn-sm btn-default' href=''>Tomato</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Chilli</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Palak</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coriander</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Methi</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Suran</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Turmeric</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Basil</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                    <div class='col-sm-6'>\n" +
"                                                        <h5>Fruits</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Banana</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Litchie</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Coconut</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Papaya</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Amla</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>Jamfal</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                        <h5>Type</h5>\n" +
"                                                        <ul>\n" +
"                                                            <li><a href=''>Organic</a>\n" +
"                                                            </li>\n" +
"                                                            <li><a href=''>In-Organic</a>\n" +
"                                                            </li>\n" +
"                                                        </ul>\n" +
"                                                    </div>\n" +
"                                                </div>\n" +
"                                            </div>\n" +
"                                        </li>\n" +
"                                    </ul>\n" +
"                                </li>\n" +"                    <li>\n" + 
				"                        <a href='ClientLogin'>Login</a>\n" + 
				"                    </li>\n";
                }
				navbar +=
                                            "</ul>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </header>\n" +
            "        <div class='modal fade' id='basket-modal' tabindex='-1' role='dialog' aria-labelledby='Login' aria-hidden='true'>\n" +
            "            <div class='modal-dialog modal-md'>\n" +
            "\n" +
            "                <div class='modal-content'>\n" +
            "                    <div class='modal-header'>\n" +
            "                        <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>\n" +
            "                        <h4 class='modal-title' id='Login'>My Basket</h4>\n" +
            "                    </div>\n" +
            "                    <div class='modal-body'>\n" +
            "                        	<p class='text-center text-muted'>No Items in Basket</p>\n" +
            "                            <p class='text-center'>\n" +
            "                                <button class='btn btn-template-main'><i class='fa fa-cart'></i> Proceed to Payment</button>\n" +
            "                            </p>\n" +
            "                        <p class='text-center text-muted'>Not registered yet?</p>\n" +
            "                        <p class='text-center text-muted'><a href='customer-register.html'><strong>Register now</strong></a>! It is easy and done in 1&nbsp;minute and gives you access to special discounts and much more!</p>\n" +
            "\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>";
                                
		return navbar;
	}
	
	public String getSessionMessage(HttpSession session)
	{
		String ret = "";
		if(session.getAttribute("message")!=null)
		{
			ret += "<div class='alert alert-info alert-dismissable'>\n" + 
					"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>\n" + 
					session.getAttribute("message") +  
					"</div>";
			session.removeAttribute("message");
		}
		
		if(session.getAttribute("error")!=null)
		{
			ret += "<div class='alert alert-warning alert-dismissable'>\n" + 
					"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>\n" + 
					session.getAttribute("error") + 
					"</div>";
			session.removeAttribute("error");
		}
		
		return ret;
	}
	
	public String getFooter()
	{
		String footer = "<div id='copyright'>\n" +
                        "            <div class='container'>\n" +
                        "                <div class='col-md-12'>\n" +
                        "                    <p class='pull-left'>&copy; 2016. Organic Mart</p>\n" +
                        "                    <p class='pull-right'>\n" +
                        "                    </p>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>";
		return footer;
	}
	
	public void setClientLivePincode(HttpSession session)
	{
            if(session.getAttribute("clientLivePincode")==null)
            {
                String pin;
                if(session.getAttribute("client")!=null)
                {
                    Client client = new Client((int)session.getAttribute("client"));
                    pin = (client.getPincode()==null)?"391510":client.getPincode();
                }
                else pin = "391510";
                session.setAttribute("clientLivePincode",pin);
            }
	}
	
	public String getBasketModal(HttpSession session)
	{
            String cartBtn = "<div class='modal fade' id='basket-modal' "
                    + " tabindex='-1' role='dialog' aria-labelledby='Login' aria-hidden='true'>\n" +
                    "            <div class='modal-dialog modal-md'>\n" +
                    "\n" +
                    "                <div class='modal-content'>\n" +
                    "                    <div class='modal-header'>\n" +
                    "                        <button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button>\n" +
                    "                        <h4 class='modal-title' id='Login'>My Basket</h4>\n" +
                    "                    </div>\n" +
                    "                    <div class='modal-body'>" 
                    + "<table class='table table-hover'>\n";
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
                    cartBtn += "<tr>"
                                + "<td>"+item.getName()+"</td>"
                                + "<td>"+item.getUnit()+"</td>"
                                + "<td>"+clientQuantityList.get(counter) + "</td>"
                                + "<td> <i class='fa fa-inr'></i>  "+(clientQuantityList.get(counter)*item.getSellPrice()) + "</td>"
                                + "<td><a style='color:red;' href='ClientRemoveFromCart?itemId="+clientItemIdList.get(counter)+"' class='btn btn-sm btn default'><b>X</b></a></td>"
                              + "</tr>";
                }
            }
            else
            {
                cartBtn += "<center>No Items in Cart</center>";
            }  
        }
        else
        {
            cartBtn += "<center>No Items in Cart</center>";
        }  
                
         cartBtn += "</table></div>\n" +
        "		<div class=\"modal-footer\">\n" +
        "      			<a type=\"button\" class=\"btn btn-sm btn-default btn-simple\" data-dismiss=\"modal\">Close</a>\n" +
        "       		<a href='ClientConfirmCart' type=\"button\" class=\"btn btn-sm btn-success btn-simple\">Proceed to Payment</a>\n" +
        "		</div>\n" +
        "	</div></div>\n" +
        "</div>";
		return cartBtn;
	}
	
	public String getScripts()
	{
		String scripts = "<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js'></script>\n" +
                            "    <script src='js/jquery-1.11.0.min.js'></script>" +
                            "    <script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js'></script>\n" +
                            "\n" +
                            "    <script src='js/jquery.cookie.js'></script>\n" +
                            "    <script src='js/waypoints.min.js'></script>\n" +
                            "    <script src='js/jquery.counterup.min.js'></script>\n" +
                            "    <script src='js/jquery.parallax-1.1.3.js'></script>\n" +
                            "    <script src='js/front.js'></script>";
		return scripts;
	}
        
        public String getSlider()
        {
            String ret = "<section id=\"slider\"><!--slider-->\n" +
"		<div class=\"container\">\n" +
"			<div class=\"row\">\n" +
"				<div class=\"col-sm-12\">\n" +
"					<div id=\"slider-carousel\" class=\"carousel slide\" data-ride=\"carousel\">\n" +
"						<ol class=\"carousel-indicators\">\n" +
"							<li data-target=\"#slider-carousel\" data-slide-to=\"0\" class=\"active\"></li>\n" +
"							<li data-target=\"#slider-carousel\" data-slide-to=\"1\"></li>\n" +
"							<li data-target=\"#slider-carousel\" data-slide-to=\"2\"></li>\n" +
"						</ol>\n" +
"						\n" +
"						<div class=\"carousel-inner\">\n" +
"							<div class=\"item active\">\n" +
"								<div class=\"col-sm-6\">\n" +
"									<h1><span>Farmer</span>360</h1>\n" +
"									<h2>Organic Fruits and Vegetables at best Prices</h2>\n" +
"									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>\n" +
"									<button type=\"button\" class=\"btn btn-default get\">Get it now</button>\n" +
"								</div>\n" +
"								<div class=\"col-sm-6\">\n" +
"									<img src='ItemImageDisplay?itemId=15&imageId=1' class=\"girl img-responsive\" alt=\"\" />\n" +
"								</div>\n" +
"							</div>\n" +
"							<div class=\"item\">\n" +
"								<div class=\"col-sm-6\">\n" +
"									<h1><span>Farmer</span>360</h1>\n" +
"									<h2>Organic Fruits and Vegetables at best Prices</h2>\n" +
"									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>\n" +
"									<button type=\"button\" class=\"btn btn-default get\">Get it now</button>\n" +
"								</div>\n" +
"								<div class=\"col-sm-6\">\n" +
"									<img src='ItemImageDisplay?itemId=14&imageId=1' class=\"girl img-responsive\" alt=\"\" />\n" +
"								</div>\n" +
"							</div>\n" +
"							\n" +
"							<div class=\"item\">\n" +
"								<div class=\"col-sm-6\">\n" +
"									<h1><span>Farmer</span>360</h1>\n" +
"									<h2>Organic Fruits and Vegetables at best Prices</h2>\n" +
"									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>\n" +
"									<button type=\"button\" class=\"btn btn-default get\">Get it now</button>\n" +
"								</div>\n" +
"								<div class=\"col-sm-6\">\n" +
"									<img src='ItemImageDisplay?itemId=8&imageId=1' class=\"girl img-responsive\" alt=\"\" />\n" +
"								</div>\n" +
"							</div>\n" +
"							\n" +
"						</div>\n" +
"						\n" +
"						<a href=\"#slider-carousel\" class=\"left control-carousel hidden-xs\" data-slide=\"prev\">\n" +
"							<i class=\"fa fa-angle-left\"></i>\n" +
"						</a>\n" +
"						<a href=\"#slider-carousel\" class=\"right control-carousel hidden-xs\" data-slide=\"next\">\n" +
"							<i class=\"fa fa-angle-right\"></i>\n" +
"						</a>\n" +
"					</div>\n" +
"					\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"	</section>";
            return ret;
        }
}
