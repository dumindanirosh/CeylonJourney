/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.HotelContract;

/**
 * It is the DAO interface for HotelContract object. The HotelContractDAO provides three methods
 * to access the HotelContract objects from the database.
 *
 * @author Duminda
 */
public interface HotelContractDAO {
    /**
     * Add a new HotelContract object in the database.
     *
     * @param hotelContract an absolute HotelContract object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addContract(HotelContract hotelContract);
    
    /**
     * Update an existing HotelContract object in the database.
     *
     * @param hotelContract an absolute HotelContract object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateContract(HotelContract hotelContract);
    
    /**
     * Search the database for a given hotel id and returns the HotelContract. If the
     * HotelContract is not found in the database then a null pointer will be returned.
     *
     * @param hotelId a int representation of the Hotel ID.
     * @return if the HotelContract is found return a HotelContract object; otherwise returns a
     * null pointer.
     */
    public HotelContract getContract(String hotelId);
}
