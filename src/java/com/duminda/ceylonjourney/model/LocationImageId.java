package com.duminda.ceylonjourney.model;
// Generated Feb 8, 2013 12:10:38 AM by Hibernate Tools 3.2.1.GA



/**
 * LocationImageId generated by hbm2java
 */
public class LocationImageId  implements java.io.Serializable {


     private int locationId;
     private String imageName;

    public LocationImageId() {
    }

    public LocationImageId(int locationId, String imageName) {
       this.locationId = locationId;
       this.imageName = imageName;
    }
   
    public int getLocationId() {
        return this.locationId;
    }
    
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public String getImageName() {
        return this.imageName;
    }
    
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof LocationImageId) ) return false;
		 LocationImageId castOther = ( LocationImageId ) other; 
         
		 return (this.getLocationId()==castOther.getLocationId())
 && ( (this.getImageName()==castOther.getImageName()) || ( this.getImageName()!=null && castOther.getImageName()!=null && this.getImageName().equals(castOther.getImageName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getLocationId();
         result = 37 * result + ( getImageName() == null ? 0 : this.getImageName().hashCode() );
         return result;
   }   


}


