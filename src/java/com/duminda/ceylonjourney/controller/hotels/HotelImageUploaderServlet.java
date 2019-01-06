package com.duminda.ceylonjourney.controller.hotels;

import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelImage;
import com.duminda.ceylonjourney.model.HotelImageId;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * This servlet uploads the images of Hotels which are selected by the users and
 * create database entries.
 *
 * @author Duminda
 */
public class HotelImageUploaderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(BackendConstants.USER);
        if (user != null) {
            try {
                
                String hotelId = request.getParameter("hotelId");
                File file;
                int maxFileSize = 5000 * 1024;
                int maxMemSize = 5000 * 1024;
                String filePath = BackendConstants.HOTEL_IMAGE_UPLOAD_LOCATION + hotelId + "\\";
                String dirPath = BackendConstants.HOTEL_IMAGE_UPLOAD_LOCATION + hotelId + "\\";
                
                File imageFile = new File(dirPath);
                if (!imageFile.isDirectory()) {
                    imageFile.mkdir();
                }
                
                if (ServletFileUpload.isMultipartContent(request)) {
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setSizeThreshold(maxMemSize);
                    factory.setRepository(imageFile);
                    
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    upload.setSizeMax(maxFileSize);
                    
                    List fileItems = upload.parseRequest(request);
                    Iterator i = fileItems.iterator();
                    
                    HotelDAO hotelDAO = new HotelDAOImpl();
                    
                    HotelImage hotelImage = null;
                    Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));
                    
                    while (i.hasNext()) {
                        
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {
                            String fileName = hotelId + "-" + fi.getName();
                            long fileSize = fi.getSize();
                            
                            
                            hotelImage = new HotelImage();
                            
                            HotelImageId hotelImageId = new HotelImageId();
                            hotelImageId.setHotelId(Integer.parseInt(hotelId));
                            hotelImageId.setImageName(fileName);
                            
                            hotelImage.setId(hotelImageId);
                            hotelImage.setImageUrl(filePath);
                            hotelImage.setImageSize(Long.toString(fileSize));
                            hotelImage.setImageType(fi.getContentType());
                            
                            
                            Set<HotelImage> hotelSet = new HashSet<HotelImage>();
                            hotelSet.add(hotelImage);
                            hotel.setHotelImages(hotelSet);
                            
                            
                            file = new File(filePath + fileName);
                            fi.write(file);
                        }
                    }
                    String requestURL = request.getContextPath()
                            + "/hotel/hotelImageUpload.jsp?"
                            + "hotelId=" + hotelId;
                    
                    hotelDAO.addNewHotelImages(hotelImage);
                    
                    
                    response.sendRedirect(requestURL);
                    
                }
            } catch (Exception ex) {
                Log4jUtil.logErrorMessage(ex);
            }
        } else { // Ilegale Access
            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
