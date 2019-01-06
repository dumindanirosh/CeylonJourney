<%-- 
    Document   : viewUser
    Created on : Feb 18, 2013, 4:37:06 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
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
        <title>View User</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
            
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
                       View User
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

                    %>

                    <%
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
                            <input type="hidden" name="actionType" value="<%=BackendConstants.VIEW%>"/>
                    </form>
                    <%


                        }
                           
                    %>


                    <%
                        User selectedUser = (User) (request.getAttribute(BackendConstants.SELECTED_USER));

                        if (selectedUser != null) {

                            String firstName = "";
                            String lastName = "";
                            String emailAddress = "";
                            String officeTelephone = "";
                            String mobileNumber = "";
                            String hotelName = "";
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
                                hotelName = hotelOfficer.getHotel().getHotelName();


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
                    <form action="/CeylonJourney/user/userManagement.jsp">
                        <table border="1">

                            <tbody>
                                <tr>
                                    <td>
                                        User Name :
                                    </td>
                                    <td>
                                        <input type="text" name="username" readonly value="<%=selectedUser.getUsername()%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        First Name :
                                    </td>
                                    <td>
                                        <input type="text" name="firstName" readonly value="<%=firstName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Last Name : </td>
                                    <td>
                                        <input type="text" name="lastName" readonly value="<%=lastName%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Email Address : </td>
                                    <td>
                                        <input type="text" name="emailAddress" readonly value="<%=emailAddress%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Office Telephone : </td>
                                    <td>
                                        <input type="text" name="emailAddress" readonly value="<%=officeTelephone%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Mobile Telephone : </td>
                                    <td>
                                        <input type="text" name="emailAddress" readonly value="<%=mobileNumber%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>User Type : </td>
                                    <td>
                                        <select name="userType" disabled  >
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
                                    </td>
                                </tr>
                                
                                <%
                                if(userTypeId.equals(BackendConstants.HOTEL_OFFICER_ID)){
                                    %>
                                    
                                    <tr>
                                    <td>Hotel Name : </td>
                                    <td>
                                        <input type="text" name="hotelName" readonly value="<%=hotelName%>" />
                                    </td>
                                </tr>
                                    
                                <%}
                            %>
                                <tr>
                                    <td></td>
                                    <td>
                                        
                                      
                                        <input type="submit" value="Close" />
                                      
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
 <%
                                                                       
                                }
                                %>
                    <% }else {
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
