<%-- 
    Document   : updateCity
    Created on : Feb 14, 2013, 1:18:40 PM
    Author     : Gobinath
--%>


<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.dao.DistrictDetailDAOImpl"%>
<%@page import="com.duminda.ceylonjourney.dao.DistrictDetailDAO"%>
<%@page import="com.duminda.ceylonjourney.model.DistrictDetail"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.TravelGuide"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Travel Guide</title>
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
                        Update City
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
                        Select City :   <select id="cityID"  name="cityID" onchange="javascript:loadSelectedCityData(this.form)">
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
                        <input type="hidden" name="actionType" value="<%=BackendConstants.UPDATE%>"/>
                    </form>

                    <%
                        }
                    %>
                    <%
                        CityDetail selectedCity = (CityDetail) (request.getAttribute(BackendConstants.SELECTED_CITY));

                        if (selectedCity != null) {
                            String cityName = selectedCity.getCityName();
                            String cityId = selectedCity.getCityId().toString();
                            Integer districtId = selectedCity.getDistrictDetail().getDistrictId();
                            Byte cityStatus = selectedCity.getCityStatus();

                    %>
                    <form action="/CeylonJourney/cityController.action?actionType=updateCity" method="post">
                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        City Name :
                                    </td>
                                    <td>
                                        <input type="text" name="cityName" value="<%=cityName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>District Name : </td>
                                    <td>
                                        
                                        <select name="districtName">
                                            <% for (int i = 1; i <= 25; i++) {

                                                    String district = "";
                                                    switch (i) {
                                                        case 1:
                                                            district = BackendConstants.AMPARA;
                                                            break;
                                                        case 2:
                                                            district = BackendConstants.ANURADHAPURA;
                                                            break;
                                                        case 3:
                                                            district = BackendConstants.BADULLA;
                                                            break;
                                                        case 4:
                                                            district = BackendConstants.BATTICALOA;
                                                            break;
                                                        case 5:
                                                            district = BackendConstants.COLOMBO;
                                                            break;
                                                        case 6:
                                                            district = BackendConstants.GALLE;
                                                            break;
                                                        case 7:
                                                            district = BackendConstants.GAMPAHA;
                                                            break;
                                                        case 8:
                                                            district = BackendConstants.HAMBANTOTA;
                                                            break;
                                                        case 9:
                                                            district = BackendConstants.JAFFNA;
                                                            break;
                                                        case 10:
                                                            district = BackendConstants.KALUTARA;
                                                            break;
                                                        case 11:
                                                            district = BackendConstants.KANDY;
                                                            break;
                                                        case 12:
                                                            district = BackendConstants.KEGALLE;
                                                            break;
                                                        case 13:
                                                            district = BackendConstants.KILINOCHCHI;
                                                            break;
                                                        case 14:
                                                            district = BackendConstants.KURUNEGALA;
                                                            break;
                                                        case 15:
                                                            district = BackendConstants.MANNAR;
                                                            break;
                                                        case 16:
                                                            district = BackendConstants.MATALE;
                                                            break;
                                                        case 17:
                                                            district = BackendConstants.MATARA;
                                                            break;
                                                        case 18:
                                                            district = BackendConstants.MONERAGALA;
                                                            break;                                                                                                                                                                                                                                                                                                                                                                                                                                 
                                                        case 19:
                                                            district = BackendConstants.MULLAITIVU;
                                                            break;
                                                        case 20:
                                                            district = BackendConstants.NUWARA_ELIYA;
                                                            break;
                                                        case 21:
                                                            district = BackendConstants.POLONNARUWA;
                                                            break;
                                                        case 22:
                                                            district = BackendConstants.PUTTALAM;
                                                            break;
                                                        case 23:
                                                            district = BackendConstants.RATNAPURA;
                                                            break;
                                                        case 24:
                                                            district = BackendConstants.TRINCOMALEE;
                                                            break;
                                                        case 25:
                                                            district = BackendConstants.VAVUNIYA;
                                                            break;  
                                                    }
                                                    if(districtId==i){
                                                        out.println("<option selected value=" + district + "> " + district + " </option>");
                                                    }
                                                    else{
                                                        out.println("<option value=" + district + "> " + district + " </option>");
                                                    }
                                                 }%>



                                        </select>


                                    </td>
                                </tr>
                                <tr>
                                    <td>City Status : </td>
                                    <td>

                                        <%
                                            if (cityStatus == 1) {
                                        %>
                                        <input type="checkbox" name="cityStatus" value="ON"  checked=""/>
                                        <%                                } else {
                                        %>
                                        <input type="checkbox" name="cityStatus" value="ON"  />
                                        <%                                    }
                                        %>



                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="hidden" name="cityId" value="<%=cityId%>"/>
                                        <input type="submit" value="Update City" />
                                        <input type="reset" value="Reset Data" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>



                    <%
                            }

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
