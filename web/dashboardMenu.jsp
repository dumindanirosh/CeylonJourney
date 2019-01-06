<%-- 
    Document   : dashboardMenu
    Created on : Feb 8, 2013, 8:05:52 AM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page import="com.duminda.ceylonjourney.util.Utilities"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
String todayDate = Utilities.getTodayDate();
%>
<html>
    <head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="../styles.css" rel="stylesheet" type="text/css"/>
    
    </head>
    
    <body>
    
    <div id="wrapper">
    
    <div id="header">
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="dashboard_marquee">
                <marquee behavior="alternate" scrollamount="2">
                    <h2>
                    Welcome to Ceylon Journey Tourism Service
                    </h2>
                </marquee>
                Today Date : <%= todayDate %>
                </div>
                <div id="menu_bar">


                    <div id="templatemo_menu">
                        <ul>
                            <li></li>
                            <li> <a href="/CeylonJourney/viewLocationForWelcome.action">  Home </a> </li>
                            <li> <a href="/CeylonJourney/dashboard.action">  Dashboard </a> </li>
                            <li><a href="/CeylonJourney/signout.action">Sign Out </a></li>
                            <li><a href="/CeylonJourney/changePassword.jsp">Change Password</a></li>
                            <li></li>
                        </ul> 
                    </div>


                </div>


   
            </div>
    
 <div class="cleaner"/>
    </div>
    
        
        
    
    </body>

</html>
