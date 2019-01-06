package com.duminda.ceylonjourney.city;

import com.duminda.ceylonjourney.dao.CityDetailDAO;
import com.duminda.ceylonjourney.dao.CityDetailDAOImpl;
import com.duminda.ceylonjourney.dao.DistrictDetailDAO;
import com.duminda.ceylonjourney.dao.DistrictDetailDAOImpl;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.DistrictDetail;
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
 * The servlet for update, add and delete function of CityDetails
 *
 * @author Duminda
 */
public class CityControllerServlet extends HttpServlet {

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

        if (user != null && !user.getUserType().getTypeName().equals(BackendConstants.MEMBER)) {

            String actionType = request.getParameter("actionType");
            String returnPlace = request.getParameter("returnPlace");
            String cityName = request.getParameter("cityName");
            String districtName = request.getParameter("districtName");
            String cityStatus = request.getParameter("cityStatus");
            String cityId = request.getParameter("cityId");
            Byte staus = 1;
            if (cityStatus == null) {
                staus = 0;
            }
            boolean isValidated = false;
            if (cityName != null && cityName.length() != 0) {
                isValidated = true;
            }
            if (isValidated && actionType.equals("addCity")) {
                CityDetail cityDetail = new CityDetail();
                cityDetail.setCityName(cityName);
                cityDetail.setCityStatus(staus);
                DistrictDetailDAO districtDetailDAO = new DistrictDetailDAOImpl();
                DistrictDetail districtDetail = districtDetailDAO.viewDistrict(districtName);
                cityDetail.setDistrictDetail(districtDetail);
                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                String status = cityDetailDAO.addNewCity(cityDetail);
                RequestDispatcher requestDispatcher = null;

                if (returnPlace != null && returnPlace.equals("addLocation") && status.equals(BackendConstants.SUCCESS)) {
                    requestDispatcher = request.getRequestDispatcher("/loadCityForLocation.action?actionType=addLocation");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_ADDED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);
                } else if (returnPlace != null && returnPlace.equals("addHotel") && status.equals(BackendConstants.SUCCESS)) {
                    requestDispatcher = request.getRequestDispatcher("/loadCityForLocation.action?actionType=addHotel");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_ADDED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);
                } else {
                    if (status.equals(BackendConstants.SUCCESS)) {

                        requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                                FrontMessages.DATA_ADDED_SUCCESSFULLY);
                        requestDispatcher.forward(request, response);
                    } else if (status.equals(BackendConstants.ERROR)) {
                        requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    } else {
                        requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                        request.setAttribute(BackendConstants.ERROR_MESSAGE,
                                FrontMessages.DATE_PROCESSING_ERROR);
                        requestDispatcher.forward(request, response);
                    }

                }
            } else if (isValidated && actionType.equals("updateCity")) {

                CityDetail cityDetail = new CityDetail();
                cityDetail.setCityName(cityName);
                cityDetail.setCityStatus(staus);
                cityDetail.setCityId(Integer.parseInt(cityId));

                DistrictDetailDAO districtDetailDAO = new DistrictDetailDAOImpl();
                DistrictDetail districtDetail = districtDetailDAO.viewDistrict(districtName);
                cityDetail.setDistrictDetail(districtDetail);

                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                String status = cityDetailDAO.updateCity(cityDetail);


                RequestDispatcher requestDispatcher = null;

                if (status.equals(BackendConstants.SUCCESS)) {

                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_UPDATED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);
                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                }

            } else if (isValidated && actionType.equals("deleteCity")) {
                CityDetail cityDetail = new CityDetail();
                cityDetail.setCityName(cityName);
                cityDetail.setCityStatus(staus);
                cityDetail.setCityId(Integer.parseInt(cityId));

                DistrictDetailDAO districtDetailDAO = new DistrictDetailDAOImpl();
                DistrictDetail districtDetail = districtDetailDAO.viewDistrict(districtName);
                cityDetail.setDistrictDetail(districtDetail);

                CityDetailDAO cityDetailDAO = new CityDetailDAOImpl();
                String status = cityDetailDAO.deleteCity(cityDetail);


                RequestDispatcher requestDispatcher = null;

                if (status.equals(BackendConstants.SUCCESS)) {

                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.SUCCESS_MESSAGE,
                            FrontMessages.DATA_DELETED_SUCCESSFULLY);
                    requestDispatcher.forward(request, response);
                } else if (status.equals(BackendConstants.ERROR)) {
                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                    request.setAttribute(BackendConstants.ERROR_MESSAGE,
                            FrontMessages.DATE_PROCESSING_ERROR);
                    requestDispatcher.forward(request, response);
                }
            } else {
                RequestDispatcher requestDispatcher = null;
                requestDispatcher = request.getRequestDispatcher("/city/cityManagement.jsp");
                request.setAttribute(BackendConstants.ERROR_MESSAGE,
                        FrontMessages.CITY_NAME_REQUIRED);
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
