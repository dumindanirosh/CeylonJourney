package com.duminda.ceylonjourney.controller.travelGuide;

import com.duminda.ceylonjourney.dao.TravelGuideDAO;
import com.duminda.ceylonjourney.dao.TravelGuideDAOImpl;
import com.duminda.ceylonjourney.model.TravelGuide;
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
 * This servlet loads all the TravelGuides and pass them as a List of
 * TravelGuides for the Dashboard TravelGuide Management operations.
 *
 * @author Duminda
 */
public class LoadTravelGuideForUpdateDeleteServlet extends HttpServlet {

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
            TravelGuideDAO travelGuideDAO = new TravelGuideDAOImpl();

            List<TravelGuide> guideList = travelGuideDAO.viewAllTravelGuides();

            request.setAttribute(BackendConstants.GUIDE_LIST, guideList);

            String actionType = request.getParameter("actionType");

            RequestDispatcher requestDispatcher = null;

            if (actionType.equals(BackendConstants.UPDATE)) {
                requestDispatcher =
                        request.getRequestDispatcher("/travelGuide/updateTravelGuide.jsp");

            } else if (actionType.equals(BackendConstants.DELETE)) {
                requestDispatcher =
                        request.getRequestDispatcher("/travelGuide/deleteTravelGuide.jsp");

            } else if (actionType.equals(BackendConstants.VIEW)) {
                requestDispatcher =
                        request.getRequestDispatcher("/travelGuide/viewTravelGuide.jsp");

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
