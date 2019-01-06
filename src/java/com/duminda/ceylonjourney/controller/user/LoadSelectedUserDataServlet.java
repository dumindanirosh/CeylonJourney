package com.duminda.ceylonjourney.controller.user;

import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.dao.UserDAO;
import com.duminda.ceylonjourney.dao.UserDAOImpl;
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
 * Whenever a user select a user in the Update User wizard or in Delete User
 * wizard the system needs to load all the relevant details about that user.
 * This servlet is created to provides those necessary details of a selected
 * user.
 *
 * @author Duminda
 */
public class LoadSelectedUserDataServlet extends HttpServlet {

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
        User sessionUser = (User) session.getAttribute(BackendConstants.USER);
        if (sessionUser != null) {
            String originalUsername = request.getParameter("originalUsername");

            String username = request.getParameter("username");

            UserDAO userDAO = new UserDAOImpl();

            User user = userDAO.viewUser(username);


            String actionType = request.getParameter("actionType");

            RequestDispatcher requestDispatcher = null;
            request.setAttribute("originalUsername", originalUsername);
            request.setAttribute(BackendConstants.SELECTED_USER, user);

            if (actionType.equals(BackendConstants.UPDATE)) {
                HotelDAO hotelDAO = new HotelDAOImpl();
                List<Hotel> listHotel = hotelDAO.loadActiveHotels();

                request.setAttribute(BackendConstants.ACTIVE_HOTELS, listHotel);
                requestDispatcher =
                        request.getRequestDispatcher("/user/updateUser.jsp");

            } else if (actionType.equals(BackendConstants.DELETE)) {

                requestDispatcher =
                        request.getRequestDispatcher("/user/deleteUser.jsp");
            } else if (actionType.equals(BackendConstants.VIEW)) {
                requestDispatcher =
                        request.getRequestDispatcher("/user/viewUser.jsp");

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
