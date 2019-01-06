package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.LocationComment;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The LocationCommentDAOImpl is the direct implementation of the LocationCommentDAO interface. It
 * overrides all the six methods of LocationCommentDAO interface to deal with the LocationComment
 * database.
 *
 * @author Duminda
 */
public class LocationCommentDAOImpl implements LocationCommentDAO {
    /**
     * Hibernate Session Object which provides the functions for database
     * handling. The initial value is null.
     */
    private Session session = null;
    /**
     * A StringBuffer reference which is used to store Log messages and finally
     * it will passes to Log4jUtil object.
     */
    private StringBuffer LOG_MESSAGE;
    
    /**
     * Add a new LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addComment(LocationComment locationComment){
        LOG_MESSAGE = new StringBuffer("public String addComment(LocationComment locationComment) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.save(locationComment);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                String infoMsg = locationComment.getLocationId() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;
                LOG_MESSAGE.append(infoMsg);
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);
                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(LOG_MESSAGE.append(BackendConstants.ROLLACK_COMPLETED));
                }
                return BackendConstants.ERROR;
            } finally {
                // close session object
                session.close();
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
    
    /**
     * Update an existing LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateComment(LocationComment locationComment){
        LOG_MESSAGE = new StringBuffer("public String addComment(LocationComment locationComment) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.update(locationComment);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                String infoMsg = locationComment.getLocationId() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;
                LOG_MESSAGE.append(infoMsg);
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);
                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(LOG_MESSAGE.append(BackendConstants.ROLLACK_COMPLETED));
                }
                return BackendConstants.ERROR;
            } finally {
                // close session object
                session.close();
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
    
    /**
     * Delete an existing LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteComment(LocationComment locationComment){
        LOG_MESSAGE = new StringBuffer("public String addComment(LocationComment locationComment) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.delete(locationComment);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                String infoMsg = locationComment.getLocationId() + " " + BackendConstants.DATA_DELETED_SUCCESSFULLY;
                LOG_MESSAGE.append(infoMsg);
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);
                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(LOG_MESSAGE.append(BackendConstants.ROLLACK_COMPLETED));
                }
                return BackendConstants.ERROR;
            } finally {
                // close session object
                session.close();
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
    
     /**
     * Search the database for a all the non approved comments.
     *
     * @return return a collection of LocationComments. If there are no more non-approved comments return null
     * @see List
     */
    @Override
    public List<LocationComment> getNonApprovedComments(){
        LOG_MESSAGE = new StringBuffer("public List<LocationComment> getNonApprovedComments(String locationId) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                String HQLQuery = "FROM LocationComment l WHERE l.commentStatus=0";
                Query selectQuery = session.createQuery(HQLQuery);
                List<LocationComment> locationComment = selectQuery.list();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                // Close Session object
                return locationComment;

            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
    
    /**
     * Search the database for a given location id and returns all the approved comments. If the
     * location id is not found in the database then a null pointer will be returned.
     *
     * @param locationId a String representation of the Location ID.
     * @return return a collection of LocationComments.
     * @see List
     */
    @Override
    public List<LocationComment> getApprovedComments(String locationId){
        LOG_MESSAGE = new StringBuffer("public List<LocationComment> getNonApprovedComments(String locationId) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                String HQLQuery = "FROM LocationComment l WHERE l.commentStatus=1 AND l.locationId="+locationId;
                Query selectQuery = session.createQuery(HQLQuery);
                List<LocationComment> locationComment = selectQuery.list();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                // Close Session object
                return locationComment;

            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
    
    /**
     * Search the database for a all the approved comments.
     *
     * @return return a collection of LocationComments. If there are no more approved comments return null
     * @see List
     */
    public List<LocationComment> getAllApprovedComments(){
        LOG_MESSAGE = new StringBuffer("public List<LocationComment> getNonApprovedComments(String locationId) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                String HQLQuery = "FROM LocationComment l WHERE l.commentStatus=1";
                Query selectQuery = session.createQuery(HQLQuery);
                List<LocationComment> locationComment = selectQuery.list();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                // Close Session object
                return locationComment;

            } catch (Exception ex) {
                // Write info Log4j File
                Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else { // Session Object Null
            LOG_MESSAGE.append(BackendConstants.SESSION_NULL);
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
}
