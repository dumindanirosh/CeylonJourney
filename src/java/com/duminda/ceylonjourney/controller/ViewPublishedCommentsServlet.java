/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.LocationCommentDAO;
import com.duminda.ceylonjourney.dao.LocationCommentDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet loads all the published comments and send them to the browser to
 * show them for the member
 *
 * @author Duminda
 */
public class ViewPublishedCommentsServlet extends HttpServlet {

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
        String actionType = request.getParameter("actionType");
        if (user != null && actionType!=null) {
            if (actionType != null && actionType.equals("publishedComments")) {
                LocationCommentDAO locationCommentDAO = new LocationCommentDAOImpl();
                List<LocationComment> commentList = locationCommentDAO.getAllApprovedComments();
                LocationDAO locationDAO = new LocationDAOImpl();
                Map<LocationComment, Location> map = new HashMap<LocationComment, Location>();
                for (LocationComment lc : commentList) {
                    map.put(lc, locationDAO.loadSelectedActiveLocation(Integer.toString(lc.getLocationId())));
                }
                request.setAttribute(BackendConstants.LOCATION_COMMENT_MAP, map);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/location/publishedComments.jsp");
                requestDispatcher.forward(request, response);

            }
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
