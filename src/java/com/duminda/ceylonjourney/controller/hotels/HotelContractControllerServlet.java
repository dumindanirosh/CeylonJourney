package com.duminda.ceylonjourney.controller.hotels;

import com.duminda.ceylonjourney.dao.HotelContractDAO;
import com.duminda.ceylonjourney.dao.HotelContractDAOImpl;
import com.duminda.ceylonjourney.model.HotelContract;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet handles all the database operations of HotelContract
 *
 * @author Duminda
 */
public class HotelContractControllerServlet extends HttpServlet {

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

            if (actionType != null && actionType.equals("addContract")) {
                String hotelId = request.getParameter("hotelId");
                String sDS = request.getParameter("sD");
                String sMS = request.getParameter("sM");
                String sYS = request.getParameter("sY");

                String eDS = request.getParameter("eD");
                String eMS = request.getParameter("eM");
                String eYS = request.getParameter("eY");

                int sD = Integer.parseInt(sDS);
                int sM = Integer.parseInt(sMS) - 1;
                int sY = Integer.parseInt(sYS);

                int eD = Integer.parseInt(eDS);
                int eM = Integer.parseInt(eMS) - 1;
                int eY = Integer.parseInt(eYS);

                Calendar startCalendar = Calendar.getInstance();
                startCalendar.set(sY, sM, sD);

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.set(eY, eM, eD);

                HotelContract hotelContract = new HotelContract();
                hotelContract.setHotelId(Integer.parseInt(hotelId));
                hotelContract.setStartDate(startCalendar.getTime());
                hotelContract.setEndDate(endCalendar.getTime());
                hotelContract.setEnteredDate(new Date());
                HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();

                String status = hotelContractDAO.addContract(hotelContract);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");

                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_ADDED_SUCCESSFULLY);

                } else if (status.equals(BackendConstants.ERROR)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);

                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                }
                requestDispatcher.forward(request, response);




            } else if (actionType.equals("updateContract")) {
                String hotelId = request.getParameter("hotelId");
                String sDS = request.getParameter("sD");
                String sMS = request.getParameter("sM");
                String sYS = request.getParameter("sY");

                String eDS = request.getParameter("eD");
                String eMS = request.getParameter("eM");
                String eYS = request.getParameter("eY");

                int sD = Integer.parseInt(sDS);
                int sM = Integer.parseInt(sMS) - 1;
                int sY = Integer.parseInt(sYS);

                int eD = Integer.parseInt(eDS);
                int eM = Integer.parseInt(eMS) - 1;
                int eY = Integer.parseInt(eYS);

                Calendar startCalendar = Calendar.getInstance();
                startCalendar.set(sY, sM, sD);

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.set(eY, eM, eD);

                HotelContract hotelContract = new HotelContract();
                hotelContract.setHotelId(Integer.parseInt(hotelId));
                hotelContract.setStartDate(startCalendar.getTime());
                hotelContract.setEndDate(endCalendar.getTime());
                hotelContract.setEnteredDate(new Date());
                HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();

                String status = hotelContractDAO.updateContract(hotelContract);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");

                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_UPDATED_SUCCESSFULLY);

                } else if (status.equals(BackendConstants.ERROR)) {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);

                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                }
                requestDispatcher.forward(request, response);
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
