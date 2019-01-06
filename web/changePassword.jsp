<%-- 
    Document   : changePassword
    Created on : Feb 12, 2013, 4:10:06 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.util.Utilities"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
String todayDate = Utilities.getTodayDate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
             <div id="wrapper">

            <div id="header">
                <jsp:include page="/dashboardMenu.jsp"/>
            </div>
         

            <div id="content">
                <div id="content_left">

                 <jsp:include page="dashboard/adminDashboardMenu.jsp"/>
                </div>
                <div id="content_right">

                    <div id="content_title">
                        Change Password
                    </div>
                
                
     
            <%

            User user = (User) session.getAttribute(BackendConstants.USER);

            if (user != null) {

                String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                if (errorMessage != null) {
                    
               %>
        <div id="messages_box">
            <% out.println(errorMessage);%>
        </div>
        <% }

        %>
        <form method="post" action="/CeylonJourney/changePassword.action">
        <table border="1">
          
            <tbody>
                <tr>
                    <td>Current Password : </td>
                    <td>
                        <input type="text" name="currentPassword" size="35"/>
                    </td>
                </tr>
                <tr>
                    <td>New Password :</td>
                    <td>
                        <input type="password" name="newPassword" size="35"/>
                    </td>
                </tr>
                
                <tr>
                    <td>Confirm Password :</td>
                    <td>
                        <input type="password" name="confirmPassword" size="35"/>
                    </td>
                </tr>
                
                     
                <tr>
                    <td></td>
                    <td>
                         <input type="submit" value="Change Password"/>
                    </td>
                </tr>
                
             
            </tbody>
        </table>
        </form>
            <%}else {
                            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    %>
    </body>
</html>
