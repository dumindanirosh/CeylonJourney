<%-- 
    Document   : illegalAccess
    Created on : Feb 5, 2013, 3:00:39 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Illegal Access</title>
        <link href="/CeylonJourney/styles.css" rel="stylesheet" type="text/css"/>
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
                            <li><a href="/CeylonJourney/viewLocationForWelcome.action">Home</a></li>
                            <li><a href="/CeylonJourney/loadActiveLocations.action">View Locations</a></li>
                            <li><a href="/CeylonJourney/loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="/CeylonJourney/blog.html">About us</a></li>
                            <li><a href="/CeylonJourney/contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
            <div class="cleaner"></div>
            <div id="content">

                <div id="illegal_access">
<h1>You are trying to access the system illegally</h1>
                </div>
                <div id="content_left"></div>
                <div id="content_right"></div>
                
            </div>
        </div>
    </body>
</html>
