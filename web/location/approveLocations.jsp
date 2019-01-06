<%-- 
    Document   : approveLocations
    Created on : Feb 18, 2013, 4:43:58 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.util.FrontMessages"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Approve Locations</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function loadSelectedLocationData(myform){
               
                myform.action = "viewSelectedLocation.action";
                myform.submit();
            }
            
            function cancel(myform){
                myform.action = "/CeylonJourney/location/locationManagement.jsp";
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
                    <jsp:include page="/dashboard/adminDashboardMenu.jsp"/>
                </div>
                <div id="content_right">

                    <div id="content_title">
                        Approve Locations
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                    %>

                    <%
                        List<Location> locationList = (List<Location>) (request.getAttribute(BackendConstants.LOCATION_LIST));
                        if (locationList != null) {

                            if (locationList.size() <= 0) {
                                request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.NO_WAITING_LOCATIONS_FOR_APPROVAL);
                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("locationManagement.jsp");
                                requestDispatcher.forward(request, response);
                            } else {
                    %>

                    <form id="loadLocationDataForm" name="loadLocationDataForm" action="viewSelectedLocation.action">
                        Select Location :   <select id="locationId"  name="locationId" readonly onchange="javascript:loadSelectedLocationData(this.form)">
                            <option>Select Location</option>
                            <%   Iterator<Location> it = locationList.iterator();
                                Location location = null;
                                while (it.hasNext()) {
                                    location = it.next();

                            %>

                            <option value="<%=location.getLocationId().toString()%>">
                                <%=location.getLocationId().toString() + "  " + location.getLocationName()%>
                            </option>



                            <%
                                }

                            %>

                        </select>
                        <input type="hidden" name="actionType" value="<%=BackendConstants.LOCATION_FOR_DASHBOARD_APPROVE%>"/>
                    </form>

                    <%
                            }
                        }
                    %>
                    <%
                        Location selectedLocation = (Location) (request.getAttribute(BackendConstants.SELECTED_LOCATION));
                        if (selectedLocation != null) {

                            String locationName = selectedLocation.getLocationName();
                            String locationAddress = selectedLocation.getLocationAddress();
                            String locationDescription = selectedLocation.getLocationDescription();

                            String imageURL = "uploadImages/" + selectedLocation.getLocationId()
                                    + "/" + selectedLocation.getWelcomeImageName();
                            String locationId = selectedLocation.getLocationId().toString();

                            Set<CityDetail> locationCityList = selectedLocation.getCityDetails();
                            Iterator<CityDetail> it = locationCityList.iterator();
                            String cityName = "";
                            if (it.hasNext()) {
                                cityName = it.next().getCityName();
                            }

                    %>
                    <form action="/CeylonJourney/locationController.action?actionType=approveLocation" method="post">
                        <img src="<%=imageURL%>" width="190" height="200" alt="people"/>

                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        Location Name :
                                    </td>
                                    <td>

                                        <input type="text" name="locationName" readonly value="<%=locationName%>" />
                                    </td>
                                </tr>



                                <tr>
                                    <td>Location City : </td>
                                    <td>

                                        <input type="text" name="locationCity" readonly value="<%=cityName%>" />

                                    </td>
                                </tr>



                                <tr>
                                    <td>Location Address : </td>
                                    <td>

                                        <input type="text" name="locationAddress" readonly value="<%=locationAddress%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Description : </td>
                                    <td>

                                        <input type="text" name="locationDescription" readonly value="<%=locationDescription%>" />

                                    </td>
                                </tr>


                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="hidden" name="locationId" value="<%=locationId%>"/>
                                        <input type="submit" value="Approve Location" />
                                        <input type="button" value="Cancel" onclick="javascript:cancel(this.form)"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </form>



                    <%
                            }

                        }else {
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
