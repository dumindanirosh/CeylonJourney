
<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.controller.LoginSessionTracking"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="com.duminda.ceylonjourney.model.LocationComment"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
<%@page import="com.duminda.ceylonjourney.util.FrontMessages"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%-- 
    Document   : memberDashboard
    Created on : Dec 14, 2012, 11:08:21 PM
    Author     : Duminda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Ceylon Journey</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
            function deleteComment(myform){
                myform.action = "/CeylonJourney/locationCommentController.action?actionType=deleteComment";
                myform.submit();
            }
        </script>
    </head>


    <body>

        <div id="wrapper">
            <div id="header">
                <jsp:include page="/dashboardMenu.jsp"/>
            </div>


            <div id="content">
                <div id="content_left">
                    <jsp:include page="adminDashboardMenu.jsp"/>
                </div>
                <div id="content_right">

                    <div id="content_title">
                        Internal Officer Dashboard
                    </div>

                    <%
                        User user = (User) session.getAttribute(BackendConstants.USER);
                        if (user != null) {
                            String successMessage = (String) request.getAttribute(BackendConstants.SUCCESS_MESSAGE);
                            Map<LocationComment, Location> map = (Map<LocationComment, Location>) request.getAttribute(BackendConstants.LOCATION_COMMENT_MAP);
                            Integer approvalRequired = (Integer) request.getAttribute("approvalRequired");
                            if (successMessage != null) {
                    %>
                    <div id="messages_box">
                        <% out.println(successMessage);%>
                    </div>
                    <% }
                    %>

                    <div id="location_comment_title">World Clock</div>
                    <div id="clock_table" align="center">
                        <table align="center" width="790" height="136" border="0">
                            <tr>
                                <td align="center" width="183">
                                    <SCRIPT type=text/javascript src="http://www.worldtimeserver.com/clocks/embed.js"></SCRIPT>
                                    <SCRIPT language=JavaScript type=text/javascript>objLK=new Object;objLK.wtsclock="wtsclock001.swf";objLK.color="000000";objLK.wtsid="LK";objLK.width=100;objLK.height=100;objLK.wmode="transparent";showClock(objLK);</SCRIPT>
                                </td>
                                <td width="200" align="center">
                                    <SCRIPT type=text/javascript src="http://www.worldtimeserver.com/clocks/embed.js"></SCRIPT>
                                    <SCRIPT language=JavaScript type=text/javascript>objUSNY=new Object;objUSNY.wtsclock="wtsclock001.swf";objUSNY.color="000000";objUSNY.wtsid="US-NY";objUSNY.width=100;objUSNY.height=100;objUSNY.wmode="transparent";showClock(objUSNY);</SCRIPT>
                                </td>
                                <td width="186" align="center">
                                    <SCRIPT type=text/javascript src="http://www.worldtimeserver.com/clocks/embed.js"></SCRIPT>
                                    <SCRIPT language=JavaScript type=text/javascript>objGB=new Object;objGB.wtsclock="wtsclock001.swf";objGB.color="000000";objGB.wtsid="GB";objGB.width=100;objGB.height=100;objGB.wmode="transparent";showClock(objGB);</SCRIPT>
                                </td>
                                <td width="193" align="center">
                                    <SCRIPT type=text/javascript src="http://www.worldtimeserver.com/clocks/embed.js"></SCRIPT>
                                    <SCRIPT language=JavaScript type=text/javascript>objJP=new Object;objJP.wtsclock="wtsclock001.swf";objJP.color="000000";objJP.wtsid="JP";objJP.width=100;objJP.height=100;objJP.wmode="transparent";showClock(objJP);</SCRIPT>
                                </td>
                            </tr>
                            <tr>
                                <td align="center">
                                    Colombo
                                </td>
                                <td align="center">
                                    New York
                                </td>
                                <td align="center">
                                    London
                                </td>
                                <td align="center">
                                    Tokoyo
                                </td>
                            </tr>
                        </table>
                    </div>
                    <br/>

                    <%
                        Location hotLocation = (Location) request.getAttribute(BackendConstants.SELECTED_HOT_LOCATION);
                        if (hotLocation != null) {
                            String locationName = hotLocation.getLocationName();
                            String locationId = hotLocation.getLocationId().toString();
                            String locationDescription = hotLocation.getLocationDescription();
                            if (hotLocation.getLocationDescription().length() > 1000) {
                                locationDescription = hotLocation.getLocationDescription().substring(0, 1000) + "...";

                            }
                            String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                            if (hotLocation.getWelcomeImageName() != null) {
                                imageURL = "uploadImages/" + hotLocation.getLocationId() + "/" + hotLocation.getWelcomeImageName();
                            }
                    %>   
                    <div id="monthly_location" >
                        <div id="location_comment_title">Monthly Hot Location: <%out.println(locationName);%></div>

                        <table border="0">
                            <tr>
                                <td height="190">       
                                    <div id="monthly_location_image_box">
                                        <a href="viewSelectedLocation.action?locationId=<%=locationId%>&actionType=<%=BackendConstants.LOCATION_FOR_HOME_VIEW%>">
                                            <img src="<%=imageURL%>" width="190" height="190">
                                        </a>

                                    </div>
                                </td>  
                                <td height="190" width="600">  
                                    <div id="monthly_location_description_for_dashboard">
                                        <%
                                            out.println(locationDescription);
                                        %>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <%}
                        }
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