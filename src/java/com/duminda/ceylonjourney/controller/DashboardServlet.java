package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.LocationCommentDAO;
import com.duminda.ceylonjourney.dao.LocationCommentDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * this servlet identifies the relevant Dashboard according to the type of user
 * and directs the browser to that specific Dashboard.
 *
 * @author Duminda
 */
public class DashboardServlet extends HttpServlet {

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

        RequestDispatcher requestDispatcher = null;
        // Create a HttpSession object per successful login
        HttpSession sessoin = request.getSession();
        // Set to Session object
        User user = (User) sessoin.getAttribute(BackendConstants.USER);

        // Retrieve User Type for given user details                        
        String userType = user.getUserType().getTypeName();

        // Member of system
        if (userType.equals(BackendConstants.MEMBER)) {
            LocationDAO locationDAO = new LocationDAOImpl();
            List<Location> allActiveLocations = locationDAO.loadAllActiveLocations();
            Location hotLocation = locationDAO.getHotLocation();
            request.setAttribute(BackendConstants.ACTIVE_LOCATIONS, allActiveLocations);
            request.setAttribute(BackendConstants.SELECTED_HOT_LOCATION, hotLocation);
            requestDispatcher = request.getRequestDispatcher("/dashboard/memberDashboard.jsp");
            
        }// Administrator of System 
        else if (userType.equals(BackendConstants.ADMIN)) {
            LocationCommentDAO locationCommentDAO = new LocationCommentDAOImpl();
            List<LocationComment> commentList = locationCommentDAO.getNonApprovedComments();
            LocationDAO locationDAO = new LocationDAOImpl();
            Map<LocationComment, Location> map = new HashMap<LocationComment, Location>();
            for (LocationComment lc : commentList) {
                map.put(lc, locationDAO.loadSelectedActiveLocation(Integer.toString(lc.getLocationId())));
            }
            request.setAttribute(BackendConstants.LOCATION_COMMENT_MAP, map);
            List<Location> locationList = locationDAO.loadNonActiveLocations();
            if (locationList != null && locationList.size() > 0) {
                request.setAttribute("approvalRequired", locationList.size());
            }
            requestDispatcher = request.getRequestDispatcher("/dashboard/adminDashboard.jsp");
            
        } // Internal Officer of System 
        else if (userType.equals(BackendConstants.INTERNAL_OFFICER)) {
            LocationDAO locationDAO = new LocationDAOImpl();
            List<Location> allActiveLocations = locationDAO.loadAllActiveLocations();
            Location hotLocation = locationDAO.getHotLocation();
            request.setAttribute(BackendConstants.ACTIVE_LOCATIONS, allActiveLocations);
            request.setAttribute(BackendConstants.SELECTED_HOT_LOCATION, hotLocation);
            requestDispatcher = request.getRequestDispatcher("/dashboard/internalOfficerDashboard.jsp");
            
        } // Hotel Officer of System
        else if (userType.equals(BackendConstants.HOTEL_OFFICER)) {
            LocationDAO locationDAO = new LocationDAOImpl();
            List<Location> allActiveLocations = locationDAO.loadAllActiveLocations();
            Location hotLocation = locationDAO.getHotLocation();
            request.setAttribute(BackendConstants.ACTIVE_LOCATIONS, allActiveLocations);
            request.setAttribute(BackendConstants.SELECTED_HOT_LOCATION, hotLocation);
            requestDispatcher = request.getRequestDispatcher("/dashboard/hotelOfficerDashboard.jsp");
        } // Not matching User type
        else {
            requestDispatcher = request.
                    getRequestDispatcher("/index.jsp");
        }

        // Forward to dispatched Jsp
        requestDispatcher.forward(request, response);

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
