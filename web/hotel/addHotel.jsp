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
        <title>Add New Hotel</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
       <script type="text/javascript">
            
            function addNewCityData(myform){
               
                myform.action = "/CeylonJourney/callAddCityFromLocation.action?actionType=addHotel";
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
                   Add New Hotel
                    </div>
        
        <%
        
        User user = (User)session.getAttribute(BackendConstants.USER);   
        
        if(user != null){
        %>
        
        <form action="/CeylonJourney/hotelController.action?actionType=addHotel" method="post">
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
                        <td>Hotel Name : *</td>
                        <td>
                            <input type="text" name="hotelName" size="50"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Hotel Description : </td>
                        <td>
                            <textarea name="hotelDescription" rows="8" cols="50">
                            </textarea>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>Hotel Address : </td>
                        <td>
                           <textarea name="hotelAddress" rows="4" cols="50">
                            </textarea>
                           </td>
                    </tr>
                 
                       <tr>
                        <td>Hotel Email Address : </td>
                        <td>
                            <input type="text" name="hotelEmailAddress" />
                                                        
                        </td>
                    </tr>
                    
                    
                     <tr>
                        <td>Hotel Status : </td>
                        <td>
                            <input type="checkbox" checked name="hotelStatus"/>
                                                        
                        </td>
                    </tr>
                    
                              <tr>
                        <td>Hotel Type : </td>
                        <td>
                       <select id="hotelType"  name="hotelType">
                            <option>Select Type</option>
                            <option value="<%=BackendConstants.ONE_STAR%>"/>
                                <%=BackendConstants.ONE_STAR%>
                            </option>
                            <option value="<%=BackendConstants.TWO_STAR%>"/>
                                <%=BackendConstants.TWO_STAR%>
                            </option>
                            <option value="<%=BackendConstants.THREE_STAR%>"/>
                                <%=BackendConstants.THREE_STAR%>
                            </option>
                            <option value="<%=BackendConstants.FOUR_STAR%>"/>
                                <%=BackendConstants.FOUR_STAR%>
                            </option>
                            <option value="<%=BackendConstants.FIVE_STAR%>"/>
                                <%=BackendConstants.FIVE_STAR%>
                            </option>
                        </select>
                        </td>
                    </tr>
                    
                    
                    <tr>
                        <td>Hotel Type Status : </td>
                        <td>
                            <input type="checkbox" checked name="hotelTypeStatus"/>
                                                        
                        </td>
                    </tr>
     
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" value="Save Hotel" />
                        </td>
                    </tr>
                </tbody>
            </table>

            
        </form>
        
        
        <%
               }else {
                            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("../illegalAccess.jsp");
                            requestDispatcher.forward(request, response);
                        }
        %>
        
    </body>
</html>
