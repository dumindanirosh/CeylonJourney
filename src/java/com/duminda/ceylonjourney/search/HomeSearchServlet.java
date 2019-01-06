package com.duminda.ceylonjourney.search;

import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is created in-order to serve the home Page search engine.
 * It receives the search type and search keyword and returns back the related results according to the input.
 * @author Duminda
 */
public class HomeSearchServlet extends HttpServlet {

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

        String actionType = request.getParameter("actionType");
        String searchKeyWord = request.getParameter("searchKeyWord");
        RequestDispatcher requestDispatcher = null;
        
        if (searchKeyWord == null) {
            requestDispatcher = request.getRequestDispatcher("/index.jsp");
            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                    FrontMessages.KEYWORD_REQUIRED);
            requestDispatcher.forward(request, response);
        }
        else{
            if (actionType.equals(BackendConstants.SEARCH_LOCATIONS)) {
                //search for locations 
                String searchType = request.getParameter("locationSearchType");
                LocationDAO searchDAO = new LocationDAOImpl();
                List<Location> locationList = searchDAO.searchLocations(searchType, searchKeyWord);
                request.setAttribute(BackendConstants.ACTIVE_LOCATIONS, locationList);
                requestDispatcher = request.getRequestDispatcher("/location/viewActiveLocationsHome.jsp");
                requestDispatcher.forward(request, response);
                
            } else if (actionType.equals(BackendConstants.SEARCH_HOTELS)) {
                //search for hotels
                String searchType = request.getParameter("hotelSearchType");
                HotelDAO searchDAO = new HotelDAOImpl();
                List<Hotel> hotelList = searchDAO.searchHotels(searchType, searchKeyWord);
                request.setAttribute(BackendConstants.ACTIVE_HOTELS, hotelList);
                requestDispatcher = request.getRequestDispatcher("/hotel/viewActiveHotelsHome.jsp");
                requestDispatcher.forward(request, response);
            } else {
                //invalid request
                requestDispatcher = request.getRequestDispatcher("/index.jsp");
            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                    FrontMessages.ERROR);
            requestDispatcher.forward(request, response);
            }
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
