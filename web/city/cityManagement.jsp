<%-- 
    Document   : cityManagement
    Created on : Feb 14, 2013, 1:27:25 PM
    Author     : Gobinath
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>City Management</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>

    </head>


    <body>
        
  

        <div id="wrapper">

            <div id="header">

                <jsp:include page="/dashboardMenu.jsp"/>

            </div>

            <div id="content">
                <div id="content_left">

                    <jsp:include page="/dashboard/adminDashboardMenu.jsp"/>
                </div>
                 <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                            %>
                        
                <div id="content_right">

                    <div id="content_title">
                        City Management
                    </div>
                  
                    <div id="message_box">
                        
                        <%
                            String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                            String successMessage = (String) request.getAttribute(BackendConstants.SUCCESS_MESSAGE);
                            if (errorMessage != null) {
                            out.println("<font color='red'>" + errorMessage + "</font>");
                            } if (successMessage != null) {
                            out.println("<font color='green'>" + successMessage + "</font>");
                            }
                        %>  
                    </div>
                    <br/>
                    <br/>
                    <% if (!user.getUserType().getTypeName().equals(BackendConstants.MEMBER)) {
                    %>
                    <li><a href="/CeylonJourney/city/addCity.jsp">Add City</a></li><br/>
                    <li><a href="/CeylonJourney/loadCityForUpdateDelete.action?actionType=update">Update City</a></li><br/>
                    <%}%>
                    <li><a href="/CeylonJourney/loadCityForUpdateDelete.action?actionType=view">View City</a></li><br/>
                    <% if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {
                    %>
                    <li><a href="/CeylonJourney/loadCityForUpdateDelete.action?actionType=delete">Delete City</a></li><br/>
                    <%}%>
                    
                </div>
                    <%}else {
                            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../illegalAccess.jsp");
                            requestDispatcher.forward(request, response);
                        }%>
            </div>

        </div>

    </body>
</html>
