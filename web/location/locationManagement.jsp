<%-- 
    Document   : locationManagement
    Created on : Dec 16, 2012, 8:36:13 PM
    Author     : Duminda
--%>


<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.UserType"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ceylon Journey</title>
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
                        Location Management
                    </div>
                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {

                            String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                            String successMessage = (String) request.getAttribute(BackendConstants.SUCCESS_MESSAGE);
                            UserType userType = user.getUserType();
                            
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
                      <%
                    if(!userType.getTypeName().equals(BackendConstants.MEMBER)){
                    %>
                    <br/>
                    <br/>
                    <li><a href="/CeylonJourney/loadCityForLocation.action?actionType=addLocation">Add Location</a></li><br/>
                    
                    <%}
                    if(userType.getTypeName().equals(BackendConstants.ADMIN)){
                    %>
                    <li><a href="/CeylonJourney/loadLocationForUpdateDeleteView.action?actionType=<%=BackendConstants.LOCATION_FOR_DASHBOARD_UPDATE%>">Update Location</a></li><br/>
                    <li><a href="/CeylonJourney/loadLocationForUpdateDeleteView.action?actionType=<%=BackendConstants.LOCATION_FOR_DASHBOARD_DELETE%>">Delete Location</a></li><br/>
                    
                    <li><a href="/CeylonJourney/loadLocationForUpdateDeleteView.action?actionType=<%=BackendConstants.LOCATION_FOR_DASHBOARD_APPROVE%>">Approve Location</a></li><br/>
                    <li><a href="/CeylonJourney/loadLocationForUpdateDeleteView.action?actionType=<%=BackendConstants.HOT_LOCATION_FOR_DASHBOARD%>">Set Hot Location</a></li><br/>
                    
                    <%}%>
                    <li><a href="/CeylonJourney/loadLocationForUpdateDeleteView.action?actionType=<%=BackendConstants.LOCATION_FOR_DASHBOARD_VIEW%>">View Location</a></li><br/>
                    
                    <%}else {
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

