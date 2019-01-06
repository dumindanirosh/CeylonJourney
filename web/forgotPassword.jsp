<%-- 
    Document   : forgotPassword
    Created on : Feb 5, 2013, 3:00:39 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
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



                <div id="content_left"></div>
                <div id="content_right"></div>
                <div id="send_mail">
                    <b>
                        Send My Password</b>


                    <form action="forgotPassword.action">
                        Email Address : 
                        <input type="text" name="emailAddress" />
                        <input type="submit" value="Send Email" /> </form>

                    <%
                        String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                        String successMessage = (String) request.getAttribute(BackendConstants.SUCCESS_MESSAGE);

                        if (errorMessage != null) {

                    %>
                    <div id="error_messages_box">
                        <% out.println(errorMessage);%>
                    </div>
                    <% }



                        if (successMessage != null) {

                    %>
                    <div id="success_messages_box">
                        <% out.println(successMessage);%>
                    </div>
                    <% }

                    %>

                </div>
            </div>
        </div>
    </body>
</html>
