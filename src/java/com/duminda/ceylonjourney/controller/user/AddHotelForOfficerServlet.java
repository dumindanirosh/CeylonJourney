package com.duminda.ceylonjourney.controller.user;

import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.model.Hotel;
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
 * In the 'Add User' and 'Update User' wizards if the user choices to handle the
 * HotelOfficer the system must show the available active list of hotels. This
 * servlet takes care of that and it loads all the active hotels and pass them
 * as a List of Hotels.
 *
 * @author Duminda
 */
public class AddHotelForOfficerServlet extends HttpServlet {

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
            String userType = request.getParameter("userType");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String originalUsername = request.getParameter("originalUsername");
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailAddress = request.getParameter("emailAddress");
            String officeTelephone = request.getParameter("officeTelephone");
            String mobileNumber = request.getParameter("mobileNumber");

            Log4jUtil.logErrorMessage("Original in addhotel servlet:    " + originalUsername);



            RequestDispatcher requestDispatcher = null;

            if (actionType.equals("addHotel")) {
                requestDispatcher = request.getRequestDispatcher("/user/addUser.jsp");
            } else if (actionType.equals("addHotelForUpdate")) {
                requestDispatcher = request.getRequestDispatcher("/user/updateUser.jsp");
            }
            if (userType.equals(BackendConstants.HOTEL_OFFICER)) {
                HotelDAO hotelDAO = new HotelDAOImpl();
                List<Hotel> listHotel = hotelDAO.loadActiveHotels();
                request.setAttribute(BackendConstants.ACTIVE_HOTELS, listHotel);
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("emailAddress", emailAddress);
                request.setAttribute("officeTelephone", officeTelephone);
                request.setAttribute("mobileNumber", mobileNumber);
                request.setAttribute("password", password);
                request.setAttribute("userType", userType);
                request.setAttribute("confirmPassword", confirmPassword);
                request.setAttribute("originalUsername", originalUsername);



            } else {
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("emailAddress", emailAddress);
                request.setAttribute("officeTelephone", officeTelephone);
                request.setAttribute("mobileNumber", mobileNumber);
                request.setAttribute("password", password);
                request.setAttribute("confirmPassword", confirmPassword);
                request.setAttribute("userType", userType);
                request.setAttribute("originalUsername", originalUsername);
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
