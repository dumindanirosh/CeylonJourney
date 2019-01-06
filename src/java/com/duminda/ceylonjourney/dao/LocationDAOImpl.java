package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.controller.hotels.HotelImageUploaderServlet;
import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.HotLocation;
import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationImage;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The LocationDAOImpl is the direct implementation of the LocationDAO
 * interface. It overrides all the thirteen methods of LocationDAO interface to
 * deal with the CityDetail database.
 *
 * @author Duminda
 */
public class LocationDAOImpl implements LocationDAO {

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
     * Adds a new Location object to the database.
     *
     * @param location an absolute Location object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addNewLocation(Location location) {

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.save(location);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = location.getLocationName() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

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
     * Adds a new LocationImage object to the database.
     *
     * @param locationImage an absolute LocationImage object which is going to
     * be added.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addNewLocationImages(LocationImage locationImage) {

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            // Begin Transaction Object
            transaction = session.beginTransaction();

            // Save Location Details 
            session.save(locationImage);
            session.flush();
            transaction.commit();

            // Create Log4j Message
            LOG_MESSAGE = BackendConstants.LOCATION_IMAGES_ADDED;

            // Write into Log4j File
            Log4jUtil.logInfoMessage(LOG_MESSAGE);

            return BackendConstants.DATA_ADDED_SUCCESSFULLY;

        } else { // Session Object Null
        }

        return null;

    }

    /**
     * Update an existing Location object to the database.
     *
     * @param location an absolute Location object which is going to be updated.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateLocation(Location location) {


        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {

                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.update(location);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = location.getLocationName() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();

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
     * Reads all the Location objects from the database and return them to the
     * user.
     *
     * @return returns a List of Locations.
     * @see List
     */
    @Override
    public List<Location> loadAllLocations() {

        LOG_MESSAGE = "public List<Location> loadAllLocations() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Location l ";

                Query loginQuery = session.createQuery(HQLQuery);

                List<Location> locationsList = loginQuery.list();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return locationsList;

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
     * Adds a new LocationWelcomeImage object to an existing Location in the
     * database.
     *
     * @param location an absolute Location object whose WelcomeImage is going
     * to be added.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addLocationWelcomeImage(Location location) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {

                Location locationTmp = (Location) session.get(Location.class, location.getLocationId());

                locationTmp.setWelcomeImageName(location.getWelcomeImageName());
                locationTmp.setWelcomeImageUrl(location.getWelcomeImageUrl());

                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.update(locationTmp);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = location.getLocationName() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();

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
     * Reads all the active Location objects from the database and return them
     * to the user.
     *
     * @return returns a List of active Locations.
     * @see List
     */
    @Override
    public List<Location> loadAllActiveLocations() {

        LOG_MESSAGE = "public List<Location> loadAllActiveLocations() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Location l WHERE l.locationStatus=1";

                Query loginQuery = session.createQuery(HQLQuery);

                List<Location> locationsList = loginQuery.list();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return locationsList;

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
     * Searches the database for a given location id and if it is an active
     * location then returns the Location. If the Location is not found in the
     * database or if it is a non-active location then a null pointer will be
     * returned.
     *
     * @param locationId a String representation of the location ID.
     * @return a Location object if the Location is found and it is an active
     * Location otherwise returns a null pointer.
     */
    @Override
    public Location loadSelectedActiveLocation(String locationId) {

        LOG_MESSAGE = "public Location loadSelectedActiveLocation(String locationId) : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Location l WHERE l.locationStatus =1 AND l.locationId='" + locationId + "'";

                Query loginQuery = session.createQuery(HQLQuery);

                Location location = (Location) loginQuery.uniqueResult();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return location;

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
     * Searches the database for a given location id and if it is found then
     * returns the Location. If the Location is not found in the database then a
     * null pointer will be returned.
     *
     * @param locationId a String representation of the location ID.
     * @return a Location object if the Location is found otherwise returns a
     * null pointer.
     */
    @Override
    public Location loadSelectedLocation(String locationId) {
        LOG_MESSAGE = "public Location loadSelectedLocation(String locationId) : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Location l WHERE l.locationId='" + locationId + "'";

                Query loginQuery = session.createQuery(HQLQuery);

                Location location = (Location) loginQuery.uniqueResult();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return location;

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
     * Deletes an existing Location object from the database.
     *
     * @param location an absolute Location object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteLocation(Location location) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            // Begin Transaction Object
            transaction = session.beginTransaction();

            // Save Location Details 
            session.delete(location);
            session.flush();
            transaction.commit();

            // Create Log4j Message
            LOG_MESSAGE = BackendConstants.LOCATION_IMAGES_DELETED;

            // Write into Log4j File
            Log4jUtil.logInfoMessage(LOG_MESSAGE);

            return BackendConstants.SUCCESS;

        } else { // Session Object Null
        }

        return null;
    }

    /**
     * Reads all the non-active Location objects from the database and return
     * them to the user.
     *
     * @return returns a List of non-active Locations.
     * @see List
     */
    @Override
    public List<Location> loadNonActiveLocations() {
        LOG_MESSAGE = "public List<Location> loadNonActiveLocations() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Location l WHERE l.locationStatus=0";

                Query loginQuery = session.createQuery(HQLQuery);

                List<Location> locationsList = loginQuery.list();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return locationsList;

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
     * Searches the database for given search type and keyword, and returns a
     * List of Locations which are matched to the keyword.
     *
     * @param searchType a String representation of searchType. Which is
     * maintained in BackendConstants class.
     * @param keyword a String keyword which is typed by the user of this system
     * in-order to search for a location.
     * @return a List of Locations which are matched to the search type and
     * keyword.
     * @see List
     * @see BackendConstants
     */
    @Override
    public List<Location> searchLocations(String searchType, String keyword) {
        LOG_MESSAGE = "public List<Location> searchLocations(String searchType, String keyword) : ";

        if (searchType == null || keyword == null) {
            // Write info Log4j File
            Log4jUtil.logFatalMessage(LOG_MESSAGE.concat("Null searchType or keyword"));
            return null;
        } else {
            // Retrieve Hibernate Session Object
            session = CeylonHibernateUtil.getSessionFactory().openSession();
            if (session != null) {
                String HQLQuery = "";
                if (searchType.equals(BackendConstants.SEARCH_BY_LOCATION_NAME)) {
                    HQLQuery = "FROM Location l WHERE l.locationStatus=1 AND l.locationName LIKE '" + keyword + "%'";
                } else if (searchType.equals(BackendConstants.SEARCH_BY_LOCATION_DESCRIPTION)) {
                    HQLQuery = "FROM Location l WHERE l.locationStatus=1 AND l.locationDescription LIKE '" + keyword + "%'";
                } else if (searchType.equals(BackendConstants.SEARCH_BY_LOCATION_CITY)) {
                    //HQLQuery = "FROM Location l WHERE l.locationStatus=1 AND l.cityName LIKE '" + keyword + "%'";
                    HQLQuery = "FROM CityDetail c WHERE c.cityStatus=1 AND c.cityName LIKE '" + keyword + "%'";

                    try {
                        Query loginQuery = session.createQuery(HQLQuery);
                        List<CityDetail> cityList = loginQuery.list();
                        List<Location> locationsList = new ArrayList<Location>();

                        Iterator<CityDetail> iterator = cityList.iterator();
                        while (iterator.hasNext()) {
                            CityDetail cityDetail = iterator.next();
                            locationsList.addAll(cityDetail.getLocations());
                        }
                        // Write info Log4j File
                        Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                        // Close Session object
                        return locationsList;
                    } catch (Exception ex) {
                        // Write info Log4j File
                        Log4jUtil.logFatalMessage(LOG_MESSAGE.concat(ex.toString()));
                        return null;
                    } finally {
                        if (session != null) {
                            session.close();
                        }
                    }
                }

                try {
                    Query loginQuery = session.createQuery(HQLQuery);
                    List<Location> locationsList = loginQuery.list();
                    // Write info Log4j File
                    Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));
                    // Close Session object
                    return locationsList;
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

    /**
     * Adds an existing Location as a hot location.
     *
     * @param locationId a String representation of location Id of an existing
     * Location
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String setHotLocation(String locationId) {

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {


                try {
                    String HQLQuery = "FROM HotLocation h";
                    Query loginQuery = session.createQuery(HQLQuery);
                    List<HotLocation> oldHotLocationList = loginQuery.list();
                    transaction = session.beginTransaction();
                    for(HotLocation h : oldHotLocationList){
                    session.delete(h);
                    session.flush();
                    }
                    transaction.commit();
                } catch (Exception e) {
                }

                // Begin Transaction Object
                transaction = session.beginTransaction();
                HotLocation hotLocation = new HotLocation();
                hotLocation.setLocationId(Integer.parseInt(locationId));
                // Save Location Details 
                session.save(hotLocation);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = hotLocation.getLocationId() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

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
     * Reads the database and return the current hot location.
     *
     * @return The current hot location
     */
    @Override
    public Location getHotLocation(){
        LOG_MESSAGE = "public Location getHotLocation() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM HotLocation h";

                Query loginQuery = session.createQuery(HQLQuery);

                HotLocation hotLocation = (HotLocation) loginQuery.uniqueResult();
                String locationId = Integer.toString(hotLocation.getLocationId());
                
                HQLQuery = "FROM Location l WHERE l.locationId='" + locationId + "'";

                Query locationQuery = session.createQuery(HQLQuery);

                Location location = (Location) locationQuery.uniqueResult();
                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return location;

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
