package com.duminda.ceylonjourney.model;
// Generated Feb 8, 2013 12:10:38 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * SearchType generated by hbm2java
 */
public class SearchType  implements java.io.Serializable {


     private Integer searchTypeId;
     private String typeName;
     private Byte typeStatus;
     private String typeDescription;
     private Set locationSearchTypes = new HashSet(0);

    public SearchType() {
    }

    public SearchType(String typeName, Byte typeStatus, String typeDescription, Set locationSearchTypes) {
       this.typeName = typeName;
       this.typeStatus = typeStatus;
       this.typeDescription = typeDescription;
       this.locationSearchTypes = locationSearchTypes;
    }
   
    public Integer getSearchTypeId() {
        return this.searchTypeId;
    }
    
    public void setSearchTypeId(Integer searchTypeId) {
        this.searchTypeId = searchTypeId;
    }
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Byte getTypeStatus() {
        return this.typeStatus;
    }
    
    public void setTypeStatus(Byte typeStatus) {
        this.typeStatus = typeStatus;
    }
    public String getTypeDescription() {
        return this.typeDescription;
    }
    
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public Set getLocationSearchTypes() {
        return this.locationSearchTypes;
    }
    
    public void setLocationSearchTypes(Set locationSearchTypes) {
        this.locationSearchTypes = locationSearchTypes;
    }




}


