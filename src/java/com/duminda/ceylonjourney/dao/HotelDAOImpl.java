package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.CityDetail;
import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelContract;
import com.duminda.ceylonjourney.model.HotelImage;
import com.duminda.ceylonjourney.model.HotelType;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The HotelDAOImpl is the direct implementation of the HotelDAO interface. It
 * overrides all the nine methods of HotelDAO interface to deal with the Hotel
 * database.
 *
 * @author Duminda
 */
public class HotelDAOImpl implements HotelDAO {

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
     * Reads all the active Hotel object from the database and return them to
     * the user.
     *
     * @return returns a List of active Hotels.
     * @see List
     */
    @Override
    public List<Hotel> loadActiveHotels() {
        LOG_MESSAGE = new StringBuffer("public List<Hotel> loadActiveHotels() : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Hotel h WHERE h.hotelStatus='1'";

                Query selectQuery = session.createQuery(HQLQuery);

                List<Hotel> hotelList = selectQuery.list();
                List<Hotel> newHotelList = new ArrayList<Hotel>();
                HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();
                Date today = new Date();
                for (Hotel hotel : hotelList) {
                    HotelContract hotelContract = hotelContractDAO.getContract(hotel.getHotelId().toString());

                    if (hotelContract!=null && hotelContract.getEndDate().compareTo(today) >= 0) {
                        newHotelList.add(hotel);
                    }
                }

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));

                // Close Session object
                return newHotelList;

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
     * Add a new Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addHotel(Hotel hotel) {

        LOG_MESSAGE = new StringBuffer("public String addHotel(Hotel hotel) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.save(hotel);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                String infoMsg = hotel.getHotelName() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

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
     * Update an existing Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateHotel(Hotel hotel) {
        LOG_MESSAGE = new StringBuffer("public String updateHotel(Hotel hotel) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.update(hotel);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                String infoMsg = hotel.getHotelName() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

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
     * Search the database for a given hotel id and returns the hotel. If the
     * Hotel is not found in the database then a null pointer will be returned.
     *
     * @param hotelId a int representation of the Hotel ID.
     * @return if the Hotel is found return a Hotel object; otherwise returns a
     * null pointer.
     */
    @Override
    public Hotel viewSelectedHotel(int hotelId) {
        LOG_MESSAGE = new StringBuffer("public Hotel viewSelectedHotel(int hotelId) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Hotel h ";

                Hotel selectdHotel = (Hotel) session.get(Hotel.class, hotelId);


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));

                // Close Session object
                return selectdHotel;

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
     * Add a new HotelImage object in the database.
     *
     * @param hotelImage an absolute HotelImage object which is going to be
     * added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addNewHotelImages(HotelImage hotelImage) {
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            // Begin Transaction Object
            transaction = session.beginTransaction();

            // Save Location Details 
            session.save(hotelImage);
            session.flush();
            transaction.commit();

            // Create Log4j Message
            LOG_MESSAGE = new StringBuffer(BackendConstants.LOCATION_IMAGES_ADDED);

            // Write into Log4j File
            Log4jUtil.logInfoMessage(LOG_MESSAGE);

            return BackendConstants.DATA_ADDED_SUCCESSFULLY;

        } else { // Session Object Null
        }

        return null;

    }

    /**
     * Reads all the Hotel object from the database and return them to the user.
     *
     * @return returns a List of Hotels.
     * @see List
     */
    @Override
    public List<Hotel> loadAllHotels() {
        LOG_MESSAGE = new StringBuffer("public List<Hotel> loadAllHotels() : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Hotel h";

                Query selectQuery = session.createQuery(HQLQuery);

                List<Hotel> hotelList = selectQuery.list();

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));

                // Close Session object
                return hotelList;

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
     * Add a new HotelType object in the database.
     *
     * @param hotelType an absolute HotelType object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addHoteltype(HotelType hotelType) {
        LOG_MESSAGE = new StringBuffer("public String addHoteltype(HotelType hotelType) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save Location Details 
                session.save(hotelType);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                String infoMsg = hotelType.getHotelTypeId() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

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
     * Delete an existing Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteHotel(Hotel hotel) {
        LOG_MESSAGE = new StringBuffer("public String deleteHotel(Hotel hotel) : ");
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

//                Object[] hoteltypeArray = hotel.getHotelTypes().toArray();
//                if(hoteltypeArray.length>0){
//                HotelType hotelType = (HotelType) hoteltypeArray[0];
//                
//                // Save Location Details 
//                session.delete(hotelType);
//                }
                session.delete(hotel);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                String infoMsg = BackendConstants.DATA_DELETED_SUCCESSFULLY;

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
     * Searches the database for given search type and keyword, and returns a
     * List of Hotels which are matched to the keyword.
     *
     * @param searchType a String representation of searchType. Which is
     * maintained in BackendConstants class.
     * @param keyword a String keyword which is typed by the user of this system
     * in-order to search for a hotels.
     * @return a List of Hotels which are matched to the search type and
     * keyword.
     * @see List
     * @see BackendConstants
     */
    @Override
    public List<Hotel> searchHotels(String searchType, String keyword) {
        LOG_MESSAGE = new StringBuffer("public List<Hotel> searchHotels(String searchType, String keyword) : ");

        if (searchType == null || keyword == null) {
            // Write info Log4j File
            Log4jUtil.logFatalMessage(LOG_MESSAGE.append("Null searchType or keyword"));
            return null;
        } else {
            // Retrieve Hibernate Session Object
            session = CeylonHibernateUtil.getSessionFactory().openSession();
            if (session != null) {
                String HQLQuery = "";
                if (searchType.equals(BackendConstants.SEARCH_BY_HOTEL_NAME)) {
                    HQLQuery = "FROM Hotel h WHERE h.hotelStatus=1 AND h.hotelName LIKE '" + keyword + "%'";
                } else if (searchType.equals(BackendConstants.SEARCH_BY_HOTEL_DESCRIPTION)) {
                    HQLQuery = "FROM Hotel h WHERE h.hotelStatus=1 AND h.hotelDescription LIKE '" + keyword + "%'";
                } else if (searchType.equals(BackendConstants.SEARCH_BY_HOTEL_CITY)) {
                    //HQLQuery = "FROM Hotel h WHERE h.hotelStatus=1 AND h.cityName LIKE '" + keyword + "%'";

                    HQLQuery = "FROM CityDetail c WHERE c.cityStatus=1 AND c.cityName LIKE '" + keyword + "%'";

                    try {
                        Query loginQuery = session.createQuery(HQLQuery);
                        List<CityDetail> cityList = loginQuery.list();
                        List<Hotel> hotelList = new ArrayList<Hotel>();

                        Iterator<CityDetail> iterator = cityList.iterator();
                        while (iterator.hasNext()) {
                            CityDetail cityDetail = iterator.next();
                            hotelList.addAll(cityDetail.getHotels());
                        }

                        List<Hotel> newHotelList = new ArrayList<Hotel>();
                        HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();
                        Date today = new Date();
                        for (Hotel hotel : hotelList) {
                            HotelContract hotelContract = hotelContractDAO.getContract(hotel.getHotelId().toString());

                            if (hotelContract!=null && hotelContract.getEndDate().compareTo(today) >= 0) {
                                newHotelList.add(hotel);
                            }
                        }


                        // Write info Log4j File
                        Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                        // Close Session object
                        return newHotelList;
                    } catch (Exception ex) {
                        // Write info Log4j File
                        Log4jUtil.logFatalMessage(LOG_MESSAGE.append(ex.toString()));
                        return null;
                    } finally {
                        if (session != null) {
                            session.close();
                        }
                    }

                }

                try {
                    Query loginQuery = session.createQuery(HQLQuery);
                    List<Hotel> hotelList = loginQuery.list();
                    // Write info Log4j File
                    Log4jUtil.logInfoMessage(LOG_MESSAGE.append(BackendConstants.SUCCESS));
                    List<Hotel> newHotelList = new ArrayList<Hotel>();
                    HotelContractDAO hotelContractDAO = new HotelContractDAOImpl();
                    Date today = new Date();
                    for (Hotel hotel : hotelList) {
                        HotelContract hotelContract = hotelContractDAO.getContract(hotel.getHotelId().toString());

                        if (hotelContract!=null && hotelContract.getEndDate().compareTo(today) >= 0) {
                            newHotelList.add(hotel);
                        }
                    }

                    // Close Session object
                    return newHotelList;
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
}
