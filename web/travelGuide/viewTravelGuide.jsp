<%-- 
    Document   : viewTravelGuide
    Created on : Feb 18, 2013, 6:54:08 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.duminda.ceylonjourney.model.TravelGuide"%>
<%@page import="java.util.List"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Travel Guide</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
            
            function loadSelectedGuideData(myform){
               
                myform.action = "loadSelectedTravelGuide.action";
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
                        View Travel Guide
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                    %>

                    <%
                        List<TravelGuide> guideList = (List<TravelGuide>) (request.getAttribute(BackendConstants.GUIDE_LIST));
                        if (guideList != null) {
                    %>
                    <form id="loadGuideDataForm" name="loadGuideDataForm" action="loadSelectedTravelGuide.action">
                        Select Travel Guide :   <select id="travelGuideID"  name="travelGuideID" onchange="javascript:loadSelectedGuideData(this.form)">
                            <option>Select Travel Guide</option>
                            <%   Iterator<TravelGuide> it = guideList.iterator();
                                TravelGuide guide = null;
                                while (it.hasNext()) {
                                    guide = it.next();
                            %>

                            <option value="<%=guide.getTravelGuideId()%>">
                                <%="ID: " + guide.getTravelGuideId() + ", " + guide.getFirstName() + " " + guide.getLastName()%>
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
                        TravelGuide selectedGuide = (TravelGuide) (request.getAttribute(BackendConstants.SELECTED_GUIDE));

                        if (selectedGuide != null) {

                            String firstName = selectedGuide.getFirstName();
                            String lastName = selectedGuide.getLastName();
                            String emailAddress = selectedGuide.getEmailAddress();
                            String officeTelephone = selectedGuide.getTelephoneNumber();
                            String mobileNumber = selectedGuide.getMobileNumber();
                            String travelGuideId = selectedGuide.getTravelGuideId().toString();
                    %>
                    <form action="travelGuide/travelGuideManagement.jsp">
                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>
                                        First Name :
                                    </td>
                                    <td>
                                        <input type="text" readonly name="firstName" value="<%=firstName%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Last Name : </td>
                                    <td>
                                        <input type="text" readonly name="lastName" value="<%=lastName%>" />

                                    </td>
                                </tr>
                                <tr>
                                    <td>Email Address : </td>
                                    <td>
                                        <input type="text" readonly name="emailAddress" value="<%=emailAddress%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Office Telephone : </td>
                                    <td>
                                        <input type="text" readonly name="officeTelephone" value="<%=officeTelephone%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Mobile Telephone : </td>
                                    <td>
                                        <input type="text" readonly name="mobileNumber" value="<%=mobileNumber%>" />
                                    </td>
                                </tr>


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

