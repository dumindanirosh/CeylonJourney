<%-- 
    Document   : updateUser
    Created on : Feb 11, 2013, 2:37:25 AM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.Hotel"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.SWAP"%>
<%@page import="com.duminda.ceylonjourney.model.Admin"%>
<%@page import="com.duminda.ceylonjourney.model.InternalOfficer"%>
<%@page import="com.duminda.ceylonjourney.model.HotelOfficer"%>
<%@page import="com.duminda.ceylonjourney.model.Member"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update User</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function checkMemberForHotelOfficer(myform){
                myform.action = "/CeylonJourney/addHotelForOfficer.action?actionType=addHotelForUpdate";
                myform.submit();
            }
            function loadSelectedUserData(myform){
                myform.action = "loadSelectedUserDataServlet.action";
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
                        Update User
                    </div>
                    <%
                        User userObj = (User) session.getAttribute(BackendConstants.USER);
                        if (userObj != null) {
                            String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                            if (errorMessage != null) {
                    %>
                    <div id="messages_box">
                        <% out.println(errorMessage);%>
                    </div>
                    <% }
                        List<User> userList = (List<User>) (request.getAttribute(BackendConstants.USER_LIST));
                        if (userList != null) {
                    %>
                    <form id="loadUserDataForm" name="loadUserDataForm" action="loadSelectedUserDataServlet.action">
                        Select User Name :   <select id="username"  name="username" onchange="javascript:loadSelectedUserData(this.form)">
                            <option>Select User Name</option>
                            <%   Iterator<User> it = userList.iterator();
                                User user = null;
                                while (it.hasNext()) {
                                    user = it.next();
                            %>
                            <option value="<%=user.getUsername()%>">
                                <%=user.getUsername()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                        <input type="hidden" name="actionType" value="<%=BackendConstants.UPDATE%>"/>
                    </form>
                    <%
                    } else {
                    %>

                    <%
                        User selectedUser = (User) (request.getAttribute(BackendConstants.SELECTED_USER));
                        List<Hotel> listHotel = (List<Hotel>) request.getAttribute(BackendConstants.ACTIVE_HOTELS);
                        if (selectedUser != null) {
                            String originalUsername = selectedUser.getUsername();
                            String firstName = "";
                            String lastName = "";
                            String emailAddress = "";
                            String officeTelephone = "";
                            String mobileNumber = "";
                            Integer hotelId = 0;
                            String password = selectedUser.getPassword();
                            String userTypeId = selectedUser.getUserType().getUserTypeId().toString();
                            if (userTypeId.equals(BackendConstants.MEMBER_ID)) {
                                Object[] usersList = (Object[]) selectedUser.getMembers().toArray();
                                Member member = (Member) usersList[0];
                                firstName = member.getFirstName();
                                lastName = member.getLastName();
                                emailAddress = member.getEmailAddress();
                                officeTelephone = member.getOfficeTelephone();
                                mobileNumber = member.getMobileNuermb();
                            } else if (userTypeId.equals(BackendConstants.HOTEL_OFFICER_ID)) {
                                Object[] usersList = (Object[]) selectedUser.getHotelOfficers().toArray();
                                HotelOfficer hotelOfficer = (HotelOfficer) usersList[0];
                                firstName = hotelOfficer.getFirstName();
                                lastName = hotelOfficer.getLastName();
                                emailAddress = hotelOfficer.getEmailAddress();
                                officeTelephone = hotelOfficer.getOfficeTelephone();
                                mobileNumber = hotelOfficer.getMobileNumber();
                                hotelId = hotelOfficer.getHotel().getHotelId();
                            } else if (userTypeId.equals(BackendConstants.INTERNAL_OFFICER_ID)) {
                                Object[] usersList = (Object[]) selectedUser.getInternalOfficers().toArray();
                                InternalOfficer internalOfficer = (InternalOfficer) usersList[0];
                                firstName = internalOfficer.getFirstName();
                                lastName = internalOfficer.getLastName();
                                emailAddress = internalOfficer.getEmailAddress();
                                officeTelephone = internalOfficer.getOfficeTelephone();
                                mobileNumber = internalOfficer.getMobileNumber();
                            } else if (userTypeId.equals(BackendConstants.ADMIN_ID)) {
                                Object[] usersList = (Object[]) selectedUser.getAdmins().toArray();
                                Admin admin = (Admin) usersList[0];
                                firstName = admin.getFirstName();
                                lastName = admin.getLastName();
                                emailAddress = admin.getEmaillAddress();
                                officeTelephone = admin.getOfficeTelephone();
                                mobileNumber = admin.getMobileNumber();
                            }

                    %>
                    <form action="/CeylonJourney/userManagement.action?actionType=updateUser" method="post">

                        <table border="1">
                            <tbody>
                                <tr>
                                    <td>
                                        User Name :
                                    </td>
                                    <td>
                                        <input type="text" name="username" value="<%=selectedUser.getUsername()%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        First Name :
                                    </td>
                                    <td>
                                        <input type="text" name="firstName" value="<%=firstName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Last Name : </td>
                                    <td>
                                        <input type="text" name="lastName" value="<%=lastName%>" />

                                    </td>
                                </tr>

                                <tr>
                                    <td>User Type : </td>
                                    <td>
                                        <%
                                            if (userObj.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {
                                        %>
                                        <select id="userType"  name="userType" readonly onchange="javascript:checkMemberForHotelOfficer(this.form)">
                                            <% if (userTypeId.equals(BackendConstants.MEMBER_ID)) {%>
                                            <option selected value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option  value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option  value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>
                                            <% } else if (userTypeId.equals(BackendConstants.HOTEL_OFFICER_ID)) {%>
                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option  value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option selected value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>
                                            <%   } else if (userTypeId.equals(BackendConstants.INTERNAL_OFFICER_ID)) {%>
                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option selected value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option  value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option value="<%=BackendConstants.ADMIN%>"> Admin </option>
                                            <%   } else if (userTypeId.equals(BackendConstants.ADMIN_ID)) {%>
                                            <option value="<%=BackendConstants.MEMBER%>"> Member </option>
                                            <option  value="<%=BackendConstants.INTERNAL_OFFICER%>"> Internal Officer </option>
                                            <option  value="<%=BackendConstants.HOTEL_OFFICER%>"> Hotel Officer </option>
                                            <option selected value="<%=BackendConstants.ADMIN%>"> Admin </option>
                                            <%  }%>
                                        </select>
                                        <%} else {%>

                                        <% if (userTypeId.equals(BackendConstants.MEMBER_ID)) {%>
                                        <input type="hidden" name="userType" value="member"/>
                                        <input type="text" value="Member" readonly/>

                                        <% } else if (userTypeId.equals(BackendConstants.HOTEL_OFFICER_ID)) {%>
                                        <input type="hidden" name="userType" value="hotel_officer"/>
                                        <input type="text" value="Hotel Officer" readonly/>

                                        <%   } else if (userTypeId.equals(BackendConstants.INTERNAL_OFFICER_ID)) {%>
                                        <input type="hidden" name="userType" value="internal_officer"/>
                                        <input type="text" value="Internal Officer" readonly/>

                                        <%   } else if (userTypeId.equals(BackendConstants.ADMIN_ID)) {%>
                                        <input type="hidden" name="userType" value="admin"/>
                                        <input type="text"  value="Admin" readonly/>

                                        <%  }%>

                                        <%}%>


                                    </td>
                                </tr>
                                <% if (userTypeId.equals(BackendConstants.HOTEL_OFFICER_ID)) {
                                %>
                                <tr>
                                    <td>Hotel Name : * </td>
                                    <td>
                                        <select id="hotelId"  name="hotelId" readonly>
                                            <%
                                                Iterator<Hotel> iterator = listHotel.iterator();
                                                while (iterator.hasNext()) {
                                                    Hotel hotel = iterator.next();
                                                    String hotelName = hotel.getHotelName();
                                                    Integer hotelIdOfList = hotel.getHotelId();

                                                    if (hotelId == hotelIdOfList) {
                                                        out.println("<option selected value=" + hotelIdOfList + "> " + hotelName + " </option>");
                                                    } else {
                                                        out.println("<option value=" + hotelIdOfList + "> " + hotelName + " </option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>

                                <tr>
                                    <td>Email Address : </td>
                                    <td>
                                        <input type="text" name="emailAddress" value="<%=emailAddress%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Office Telephone : </td>
                                    <td>
                                        <input type="text" name="officeTelephone" value="<%=officeTelephone%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Mobile Telephone : </td>
                                    <td>
                                        <input type="text" name="mobileNumber" value="<%=mobileNumber%>" />
                                    </td>
                                </tr>


                                <tr>
                                    <td></td>
                                    <td>
                                        <input type="hidden" name="selectedUser" value="<%=selectedUser%>"/>
                                        <input type="hidden" name="originalUsername" value="<%=originalUsername%>"/>
                                        <input type="hidden" name="password" value="<%=password%>"/>
                                        <input type="hidden" name="actionType" value="<%=BackendConstants.UPDATE%>"/>
                                        <input type="submit" value="Update User" />
                                        <input type="button" value="Reset Data" onclick="javascript:loadSelectedUserData(this.form)"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <% } else {
                        String originalUsername = request.getParameter("originalUsername");

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
                        if (originalUsername == null) {
                            originalUsername = "";
                        }

                    %>
                    <form action="/CeylonJourney/userManagement.action?actionType=updateUser" method="post">
                        <table border="1">
                            <tbody>
                                <tr>
                                    <td>User Name  : *</td>
                                    <td>
                                        <input type="text" name="username" size="35" value="<%=username%>"/>
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

                                                if (listHotel.size() <= 0) {
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
                                    <td>Email Address : * </td>
                                    <td>
                                        <input type="text" name="emailAddress" size="35" value="<%=emailAddress%>"/>
                                    </td>
                                </tr>
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
                                        <input type="hidden" name="originalUsername" value="<%=originalUsername%>"/>
                                        <input type="hidden" name="username" value="<%=username%>"/>
                                        <input type="hidden" name="password" value="<%=password%>"/>
                                        <input type="hidden" name="actionType" value="<%=BackendConstants.UPDATE%>"/>
                                        <input type="submit" value="Update User" />
                                        <input type="button" value="Reset Data" onclick="javascript:loadSelectedUserData(this.form)"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <%
                                }
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
