package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.dao.LocationCommentDAO;
import com.duminda.ceylonjourney.dao.LocationCommentDAOImpl;
import com.duminda.ceylonjourney.dao.LocationDAO;
import com.duminda.ceylonjourney.dao.LocationDAOImpl;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.FrontMessages;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.io.IOException;
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
 * This servlet handles all the operations of LocatonComment which try to access
 * database.
 *
 * @author Duminda
 */
public class LocationCommentControllerServlet extends HttpServlet {

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


        String commentId = request.getParameter("commentId");
        String locationId = request.getParameter("locationId");
        String locationComment = request.getParameter("locationComment");
        String actionType = request.getParameter("actionType");
        String commentStatus = request.getParameter("commentStatus");
        LocationComment comment = new LocationComment();
        LocationCommentDAO locationCommentDAO = new LocationCommentDAOImpl();

        if (actionType != null && actionType.equals("addComment")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewSelectedLocation.action?actionType=" + BackendConstants.LOCATION_FOR_HOME_VIEW);
            if (isValidate(request, locationId, locationComment)) {
                comment.setLocationId(Integer.parseInt(locationId));
                comment.setComment(locationComment);
                comment.setCommentStatus(0);

                String status = locationCommentDAO.addComment(comment);
                //request.setAttribute("actionType", BackendConstants.LOCATION_FOR_HOME_VIEW);
                request.setAttribute("message", status);
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher.forward(request, response);
            }
        } else {



            HttpSession session = request.getSession();

            User user = (User) session.getAttribute(BackendConstants.USER);

            if (user != null) {

                RequestDispatcher requestDispatcher = null;

                String userType = user.getUserType().getTypeName();

                if (actionType != null && actionType.equals("approveComment")) {
                    comment.setCommentId(Integer.parseInt(commentId));
                    comment.setLocationId(Integer.parseInt(locationId));
                    comment.setComment(locationComment);
                    comment.setCommentStatus(1);
                    String status = locationCommentDAO.updateComment(comment);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.COMMENT_APPROVED_SUCCESSFULLY);
                    } else if (status.equals(BackendConstants.ERROR)) {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    }

                    if (userType.equals(BackendConstants.MEMBER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/memberDashboard.jsp");
                    }// Administrator of System 
                    else if (userType.equals(BackendConstants.ADMIN)) {
                        List<LocationComment> commentList = locationCommentDAO.getNonApprovedComments();
                        LocationDAO locationDAO = new LocationDAOImpl();
                        Map<LocationComment, Location> map = new HashMap<LocationComment, Location>();
                        for (LocationComment lc : commentList) {
                            map.put(lc, locationDAO.loadSelectedActiveLocation(Integer.toString(lc.getLocationId())));
                        }
                        request.setAttribute(BackendConstants.LOCATION_COMMENT_MAP, map);
                        requestDispatcher = request.getRequestDispatcher("/dashboard/adminDashboard.jsp");
                    } // Internal Officer of System 
                    else if (userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/internalOfficer.jsp");
                    } // Hotel Officer of System
                    else if (userType.equals(BackendConstants.HOTEL_OFFICER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/hotelOfficer.jsp");
                    } // Not matching User type
                    else {
                        requestDispatcher = request.getRequestDispatcher("/index.jsp");
                    }
                    requestDispatcher.forward(request, response);

                } else if (actionType != null && actionType.equals("deleteComment")) {
                    comment.setCommentId(Integer.parseInt(commentId));
                    comment.setLocationId(Integer.parseInt(locationId));
                    comment.setComment(locationComment);
                    if (commentStatus == null) {
                        comment.setCommentStatus(0);
                    } else {
                        comment.setCommentStatus(1);
                    }
                    String status = locationCommentDAO.deleteComment(comment);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.COMMENT_DELETED_SUCCESSFULLY);
                    } else if (status.equals(BackendConstants.ERROR)) {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    }

                    if (userType.equals(BackendConstants.MEMBER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/memberDashboard.jsp");
                    }// Administrator of System 
                    else if (userType.equals(BackendConstants.ADMIN)) {
                        List<LocationComment> commentList = locationCommentDAO.getNonApprovedComments();
                        LocationDAO locationDAO = new LocationDAOImpl();
                        Map<LocationComment, Location> map = new HashMap<LocationComment, Location>();
                        for (LocationComment lc : commentList) {
                            map.put(lc, locationDAO.loadSelectedActiveLocation(Integer.toString(lc.getLocationId())));
                        }
                        request.setAttribute(BackendConstants.LOCATION_COMMENT_MAP, map);
                        requestDispatcher = request.getRequestDispatcher("/dashboard/adminDashboard.jsp");
                    } // Internal Officer of System 
                    else if (userType.equals(BackendConstants.INTERNAL_OFFICER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/internalOfficer.jsp");
                    } // Hotel Officer of System
                    else if (userType.equals(BackendConstants.HOTEL_OFFICER)) {
                        requestDispatcher = request.getRequestDispatcher("/dashboard/hotelOfficer.jsp");
                    } // Not matching User type
                    else {
                        requestDispatcher = request.getRequestDispatcher("/index.jsp");
                    }
                    requestDispatcher.forward(request, response);

                } else if (actionType != null && actionType.equals("deletePublished")) {
                    comment.setCommentId(Integer.parseInt(commentId));
                    comment.setLocationId(Integer.parseInt(locationId));
                    comment.setComment(locationComment);
                    comment.setCommentStatus(1);

                    String status = locationCommentDAO.deleteComment(comment);

                    if (status.equals(BackendConstants.SUCCESS)) {
                        request.setAttribute(BackendConstants.SUCCESS_MESSAGE, FrontMessages.COMMENT_DELETED_SUCCESSFULLY);
                    } else if (status.equals(BackendConstants.ERROR)) {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    } else {
                        request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.DATE_PROCESSING_ERROR);
                    }
                    requestDispatcher = request.getRequestDispatcher("/viewPublishedComments.action?actionType=publishedComments");
                    requestDispatcher.forward(request, response);

                } else {
                    request.setAttribute(BackendConstants.ERROR_MESSAGE, FrontMessages.ILLEGAL_ACTION);
                }

            } else {
                Log4jUtil.logWarnMessage(BackendConstants.ILLEGAL_ACCESS + this.getServletName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("illegalAccess.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    /**
     * Check the validity of the parameters
     *
     * @param request HttpServletRequest
     * @param locationId String representation of location id
     * @param locationComment String representation of location comment
     * @return return true if all the parameters are valid, otherwise return
     * false.
     */
    private boolean isValidate(HttpServletRequest request, String locationId, String locationComment) {
        boolean isValidate = true;
        if (locationId == null || locationId.equals("")) {
            isValidate = false;
            request.setAttribute("message",
                    "Invalid Location");
        }
        if (locationComment == null || locationComment.equals("")) {
            isValidate = false;
            request.setAttribute("message",
                    "Please enter any comments");
        }
        return isValidate;
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
