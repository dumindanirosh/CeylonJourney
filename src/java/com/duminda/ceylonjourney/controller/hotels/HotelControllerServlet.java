package com.duminda.ceylonjourney.controller.hotels;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.HotelDAO;
import com.duminda.ceylonjourney.dao.HotelDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelType;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import com.duminda.ceylonjourney.util.Utilities;
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
 * This servlet manages the add, update and delete of the Hotels.
 *
 * @author Duminda
 */
public class HotelControllerServlet extends HttpServlet {

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
            if (actionType != null && actionType.equals("addHotel")) {
                String hotelName = request.getParameter("hotelName");
                String hotelDescription = request.getParameter("hotelDescription");
                String hotelAddress = request.getParameter("hotelAddress");
                String hotelEmailAddress = request.getParameter("hotelEmailAddress");
                String hotelStatus = request.getParameter("hotelStatus");
                String hotelType = request.getParameter("hotelType");
                String hotelTypeStatus = request.getParameter("hotelTypeStatus");


                String cityId = request.getParameter("cityId");
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                CityDetail cityDetail = cityDetailDAO.viewCity(cityId);

                Byte hotelStatusByte = 1;
                Byte hotelTypeStatusByte = 1;
                if (hotelStatus == null) {
                    hotelStatusByte = 0;
                }
                if (hotelTypeStatus == null) {
                    hotelTypeStatusByte = 0;
                }


                if (hotelName == null || hotelName.length() == 0) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.HOTEL_NAME_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else if (cityDetail == null) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.CITY_IS_NOT_VALID);
                    requestDispatcher.forward(request, response);
                } else if (!validateEmail(hotelEmailAddress)) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.EMAIL_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else {

                    Hotel hotel = new Hotel();
                    hotel.setHotelName(hotelName);
                    hotel.setHotelDescription(hotelDescription);
                    hotel.setHotelAddress(hotelAddress);
                    hotel.setEmailAddress(hotelEmailAddress);
                    hotel.setEnteredBy(user.getUsername());
                    hotel.setEnteredDate(Utilities.getTime());
                    hotel.setHotelStatus(hotelStatusByte);

                    Set<CityDetail> listCity = new HashSet<CityDetail>();
                    listCity.add(cityDetail);
                    hotel.setCityDetails(listCity);


                    HotelType hotelTypeObject = new HotelType();
                    hotelTypeObject.setHotelTypeStatus(hotelTypeStatusByte);
                    hotelTypeObject.setTypeName(hotelType);
                    Set<Hotel> hotelSet = new HashSet<Hotel>();
                    hotelSet.add(hotel);
                    hotelTypeObject.setHotels(hotelSet);

                    Set<HotelType> hotelTypeSet = new HashSet<HotelType>();
                    hotelTypeSet.add(hotelTypeObject);

                    hotel.setHotelTypes(hotelTypeSet);


                    HotelDAO hotelDAO = new HotelDAOImpl();

                    String status = hotelDAO.addHotel(hotel);

                    RequestDispatcher requestDispatcher = null;

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_ADDED_SUCCESSFULLY);
                        String requestURL = request.getContextPath()
                                + "/hotel/hotelImageUpload.jsp?"
                                + "hotelId=" + hotel.getHotelId();

                        response.sendRedirect(requestURL);

                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }



            } else if (actionType.equals("updateHotel")) {
                String hotelId = request.getParameter("hotelId");

                String hotelName = request.getParameter("hotelName");
                String hotelDescription = request.getParameter("hotelDescription");
                String hotelAddress = request.getParameter("hotelAddress");
                String hotelEmailAddress = request.getParameter("hotelEmailAddress");
                String hotelStatus = request.getParameter("hotelStatus");
                String hotelType = request.getParameter("hotelType");
                String hotelTypeStatus = request.getParameter("hotelTypeStatus");

                String cityId = request.getParameter("cityId");
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                CityDetail cityDetail = cityDetailDAO.viewCity(cityId);

                Byte hotelStatusByte = 1;


                if (hotelStatus == null) {
                    hotelStatusByte = 0;
                }
                if (hotelStatus == null) {
                    hotelStatusByte = 0;
                }

                if (hotelName == null || hotelName.length() == 0) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.HOTEL_NAME_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else if (cityDetail == null) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.CITY_IS_NOT_VALID);
                    requestDispatcher.forward(request, response);

                } else if (!validateEmail(hotelEmailAddress)) {
                    RequestDispatcher requestDispatcher = null;
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.EMAIL_REQUIRED);
                    requestDispatcher.forward(request, response);
                } else {
                    HotelDAO hotelDAO = new HotelDAOImpl();

                    Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));
                    hotel.setHotelName(hotelName);
                    hotel.setHotelDescription(hotelDescription);
                    hotel.setHotelAddress(hotelAddress);
                    hotel.setEmailAddress(hotelEmailAddress);
                    hotel.setEnteredBy(user.getUsername());
                    hotel.setEnteredDate(Utilities.getTime());


                    Set<CityDetail> listCity = new HashSet<CityDetail>();
                    listCity.add(cityDetail);
                    hotel.setCityDetails(listCity);

                    hotel.setHotelStatus(hotelStatusByte);

                    String status = hotelDAO.updateHotel(hotel);

                    RequestDispatcher requestDispatcher = null;

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                        String requestURL = request.getContextPath()
                                + "/hotel/hotelImageUpload.jsp?"
                                + "hotelId=" + hotel.getHotelId();
                        response.sendRedirect(requestURL);
                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }
                }

            } else if (actionType.equals("deleteHotel")) {

                String hotelId = request.getParameter("hotelId");

                HotelDAO hotelDAO = new HotelDAOImpl();
                Hotel hotel = hotelDAO.viewSelectedHotel(Integer.parseInt(hotelId));

                String status = hotelDAO.deleteHotel(hotel);

                RequestDispatcher requestDispatcher = null;

                if (status.equals(BackendConstants.SUCCESS)) {

                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_DELETED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);

                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/hotel/hotelManagement.jsp");
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
