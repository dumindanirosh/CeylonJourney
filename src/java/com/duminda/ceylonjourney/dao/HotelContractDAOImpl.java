package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.HotelContract;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The HotelContractDAOImpl is the direct implementation of the HotelContractDAO interface. It
 * overrides all three methods of HotelContractDAO interface to deal with the Hotel
 * database.
 *
 * @author Duminda
 */
public class HotelContractDAOImpl implements HotelContractDAO{
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
     * Add a new HotelContract object in the database.
     *
     * @param hotelContract an absolute HotelContract object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addContract(HotelContract hotelContract){
        LOG_MESSAGE = new StringBuffer("public String addContract(HotelContract hotelContract) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.save(hotelContract);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                String infoMsg = hotelContract.getHotelId() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;
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
     * Update an existing HotelContract object in the database.
     *
     * @param hotelContract an absolute HotelContract object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateContract(HotelContract hotelContract){
        LOG_MESSAGE = new StringBuffer("public String updateContract(HotelContract hotelContract) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Transaction transaction = null;
            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();
                // Save Location Details 
                session.update(hotelContract);
                session.flush();
                transaction.commit();
                // Create Log4j Message
                String infoMsg = hotelContract.getHotelId() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;
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
     * Search the database for a given hotel id and returns the HotelContract. If the
     * HotelContract is not found in the database then a null pointer will be returned.
     *
     * @param hotelId a int representation of the Hotel ID.
     * @return if the HotelContract is found return a HotelContract object; otherwise returns a
     * null pointer.
     */
    @Override
    public HotelContract getContract(String hotelId){
        LOG_MESSAGE = new StringBuffer("public HotelContract getContract(String hotelId) : ");
       
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {
            try {
                String HQLQuery = "FROM HotelContract h WHERE h.hotelId="+hotelId;
                Query selectQuery = session.createQuery(HQLQuery);
                HotelContract hotelContract = (HotelContract) selectQuery.uniqueResult();
                
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                // Close Session object
                return hotelContract;

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
