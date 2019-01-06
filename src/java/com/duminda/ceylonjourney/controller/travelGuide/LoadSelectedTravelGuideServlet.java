package com.duminda.ceylonjourney.controller.travelGuide;

import com.duminda.ceylonjourney.dao.TravelGuideDAO;
import com.duminda.ceylonjourney.dao.TravelGuideDAOImpl;
import com.duminda.ceylonjourney.model.TravelGuide;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Whenever a user select a travel guide in the Update TravelGuide wizard or in
 * Delete TravelGuide wizard the system needs to load all the relevant details
 * about that travel guide. This servlet is created to provides those necessary
 * details of a selected travel guide.
 *
 * @author Duminda
 */
public class LoadSelectedTravelGuideServlet extends HttpServlet {

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
            String travelGuideID = request.getParameter("travelGuideID");
            TravelGuideDAO travelGuideDAO = new TravelGuideDAOImpl();
            TravelGuide travelGuide = travelGuideDAO.viewGuide(travelGuideID);
            request.setAttribute(BackendConstants.SELECTED_GUIDE, travelGuide);
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
