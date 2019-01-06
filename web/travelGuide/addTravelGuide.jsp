<%-- 
    Document   : addTravelGuide
    Created on : Feb 13, 2013, 3:25:32 PM
    Author     : Duminda
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Travel Guide</title>
        <link href="./styles.css" rel="stylesheet" type="text/css"/>
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
                        Add Travel Guide
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {

                    %>
                    <form method="post" action="/CeylonJourney/travelGuideManagement.action?actionType=addGuide">
                        <table border="1">
                            <tbody>

                                <tr>
                                    <td>First Name : * </td>
                                    <td>
                                        <input type="text" name="firstName" size="35"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>Last Name : * </td>
                                    <td>
                                        <input type="text" name="lastName" size="35"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Email Address : * </td>
                                    <td>
                                        <input type="text" name="emailAddress" size="35"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Telephone[O] :  </td>
                                    <td>
                                        <input type="text" name="officeTelephone" size="35"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Mobile Number :  </td>
                                    <td>
                                        <input type="text" name="mobileNumber" size="35"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td> </td>
                                    <td>
                                        <input type="submit" value="Add Guide"/>
                                    </td>
                                </tr>

                            </tbody>

                        </table>
                    </form>
                    <%         }else {
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
