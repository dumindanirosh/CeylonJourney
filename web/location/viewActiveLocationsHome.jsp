<%-- 
    Document   : viewActiveLocationsHome
    Created on : Feb 17, 2013, 9:40:04 PM
    Author     : Duminda
--%>

<%@page import="java.util.Set"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
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
        <title>Locations</title>
        <link href="styles.css" rel="stylesheet" type="text/css">
        <script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="wrapper">

            <div id="header">
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="menu_bar"></div>
                <div id="menu_bar">
                    <div id="templatemo_menu">
                        <ul>
                            <li><a href="viewLocationForWelcome.action">Home</a></li>
                            <li><a href="loadActiveLocations.action" class="current">View Locations</a></li>
                            <li><a href="loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="srilankaMap.jsp">Sri Lanka Map</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
            <div class="cleaner"></div>

            <div id="content">

                <div id="view_hotels_locations">


                    <%
                        List<Location> locationList = (List<Location>) (request.getAttribute(BackendConstants.ACTIVE_LOCATIONS));
                        if (locationList != null) {

                            Iterator<Location> it = locationList.iterator();
                            Location selectedLocation = null;
                            while (it.hasNext()) {
                                selectedLocation = it.next();
                                if (selectedLocation != null) {

                                    String locationName = selectedLocation.getLocationName();
                                    String locationAddress = selectedLocation.getLocationAddress();
                                    String locationDescription = selectedLocation.getLocationDescription();
                                    Integer numberOfVotes = selectedLocation.getNumberOfVotes();
                                    if (numberOfVotes == null) {
                                        numberOfVotes = 0;
                                    }
                                    String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                                    if (selectedLocation.getWelcomeImageName() != null) {
                                        imageURL = "uploadImages/" + selectedLocation.getLocationId() + "/" + selectedLocation.getWelcomeImageName();
                                    }

                                    Set<CityDetail> locationCityList = selectedLocation.getCityDetails();
                                    Iterator<CityDetail> iterator = locationCityList.iterator();
                                    String cityName = "";
                                    if (iterator.hasNext()) {
                                        cityName = iterator.next().getCityName();
                                    }


                    %>




                    <div id="view_hotels_locations_home">



                        <div id="view_selected_location">
                            <h1> <%=locationName%></h1>
                            <div id="view_hotels_locations_welcome_img">
                                <img src="<%=imageURL%>" width="190" height="200" alt="people"/>
                                <img src="images/like.png" width="16" height="16" alt="people"/>

                                <b>Total votes : </b>

                                <%=numberOfVotes%>


                            </div>
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>
                                            <b>Location City : </b></td>
                                        <td>

                                            <%=cityName%>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Location Address : </b></td>
                                        <td>
                                            <%=locationAddress%>

                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div id="view_selected_location_description_title">
                                                <b>Location Description : </b>
                                            </div>
                                        </td>
                                        <td width="300">
                                            <div id="view_selected_location_description">
                                                <%=locationDescription%>
                                            </div>
                                        </td>
                                    </tr>




                                </tbody>
                            </table>

                        </div>
                    </div>

                    <%
                            }
                        }

                    %>


                    <%
                        }
                    %>

                </div>
            </div>

    </body>
</html>
