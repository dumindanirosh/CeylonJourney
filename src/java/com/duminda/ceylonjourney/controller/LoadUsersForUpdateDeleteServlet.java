package com.duminda.ceylonjourney.controller;

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
 * This servlet load all the Users from database and provides the List of User
 * objects to the JSPs
 *
 * @author Duminda
 */
public class LoadUsersForUpdateDeleteServlet extends HttpServlet {

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
            UserDAO userDAO = new UserDAOImpl();

            if (!user.getUserType().getTypeName().equals(BackendConstants.ADMIN)) {
                User selectedUser = userDAO.viewUser(user.getUsername());
                request.setAttribute("originalUsername", user.getUsername());
                request.setAttribute(BackendConstants.SELECTED_USER, selectedUser);
            } else {
                List<User> userList = userDAO.viewAllUsers();
                request.setAttribute(BackendConstants.USER_LIST, userList);
            }
            if (user.getUserType().getTypeName().equals(BackendConstants.HOTEL_OFFICER)) {
                HotelDAO hotelDAO = new HotelDAOImpl();
                List<Hotel> listHotel = hotelDAO.loadActiveHotels();
                request.setAttribute(BackendConstants.ACTIVE_HOTELS, listHotel);
            }


            String actionType = request.getParameter("actionType");

            RequestDispatcher requestDispatcher = null;

            if (actionType.equals(BackendConstants.UPDATE)) {
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
        } else {
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
