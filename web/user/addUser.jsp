<%-- 
    Document   : addUser
    Created on : Feb 6, 2013, 7:58:22 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.Hotel"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page import="com.duminda.ceylonjourney.util.FrontMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function checkMemberForHotelOfficer(myform){
               
                myform.action = "/CeylonJourney/addHotelForOfficer.action?actionType=addHotel";
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
                        Add User
                    </div>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {

                            String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);

                            if (errorMessage != null) {

                    %>
                    <div id="messages_box">
                        <% out.println(errorMessage);%>
                    </div>
                    <% }

                        String userType = request.getParameter("userType");
                        String username = request.getParameter("username");
                        String firstName = request.getParameter("firstName");
                        String lastName = request.getParameter("lastName");
                        String emailAddress = request.getParameter("emailAddress");
                        String officeTelephone = request.getParameter("officeTelephone");
                        String mobileNumber = request.getParameter("mobileNumber");
                        String password = request.getParameter("password");
                        String confirmPassword = request.getParameter("confirmPassword");
                        if (userType == null) {
                            userType = "";
                        }
                        if (username == null) {
                            username = "";
                        }
                        if (firstName == null) {
                            firstName = "";
                        }
                        if (lastName == null) {
                            lastName = "";
                        }
                        if (emailAddress == null) {
                            emailAddress = "";
                        }
                        if (officeTelephone == null) {
                            officeTelephone = "";
                        }
                        if (mobileNumber == null) {
                            mobileNumber = "";
                        }
                        if (password == null) {
                            password = "";
                        }
                        if (confirmPassword == null) {
                            confirmPassword = "";
                        }


                        List<Hotel> listHotel = (List<Hotel>) request.getAttribute(BackendConstants.ACTIVE_HOTELS);

    %>


                    <form method="post" action="/CeylonJourney/userManagement.action?actionType=addUser">

                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>User Name  : *</td>
                                    <td>
                                        <input type="text" name="username" size="35" value="<%=username%>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Password : *</td>
                                    <td>
                                        <input type="password" name="password" size="35" value="<%=password%>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Confirm Password : *</td>
                                    <td>
                                        <input type="password" name="confirmPassword" size="35" value="<%=confirmPassword%>"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>First Name : * </td>
                                    <td>
                                        <input type="text" name="firstName" size="35" value="<%=firstName%>"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>Last Name : * </td>
                                    <td>
                                        <input type="text" name="lastName" size="35" value="<%=lastName%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Email Address : * </td>
                                    <td>
                                        <input type="text" name="emailAddress" size="35" value="<%=emailAddress%>"/>
                                    </td>
                                </tr>




                                <% if (listHotel != null) {
                                %>
                                <tr>
                                    <td>User Type : * </td>
                                    <td>
                                        <select id="userType"  name="userType" readonly onchange="javascript:checkMemberForHotelOfficer(this.form)">
                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option selected value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>
                                        </select>

                                    </td>
                                </tr>

                                <tr>
                                    <td>Hotel Name : * </td>
                                    <td>
                                        <select id="hotelId"  name="hotelId" readonly>


                                            <%
                                            
                                            if(listHotel.size()<=0){
                                                response.sendRedirect("loadCityForLocation.action?actionType=addHotel");
                                            }
                                                                                      
                                                Iterator<Hotel> iterator = listHotel.iterator();
                                                while (iterator.hasNext()) {
                                                    Hotel hotel = iterator.next();
                                                    String hotelName = hotel.getHotelName();
                                                    Integer hotelId = hotel.getHotelId();

                                                    out.println("<option value=" + hotelId + "> " + hotelName + " </option>");
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <%
                                } else {
                                %>

                                <tr>
                                    <td>User Type : * </td>
                                    <td>
                                        <select id="userType"  name="userType" readonly onchange="javascript:checkMemberForHotelOfficer(this.form)">

                                            <%if (userType.equals(BackendConstants.MEMBER)) {%>

                                            <option selected value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>

                                            <%} else if (userType.equals(BackendConstants.INTERNAL_OFFICER)) {%>

                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option selected value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>

                                            <%} else if (userType.equals(BackendConstants.ADMIN)) {%>

                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option selected value="<%=BackendConstants.ADMIN%>"> Admin </option>


                                            <%} else {%>
                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>

                                            <%}%>
                                        </select>


                                    </td>
                                </tr>
                                <%}%>


                                <tr>
                                    <td>Telephone[O] :  </td>
                                    <td>
                                        <input type="text" name="officeTelephone" size="35" value="<%=officeTelephone%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Mobile Number :  </td>
                                    <td>
                                        <input type="text" name="mobileNumber" size="35" value="<%=mobileNumber%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td> </td>
                                    <td>
                                        <input type="submit" value="Add User" value="<%=username%>"/>
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
                </div>
            </div>

        </div>


    </body>
</html>
