<%-- 
    Document   : addCity
    Created on : Feb 14, 2013, 1:17:45 PM
    Author     : Gobinath
--%>

<%@page import="com.duminda.ceylonjourney.util.Log4jUtil"%>
<%@page import="com.duminda.ceylonjourney.model.User"%>
<%@page import="com.duminda.ceylonjourney.util.BackendConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add City</title>
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
                        Add City
                    </div>
                    <br/>

                    <%

                        User user = (User) session.getAttribute(BackendConstants.USER);

                        if (user != null) {
                           String returnPlace = (String)request.getAttribute("returnPlace");
                    %>
                    <form method="post" action="/CeylonJourney/cityController.action?actionType=addCity&returnPlace=<%=returnPlace%>">
                        <table border="1">
                            <tbody>

                                <tr>
                                    <td>City Name : * </td>
                                    <td>
                                        <input type="text" name="cityName" size="35"/>

                                    </td>
                                </tr>
                                <tr>
                                    <td>District Name : * </td>
                                    <td>
                                      
                                        <select name="districtName">
                                            <option value="<%=BackendConstants.AMPARA%>"> <%=BackendConstants.AMPARA%> </option>
                                            <option value="<%=BackendConstants.ANURADHAPURA%>"> <%=BackendConstants.ANURADHAPURA%> </option>
                                            <option value="<%=BackendConstants.BADULLA%>"> <%=BackendConstants.BADULLA%> </option>
                                            <option value="<%=BackendConstants.BATTICALOA%>"> <%=BackendConstants.BATTICALOA%> </option>
                                            <option value="<%=BackendConstants.COLOMBO%>"> <%=BackendConstants.COLOMBO%> </option>
                                            <option value="<%=BackendConstants.GALLE%>"> <%=BackendConstants.GALLE%> </option>
                                            <option value="<%=BackendConstants.GAMPAHA%>"> <%=BackendConstants.GAMPAHA%> </option>
                                            <option value="<%=BackendConstants.HAMBANTOTA%>"> <%=BackendConstants.HAMBANTOTA%> </option>
                                            <option value="<%=BackendConstants.JAFFNA%>"> <%=BackendConstants.JAFFNA%> </option>
                                            <option value="<%=BackendConstants.KALUTARA%>"> <%=BackendConstants.KALUTARA%> </option>
                                            <option value="<%=BackendConstants.KANDY%>"> <%=BackendConstants.KANDY%> </option>
                                            <option value="<%=BackendConstants.KEGALLE%>"> <%=BackendConstants.KEGALLE%> </option>
                                            <option value="<%=BackendConstants.KILINOCHCHI%>"> <%=BackendConstants.KILINOCHCHI%> </option>
                                            <option value="<%=BackendConstants.KURUNEGALA%>"> <%=BackendConstants.KURUNEGALA%> </option>
                                            <option value="<%=BackendConstants.MANNAR%>"> <%=BackendConstants.MANNAR%> </option>
                                            <option value="<%=BackendConstants.MATALE%>"> <%=BackendConstants.MATALE%> </option>
                                            <option value="<%=BackendConstants.MATARA%>"> <%=BackendConstants.MATARA%> </option>
                                            <option value="<%=BackendConstants.MONERAGALA%>"> <%=BackendConstants.MONERAGALA%> </option>
                                            <option value="<%=BackendConstants.MULLAITIVU%>"> <%=BackendConstants.MULLAITIVU%> </option>
                                            <option value="<%=BackendConstants.NUWARA_ELIYA%>"> <%=BackendConstants.NUWARA_ELIYA%> </option>
                                            <option value="<%=BackendConstants.POLONNARUWA%>"> <%=BackendConstants.POLONNARUWA%> </option>
                                            <option value="<%=BackendConstants.PUTTALAM%>"> <%=BackendConstants.PUTTALAM%> </option>
                                            <option value="<%=BackendConstants.RATNAPURA%>"> <%=BackendConstants.RATNAPURA%> </option>
                                            <option value="<%=BackendConstants.TRINCOMALEE%>"> <%=BackendConstants.TRINCOMALEE%> </option>
                                            <option value="<%=BackendConstants.VAVUNIYA%>"> <%=BackendConstants.VAVUNIYA%> </option>
                                            
                                        </select>
                                  
                                    </td>
                                </tr>

                                <tr>
                                    <td>City Status : </td>
                                    <td>
                                         <input type="checkbox" name="cityStatus" value="ON" />
                                    </td>
                                </tr>

                               

                                <tr>
                                    <td> </td>
                                    <td>
                                        <input type="submit" value="Save City"/>
                                    </td>
                                </tr>

                            </tbody>

                        </table>
                    </form>
                    <%         }
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
