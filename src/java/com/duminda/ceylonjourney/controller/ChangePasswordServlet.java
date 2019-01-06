package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.UserDAO;
import com.duminda.ceylonjourney.dao.UserDAOImpl;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is called when the user wants to change his/her password. It
 * will change the password in database.
 *
 * @author Duminda
 */
public class ChangePasswordServlet extends HttpServlet {

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

        if (user != null) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String userPassword = user.getPassword();
            if (currentPassword != null && currentPassword.equals(userPassword)) {
                user.setPassword(newPassword);
                UserDAO userDAO = new UserDAOImpl();
                userDAO.updateUser(user);
                request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.PASSWORD_CHANGED_SUCCESSFULLY);

                String userType = user.getUserType().getTypeName();


                // Member of system
                if (userType.equals(BackendConstants.MEMBER)) {
                    requestDispatcher = request.getRequestDispatcher("/dashboard/memberDashboard.jsp");
                }// Administrator of System 
                else if (userType.equals(BackendConstants.ADMIN)) {
                    requestDispatcher = request.getRequestDispatcher("/dashboard/adminDashboard.jsp");
                } // Internal Officer of System 
                else if (userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                    requestDispatcher = request.getRequestDispatcher("/dashboard/internalOfficer.jsp");
                } // Hotel Officer of System
                else if (userType.equals(BackendConstants.HOTEL_OFFICER)) {
                    requestDispatcher = request.getRequestDispatcher("/dashboard/hotelOfficer.jsp");
                } // Not matching User type
                else {
                    requestDispatcher = request.
                            getRequestDispatcher("/viewLocationForWelcome.action");
                }

            } else {
                request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.INVALID_CURRENT_PASSWORD);
                requestDispatcher = request.getRequestDispatcher("/changePassword.jsp");
            }
            requestDispatcher.forward(request, response);

        } else { // Illegal Access
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
