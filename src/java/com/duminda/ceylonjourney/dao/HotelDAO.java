package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.Hotel;
import com.duminda.ceylonjourney.model.HotelImage;
import com.duminda.ceylonjourney.model.HotelType;
import java.util.List;

/**
 * It is the DAO interface for Hotel object. The HotelDAO provides nine methods
 * to read the Hotel objects from the database.
 *
 * @author Duminda
 */
public interface HotelDAO {
    /**
     * Reads all the active Hotel object from the database and return them to
     * the user.
     *
     * @return returns a List of active Hotels.
     * @see List
     */
    public List<Hotel> loadActiveHotels();

    /**
     * Reads all the Hotel object from the database and return them to the user.
     *
     * @return returns a List of Hotels.
     * @see List
     */
    public List<Hotel> loadAllHotels();

    /**
     * Search the database for a given hotel id and returns the hotel. If the
     * Hotel is not found in the database then a null pointer will be returned.
     *
     * @param hotelId a int representation of the Hotel ID.
     * @return if the Hotel is found return a Hotel object; otherwise returns a
     * null pointer.
     */
    public Hotel viewSelectedHotel(int hotelId);

    /**
     * Add a new HotelImage object in the database.
     *
     * @param hotelImage an absolute HotelImage object which is going to be
     * added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addNewHotelImages(HotelImage hotelImage);

    /**
     * Add a new Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addHotel(Hotel hotel);

    /**
     * Add a new HotelType object in the database.
     *
     * @param hotelType an absolute HotelType object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addHoteltype(HotelType hotelType);

    /**
     * Update an existing Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateHotel(Hotel hotel);

    /**
     * Delete an existing Hotel object in the database.
     *
     * @param hotel an absolute Hotel object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String deleteHotel(Hotel hotel);

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
    public List<Hotel> searchHotels(String searchType, String keyword);
}
