package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.DistrictDetail;

/**
 * It is the DAO interface for DistrictDetail object. The DistrictDetailDAO
 * provides two methods to read the DistrictDetail objects from the database.
 *
 * @author Duminda
 */
public interface DistrictDetailDAO {

    /**
     * Searches the database for a given district name and returns the
     * DistrictDetail. If the DistrictDetail is not found in the database then a
     * null pointer will be returned.
     *
     * @param districtName a String representation of the district name.
     * @return a DistrictDetail object if the DistrictDetail is found otherwise
     * returns a null pointer.
     */
    public DistrictDetail viewDistrict(String districtName);

    /**
     * Searches the database for a given district name and returns the
     * DistrictDetail. If the DistrictDetail is not found in the database then a
     * null pointer will be returned.
     *
     * @param districtID a Integer representation of the district id.
     * @return a DistrictDetail object if the DistrictDetail is found otherwise
     * returns a null pointer.
     */
    public DistrictDetail viewDistrict(Integer districtID);
}
