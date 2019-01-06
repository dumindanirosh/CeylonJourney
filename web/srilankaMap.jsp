<%-- 
    Document   : srilankaMap
    Created on : Feb 21, 2013, 3:10:50 PM
    Author     : Duminda
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Sri Lanka Map</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div id="wrapper">

            <div id="header">
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="menu_bar"></div>
                <div id="menu_bar">


                    <div id="templatemo_menu">
                        <ul>
                            <li><a href="viewLocationForWelcome.action">Home</a></li>
                            <li><a href="loadActiveLocations.action">View Locations</a></li>
                            <li><a href="loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="srilankaMap.jsp" class="current">Sri Lanka Map</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>

            <div id="content">
                <div id="view_hotels_locations">
                    <img src="images/map.png"/>
                    <br/>
                    
                    Image From: tourismslnow.blogspot.com
 </div>
            </div>
        </div>
    </body>
</html>