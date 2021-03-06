package com.duminda.ceylonjourney.model;
// Generated Feb 8, 2013 12:10:38 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * CityDetail generated by hbm2java
 */
public class CityDetail  implements java.io.Serializable {


     private Integer cityId;
     private DistrictDetail districtDetail;
     private String cityName;
     private Byte cityStatus;
     private Set hotels = new HashSet(0);
     private Set locations = new HashSet(0);

    public CityDetail() {
    }

	
    public CityDetail(DistrictDetail districtDetail, String cityName) {
        this.districtDetail = districtDetail;
        this.cityName = cityName;
    }
    public CityDetail(DistrictDetail districtDetail, String cityName, Byte cityStatus, Set hotels, Set locations) {
       this.districtDetail = districtDetail;
       this.cityName = cityName;
       this.cityStatus = cityStatus;
       this.hotels = hotels;
       this.locations = locations;
    }
   
    public Integer getCityId() {
        return this.cityId;
    }
    
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public DistrictDetail getDistrictDetail() {
        return this.districtDetail;
    }
    
    public void setDistrictDetail(DistrictDetail districtDetail) {
        this.districtDetail = districtDetail;
    }
    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Byte getCityStatus() {
        return this.cityStatus;
    }
    
    public void setCityStatus(Byte cityStatus) {
        this.cityStatus = cityStatus;
    }
    public Set getHotels() {
        return this.hotels;
    }
    
    public void setHotels(Set hotels) {
        this.hotels = hotels;
    }
    public Set getLocations() {
        return this.locations;
    }
    
    public void setLocations(Set locations) {
        this.locations = locations;
    }




}


