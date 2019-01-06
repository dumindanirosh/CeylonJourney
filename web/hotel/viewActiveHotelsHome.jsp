<%-- 
    Document   : viewActiveHotelsHome
    Created on : Feb 18, 2013, 9:01:21 AM
    Author     : Gobinath
--%>

<%@page import="com.duminda.ceylonjourney.model.HotelImageId"%>
<%@page import="com.duminda.ceylonjourney.model.HotelImage"%>
<%@page import="com.duminda.ceylonjourney.model.Hotel"%>
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
        <title>Hotels</title>
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
                            <li><a href="loadActiveLocations.action">View Locations</a></li>
                            <li><a href="loadActiveHotels.action" class="current">View Hotels</a></li>
                            <li><a href="srilankaMap.jsp">Sri Lanka Map</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
            <div class="cleaner"></div>

            <div id="content">

                <div id="content_right">


                    <div id="view_hotels_locations">


                        <%
                            List<Hotel> hotelList = (List<Hotel>) (request.getAttribute(BackendConstants.ACTIVE_HOTELS));
                            if (hotelList != null) {

                                Iterator<Hotel> it = hotelList.iterator();
                                Hotel selectedHotel = null;
                                while (it.hasNext()) {
                                    selectedHotel = it.next();
                                    if (selectedHotel != null) {

                                        String hotelName = selectedHotel.getHotelName();
                                        String hotelAddress = selectedHotel.getHotelAddress();
                                        String hotelDescription = selectedHotel.getHotelDescription();
                                        Byte hotelStatus = selectedHotel.getHotelStatus();

                                        String imageURL = BackendConstants.DEFAULT_HOTEL_IMAGE;
                            Set<HotelImage> listImages = selectedHotel.getHotelImages();
                            if (listImages.size() > 0) {
                                Iterator<HotelImage> ite = listImages.iterator();
                                imageURL = "uploadImages/hotel/" + selectedHotel.getHotelId() + "/";
                                if (ite.hasNext()) {
                                    HotelImageId hotelImageId = ite.next().getId();
                                    imageURL = imageURL + hotelImageId.getImageName();
                                }
                            }


                                        Set<CityDetail> locationCityList = selectedHotel.getCityDetails();
                                        Iterator<CityDetail> iterator = locationCityList.iterator();
                                        String cityName = "";
                                        if (iterator.hasNext()) {
                                            cityName = iterator.next().getCityName();
                                        }


                        %>
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        <div id="view_hotels_locations_home">
                    
                    
                   
                    <div id="view_selected_location">
                         <h1> <%=hotelName%></h1>
                    <div id="view_hotels_locations_welcome_img">
                        <img src="<%=imageURL%>" width="190" height="200" alt="people"/>
                    </div>
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <b>Hotel City : </b></td>
                                    <td>

                                       <%=cityName%>

                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Hotel Address : </b></td>
                                    <td>
                                        <%=hotelAddress%>

                                    </td>
                                </tr>
                                
                                <tr>
                                    <td>
                                        <div id="view_selected_location_description_title">
                                            <b>Hotel Description : </b>
                                        </div>
                                    </td>
                                    <td width="300">
                                        <div id="view_selected_location_description">
                                            <%=hotelDescription%>
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
            </div>

    </body>
</html>
