package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.DistrictDetail;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * The DistrictDetailDAOImpl is the direct implementation of the
 * DistrictDetailDAO interface. It overrides all the two methods of
 * DistrictDetailDAO interface to deal with the DistrictDetail database.
 *
 * @author Duminda
 */
public class DistrictDetailDAOImpl implements DistrictDetailDAO {

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
     * Searches the database for a given district name and returns the
     * DistrictDetail. If the DistrictDetail is not found in the database then a
     * null pointer will be returned.
     *
     * @param districtName a String representation of the district name.
     * @return a DistrictDetail object if the DistrictDetail is found otherwise
     * returns a null pointer.
     */
    @Override
    public DistrictDetail viewDistrict(String districtName) {
        LOG_MESSAGE = " public DistrictDetail viewDistrict(String districtName) : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            try {
                String HQLQuery = "FROM DistrictDetail d WHERE d.districtName=:districtName";
                Query loginQuery = session.createQuery(HQLQuery);
                loginQuery.setString("districtName", districtName);
                DistrictDetail districtDetail = (DistrictDetail) loginQuery.uniqueResult();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                // Close Session object
                return districtDetail;
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
     * Searches the database for a given district name and returns the
     * DistrictDetail. If the DistrictDetail is not found in the database then a
     * null pointer will be returned.
     *
     * @param districtID a Integer representation of the district id.
     * @return a DistrictDetail object if the DistrictDetail is found otherwise
     * returns a null pointer.
     */
    @Override
    public DistrictDetail viewDistrict(Integer districtID) {
        LOG_MESSAGE = " public DistrictDetail viewDistrict(Integer districtID) : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();
        if (session != null) {

            try {
                String HQLQuery = "FROM DistrictDetail d WHERE d.districtId=:districtID";
                Query loginQuery = session.createQuery(HQLQuery);
                loginQuery.setInteger("districtID", districtID);
                DistrictDetail districtDetail = (DistrictDetail) loginQuery.uniqueResult();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                // Close Session object
                return districtDetail;
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
}
