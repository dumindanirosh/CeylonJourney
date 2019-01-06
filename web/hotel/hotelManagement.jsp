<%-- 
    Document   : locationManagement
    Created on : Dec 16, 2012, 8:36:13 PM
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
                        Hotel Management
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

                    %>

                    <br/>
                    <br/>
                    <%

                        if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {

                    %>
                    <li><a href="/CeylonJourney/loadCityForLocation.action?actionType=addHotel">Add Hotel</a></li><br/>
                    <%                         }
                        if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN) || user.getUserType().getTypeName().equals(BackendConstants.HOTEL_OFFICER)) {

                    %>

                    <li><a href="/CeylonJourney/loadHotelForUpdateDeleteView.action?actionType=<%=BackendConstants.HOTEL_FOR_DASHBOARD_UPDATE%>">Update Hotel</a></li><br/>

                    <%
                        }
                        if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {

                    %>
                    <li><a href="/CeylonJourney/loadHotelForUpdateDeleteView.action?actionType=<%=BackendConstants.HOTEL_FOR_DASHBOARD_DELETE%>">Delete Hotel</a></li><br/>

                    <li><a href="/CeylonJourney/loadHotelForUpdateDeleteView.action?actionType=<%=BackendConstants.HOTEL_FOR_ADD_CONTRACT%>">Add Hotel Contract</a></li><br/>
                    <li><a href="/CeylonJourney/loadHotelForUpdateDeleteView.action?actionType=<%=BackendConstants.HOTEL_FOR_UPDATE_CONTRACT%>">Update Hotel Contract</a></li><br/>
                    <%}%>
                    <li><a href="/CeylonJourney/loadHotelForUpdateDeleteView.action?actionType=<%=BackendConstants.HOTEL_FOR_DASHBOARD_VIEW%>">View Hotel</a></li><br/>
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

