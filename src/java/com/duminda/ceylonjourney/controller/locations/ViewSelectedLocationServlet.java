package com.duminda.ceylonjourney.controller.locations;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.LocationCommentDAO;
import com.duminda.ceylonjourney.dao.LocationCommentDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Whenever a user select a location in the Update Location wizard or in Delete
 * Location wizard the system needs to load all the relevant details about that
 * location. This servlet is created to provides those necessary details of a
 * selected location.
 *
 * @author Duminda
 */
public class ViewSelectedLocationServlet extends HttpServlet {

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
        LocationDAO locationDAO = new LocationDAOImpl();

        String locationId = request.getParameter("locationId");
        Location location = locationDAO.loadSelectedLocation(locationId);

        request.setAttribute(BackendConstants.SELECTED_LOCATION, location);

        String actionType = request.getParameter("actionType");
        String status = request.getParameter("message");

        RequestDispatcher requestDispatcher = null;



        if (actionType != null && actionType.equals(BackendConstants.LOCATION_FOR_HOME_VIEW)) {

            LocationCommentDAO locationCommentDAO = new LocationCommentDAOImpl();
            List<LocationComment> locationComments = locationCommentDAO.getApprovedComments(locationId);
            request.setAttribute(BackendConstants.APPROVED_LOCATION_COMMENTS, locationComments);

            if (status != null) {
                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.COMMENT_ADDED_SUCCESSFULLY);
                } else if (status.equals(BackendConstants.ERROR)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            status);
                }
            }
            requestDispatcher = request.getRequestDispatcher("/location/viewSelectedLocationForHomePage.jsp");
            requestDispatcher.forward(request, response);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(BackendConstants.USER);
        if (user != null) {

            if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_VIEW)) {
                requestDispatcher = request.getRequestDispatcher("/location/viewLocations.jsp");
            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_UPDATE)) {
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                List<CityDetail> cityList = cityDetailDAO.loadAllActiveCities();
                request.setAttribute(BackendConstants.CITY_LIST, cityList);
                requestDispatcher = request.getRequestDispatcher("/location/updateLocation.jsp");

            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_DELETE)) {
                requestDispatcher = request.getRequestDispatcher("/location/deleteLocation.jsp");

            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_APPROVE)) {
                requestDispatcher = request.getRequestDispatcher("/location/approveLocations.jsp");

            } else if (actionType.equals(BackendConstants.HOT_LOCATION_FOR_DASHBOARD)) {
                requestDispatcher = request.getRequestDispatcher("/location/setHotLocation.jsp");

            }
            requestDispatcher.forward(request, response);

        } else { // Ilegale Access
            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
            requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
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
