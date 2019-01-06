package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The CityDetailDAOImpl is the direct implementation of the
 * CityDetailDAO interface. It overrides all the six methods of
 * CityDetailDAO interface to deal with the CityDetail database.
 *
 * @author Duminda
 */
public class CityDetailDAOImpl implements CityDetailDAO {

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
     * Adds a new CityDetail object in the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addNewCity(CityDetail cityDetail) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.save(cityDetail);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                LOG_MESSAGE = cityDetail.getCityName() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;
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
     * Updates an existing CityDetail object in the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateCity(CityDetail cityDetail) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.update(cityDetail);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                LOG_MESSAGE = cityDetail.getCityName() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;
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
     * Reads all the CityDetails object from the database and return them to the
     * user.
     *
     * @return returns a List of CityDetails.
     * @see List
     */
    @Override
    public List<CityDetail> loadAllCities() {
        LOG_MESSAGE = "public List<CityDetail> loadAllCities() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                String HQLQuery = "FROM CityDetail c ";
                Query loginQuery = session.createQuery(HQLQuery);
                List<CityDetail> cityList = loginQuery.list();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                // Close Session object
                return cityList;
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
     * Reads all the active CityDetails object from the database and return them
     * to the user.
     *
     * @return returns a List of active CityDetails.
     * @see List
     */
    @Override
    public List<CityDetail> loadAllActiveCities() {
        LOG_MESSAGE = "public List<CityDetail> loadAllActiveCities() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                String HQLQuery = "FROM CityDetail c WHERE c.cityStatus=1";
                Query loginQuery = session.createQuery(HQLQuery);
                List<CityDetail> cityList = loginQuery.list();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                // Close Session object
                return cityList;
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
     * Searches the database for a given city id and returns the CityDetail. If
     * the CityDetail is not found in the database then a null pointer will be
     * returned.
     *
     * @param cityID a String representation of the City ID.
     * @return a CityDetail object if the CityDetail is found otherwise returns
     * a null pointer.
     */
    @Override
    public CityDetail viewCity(String cityID) {
        LOG_MESSAGE = "public CityDetail viewCity(String cityID) : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                String HQLQuery = "FROM CityDetail c WHERE c.cityId=:cityID";
                Query loginQuery = session.createQuery(HQLQuery);
                loginQuery.setInteger("cityID", Integer.parseInt(cityID));
                CityDetail cityDetail = (CityDetail) loginQuery.uniqueResult();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                // Close Session object
                return cityDetail;
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
     * Deletes an existing CityDetail object from the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteCity(CityDetail cityDetail) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.delete(cityDetail);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                LOG_MESSAGE = cityDetail.getCityName() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;
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
}