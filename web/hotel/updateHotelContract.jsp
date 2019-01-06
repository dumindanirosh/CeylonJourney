<%-- 
    Document   : deleteHotel
    Created on : Dec 22, 2012, 9:15:53 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.duminda.ceylonjourney.model.HotelContract"%>
<%@page import="com.duminda.ceylonjourney.model.HotelType"%>
<%@page import="com.duminda.ceylonjourney.model.HotelImageId"%>
<%@page import="com.duminda.ceylonjourney.model.HotelImage"%>
<%@page import="java.util.Set"%>
<%@page import="com.duminda.ceylonjourney.model.CityDetail"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.model.Hotel"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--@ taglib prefix="s" uri="/struts-tags" --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Hotel Contract</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function loadSelectedHotelData(myform){
               
                myform.action = "viewSelectedHotel.action";
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
                        Update Hotel Contract
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                    %>

                    <%
                        List<Hotel> hotelList = (List<Hotel>) (request.getAttribute(BackendConstants.HOTEL_LIST));
                        if (hotelList != null) {
                    %>

                    <form id="loadHotelDataForm" name="loadHotelDataForm" action="viewSelectedHotel.action">
                        Select Hotel :   <select id="hotelId"  name="hotelId" readonly onchange="javascript:loadSelectedHotelData(this.form)">
                            <option>Select Hotel</option>
                            <%   Iterator<Hotel> it = hotelList.iterator();
                                Hotel hotel = null;
                                while (it.hasNext()) {
                                    hotel = it.next();

                            %>

                            <option value="<%=hotel.getHotelId().toString()%>">
                                <%=hotel.getHotelId().toString() + "  " + hotel.getHotelName()%>
                            </option>



                            <%
                                }

                            %>

                        </select>
                        <input type="hidden" name="actionType" value="<%=BackendConstants.HOTEL_FOR_UPDATE_CONTRACT%>"/>
                    </form>

                    <%
                        }
                    %>
                    <%
                        Hotel selectedHotel = (Hotel) (request.getAttribute(BackendConstants.SELECTED_HOTEL));
                        HotelContract selectedHotelContract = (HotelContract) (request.getAttribute(BackendConstants.SELECTED_HOTEL_CONTRACT));

                        if (selectedHotel != null) {
                            String hotelName = selectedHotel.getHotelName();
                            String hotelId = selectedHotel.getHotelId().toString();
                            String hotelAddress = selectedHotel.getHotelAddress();
                            String hotelDescription = selectedHotel.getHotelDescription();
                            Byte hotelStatus = selectedHotel.getHotelStatus();
                            String hotelType = "";
                            Byte hotelTypeStatus = 0;

                            Calendar startDate = Calendar.getInstance();
                            Calendar endDate = Calendar.getInstance();
                            if (selectedHotelContract != null) {
                                startDate.setTime(selectedHotelContract.getStartDate());
                                endDate.setTime(selectedHotelContract.getEndDate());
                            }

                            Set<HotelType> hotelTypesSet = selectedHotel.getHotelTypes();
                            Iterator<HotelType> it1 = hotelTypesSet.iterator();
                            while (it1.hasNext()) {
                                HotelType hotelTypeObject = it1.next();
                                hotelType = hotelTypeObject.getTypeName();
                                hotelTypeStatus = hotelTypeObject.getHotelTypeStatus();
                            }


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
                            Iterator<CityDetail> it = locationCityList.iterator();
                            String cityName = "";
                            if (it.hasNext()) {
                                cityName = it.next().getCityName();
                            }


                    %>

                    <form action="/CeylonJourney/hotelContractController.action?actionType=updateContract" method="post">
                        <img src="<%=imageURL%>" width="190" height="200" alt="people"/>

                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        Hotel Name :
                                    </td>
                                    <td>
                                        <input type="text" name="hotelName" readonly value="<%=hotelName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Hotel Address : </td>
                                    <td>

                                        <input type="text" name="hotelAddress" readonly value="<%=hotelAddress%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Hotel Description : </td>
                                    <td>
                                        <input type="text" name="hotelDescription" readonly value="<%=hotelDescription%>" />
                                    </td>
                                </tr>

                                <tr>
                                    <td>Hotel City : </td>
                                    <td>

                                        <input type="text" name="hotelCity" readonly value="<%=cityName%>" />

                                    </td>
                                </tr>


                                <tr>
                                    <td>Hotel Status : </td>
                                    <td>

                                        <%
                                            if (hotelStatus == 1) {
                                        %>
                                        <input type="checkbox" name="hotelStatus" checked readonly="true" disabled=""/>
                                        <%                                } else {
                                        %>
                                        <input type="checkbox" name="hotelStatus" readonly="true" disabled=""/>
                                        <%                                    }
                                        %>



                                    </td>
                                </tr>

                                <tr>
                                    <td>Hotel Type : </td>
                                    <td>

                                        <input type="text" name="hotelType" readonly value="<%=hotelType%>" />

                                    </td>
                                </tr>


                                <tr>
                                    <td>Hotel Type Status : </td>
                                    <td>
                                        <%
                                            if (hotelTypeStatus == 1) {
                                        %>
                                        <input type="checkbox" name="hotelTypeStatus" checked readonly="true" disabled=""/>
                                        <%                                } else {
                                        %>
                                        <input type="checkbox" name="hotelTypeStatus" readonly="true" disabled=""/>
                                        <%                                    }
                                        %>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Contract Start Date : *</td>
                                    <td>
                                        <select name="sD">
                                            <%
                                                for (int i = 1; i <= 31; i++) {

                                                    if (i == startDate.get(Calendar.DATE)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>

                                        <select name="sM">
                                            <%
                                                for (int i = 1; i <= 12; i++) {
                                                    if (i == (startDate.get(Calendar.MONTH)+1)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>

                                        <select name="sY">
                                            <%
                                                for (int i = 2013; i <= 2099; i++) {
                                                    if (i == startDate.get(Calendar.YEAR)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>(DD/MM/YYYY)
                                    </td>
                                </tr>
                                <tr>
                                    <td>Contract End Date : *</td>
                                    <td>
                                       <select name="eD">
                                            <%
                                                for (int i = 1; i <= 31; i++) {

                                                    if (i == endDate.get(Calendar.DATE)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>

                                        <select name="eM">
                                            <%
                                                for (int i = 1; i <= 12; i++) {
                                                    if (i == (endDate.get(Calendar.MONTH)+1)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>

                                        <select name="eY">
                                            <%
                                                for (int i = 2013; i <= 2099; i++) {
                                                    if (i == endDate.get(Calendar.YEAR)) {
                                            %>
                                            <option selected value=<%=i%>><%=i%></option>
                                            <%
                                            } else {
                                            %>
                                            <option value=<%=i%>><%=i%></option>
                                            <%}
                                                }%>
                                        </select>(DD/MM/YYYY)
                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="hidden" name="hotelId" value="<%=hotelId%>"/>
                                        <input type="submit" value="Update" />
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
