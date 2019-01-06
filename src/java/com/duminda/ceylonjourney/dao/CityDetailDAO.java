package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.CityDetail;
import java.util.List;

/**
 * It is the DAO interface for CityDetail object. The CityDetailDAO provides six
 * methods to deal with the CityDetail database.
 *
 * @author Duminda
 */
public interface CityDetailDAO {

    /**
     * Adds a new CityDetail object in the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addNewCity(CityDetail cityDetail);

    /**
     * Updates an existing CityDetail object in the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateCity(CityDetail cityDetail);

    /**
     * Reads all the CityDetails object from the database and return them to the
     * user.
     *
     * @return returns a List of CityDetails.
     * @see List
     */
    public List<CityDetail> loadAllCities();

    /**
     * Reads all the active CityDetails object from the database and return them
     * to the user.
     *
     * @return returns a List of active CityDetails.
     * @see List
     */
    public List<CityDetail> loadAllActiveCities();

    /**
     * Deletes an existing CityDetail object from the database.
     *
     * @param cityDetail an absolute CityDetail object which is going to be
     * deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    String deleteCity(CityDetail cityDetail);

    /**
     * Searches the database for a given city id and returns the CityDetail. If
     * the CityDetail is not found in the database then a null pointer will be
     * returned.
     *
     * @param cityID a String representation of the City ID.
     * @return a CityDetail object if the CityDetail is found otherwise returns
     * a null pointer.
     */
    public CityDetail viewCity(String cityID);
}
