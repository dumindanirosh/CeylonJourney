package com.duminda.ceylonjourney.controller.locations;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
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
 * This servlet loads all the Locations and pass them as a List of Locations for
 * the Dashboard Location Management operations.
 *
 * @author Duminda
 */
public class LoadLocationForUpdateDeleteViewServlet extends HttpServlet {

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
            String actionType = request.getParameter("actionType");
            LocationDAO locationDAO = new LocationDAOImpl();

            List<Location> locationList = locationDAO.loadAllLocations();


            request.setAttribute(BackendConstants.LOCATION_LIST, locationList);


            RequestDispatcher requestDispatcher = null;

            if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_UPDATE)) {
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                List<CityDetail> cityList = cityDetailDAO.loadAllActiveCities();
                request.setAttribute(BackendConstants.CITY_LIST, cityList);
                requestDispatcher = request.getRequestDispatcher("/location/updateLocation.jsp");

            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_DELETE)) {
                requestDispatcher = request.getRequestDispatcher("/location/deleteLocation.jsp");

            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_VIEW)) {
                requestDispatcher = request.getRequestDispatcher("/location/viewLocations.jsp");
            } else if (actionType.equals(BackendConstants.LOCATION_FOR_DASHBOARD_APPROVE)) {
                locationList = locationDAO.loadNonActiveLocations();


                request.setAttribute(BackendConstants.LOCATION_LIST, locationList);

                requestDispatcher = request.getRequestDispatcher("/location/approveLocations.jsp");

            } else if (actionType.equals(BackendConstants.HOT_LOCATION_FOR_DASHBOARD)) {
                List<Location> allActiveLocations = locationDAO.loadAllActiveLocations();
                request.setAttribute(BackendConstants.LOCATION_LIST, allActiveLocations);
                requestDispatcher = request.getRequestDispatcher("/location/setHotLocation.jsp");

            }
            requestDispatcher.forward(request, response);
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
