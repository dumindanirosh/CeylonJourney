package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.UserDAO;
import com.duminda.ceylonjourney.dao.UserDAOImpl;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * If the user has been forgotten his/her password this system call this
 * servlet. This servlet sends the password to the corresponding user's email
 * address.
 *
 * @author Duminda
 */
public class ForgotPasswordServlet extends HttpServlet {

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
        String emailAddress = request.getParameter("emailAddress");
        boolean isValidated = true;
        RequestDispatcher requestDispatcher = null;
        if (emailAddress == null || emailAddress.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.EMAIL_REQUIRED);
        }
        if (isValidated) {
            UserDAO userDAO = new UserDAOImpl();
            String status = userDAO.sendEmail(emailAddress);
            requestDispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
            if (status.equals(BackendConstants.SUCCESS)) {
                request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.PASSWORD_SEND);
            } else if (status.equals(BackendConstants.ERROR)) {
                request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
            } else {
                requestDispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
                request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
            }
            requestDispatcher.forward(request, response);
        } else {
            requestDispatcher = request.getRequestDispatcher("/forgotPassword.jsp");
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
