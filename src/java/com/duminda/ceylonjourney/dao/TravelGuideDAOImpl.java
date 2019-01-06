package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.TravelGuide;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The TravelGuideDAOImpl is the direct implementation of the TravelGuideDAO
 * interface. It overrides all the five methods of TravelGuideDAO interface to
 * deal with the TravelGuide database.
 *
 * @author Duminda
 */
public class TravelGuideDAOImpl implements TravelGuideDAO {

    /**
     * Hibernate Session Object which provides the functions for database
     * handling. The initial value is null.
     */
    private Session session = null;
    /**
     * A String reference which is used to store Log messages and finally it
     * will passes to Log4jUtil object.
     */
    private String LOG_MESSAGE;

    /**
     * Adds a new TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addTravelGuide(TravelGuide guide) {
        LOG_MESSAGE = "public String addTravelGuide(TravelGuide guide)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save User Details 
                session.save(guide);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = guide.getFirstName() + " GUIDE ID: " + guide.getTravelGuideId() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Updates an existing TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateTravelGuide(TravelGuide guide) {
        LOG_MESSAGE = "public String updateTravelGuide(TravelGuide guide)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {

                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save User Details 
                session.update(guide);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = guide.getFirstName() + " GUIDE ID: " + guide.getTravelGuideId() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Deletes an existing TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteTravelGuide(TravelGuide guide) {
        LOG_MESSAGE = "public String deleteTravelGuide(TravelGuide guide)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {

                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save User Details 
                session.delete(guide);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = guide.getFirstName() + " GUIDE ID: " + guide.getTravelGuideId() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Reads all the TravelGuide objects from the database and return them to
     * the user.
     *
     * @return returns a List of TravelGuides.
     * @see List
     */
    @Override
    public List<TravelGuide> viewAllTravelGuides() {
        LOG_MESSAGE = "public List<User> viewAllUsers() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM TravelGuide u ";

                Query loginQuery = session.createQuery(HQLQuery);

                List<TravelGuide> guideList = loginQuery.list();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return guideList;

            } catch (Exception ex) {
                // Write info Log4j File

                Log4jUtil.logFatalMessage(LOG_MESSAGE.concat(ex.toString()));

                return null;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Searches the database for a given travel guide id and returns the
     * TravelGuide. If the TravelGuide is not found in the database then a null
     * pointer will be returned.
     *
     * @param travelGuideID a String representation of the Travel guide ID.
     * @return a TravelGuide object if the TravelGuide is found otherwise
     * returns a null pointer.
     */
    @Override
    public TravelGuide viewGuide(String travelGuideID) {
        LOG_MESSAGE = "public TravelGuide viewGuide(String travelGuideID) : ";

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                TravelGuide travelGuide = (TravelGuide) session.get(TravelGuide.class, Integer.parseInt(travelGuideID));
                return travelGuide;

            } catch (Exception ex) {
                // Write info Log4j File
                LOG_MESSAGE = LOG_MESSAGE + ex.toString();
                Log4jUtil.logFatalMessage(LOG_MESSAGE);
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
}
