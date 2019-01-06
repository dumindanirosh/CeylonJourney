package com.duminda.ceylonjourney.controller.travelGuide;

import com.duminda.ceylonjourney.dao.TravelGuideDAO;
import com.duminda.ceylonjourney.dao.TravelGuideDAOImpl;
import com.duminda.ceylonjourney.model.TravelGuide;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet manages all the database operations of TravelGuide object
 * @author Duminda
 */
public class TravelGuideManagementServlet extends HttpServlet {

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
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailAddress = request.getParameter("emailAddress");
            String officeTelephone = request.getParameter("officeTelephone");
            String mobileNumber = request.getParameter("mobileNumber");
            String travelGuideId = request.getParameter("travelGuideId");


            if (actionType != null && actionType.equals("addGuide")) {
                boolean isValidated = validateRequiredFields(request, firstName, lastName, emailAddress);


                RequestDispatcher requestDispatcher = null;

                if (isValidated) {
                    TravelGuide addGuide = new TravelGuide();
                    addGuide.setEmailAddress(emailAddress);
                    addGuide.setFirstName(firstName);
                    addGuide.setLastName(lastName);
                    addGuide.setMobileNumber(mobileNumber);
                    addGuide.setTelephoneNumber(officeTelephone);

                    TravelGuideDAO travelGuideDAO = new TravelGuideDAOImpl();

                    String status = travelGuideDAO.addTravelGuide(addGuide);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.DATA_ADDED_SUCCESSFULLY);
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }
                else{
                     requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.EMAIL_REQUIRED);
                        requestDispatcher.forward(request, response);
                }

            } else if (actionType != null && actionType.equals("updateGuide")) {
                boolean isValidated = validateRequiredFields(request, firstName, lastName, emailAddress);


                RequestDispatcher requestDispatcher = null;

                if (isValidated) {
                    TravelGuide addGuide = new TravelGuide();
                    addGuide.setEmailAddress(emailAddress);
                    addGuide.setFirstName(firstName);
                    addGuide.setLastName(lastName);
                    addGuide.setMobileNumber(mobileNumber);
                    addGuide.setTelephoneNumber(officeTelephone);
                    addGuide.setTravelGuideId(Integer.parseInt(travelGuideId));

                    TravelGuideDAO travelGuideDAO = new TravelGuideDAOImpl();

                    String status = travelGuideDAO.updateTravelGuide(addGuide);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }
                else{
                     requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.EMAIL_REQUIRED);
                        requestDispatcher.forward(request, response);
                }
            } else if (actionType != null && actionType.equals("deleteGuide")) {
                boolean isValidated = validateRequiredFields(request, firstName, lastName, emailAddress);


                RequestDispatcher requestDispatcher = null;

                if (isValidated) {
                    TravelGuide addGuide = new TravelGuide();
                    addGuide.setEmailAddress(emailAddress);
                    addGuide.setFirstName(firstName);
                    addGuide.setLastName(lastName);
                    addGuide.setMobileNumber(mobileNumber);
                    addGuide.setTelephoneNumber(officeTelephone);
                    addGuide.setTravelGuideId(Integer.parseInt(travelGuideId));

                    TravelGuideDAO travelGuideDAO = new TravelGuideDAOImpl();

                    String status = travelGuideDAO.deleteTravelGuide(addGuide);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.DATA_DELETED_SUCCESSFULLY);
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/travelGuide/travelGuideManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }
            }
        } else { // Ilegale Access
            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private boolean validateRequiredFields(HttpServletRequest request, String firstName, String lastName, String emailAddress) {
        boolean isValidated = true;

        if (firstName != null && firstName.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.FIRST_NAME_REQUIRED);
        } else if (lastName != null && lastName.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.LAST_NAME_REQUIRED);
        } else if (!validateEmail(emailAddress)) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.EMAIL_REQUIRED);
        }
        return isValidated;
    }
    
    private boolean validateEmail(String email) {
        if (email != null && email.length() > 0) {
            final String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
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
