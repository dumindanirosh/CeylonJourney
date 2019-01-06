package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.LocationComment;
import java.util.List;

/**
 * It is the DAO interface for LocationComment object. The LocationCommentDAO provides six methods
 * to access the LocationComment objects from the database.
 *
 * @author Duminda
 */
public interface LocationCommentDAO {
    /**
     * Add a new LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addComment(LocationComment locationComment);
    
    /**
     * Update an existing LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateComment(LocationComment locationComment);
    
    /**
     * Delete an existing LocationComment object in the database.
     *
     * @param locationComment an absolute LocationComment object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String deleteComment(LocationComment locationComment);
    
    /**
     * Search the database for a all the non approved comments.
     *
     * @return return a collection of LocationComments. If there are no more non-approved comments return null
     * @see List
     */
    public List<LocationComment> getNonApprovedComments();
    
    /**
     * Search the database for a given location id and returns all the approved comments. If the
     * location id is not found in the database then a null pointer will be returned.
     *
     * @param locationId a String representation of the Location ID.
     * @return return a collection of LocationComments.
     * @see List
     */
    public List<LocationComment> getApprovedComments(String locationId);
    
    /**
     * Search the database for a all the approved comments.
     *
     * @return return a collection of LocationComments. If there are no more approved comments return null
     * @see List
     */
    public List<LocationComment> getAllApprovedComments();
}
