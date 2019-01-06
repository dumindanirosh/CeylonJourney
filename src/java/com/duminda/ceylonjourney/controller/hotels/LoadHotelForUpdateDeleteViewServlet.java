package com.duminda.ceylonjourney.controller.hotels;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelOfficer;
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
 * Some members has the facility to view and update the Hotel details in their
 * Dashboard. This servlet is designed to support that kind of operation. This
 * servlet loads the Hotels and provides the List of Hotels to the dashboard.
 *
 * @author Duminda
 */
public class LoadHotelForUpdateDeleteViewServlet extends HttpServlet {

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

            HotelDAO hotelDAO = new HotelDAOImpl();
            List<Hotel> hotelList = hotelDAO.loadAllHotels();
            request.setAttribute(BackendConstants.HOTEL_LIST, hotelList);

            


            RequestDispatcher requestDispatcher = null;

            if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_UPDATE)) {
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                List<CityDetail> cityList = cityDetailDAO.loadAllActiveCities();
                request.setAttribute(BackendConstants.CITY_LIST, cityList);
                requestDispatcher = request.getRequestDispatcher("/hotel/updateHotel.jsp");
                
                if (user.getUserType().getTypeName().equals(BackendConstants.HOTEL_OFFICER)) {
                    Object[] usersList = (Object[]) user.getHotelOfficers().toArray();
                    HotelOfficer hotelOfficer = (HotelOfficer) usersList[0];
                    request.setAttribute(BackendConstants.HOTEL_LIST, null);
                    request.setAttribute(BackendConstants.SELECTED_HOTEL, hotelOfficer.getHotel());
                }
                
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_DELETE)) {
                requestDispatcher = request.getRequestDispatcher("/hotel/deleteHotel.jsp");

            } else if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_VIEW)) {
                requestDispatcher = request.getRequestDispatcher("/hotel/viewHotels.jsp");
                
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_ADD_CONTRACT)) {
                requestDispatcher =
                        request.getRequestDispatcher("/hotel/addHotelContract.jsp");
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_UPDATE_CONTRACT)) {
                hotelList = hotelDAO.loadActiveHotels();
                request.setAttribute(BackendConstants.HOTEL_LIST, hotelList);
                requestDispatcher = request.getRequestDispatcher("/hotel/updateHotelContract.jsp");
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
