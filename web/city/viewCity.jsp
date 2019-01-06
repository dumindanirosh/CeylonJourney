<%-- 
    Document   : viewCity
    Created on : Feb 18, 2013, 7:08:06 PM
    Author     : Gobinath
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View City</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
         <script type="text/javascript">
            
            function loadSelectedCityData(myform){
               
                myform.action = "loadSelectedCity.action";
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
                        View City
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                    %>

                    <%
                        List<CityDetail> cityList = (List<CityDetail>) (request.getAttribute(BackendConstants.CITY_LIST));
                        if (cityList != null) {
                    %>

                    <form id="loadCityDataForm" name="loadCityDataForm" action="loadSelectedCity.action">
                        Select City :   <select id="cityID"  name="cityID" readonly onchange="javascript:loadSelectedCityData(this.form)">
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
                        <input type="hidden" name="actionType" value="<%=BackendConstants.VIEW%>"/>
                    </form>

                    <%
                        }
                    %>
                    <%
                        CityDetail selectedCity = (CityDetail) (request.getAttribute(BackendConstants.SELECTED_CITY));

                        if (selectedCity != null) {
                            String cityName = selectedCity.getCityName();
                            String cityId = selectedCity.getCityId().toString();
                            String districtName = selectedCity.getDistrictDetail().getDistrictName();
                            Byte cityStatus = selectedCity.getCityStatus();

                    %>
                    <form action="/CeylonJourney/city/cityManagement.jsp">
                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        City Name :
                                    </td>
                                    <td>
                                        <input type="text" name="cityName" readonly value="<%=cityName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>District Name : </td>
                                    <td>
                          
                                    <input type="text" name="districtName" readonly value="<%=districtName%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>City Status : </td>
                                    <td>

                                        <%
                                            if (cityStatus == 1) {
                                        %>
                                        <input type="checkbox" name="cityStatus" checked readonly="true" disabled=""/>
                                        <%                                } else {
                                        %>
                                        <input type="checkbox" name="cityStatus" readonly="true" disabled=""/>
                                        <%                                    }
                                        %>



                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="submit" value="close" />
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
