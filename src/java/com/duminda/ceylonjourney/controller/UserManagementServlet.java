package com.duminda.ceylonjourney.controller;

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
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet has the responsibility to add, update and delete User's
 * informations.
 *
 * @author Duminda
 */
public class UserManagementServlet extends HttpServlet {

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
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String emailAddress = request.getParameter("emailAddress");
            String officeTelephone = request.getParameter("officeTelephone");
            String mobileNumber = request.getParameter("mobileNumber");



            if (actionType != null && actionType.equals("addUser")) {
                boolean isValidated = validateRequiredFields(request, username, firstName, lastName, emailAddress, userType);
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
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
                RequestDispatcher requestDispatcher = null;


                if (isValidated) {
                    User addUser = new User();
                    addUser.setUsername(username);
                    addUser.setPassword(password);
                    addUser.setUserStatus(true);
                    UserType userTypeObj = new UserType();

                    if (userType != null && userType.equals(BackendConstants.MEMBER)) {
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

                    } else if (userType != null && userType.equals(BackendConstants.HOTEL_OFFICER)) {
                        userTypeObj.setUserTypeId(new Integer(BackendConstants.HOTEL_OFFICER_ID));
                        String hotelId = request.getParameter("hotelId");
                        HotelDAO hotelDAO = new HotelDAOImpl();
                        Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));
                        HotelOfficer hotelOfficer = new HotelOfficer();
                        hotelOfficer.setHotel(hotel);
                        hotelOfficer.setUser(addUser);
                        hotelOfficer.setFirstName(firstName);
                        hotelOfficer.setLastName(lastName);
                        hotelOfficer.setOfficeTelephone(officeTelephone);
                        hotelOfficer.setMobileNumber(mobileNumber);
                        hotelOfficer.setEmailAddress(emailAddress);
                        Set hotelOfficers = new HashSet();
                        hotelOfficers.add(hotelOfficer);
                        addUser.setHotelOfficers(hotelOfficers);

                    } else if (userType != null && userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                        userTypeObj.setUserTypeId(new Integer(BackendConstants.INTERNAL_OFFICER_ID));
                        InternalOfficer internalOfficer = new InternalOfficer();
                        internalOfficer.setUser(addUser);
                        internalOfficer.setFirstName(firstName);
                        internalOfficer.setLastName(lastName);
                        internalOfficer.setOfficeTelephone(officeTelephone);
                        internalOfficer.setMobileNumber(mobileNumber);
                        internalOfficer.setEmailAddress(emailAddress);
                        Set internalOfficers = new HashSet();
                        internalOfficers.add(internalOfficer);
                        addUser.setInternalOfficers(internalOfficers);

                    } else if (userType != null && userType.equals(BackendConstants.ADMIN)) {
                        userTypeObj.setUserTypeId(new Integer(BackendConstants.ADMIN_ID));
                        Admin admin = new Admin();
                        admin.setUser(addUser);
                        admin.setFirstName(firstName);
                        admin.setLastName(lastName);
                        admin.setOfficeTelephone(officeTelephone);
                        admin.setMobileNumber(mobileNumber);
                        admin.setEmaillAddress(emailAddress);
                        Set admins = new HashSet();
                        admins.add(admin);
                        addUser.setAdmins(admins);
                    }
                    addUser.setUserType(userTypeObj);
                    UserDAO userDAO = new UserDAOImpl();
                    String status = userDAO.addUser(addUser);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_ADDED_SUCCESSFULLY);
                        String requestURL =
                                "user/userManagement.jsp?" + "userId=" + addUser.getUsername();
                        requestDispatcher = request.getRequestDispatcher(requestURL);
                        requestDispatcher.forward(request, response);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/addUser.jsp");
                    requestDispatcher.forward(request, response);
                }

            } else if (actionType.equals("updateUser")) {
                boolean isValidated = validateRequiredFields(request, username, firstName, lastName, emailAddress, userType);
                String password = request.getParameter("password");
                String originalUsername = request.getParameter("originalUsername");
                RequestDispatcher requestDispatcher = null;

                if (isValidated) {
                    UserDAO userDAO = new UserDAOImpl();
                    if (originalUsername.equals(username)) {
                        User oldUpdateUser = userDAO.viewUser(username);
                        Set<Admin> setAdmin = oldUpdateUser.getAdmins();
                        for (Admin a : setAdmin) {
                            userDAO.deleteUserType(a);
                        }
                        Set<HotelOfficer> setHotelOfficer = oldUpdateUser.getHotelOfficers();
                        for (HotelOfficer h : setHotelOfficer) {
                            userDAO.deleteUserType(h);
                        }
                        Set<InternalOfficer> setInternalOfficer = oldUpdateUser.getInternalOfficers();
                        for (InternalOfficer i : setInternalOfficer) {
                            userDAO.deleteUserType(i);
                        }
                        Set<Member> setMember = oldUpdateUser.getMembers();
                        for (Member m : setMember) {
                            userDAO.deleteUserType(m);
                        }
                        User updateUser = new User();
                        updateUser.setUsername(username);
                        updateUser.setPassword(password);

                        updateUser.setUserStatus(true);
                        UserType userTypeObj = new UserType();

                        if (userType != null && userType.equals(BackendConstants.MEMBER)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.MEMBER_ID));
                            Member member = new Member();
                            member.setUser(updateUser);
                            member.setFirstName(firstName);
                            member.setLastName(lastName);
                            member.setOfficeTelephone(officeTelephone);
                            member.setMobileNuermb(mobileNumber);
                            member.setEmailAddress(emailAddress);
                            Set members = new HashSet();
                            members.add(member);
                            updateUser.setMembers(members);

                        } else if (userType != null && userType.equals(BackendConstants.HOTEL_OFFICER)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.HOTEL_OFFICER_ID));
                            String hotelId = request.getParameter("hotelId");
                            Hotel hotel = new Hotel();
                            hotel.setHotelId(new Integer(hotelId));
                            HotelOfficer hotelOfficer = new HotelOfficer();
                            hotelOfficer.setHotel(hotel);
                            hotelOfficer.setUser(updateUser);
                            hotelOfficer.setFirstName(firstName);
                            hotelOfficer.setLastName(lastName);
                            hotelOfficer.setOfficeTelephone(officeTelephone);
                            hotelOfficer.setMobileNumber(mobileNumber);
                            hotelOfficer.setEmailAddress(emailAddress);
                            Set hotelOfficers = new HashSet();
                            hotelOfficers.add(hotelOfficer);
                            updateUser.setHotelOfficers(hotelOfficers);

                        } else if (userType != null && userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.INTERNAL_OFFICER_ID));

                            InternalOfficer internalOfficer = new InternalOfficer();
                            internalOfficer.setUser(updateUser);
                            internalOfficer.setFirstName(firstName);
                            internalOfficer.setLastName(lastName);
                            internalOfficer.setOfficeTelephone(officeTelephone);
                            internalOfficer.setMobileNumber(mobileNumber);
                            internalOfficer.setEmailAddress(emailAddress);

                            Set internalOfficers = new HashSet();
                            internalOfficers.add(internalOfficer);

                            updateUser.setInternalOfficers(internalOfficers);


                        } else if (userType != null && userType.equals(BackendConstants.ADMIN)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.ADMIN_ID));

                            Admin admin = new Admin();
                            admin.setUser(updateUser);
                            admin.setFirstName(firstName);
                            admin.setLastName(lastName);
                            admin.setOfficeTelephone(officeTelephone);
                            admin.setMobileNumber(mobileNumber);
                            admin.setEmaillAddress(emailAddress);

                            Set admins = new HashSet();
                            admins.add(admin);


                            updateUser.setAdmins(admins);
                        }
                        updateUser.setUserType(userTypeObj);



                        String status = userDAO.updateUser(updateUser);



                        if (status.equals(BackendConstants.SUCCESS)) {
                            request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                    FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                            String requestURL =
                                    "user/userManagement.jsp?"
                                    + "userId=" + updateUser.getUsername();

                            //response.sendRedirect(requestURL);
                            requestDispatcher = request.getRequestDispatcher(requestURL);
                            requestDispatcher.forward(request, response);

                        } else if (status.equals(BackendConstants.ERROR)) {
                            requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                    FrontMessages.DATE_PROCESSING_ERROR);
                            requestDispatcher.forward(request, response);
                        } else {
                            requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                    FrontMessages.DATE_PROCESSING_ERROR);
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        ///////////////////////////////////////////////////////////


                        User deleteUser = new User();
                        deleteUser.setUsername(originalUsername);
                        String status = userDAO.deleteUser(deleteUser);

                        User addUser = new User();
                        addUser.setUsername(username);
                        addUser.setPassword(password);
                        addUser.setUserStatus(true);
                        UserType userTypeObj = new UserType();

                        if (userType != null && userType.equals(BackendConstants.MEMBER)) {
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

                        } else if (userType != null && userType.equals(BackendConstants.HOTEL_OFFICER)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.HOTEL_OFFICER_ID));
                            String hotelId = request.getParameter("hotelId");
                            HotelDAO hotelDAO = new HotelDAOImpl();
                            Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));
                            HotelOfficer hotelOfficer = new HotelOfficer();
                            hotelOfficer.setHotel(hotel);
                            hotelOfficer.setUser(addUser);
                            hotelOfficer.setFirstName(firstName);
                            hotelOfficer.setLastName(lastName);
                            hotelOfficer.setOfficeTelephone(officeTelephone);
                            hotelOfficer.setMobileNumber(mobileNumber);
                            hotelOfficer.setEmailAddress(emailAddress);
                            Set hotelOfficers = new HashSet();
                            hotelOfficers.add(hotelOfficer);
                            addUser.setHotelOfficers(hotelOfficers);

                        } else if (userType != null && userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.INTERNAL_OFFICER_ID));
                            InternalOfficer internalOfficer = new InternalOfficer();
                            internalOfficer.setUser(addUser);
                            internalOfficer.setFirstName(firstName);
                            internalOfficer.setLastName(lastName);
                            internalOfficer.setOfficeTelephone(officeTelephone);
                            internalOfficer.setMobileNumber(mobileNumber);
                            internalOfficer.setEmailAddress(emailAddress);
                            Set internalOfficers = new HashSet();
                            internalOfficers.add(internalOfficer);
                            addUser.setInternalOfficers(internalOfficers);

                        } else if (userType != null && userType.equals(BackendConstants.ADMIN)) {
                            userTypeObj.setUserTypeId(new Integer(BackendConstants.ADMIN_ID));
                            Admin admin = new Admin();
                            admin.setUser(addUser);
                            admin.setFirstName(firstName);
                            admin.setLastName(lastName);
                            admin.setOfficeTelephone(officeTelephone);
                            admin.setMobileNumber(mobileNumber);
                            admin.setEmaillAddress(emailAddress);
                            Set admins = new HashSet();
                            admins.add(admin);
                            addUser.setAdmins(admins);
                        }
                        addUser.setUserType(userTypeObj);

                        status = userDAO.addUser(addUser);

                        if (status.equals(BackendConstants.SUCCESS)) {
                            request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                    FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                            String requestURL =
                                    "user/userManagement.jsp?" + "userId=" + addUser.getUsername();
                            requestDispatcher = request.getRequestDispatcher(requestURL);
                            requestDispatcher.forward(request, response);

                        } else if (status.equals(BackendConstants.ERROR)) {
                            requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                    FrontMessages.DATE_PROCESSING_ERROR);
                            requestDispatcher.forward(request, response);
                        } else {
                            requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                            request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                    FrontMessages.DATE_PROCESSING_ERROR);
                            requestDispatcher.forward(request, response);
                        }
                    }
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/updateUser.jsp");
                    requestDispatcher.forward(request, response);
                }

            } else if (actionType.equals("deleteUser")) {

                RequestDispatcher requestDispatcher = null;

                User deleteUser = new User();
                deleteUser.setUsername(username);

                UserDAO userDAO = new UserDAOImpl();

                String status = userDAO.deleteUser(deleteUser);

                if (status.equals(BackendConstants.SUCCESS)) {
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_DELETED_SUCCESSFULLY);
                    String requestURL =
                            "user/userManagement.jsp?"
                            + "userId=" + deleteUser.getUsername();

                    //response.sendRedirect(requestURL);
                    requestDispatcher = request.getRequestDispatcher(requestURL);
                    requestDispatcher.forward(request, response);

                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/user/userManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                }


            }


        } else { // Ilegale Access
            Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * Validates the necessary arguments
     *
     * @param request HttpServletRequest request
     * @param username String representation of user name
     * @param firstName String representation of first name
     * @param lastName String representation of last name
     * @param emailAddress String representation of email address
     * @param userType String representation of user type
     * @return If all the arguments are valid return true otherwise return false
     */
    private boolean validateRequiredFields(HttpServletRequest request,
            String username, String firstName, String lastName, String emailAddress, String userType) {
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
        } else if (!validateEmail(emailAddress)) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.EMAIL_REQUIRED);
        } else if (userType != null && userType.length() == 0) {
            isValidated = false;
            request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.USER_TYPE_REQUIRED);
        }
        return isValidated;
    }

    private boolean validateEmail(String email) {
        if(email!=null && email.length()>0){
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        }
        else{
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
