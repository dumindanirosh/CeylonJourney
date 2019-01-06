package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import com.duminda.ceylonjourney.util.Utilities;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet manages the add, update and delete mechanism of Locations.
 *
 * @author Duminda
 */
public class LocationControllerServlet extends HttpServlet {

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

        if (user != null && !user.getUserType().getTypeName().equals(BackendConstants.MEMBER)) {

            String actionType = request.getParameter("actionType");

            if (actionType.equals("addLocation")) {

                String locationName = request.getParameter("locationName");
                String locationDescription = request.getParameter("locationDescription");
                String locationAddress = request.getParameter("locationAddress");
                String locationStatus = request.getParameter("locationStatus");
                String cityId = request.getParameter("cityId");
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                CityDetail cityDetail = cityDetailDAO.viewCity(cityId);

                Byte staus = 1;

                if (locationStatus == null) {
                    staus = 0;
                }

                if (locationName == null || locationName.length() == 0) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.LOCATION_NAME_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else if (cityDetail == null) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.CITY_IS_NOT_VALID);
                    requestDispatcher.forward(request, response);
                } else {

                    Location location = new Location();
                    location.setLocationName(locationName);
                    location.setLocationDescription(locationDescription);
                    location.setLocationAddress(locationAddress);
                    location.setEnteredBy(user.getUsername());
                    location.setEnteredDate(Utilities.getTime());


                    Set<CityDetail> listCity = new HashSet<CityDetail>();
                    listCity.add(cityDetail);
                    location.setCityDetails(listCity);

                    location.setLocationStatus(staus);

                    LocationDAO locationDAO = new LocationDAOImpl();
                    String status = locationDAO.addNewLocation(location);

                    RequestDispatcher requestDispatcher = null;

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_ADDED_SUCCESSFULLY);
                        String requestURL = request.getContextPath()
                                + "/location/locationImageUpload.jsp?"
                                + "locationId=" + location.getLocationId();

                        response.sendRedirect(requestURL);
//                       String requestURL ="/location/locationImageUpload.jsp?" 
//                             + "locationId="+location.getLocationId();
//                         requestDispatcher = request.getRequestDispatcher
//                                (requestURL);
//                          requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }




            } else if (actionType.equals("updateLocation")) {
                String locationId = request.getParameter("locationId");
                String locationName = request.getParameter("locationName");
                String locationDescription = request.getParameter("locationDescription");
                String locationAddress = request.getParameter("locationAddress");
                String locationStatus = request.getParameter("locationStatus");
                String cityId = request.getParameter("cityId");
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                CityDetail cityDetail = cityDetailDAO.viewCity(cityId);

                Byte staus = 1;

                if (locationStatus == null) {
                    staus = 0;
                }

                if (locationName == null || locationName.length() == 0) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.LOCATION_NAME_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else if (cityDetail == null) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.CITY_IS_NOT_VALID);
                    requestDispatcher.forward(request, response);
                } else {
                    LocationDAO locationDAO = new LocationDAOImpl();
                    Location location = locationDAO.loadSelectedLocation(locationId);
                    location.setLocationName(locationName);
                    location.setLocationDescription(locationDescription);
                    location.setLocationAddress(locationAddress);
                    location.setEnteredBy(user.getUsername());
                    location.setEnteredDate(Utilities.getTime());


                    Set<CityDetail> listCity = new HashSet<CityDetail>();
                    listCity.add(cityDetail);
                    location.setCityDetails(listCity);

                    location.setLocationStatus(staus);


                    String status = locationDAO.updateLocation(location);

                    RequestDispatcher requestDispatcher = null;

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                        String requestURL = request.getContextPath()
                                + "/location/locationImageUpload.jsp?"
                                + "locationId=" + location.getLocationId();

                        response.sendRedirect(requestURL);
//                       String requestURL ="/location/locationImageUpload.jsp?" 
//                             + "locationId="+location.getLocationId();
//                         requestDispatcher = request.getRequestDispatcher
//                                (requestURL);
//                          requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }

            } else if (actionType.equals("approveLocation")) {

                String locationId = request.getParameter("locationId");

                LocationDAO locationDAO = new LocationDAOImpl();
                Location location = locationDAO.loadSelectedLocation(locationId);
                location.setLocationStatus(Byte.parseByte("1"));


                String status = locationDAO.updateLocation(location);

                RequestDispatcher requestDispatcher = null;

                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.LOCATION_APPROVED_SUCCESSFULLY);
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");

                    requestDispatcher.forward(request, response);


                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);

                }
            } else if (actionType.equals("deleteLocation")) {

                String locationId = request.getParameter("locationId");
                LocationDAO locationDAO = new LocationDAOImpl();
                Location location = locationDAO.loadSelectedLocation(locationId);

                String status = locationDAO.deleteLocation(location);

                RequestDispatcher requestDispatcher = null;

                if (status.equals(BackendConstants.SUCCESS)) {

                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_DELETED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);

                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/location/locationManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                }

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
