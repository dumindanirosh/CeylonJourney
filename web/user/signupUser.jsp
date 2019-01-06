<%-- 
    Document   : signupUser
    Created on : Feb 19, 2013, 12:51:54 PM
    Author     : Duminda
--%>

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
        <title>Sign Up</title>
        <link href="/CeylonJourney/styles.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript">
            function cancel(myform){
                myform.action = "/CeylonJourney/viewLocationForWelcome.action";
                myform.submit();
            }
        </script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="logo">
                    <img src=<%=BackendConstants.LOGO_IMAGE%> width="138" height="138"/>
                </div>
                <div id="menu_bar"></div>
                <div id="menu_bar">
                    <div id="templatemo_menu">
                        <ul>
                            <li><a href="/CeylonJourney/viewLocationForWelcome.action">Home</a></li>
                            <li><a href="/CeylonJourney/loadActiveLocations.action">View Locations</a></li>
                            <li><a href="/CeylonJourney/loadActiveHotels.action">View Hotels</a></li>
                            <li><a href="/CeylonJourney/blog.html">About us</a></li>
                            <li><a href="/CeylonJourney/contact.html">Contact Us</a></li>
                        </ul> 
                    </div>
                </div>
            </div>
            <div class="cleaner"></div>
            <div id="content">

                <div id="content_left"></div>
                <div id="content_right"></div>

                <%
                    String errorMessage = (String) request.getAttribute(BackendConstants.ERROR_MESSAGE);
                    if (errorMessage != null) {
                %>
                <div id="messages_box">
                    <% out.println(errorMessage);%>
                </div>
                <% }
                %>
                <div id="signup_box">
                    <div id="content_title">
                        Sign Up
                    </div>
                    <form method="post" action="/CeylonJourney/signup.action?actionType=signupUser">

                        <table border="1">

                            <tbody>

                                <tr>
                                    <td>User Name  : *</td>
                                    <td>
                                        <input type="text" name="username" size="35"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Password : *</td>
                                    <td>
                                        <input type="password" name="password" size="35"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Confirm Password : *</td>
                                    <td>
                                        <input type="password" name="confirmPassword" size="35"/>

                                    </td>
                                </tr>
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
                                        <input type="submit" value="Sign Up"/>
                                        <input type="button" value="Cancel" onclick="javascript:cancel(this.form)"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>

        </div>


    </body>
</html>