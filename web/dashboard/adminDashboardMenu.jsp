<%-- 
    Document   : adminDashboardMenu
    Created on : Jan 1, 2013, 12:13:55 PM
    Author     : Duminda
--%>


<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>

        <link href="../styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="item"> <a href="/CeylonJourney/signout.action">Sign Out </a></div>
        <%
            User user = (User) session.getAttribute(BackendConstants.USER);

            if (user != null) {
               

        %>
        <div id="item"> <a href="/CeylonJourney/location/locationManagement.jsp"> Location Management</a></div>
        <div id="item"> <a href="/CeylonJourney/hotel/hotelManagement.jsp">Hotel Management</a></div>
        <div id="item"><a href="/CeylonJourney/user/userManagement.jsp">User Management</a></div>
        <div id="item"><a href="/CeylonJourney/travelGuide/travelGuideManagement.jsp">Travel Guide Management</a></div>
        <div id="item"><a href="/CeylonJourney/city/cityManagement.jsp">City Management</a></div>
        <% if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {%>
        <div id="item"><a href="/CeylonJourney/viewPublishedComments.action?actionType=publishedComments">Published Comments</a></div>
        <%}}%>
    </body>
</html>
