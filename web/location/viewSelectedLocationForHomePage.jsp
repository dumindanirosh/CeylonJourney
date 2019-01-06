<%-- 
    Document   : viewSelectedLocationForHomePage
    Created on : Feb 14, 2013, 5:00:34 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.model.LocationComment"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="com.duminda.ceylonjourney.model.Location"%>
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
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="menu_bar"></div>
                <div id="menu_bar">


                    <div id="templatemo_menu">
                        <ul>
                            <li><a href="viewLocationForWelcome.action" >Home</a></li>
                            <li><a href="loadActiveLocations.action" class="current">View Locations</a></li>
                            <li><a href="loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="srilankaMap.jsp">Sri Lanka Map</a></li>
                            <li><a href="contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>

            <div id="content">
                <%
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
                    
                <div id="view_hotels_locations">
                    <%
                        Location location = (Location) request.getAttribute(BackendConstants.SELECTED_LOCATION);
                        if (location != null) {
                            String imageURL = BackendConstants.DEFAULT_LOCATION_IMAGE;
                            if (location.getWelcomeImageName() != null) {
                                imageURL = "uploadImages/" + location.getLocationId() + "/" + location.getWelcomeImageName();
                            }
                            String locationId = location.getLocationId().toString();
                            Integer numberOfVotes = location.getNumberOfVotes();
                            if(numberOfVotes==null){
                                numberOfVotes=0;
                            }
                    %>
                    <h1> <%=location.getLocationName()%></h1>

                    <div id="view_selected_location">
                        <div id="view_hotels_locations_welcome_img">
                            <img src="<%=imageURL%>" width="190" height="200" alt="people"/>
                            <img src="images/like.png" width="16" height="16" alt="people"/>
                              
                                        <b>Total votes : </b>
                                   
                                        <%=numberOfVotes%>
                        </div>
                        <table border="0">
                            <tbody>

                                <tr>
                                    <td><b>Location Address : </b></td>
                                    <td>

                                        <%=location.getLocationAddress()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Location City : </b></td>
                                    <td>

                                        <%
                                            Set<CityDetail> listCity = location.getCityDetails();
                                            Object[] cityarray = listCity.toArray();
                                            CityDetail city = (CityDetail) cityarray[0];
                                            out.println(city.getCityName());
                                        %>

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
                                            <%=location.getLocationDescription()%>
                                        </div>
                                    </td>
                                </tr>


                            </tbody>
                        </table>
                                        <div id="location_vote">
                                            
                                            
                                              <div id="location_vote_content">
                                                    Vote this location:
                                                    </div>
                                                    <a href="/CeylonJourney/locationVoteController.action?actionType=addVote&locationId=<%=locationId%>">
                                                        <img src="images/like.png" alt="Total Likes"/>
                                                    </a>
                           
                                        </div>
                            
                            
                            
                                        <div id="location_comment">
                                            <div id="location_comment_title">Comments</div>
                        <%
                             List<LocationComment> commentList = (List<LocationComment>) request.getAttribute(BackendConstants.APPROVED_LOCATION_COMMENTS);
                             
                             for(LocationComment locationComment : commentList){
                        %>
                                          
                        <div id="view_location_comment">
                            <%out.println(locationComment.getComment());%>
                        </div>
                        <%
                                               }
                        %>
                        <form action="/CeylonJourney/locationCommentController.action?actionType=addComment" method="post">                
                       
                        <div id="view_location_comment">
                            <div id="location_comment_title">Add comment...</div>
                             <textarea name="locationComment" rows="8" cols="99">
                            </textarea>
                            <input type="hidden" name="locationId" value="<%=locationId%>"/>
                            <input type="submit" name="submit" value="Add"/>
                        </div>
                        </form>
                    </div>
                            
                            
                     
                            
                            
                            
                    </div>

                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
