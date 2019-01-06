<%-- 
    Document   : editLocation
    Created on : Dec 17, 2012, 7:32:28 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
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
        <title>Update Location</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function loadSelectedLocationData(myform){
               
                myform.action = "viewSelectedLocation.action";
                myform.submit();
            }
            
            function addNewCityData(myform){
                myform.action = "/CeylonJourney/callAddCityFromLocation.action";
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
                        Update Location
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                    %>

                    <%
                        List<Location> locationList = (List<Location>) (request.getAttribute(BackendConstants.LOCATION_LIST));
                        if (locationList != null) {
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
                        <input type="hidden" name="actionType" value="<%=BackendConstants.LOCATION_FOR_DASHBOARD_UPDATE%>"/>
                    </form>

                    <%
                        }
                    %>
                    <%
                        Location selectedLocation = (Location) (request.getAttribute(BackendConstants.SELECTED_LOCATION));
                        if (selectedLocation != null) {

                            String locationName = selectedLocation.getLocationName();
                            String locationAddress = selectedLocation.getLocationAddress();
                            String locationDescription = selectedLocation.getLocationDescription();
                            Byte locationStatus = selectedLocation.getLocationStatus();

                             String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                        if (selectedLocation.getWelcomeImageName() != null) {
                            imageURL = "uploadImages/" + selectedLocation.getLocationId() + "/" + selectedLocation.getWelcomeImageName();
                        }
                            String locationId = selectedLocation.getLocationId().toString();
                            Set<CityDetail> locationCityList = selectedLocation.getCityDetails();
                            Iterator<CityDetail> it = locationCityList.iterator();
                            Integer cityId = 0;
                            if (it.hasNext()) {
                                cityId = it.next().getCityId();
                            }

                    %>
                    <form action="/CeylonJourney/locationController.action?actionType=updateLocation" method="post">
                        <img src="<%=imageURL%>" width="190" height="200" alt="people"/>

                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        Location Name : *
                                    </td>
                                    <td>
                                        <input type="hidden" name="locationId" value="<%=locationId%>"/>
                                        <input type="text" name="locationName" value="<%=locationName%>" />
                                    </td>
                                </tr>



                                <tr>
                                    <td>City Name : *</td>
                                    <td>
                                        <%
                                            List<CityDetail> cityList = (List<CityDetail>) (request.getAttribute(BackendConstants.CITY_LIST));
                                            if (cityList != null) {
                                        %>

                                        <select id="cityId"  name="cityId">
                                            <%   Iterator<CityDetail> iterator = cityList.iterator();
                                                CityDetail city = null;
                                                while (iterator.hasNext()) {
                                                    city = iterator.next();

                                                    if (cityId.equals(city.getCityId())) {

                                            %>

                                            <option selected="selected" value="<%=city.getCityId().toString()%>">
                                                <%=city.getCityName() + " in " + city.getDistrictDetail().getDistrictName()%>
                                            </option>

                                            <%} else {
                                            %>
                                            }

                                            <option value="<%=city.getCityId().toString()%>">
                                                <%=city.getCityName() + " in " + city.getDistrictDetail().getDistrictName()%>
                                            </option>

                                            <%
                                                    }
                                                }

                                            %>

                                        </select>
                                        </form


                                        <%
                                            }
                                        %>

                                        <br/>
                                        <input type="button" value="New City" name="newCityButton" onclick="javascript:addNewCityData(this.form)"/>

                                    </td>
                                </tr>



                                <tr>
                                    <td>Location Address : </td>
                                    <td>

                                        <input type="text" name="locationAddress" value="<%=locationAddress%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Description : </td>
                                    <td>

                                        <input type="text" name="locationDescription" value="<%=locationDescription%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Status : </td>
                                    <td>

                                        <%
                                            if (locationStatus == 1) {
                                        %>
                                        <input type="checkbox" name="locationStatus" checked/>
                                        <%                                } else {
                                        %>
                                        <input type="checkbox" name="locationStatus"/>
                                        <%                                    }
                                        %>



                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="hidden" name="locationId" value="<%=locationId%>"/>
                                        <input type="submit" value="Update Location" />
                                        <input type="reset" value="Reset" />
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
