package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.TravelGuide;
import java.util.List;

/**
 * It is the DAO interface for TravelGuide object. The TravelGuideDAO provides
 * five methods to deal with the TravelGuide database.
 *
 * @author Duminda
 */
public interface TravelGuideDAO {

    /**
     * Adds a new TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addTravelGuide(TravelGuide guide);

    /**
     * Updates an existing TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateTravelGuide(TravelGuide guide);

    /**
     * Deletes an existing TravelGuide object in the database.
     *
     * @param guide an absolute TravelGuide object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String deleteTravelGuide(TravelGuide guide);

    /**
     * Reads all the TravelGuide objects from the database and return them to
     * the user.
     *
     * @return returns a List of TravelGuides.
     * @see List
     */
    public List<TravelGuide> viewAllTravelGuides();

    /**
     * Searches the database for a given travel guide id and returns the
     * TravelGuide. If the TravelGuide is not found in the database then a null
     * pointer will be returned.
     *
     * @param travelGuideID a String representation of the Travel guide ID.
     * @return a TravelGuide object if the TravelGuide is found otherwise
     * returns a null pointer.
     */
    public TravelGuide viewGuide(String travelGuideID);
}
