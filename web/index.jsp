<%-- 
    Document   : index
    Created on : Dec 4, 2012, 3:58:14 AM
    Author     : Duminda
--%>


<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Ceylon Journey Official Web Site</title>
        <link href="styles.css" rel="stylesheet" type="text/css">
        <script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
        <script src="Scripts/swfobject_modified.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            function loadLocation(myform){
                myform.action = "/CeylonJourney/searchActiveLocations.action?actionType=searchLocation";
                myform.submit();
            }
        </script>

        <script type="text/javascript">
            function searchHotels(myform){
                myform.action = "/CeylonJourney/searchActiveHotels.action?actionType=searchLocation";
                myform.submit();
            }
        </script>

    </head>
    <body>
        <%
            List<Location> activeLocations = (List<Location>) request.getAttribute(BackendConstants.ACTIVE_LOCATIONS);
            Iterator<Location> it = activeLocations.iterator();
        %>

        <div id="wrapper">


            <div id="header">
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="top_flash" align="right">
                    <object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="468" height="60">
                        <param name="movie" value="flash/Top_Banner.swf">
                        <param name="quality" value="high">
                        <param name="wmode" value="opaque">
                        <param name="swfversion" value="8.0.35.0">
                        <!-- This param tag prompts users with Flash Player 6.0 r65 and higher to download the latest version of Flash Player. Delete it if you don’t want users to see the prompt. -->
                        <param name="expressinstall" value="Scripts/expressInstall.swf">
                        <!-- Next object tag is for non-IE browsers. So hide it from IE using IECC. -->
                        <!--[if !IE]>-->
                        <object type="application/x-shockwave-flash" data="flash/Top_Banner.swf" width="468" height="60">
                            <!--<![endif]-->
                            <param name="quality" value="high">
                            <param name="wmode" value="opaque">
                            <param name="swfversion" value="8.0.35.0">
                            <param name="expressinstall" value="Scripts/expressInstall.swf">
                            <!-- The browser displays the following alternative content for users with Flash Player 6.0 and older. -->
                            <div>
                                <h4>Content on this page requires a newer version of Adobe Flash Player.</h4>
                                <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
                            </div>
                            <!--[if !IE]>-->
                        </object>
                        <!--<![endif]-->
                    </object>
                </div>
                <div id="menu_bar">
                    <div id="templatemo_menu">
                        <ul>
                            <li><a href="viewLocationForWelcome.action" class="current">Home</a></li>
                            <li><a href="loadActiveLocations.action">View Locations</a></li>
                            <li><a href="loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="srilankaMap.jsp">Sri Lanka Map</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
            <div class="cleaner"></div>

            <div id="welcome_content">

                <div id="welcome_flash">
                    <script type="text/javascript">
                        AC_FL_RunContent( 'codebase','http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0','width','670','height','290','src','flash/WelcomeFlash','quality','high','pluginspage','http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash','movie','flash/WelcomeFlash' ); //end AC code
                    </script>
                    <noscript>
                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="670" height="290">
                        <param name="movie" value="flash/WelcomeFlash.swf">
                        <param name="quality" value="high">
                        <embed src="flash/WelcomeFlash.swf" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="670" height="290"></embed>
                    </object>
                    </noscript>
                </div>
                <div id="welcome_description">

                    <%
                        User user = (User) session.getAttribute(BackendConstants.USER);
                        if (user != null) {
                    %>
                    <div id="welcome_session"> <a href="dashboard.action"> Go To Dashboard </a> | <a href="signout.action">Sign Out </a> </div>
                    <% }%>
                    <div id="welcome_marquee">
                        <marquee behavior="alternate" scrollamount="2">
                            Ayubowan!!.. Welcome..! Wankkam..!!
                        </marquee>
                    </div>
                    <p> Sri Lanka, a land of smiles and inexplicable hospitality, colour, music, history and culture. A true paradise island surrounded by the majestic Indian Ocean that is home some of the most amazing natural miracles the world has to offer. From golden, sandy beaches to magnificent rivers and waterfalls to fascinating wildlife and marine life, wetzone forests and dry zone forests, Sri Lanka is truly an explosion of beauty and wonder. 

                        Welcome Ceylon Journey Tourism Service, official web site.Ceylon Journey Tourism, is the pioneers of Sri Lanka for finding beautiful location, cultural places, scenarios,Lakpura Travel is a leading Sri Lanka Holidays tour operator and Sri Lanka Hotels Agent based in Sri Lanka: we are Sri Lankans engaged in the leisure and recreational activities within the confines of inbound tourism in Sri Lanka</p>
                    <p>&nbsp;</p>
                </div>
            </div>

            <div class="cleaner"></div>
            <div id="location_search_area">
                <div id="search_engine">

                    <div id="search_type_box">
                        <form action="/CeylonJourney/homeSearch.action?actionType=searchLocations" method="post">
                            <div id="search_title">
                                Search The Locations
                            </div>


                            <div id="search_pattern">
                                <div id="search_type_radio">
                                    Search by Location Name <input type="radio" checked name="locationSearchType" value="<%=BackendConstants.SEARCH_BY_LOCATION_NAME%>"/>
                                </div>
                                <div id="search_type_radio">
                                    Search by Location Description <input type="radio" name="locationSearchType" value="<%=BackendConstants.SEARCH_BY_LOCATION_DESCRIPTION%>"/>
                                </div>
                                <div id="search_type_radio">
                                    Search by Location City <input type="radio" name="locationSearchType" value="<%=BackendConstants.SEARCH_BY_LOCATION_CITY%>"/>
                                </div>


                                <input type="text" size="90" name="searchKeyWord"/>
                                <input type="submit" value="Search" />
                            </div>
                        </form>
                    </div>

                    <div id="search_type_box">
                        <form action="/CeylonJourney/homeSearch.action?actionType=searchHotels" method="post">
                            <div id="search_title">
                                Search The Hotels
                            </div>

                            <div id="search_pattern">
                                <div id="search_type_radio">


                                    Search by Hotel Name <input type="radio" checked name="hotelSearchType" value="<%=BackendConstants.SEARCH_BY_HOTEL_NAME%>"/>
                                </div>
                                <div id="search_type_radio">
                                    Search by Hotel Description <input type="radio" name="hotelSearchType" value="<%=BackendConstants.SEARCH_BY_HOTEL_DESCRIPTION%>"/>
                                </div>
                                <div id="search_type_radio">
                                    Search by Hotel City <input type="radio" name="hotelSearchType" value="<%=BackendConstants.SEARCH_BY_HOTEL_CITY%>"/>
                                </div>
                                <input type="text" size="90" name="searchKeyWord"/>
                                <input type="submit" value="Search" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <%
                Location hotLocation = (Location) request.getAttribute(BackendConstants.SELECTED_HOT_LOCATION);
                if (hotLocation != null) {
                    String locationName = hotLocation.getLocationName();
                    String locationId = hotLocation.getLocationId().toString();

                    String locationDescription = hotLocation.getLocationDescription();
                    if (hotLocation.getLocationDescription().length() > 350) {
                        locationDescription = hotLocation.getLocationDescription().substring(0, 350) + "...";

                    }

                    String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                    if (hotLocation.getWelcomeImageName() != null) {
                        imageURL = "uploadImages/" + hotLocation.getLocationId() + "/" + hotLocation.getWelcomeImageName();
                    }

            %>   
            <div id="monthly_location" >
                <div id="monthly_location_title" align="center">
                    &gt;&gt;&gt;Monthly Hot Location: <%out.println(locationName);%> &lt;&lt;&lt;
                </div>
                <table border="0">
                    <tr>
                        <td height="190">       
                            <div id="monthly_location_image_box">
                                <a href="viewSelectedLocation.action?locationId=<%=locationId%>&actionType=<%=BackendConstants.LOCATION_FOR_HOME_VIEW%>">
                                    <img src="<%=imageURL%>" width="190" height="190">
                                </a>

                            </div>
                        </td>  
                        <td height="190" width="300">  
                            <div id="monthly_location_description">
                                <%
                                    out.println(locationDescription);
                                %>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>

            <%} else {
                Object[] array = activeLocations.toArray();
                hotLocation = (Location) array[0];
                String locationName = hotLocation.getLocationName();
                String locationId = hotLocation.getLocationId().toString();
                String locationDescription = hotLocation.getLocationDescription();
                if (hotLocation.getLocationDescription().length() > 400) {
                    locationDescription = hotLocation.getLocationDescription().substring(0, 400) + "...";

                }
                String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                if (hotLocation.getWelcomeImageName() != null) {
                    imageURL = "uploadImages/" + hotLocation.getLocationId() + "/" + hotLocation.getWelcomeImageName();
                }
            %>


            <%}%>


            <div class="cleaner"/>
        </div>

        <div id="location_types">

            <div id="location_type_row">
                <%

                    while (it.hasNext()) {

                        Location location = it.next();
                        String locationId = location.getLocationId().toString();
                        String imageURL = "images/location.jpg";
                        if (location.getWelcomeImageName() != null) {
                         //   imageURL = "uploadImages/" + location.getLocationId() + "/" + location.getWelcomeImageName();
                       
                        imageURL = "G:/Duminda/My/Alin/FINALPROJECT/SOFT_COPY/BK/14.10.2013/Version 2/TourPlannerEJB/TourPlannerEJB-war/web/locationimages/15/siriya.jpg";
                        }




                %>
                <div id="location_box">

                    <a href="viewSelectedLocation.action?locationId=<%=locationId%>&actionType=<%=BackendConstants.LOCATION_FOR_HOME_VIEW%>">
                        <img src="G:/Duminda/My/Alin/FINALPROJECT/SOFT_COPY/BK/14.10.2013/Version 2/TourPlannerEJB/TourPlannerEJB-war/web/locationimages/15/siriya.jpg" width="190" height="200" alt="people"/>
                    </a>
                    <div align="center" id="location_box_title">
                        <a href="viewSelectedLocation.action?locationId=<%=locationId%>&actionType=<%=BackendConstants.LOCATION_FOR_HOME_VIEW%>">
                            <%=location.getLocationName()%>
                        </a>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>




        <div id="utility_box"> 

            <div id="download_android_app_box" align="center">
                <a href="DistanceCalculator.action?actionType=download">
                    <img src="images/download.gif" width="auto" height="auto" alt="Android App"/>
                </a>

            </div>

            <div id="empty_box"></div>
            <div id="login_box">
                <form  action="login.action" method="post" >
                    <div id="login_field_name">
                        User Name :
                    </div>
                    <div id="login_field">
                        <input type="text" name="username" size="30"/>
                    </div>
                    <div id="login_field_name">
                        Password:
                    </div>
                    <div id="login_field">
                        <input type="password" name="password" size="30"/>
                    </div>
                    <div align="center" id="login_messages">
                        <%
                            String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                            if (errorMessage != null) {
                                out.print(errorMessage);
                            }

                        %>
                    </div>
                    <div id="login_field_name_no_img"></div>
                    <div id="login_field" align="center">
                        <input type="submit" value="Login"/>
                    </div>
                </form>
                <div align="center" id="forgot_password">
                    <a href="forgotPassword.jsp"> Forgot Password </a>
                </div>
                <div align="center" id="sign_up">
                    <a href="user/signupUser.jsp"> >>>> Sign Up Now <<<<< </a>
                </div>
            </div>


            <div id="time_box" align="center">
              <div id="empty_box"></div>
                <div id="cont_Mzc3MTR8M3wyfDF8NXxGRkZGRkZ8MXwwMDAwMDB8Y3wx"><div id="spa_Mzc3MTR8M3wyfDF8NXxGRkZGRkZ8MXwwMDAwMDB8Y3wx"><a id="a_Mzc3MTR8M3wyfDF8NXxGRkZGRkZ8MXwwMDAwMDB8Y3wx" href="http://www.weather-wherever.co.uk/sri-lanka/colombo_v37714/" target="_blank" style="color:#333;text-decoration:none;">Colombo Weather forecast</a> © weather-wherever.co.uk</div><script type="text/javascript" src="http://widget.weather-wherever.co.uk/js/Mzc3MTR8M3wyfDF8NXxGRkZGRkZ8MXwwMDAwMDB8Y3wx"></script></div>
               <script src="http://24timezones.com/js/swfobject.js" language="javascript"></script>
                <script src="http://24timezones.com/timescript/maindata.js.php?city=1142079" language="javascript"></script>
                <table><tr><td><div id="flash_container_tt5124b0a48e251"></div><script type="text/javascript">
                    var flashMap = new SWFObject("http://24timezones.com/timescript/clock_final.swf", "main", "175", "175", "7.0.22", "#FFFFFF", true)
                    flashMap.addParam("movie",      "http://24timezones.com/timescript/clock_final.swf");
                    flashMap.addParam("quality",    "high");
                    flashMap.addParam("wmode",    "transparent");
                    flashMap.addParam("flashvars",  "color=00CC00&logo=1&city=1142079");
                    flashMap.write("flash_container_tt5124b0a48e251");
                            </script></td></tr><tr><td style="text-align: center; font-weight: bold">
                        </td></tr></table>
            </div>
        </div>

        <div class="cleaner"></div>
        <div class="cleaner"></div>

    </div>

    <div id="footer" align="center">
        CopyRight (C) 2013 http://www.ceylonjourney.com . All Rights Reserved. Design & Developd By H D Duminda Niroshana Hettiarachchi For BCS Professional Graduate Diploma Project.
        Membership Number 990405878.
    </div>
    <script type="text/javascript">
        swfobject.registerObject("FlashID");
    </script>


</body>


</html>
