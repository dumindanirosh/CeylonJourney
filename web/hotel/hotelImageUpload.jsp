<%-- 
    Document   : hotelImageUpload
    Created on : Feb 15, 2013, 4:30:17 PM
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
               
                myform.action = "/CeylonJourney/hotel/hotelManagement.jsp";
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
                        String hotelId = request.getParameter("hotelId");

                        request.setAttribute("hotelId", hotelId);
                        String contextPath = request.getContextPath();
                        String requestURL = contextPath
                                + "/hotelImageUploader.action?hotelId=" + hotelId;
                    %>

                    
                        <table border="1"  >
                            <tbody>
                                
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
                                        <form action="/CeylonJourney/hotel/hotelManagement.jsp">
                                        <input type="button" value="Finish" onclick="javascript:finishUpload(this.form)"/>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>


                   

                    </body>
                    </html>
