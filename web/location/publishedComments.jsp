<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.duminda.ceylonjourney.model.LocationComment"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
<%@page import="com.duminda.ceylonjourney.util.FrontMessages"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%-- 
    Document   : publishedComments
    Created on : Dec 14, 2012, 11:08:21 PM
    Author     : Duminda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Published Comments</title>
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
                        Published Comments
                    </div>
                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                             String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                            String successMessage = (String) request.getAttribute(BackendConstants.SUCCESS_MESSAGE);

                            Map<LocationComment, Location> map = (Map<LocationComment, Location>) request.getAttribute(BackendConstants.LOCATION_COMMENT_MAP);

                            if (successMessage != null) {

                    %>
                    <div id="success_messages_box">
                        <% out.println(successMessage);%>
                    </div>
                    <% }
if (errorMessage != null) {
                    %>
 <div id="error_messages_box">
                        <% out.println(errorMessage);%>
                    </div>
                    <%}%>
                    <div id="location_comment">
                        <%
                            if (map != null) {
                                Set<LocationComment> commentSet = map.keySet();
                                for (LocationComment comment : commentSet) {
                                    Location location = map.get(comment);
                                    String locationName = location.getLocationName();
                                    String locationComment = comment.getComment();
                                    String commentId = comment.getCommentId().toString();
                                    String locationId = Integer.toString(comment.getLocationId());
                        %>
                        <form action="/CeylonJourney/locationCommentController.action?actionType=deletePublished" method="post">
                            <div id="view_location_comment">
                                <div id="location_comment_title"><%=locationName%></div>
                                <%=locationComment%><br/>
                                <input type="hidden" name="commentId" value="<%=commentId%>"/>
                                <input type="hidden" name="locationId" value="<%=locationId%>"/>
                                <input type="hidden" name="locationComment" value="<%=locationComment%>"/>
                                <input type="submit" value="Delete"/>
                            </div>
                        </form>
                        <%
                                }
                            }
                        %>
                    </div>
                    <% }
else {
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