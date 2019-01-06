package com.duminda.ceylonjourney.controller.hotels;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.HotelContractDAO;
import com.duminda.ceylonjourney.dao.HotelContractDAOImpl;
import com.duminda.ceylonjourney.dao.HotelDAO;
import java.io.IOException;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelContract;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet receives a selected hotel's ID and returns the specific Hotel
 * that belongs to the ID.
 *
 * @author Duminda
 */
public class ViewSelectedHotelServlet extends HttpServlet {

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
        HotelDAO hotelDAO = new HotelDAOImpl();

        String hotelId = request.getParameter("hotelId");
        Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));
        request.setAttribute(BackendConstants.SELECTED_HOTEL, hotel);

        String actionType = request.getParameter("actionType");

        RequestDispatcher requestDispatcher = null;

        if (actionType != null && actionType.equals(BackendConstants.HOTEL_FOR_HOME_VIEW)) {
            requestDispatcher =
                    request.getRequestDispatcher("/hotel/viewSelectedHotelForHomePage.jsp");
            requestDispatcher.forward(request, response);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(BackendConstants.USER);
        if (user != null || actionType == null) {

            if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_VIEW)) {
                requestDispatcher =
                        request.getRequestDispatcher("/hotel/viewHotels.jsp");
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_UPDATE)) {
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                List<CityDetail> cityList = cityDetailDAO.loadAllActiveCities();

                request.setAttribute(BackendConstants.CITY_LIST, cityList);


                requestDispatcher =
                        request.getRequestDispatcher("/hotel/updateHotel.jsp");
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_DASHBOARD_DELETE)) {
                requestDispatcher =
                        request.getRequestDispatcher("/hotel/deleteHotel.jsp");

            } else if (actionType.equals(BackendConstants.HOTEL_FOR_ADD_CONTRACT)) {
                HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();
                HotelContract hotelContact = hotelContractDAO.getContract(hotelId);
                request.setAttribute(BackendConstants.SELECTED_HOTEL_CONTRACT, hotelContact);
                requestDispatcher =
                        request.getRequestDispatcher("/hotel/addHotelContract.jsp");
            } else if (actionType.equals(BackendConstants.HOTEL_FOR_UPDATE_CONTRACT)) {
                HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();
                HotelContract hotelContact = hotelContractDAO.getContract(hotelId);
                request.setAttribute(BackendConstants.SELECTED_HOTEL_CONTRACT, hotelContact);
                requestDispatcher =
                        request.getRequestDispatcher("/hotel/updateHotelContract.jsp");
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
