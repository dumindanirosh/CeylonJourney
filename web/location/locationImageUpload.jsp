<%-- 
    Document   : locationImageUpload
    Created on : Feb 3, 2013, 10:38:35 PM
    Author     : Duminda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Images</title>
         <script type="text/javascript">
            
            function finishUpload(myform){
               
                myform.action = "/CeylonJourney/location/locationManagement.jsp";
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
                <div id="content_title">
                    Location Image Upload
                </div>
                <div id="content_left">
                    <jsp:include page="/dashboard/adminDashboardMenu.jsp"/>
                </div>
                <div id="content_right">
                    <%
                        String locationId = request.getParameter("locationId");

                        request.setAttribute("locationId", locationId);
                        String contextPath = request.getContextPath();
                        String requestURL = contextPath
                                + "/locationImageUploader.action?locationId=" + locationId;
                        String welcomeImgURL = requestURL + "&welcome=w";
                    %>

                    
                        <table border="1"  >
                            <tbody>
                                <tr>
                                    <td>Welcome Image : </td>
                                    <td>
                                        <form action="<%=welcomeImgURL%>" method="POST" enctype="multipart/form-data">

                                            <input type="file" name="welcomeImage"  />

                                            <input type="submit" value="Upload Image" />
                                        </form>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Image 1 : </td>
                                    <td>
                                        <form action="<%=requestURL%>" method="POST" enctype="multipart/form-data">
                                            <input type="file" name="frontImage"  />

                                            <input type="submit" value="Upload Image" />
                                        </form>
                                    </td>
                                </tr>


                                <tr>
                                    <td>Image 2 : </td>
                                    <td>
                                        <form action="<%=requestURL%>" method="POST" enctype="multipart/form-data">

                                            <input type="file" name="behindImage"  />

                                            <input type="submit" value="Upload Image" />
                                        </form>
                                    </td>
                                </tr>


                                <tr>
                                    <td>Image 3 : </td>
                                    <td>
                                        <form action="<%=requestURL%>" method="POST" enctype="multipart/form-data">
                                            <input type="file" name="sideImage"  />

                                            <input type="submit" value="Upload Image" />
                                        </form>
                                    </td>
                                </tr>

                                <tr>
                                    <td></td>
                                    <td>
                                        <form action="/CeylonJourney/location/locationManagement.jsp" method="post">
                                        <input type="button" value="Finish" onclick="javascript:finishUpload(this.form)"/>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>


                   

                    </body>
                    </html>
