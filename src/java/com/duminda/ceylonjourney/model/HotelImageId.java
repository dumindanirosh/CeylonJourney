package com.duminda.ceylonjourney.model;
// Generated Feb 8, 2013 12:10:38 AM by Hibernate Tools 3.2.1.GA



/**
 * HotelImageId generated by hbm2java
 */
public class HotelImageId  implements java.io.Serializable {


     private int hotelId;
     private String imageName;

    public HotelImageId() {
    }

    public HotelImageId(int hotelId, String imageName) {
       this.hotelId = hotelId;
       this.imageName = imageName;
    }
   
    public int getHotelId() {
        return this.hotelId;
    }
    
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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
		 if ( !(other instanceof HotelImageId) ) return false;
		 HotelImageId castOther = ( HotelImageId ) other; 
         
		 return (this.getHotelId()==castOther.getHotelId())
 && ( (this.getImageName()==castOther.getImageName()) || ( this.getImageName()!=null && castOther.getImageName()!=null && this.getImageName().equals(castOther.getImageName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getHotelId();
         result = 37 * result + ( getImageName() == null ? 0 : this.getImageName().hashCode() );
         return result;
   }   


}


