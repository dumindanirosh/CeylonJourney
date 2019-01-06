package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.LocationCommentDAO;
import com.duminda.ceylonjourney.dao.LocationCommentDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.dao.UserDAO;
import com.duminda.ceylonjourney.dao.UserDAOImpl;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import java.io.IOException;
import java.io.PrintWriter;
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
 * This servlet has the responsible for login. If the user enters correct
 * username and password it will forward to dashboard. Otherwise it leads to
 * error.
 *
 * @author Duminda
 */
public class LoginServlet extends HttpServlet {

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
            // Retrieve user input data
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isValidated = true;

            RequestDispatcher requestDispatcher = null;
            // Validate for the user name and password whether entered or not
            if (username == null || username.length() == 0) {
                isValidated = false;
                request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.USER_NAME_REQUIRED);
            } else if (password == null || password.length() == 0) {
                isValidated = false;
                request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.PASSWORD_REQUIRED);
            }
            // login details entered
            if (isValidated) {
                // Create UserDAO object for validate 
                UserDAO userDAO = new UserDAOImpl();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user = userDAO.login(user);
                String loginStatus = user.getLoginStatus();

                // login details success
                if (loginStatus.equals(BackendConstants.SUCCESS)) {
                    // Create a HttpSession object per successful login
                    HttpSession sessoin = request.getSession();
                    // Set to Session object

                    sessoin.setAttribute(BackendConstants.USER, user);

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
                                getRequestDispatcher("/viewLocationForWelcome.action");
                    }
                } else if (loginStatus.equals(BackendConstants.INVALID_LOGIN)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.INVALID_LOGIN);
                    requestDispatcher = request.getRequestDispatcher("/viewLocationForWelcome.action");
                } else if (loginStatus.equals(BackendConstants.ERROR)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.ERROR);
                    requestDispatcher = request.getRequestDispatcher("/viewLocationForWelcome.action");
                } else if (loginStatus.equals(BackendConstants.DISABLED)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.USER_DISABLED);
                    requestDispatcher = request.getRequestDispatcher("/viewLocationForWelcome.action");
                }
            } else { // login details not entered
                requestDispatcher = request.getRequestDispatcher("viewLocationForWelcome.action");

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
