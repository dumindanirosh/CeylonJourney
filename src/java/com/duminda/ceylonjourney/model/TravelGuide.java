package com.duminda.ceylonjourney.model;
// Generated Feb 14, 2013 7:19:48 AM by Hibernate Tools 3.2.1.GA



/**
 * TravelGuide generated by hbm2java
 */
public class TravelGuide  implements java.io.Serializable {


     private Integer travelGuideId;
     private String firstName;
     private String lastName;
     private String emailAddress;
     private String telephoneNumber;
     private String mobileNumber;

    public TravelGuide() {
    }

	
    public TravelGuide(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
    public TravelGuide(String firstName, String lastName, String emailAddress, String telephoneNumber, String mobileNumber) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.emailAddress = emailAddress;
       this.telephoneNumber = telephoneNumber;
       this.mobileNumber = mobileNumber;
    }
   
    public Integer getTravelGuideId() {
        return this.travelGuideId;
    }
    
    public void setTravelGuideId(Integer travelGuideId) {
        this.travelGuideId = travelGuideId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }




}


