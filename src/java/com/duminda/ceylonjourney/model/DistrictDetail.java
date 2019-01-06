package com.duminda.ceylonjourney.model;
// Generated Feb 8, 2013 12:10:38 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * DistrictDetail generated by hbm2java
 */
public class DistrictDetail  implements java.io.Serializable {


     private Integer districtId;
     private String districtName;
     private Byte districtStatus;
     private Set cityDetails = new HashSet(0);

    public DistrictDetail() {
    }

    public DistrictDetail(String districtName, Byte districtStatus, Set cityDetails) {
       this.districtName = districtName;
       this.districtStatus = districtStatus;
       this.cityDetails = cityDetails;
    }
   
    public Integer getDistrictId() {
        return this.districtId;
    }
    
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
    public String getDistrictName() {
        return this.districtName;
    }
    
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
    public Byte getDistrictStatus() {
        return this.districtStatus;
    }
    
    public void setDistrictStatus(Byte districtStatus) {
        this.districtStatus = districtStatus;
    }
    public Set getCityDetails() {
        return this.cityDetails;
    }
    
    public void setCityDetails(Set cityDetails) {
        this.cityDetails = cityDetails;
    }




}

