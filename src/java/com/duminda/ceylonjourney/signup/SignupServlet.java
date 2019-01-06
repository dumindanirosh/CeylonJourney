package com.duminda.ceylonjourney.signup;

import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.dao.UserDAO;
import com.duminda.ceylonjourney.dao.UserDAOImpl;
import com.duminda.ceylonjourney.model.Admin;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelOfficer;
import com.duminda.ceylonjourney.model.InternalOfficer;
import com.duminda.ceylonjourney.model.Member;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.model.UserType;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * If a new user enters the informations and clicks on the Sign Up option in
 * Signup wizard, this servlet will be called and it creates a new account for
 * that user if all the informations are correct.
 *
 * @author Duminda
 */
public class SignupServlet extends HttpServlet {

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
            String userType = request.getParameter("userType");
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailAddress = request.getParameter("emailAddress");
            String officeTelephone = request.getParameter("officeTelephone");
            String mobileNumber = request.getParameter("mobileNumber");
            String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

        RequestDispatcher requestDispatcher = null;


        if (actionType != null && actionType.equals("signupUser")) {
            boolean isValidated = validateRequiredFields(request, username, firstName, lastName, emailAddress);

            if (isValidated) {
                if (password != null && password.length() == 0) {
                    isValidated = false;
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.PASSWORD_REQUIRED);
                } else if (confirmPassword != null && !(confirmPassword.equals(password))) {
                    isValidated = false;
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.CONFIRM_PASSWORD_REQUIRED);
                } else if (firstName != null && firstName.length() == 0) {
                    isValidated = false;
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.FIRST_NAME_REQUIRED);
                }
            }


            if (isValidated) {
                User addUser = new User();
                addUser.setUsername(username);
                addUser.setPassword(password);
                addUser.setUserStatus(true);
                UserType userTypeObj = new UserType();

                userTypeObj.setUserTypeId(new Integer(BackendConstants.MEMBER_ID));
                Member member = new Member();
                member.setUser(addUser);
                member.setFirstName(firstName);
                member.setLastName(lastName);
                member.setOfficeTelephone(officeTelephone);
                member.setMobileNuermb(mobileNumber);
                member.setEmailAddress(emailAddress);
                Set members = new HashSet();
                members.add(member);
                addUser.setMembers(members);



                addUser.setUserType(userTypeObj);
                UserDAO userDAO = new UserDAOImpl();
                String status = userDAO.addUser(addUser);

                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_ADDED_SUCCESSFULLY);
                    request.setAttribute("username", username);
                    request.setAttribute("password", password);
                    requestDispatcher = request.getRequestDispatcher("/login.action");
                    requestDispatcher.forward(request, response);

                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/user/signupUser.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/signupUser.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                }
            } else {
                requestDispatcher = request.getRequestDispatcher("/user/signupUser.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
        }
    }

    private boolean validateRequiredFields(HttpServletRequest request,
            String username, String firstName, String lastName, String emailAddress) {
        boolean isValidated = true;

        if (username != null && username.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.USER_NAME_REQUIRED);
        } else if (firstName != null && firstName.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.FIRST_NAME_REQUIRED);
        } else if (lastName != null && lastName.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.LAST_NAME_REQUIRED);
        } else if (emailAddress != null && emailAddress.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.EMAIL_REQUIRED);
        }
        return isValidated;
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
