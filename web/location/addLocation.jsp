<%-- 
    Document   : addLocation
    Created on : Dec 4, 2012, 2:46:51 AM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Location</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function addNewCityData(myform){
                myform.action = "/CeylonJourney/callAddCityFromLocation.action?actionType=addLocation";
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
                        Add New Location
                    </div>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null && !user.getUserType().getTypeName().equals(BackendConstants.MEMBER)) {
                    %>

                    <form action="/CeylonJourney/locationController.action?actionType=addLocation" method="post">
                        <table border="1"  >
                            <tbody>
                                <tr>
                                    <td>City Name : *</td>
                                    <td>
                                        <%
                                            List<CityDetail> cityList = (List<CityDetail>) (request.getAttribute(BackendConstants.CITY_LIST));
                                            if (cityList != null) {
                                        %>

                                        <select id="cityId"  name="cityId">
                                            <option>Select City</option>
                                            <%   Iterator<CityDetail> it = cityList.iterator();
                                                CityDetail city = null;
                                                while (it.hasNext()) {
                                                    city = it.next();

                                            %>

                                            <option value="<%=city.getCityId().toString()%>">
                                                <%=city.getCityName() + " in " + city.getDistrictDetail().getDistrictName()%>
                                            </option>

                                            <%
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
                                    <td>Location Name : *</td>
                                    <td>
                                        <input type="text" name="locationName" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Description : </td>
                                    <td>
                                        <textarea name="locationDescription" rows="8" cols="50">
                                        </textarea>

                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Address : </td>
                                    <td>
                                        <textarea name="locationAddress" rows="4" cols="50">
                                        </textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Location Status : </td>
                                    <td>
                                        <%
                                            if (user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {
                                        %>

                                        <input checked type="checkbox" name="locationStatus" value="ON" />                      

                                        <%} else {%>
                                        <input disabled type="checkbox" name="locationStatus" value="ON" />                      

                                        <%}%>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="Save Location" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>


                    </form>


                    <%
                        } else {
                            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../illegalAccess.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    %>

                    </body>
                    </html>
