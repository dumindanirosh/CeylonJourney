<%-- 
    Document   : userManagement
    Created on : Feb 7, 2013, 11:06:30 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
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
                <div id="content_right">
                    <div id="content_title">
                        User Management
                    </div>
                    <%
                        User user = (User) session.getAttribute(BackendConstants.USER);
                        if (user != null) {
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

                        if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {%>
                    <li>
                        <a href="/CeylonJourney/user/addUser.jsp">Add User</a>
                    </li>
                    <br/>
                    <li>
                        <a href="/CeylonJourney/loadUsersForUpdateDelete.action?actionType=delete">Delete User</a>
                    </li>
                    <br/>

                    <%                                 }
                    %>

                    <li>
                        <a href="/CeylonJourney/loadUsersForUpdateDelete.action?actionType=view">View User</a>
                    </li>
                    <br/>
                    <li>
                        <a href="/CeylonJourney/loadUsersForUpdateDelete.action?actionType=update">Update User</a>
                    </li>
                    <br/>

                    <%
                        } else {
                            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../illegalAccess.jsp");
                            requestDispatcher.forward(request, response);
                        }

                    %>



                </div>
            </div>
        </div>
    </body>
</html>
