package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.Location;
import com.duminda.ceylonjourney.model.LocationImage;
import com.duminda.ceylonjourney.util.BackendConstants;
import java.util.List;

/**
 * It is the DAO interface for Location object. The LocationDAO provides thirteen
 * methods to deal with the Location database.
 *
 * @author Duminda
 */
public interface LocationDAO {

    /**
     * Adds a new Location object to the database.
     *
     * @param location an absolute Location object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addNewLocation(Location location);

    /**
     * Adds a new LocationWelcomeImage object to an existing Location in the
     * database.
     *
     * @param location an absolute Location object whose WelcomeImage is going
     * to be added.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addLocationWelcomeImage(Location location);

    /**
     * Adds a new LocationImage object to the database.
     *
     * @param locationImage an absolute LocationImage object which is going to
     * be added.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addNewLocationImages(LocationImage locationImage);

    /**
     * Update an existing Location object to the database.
     *
     * @param location an absolute Location object which is going to be updated.
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateLocation(Location location);

    /**
     * Reads all the Location objects from the database and return them to the
     * user.
     *
     * @return returns a List of Locations.
     * @see List
     */
    public List<Location> loadAllLocations();

    /**
     * Reads all the active Location objects from the database and return them
     * to the user.
     *
     * @return returns a List of active Locations.
     * @see List
     */
    public List<Location> loadAllActiveLocations();

    /**
     * Reads all the non-active Location objects from the database and return
     * them to the user.
     *
     * @return returns a List of non-active Locations.
     * @see List
     */
    public List<Location> loadNonActiveLocations();

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
    public Location loadSelectedActiveLocation(String locationId);

    /**
     * Deletes an existing Location object from the database.
     *
     * @param location an absolute Location object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String deleteLocation(Location location);

    /**
     * Searches the database for a given location id and if it is found then
     * returns the Location. If the Location is not found in the database then a
     * null pointer will be returned.
     *
     * @param locationId a String representation of the location ID.
     * @return a Location object if the Location is found otherwise returns a
     * null pointer.
     */
    public Location loadSelectedLocation(String locationId);
    
    /**
     * Searches the database for given search type and keyword, and returns a List of Locations which are matched to the keyword.
     * @param searchType a String representation of searchType. Which is maintained in BackendConstants class.
     * @param keyword a String keyword which is typed by the user of this system in-order to search for a location.
     * @return a List of Locations which are matched to the search type and keyword.
     * @see List
     * @see BackendConstants
     */
    public List<Location> searchLocations(String searchType, String keyword);
    
     /**
     * Adds an existing Location as a hot location.
     *
     * @param locationId a String representation of location Id of an existing Location
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String setHotLocation(String locationId);
    
    /**
     * Reads the database and return the current hot location.
     *
     * @return The current hot location
     */
    public Location getHotLocation();
}
