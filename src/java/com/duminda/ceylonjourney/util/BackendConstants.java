package com.duminda.ceylonjourney.util;

/**
 * This interface contains a set of public static String constants in-order to
 * supports the system functions.
 *
 * @author Duminda
 */
public interface BackendConstants {

    String ERROR_MESSAGE = "errorMessage";
    String SUCCESS_MESSAGE = "successMessage";
    String DATA_ADDED_SUCCESSFULLY = "dataAddedSuccessfully";
    String DATA_DELETED_SUCCESSFULLY = "dataDeletedSuccessfully";
    String DATA_UPDATED_SUCCESSFULLY = "dataUpatedSuccessfully";
    String DATA_ALREADY_EXIST = "dataAlreadyExist";
    String DATA_NOT_EXIST = "dataNotExist";
    String LOCATION_IMAGES_ADDED = "locationImagesAddedSuccessfully";
    String LOCATION_IMAGES_DELETED = "locationImagesDeletedSuccessfully";
    String SESSION_NULL = "sessionNull";
    String INVALID_USER_TYPE_OBJECT = "invalidUserTypeObject";
    String ACTION_TYPE = "actionType";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";
    String SAVE_ADD = "saveAdd";
    String SAVE_UPDATE = "saveUpdate";
    String SAVE_SUCCESS = "save_success";
    // Location Table Column
    String LOCATION_ID = "location_id";
    String LOCATION_NAME = "location_name";
    String LOCATION_DESCRIPTION = "location_description";
    String LOCATION_ADDRESS = "location_address";
    String LOCATION_CITY = "location_city";
    String LOCATION_DISTRICT = "location_district";
    String VISIBILITY = "visibility";
    String LOCATION_STATUS = "location_status";
    String LOCATION_LIST = "location_list";
    String LOCATION_FOR_HOME_VIEW = "location_for_home_view";
    String LOCATION_FOR_DASHBOARD_VIEW = "location_for_dashboard_view";
    String LOCATION_FOR_DASHBOARD_APPROVE = "location_for_dashboard_approve";
    String LOCATION_FOR_DASHBOARD_UPDATE = "location_for_dashboard_update";
    String LOCATION_FOR_DASHBOARD_DELETE = "location_for_dashboard_delete";
    String HOT_LOCATION_FOR_DASHBOARD = "hot_location_for_dashboard";
    String SELECTED_HOT_LOCATION = "selected_hot_location";
    String APPROVED_LOCATION_COMMENTS = "approved_location_comments";
    
    
    String ADD_LOCATION = "addLocation";
    String INVALID_LOGIN = "invalidLogin";
    String ERROR = "error";
    String SUCCESS = "success";
    String DISABLED = "disabled";
    String LOGGED = "logged";
    String USER = "user";
    String ADMIN = "admin";
    String INTERNAL_OFFICER = "internal_officer";
    String HOTEL_OFFICER = "hotel_officer";
    String MEMBER = "member";
    String ROLLACK_COMPLETED = "rollBackCompleted";
    String ACTIVE_LOCATIONS = "activeLocations";
    //  String IMAGE_UPLOAD_LOCATION = "D:\\temp\\";
    String IMAGE_UPLOAD_LOCATION = "D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\";
    String HOTEL_IMAGE_UPLOAD_LOCATION = "D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\hotel\\";
    String INVALID_EMAIL_ADDRESS = "invalidEmailAddress";
    String ADMIN_ID = "1";
    String MEMBER_ID = "2";
    String INTERNAL_OFFICER_ID = "3";
    String HOTEL_OFFICER_ID = "4";
    String HOTELS = "hotels";
    String USER_LIST = "userList";
    String SELECTED_USER = "selectedUser";
    String LOCATIONS = "locations";
    //Travel Guide Column
    String TRAVEL_GUIDE = "travelguide";
    String GUIDE_LIST = "guideList";
    String SELECTED_GUIDE = "selectedGuide";
    //Districts of SriLanka
    String AMPARA = "Ampara";
    String ANURADHAPURA = "Anuradhapura";
    String BADULLA = "Badulla";
    String BATTICALOA = "Batticaloa";
    String COLOMBO = "Colombo";
    String GALLE = "Galle";
    String GAMPAHA = "Gampaha";
    String HAMBANTOTA = "Hambantota";
    String JAFFNA = "Jaffna";
    String KALUTARA = "Kalutara";
    String KANDY = "Kandy";
    String KEGALLE = "Kegalle";
    String KILINOCHCHI = "Kilinochchi";
    String KURUNEGALA = "Kurunegala";
    String MANNAR = "Mannar";
    String MATALE = "Matale";
    String MATARA = "Matara";
    String MONERAGALA = "Moneragala";
    String MULLAITIVU = "Mullaitivu";
    String NUWARA_ELIYA = "Nuwara Eliya";
    String POLONNARUWA = "Polonnaruwa";
    String PUTTALAM = "Puttalam";
    String RATNAPURA = "Ratnapura";
    String TRINCOMALEE = "Trincomalee";
    String VAVUNIYA = "Vavuniya";
    String CITY_LIST = "cityList";
    String SELECTED_CITY = "selectedCity";
    String SELECTED_LOCATION = "selectedLocation";
    // Hotel
    String HOTEL_FOR_DASHBOARD_VIEW = "hotel_for_dashboard_view";
    String HOTEL_FOR_DASHBOARD_UPDATE = "hotel_for_dashboard_update";
    String HOTEL_FOR_DASHBOARD_DELETE = "hotel_for_dashboard_delete";
    String HOTEL_FOR_UPDATE_CONTRACT = "hotel_for_update_contract";
    String HOTEL_FOR_ADD_CONTRACT= "hotel_for_add_contract";
    String SELECTED_HOTEL_CONTRACT = "selected_hotel_contract";
    
    String ADD_HOTEL = "addHotel";
    String HOTEL_LIST = "hotel_list";
    String SELECTED_HOTEL = "selectedHotel";
    String HOTEL_FOR_HOME_VIEW = "hotel_for_home_view";
    String ACTIVE_HOTELS = "activeHotels";
    String ONE_STAR = "One Star";
    String TWO_STAR = "Two Star";
    String THREE_STAR = "Three Star";
    String FOUR_STAR = "Four Star";
    String FIVE_STAR = "Five Star";
    //Search
    String SEARCH_HOTELS = "searchHotels";
    String SEARCH_LOCATIONS = "searchLocations";
    String SEARCH_BY_LOCATION_NAME = "searchByLocationName";
    String SEARCH_BY_LOCATION_CITY = "searchByLocationCity";
    String SEARCH_BY_LOCATION_DESCRIPTION = "searchByLocationDescription";
    String SEARCH_BY_HOTEL_NAME = "searchByHotelName";
    String SEARCH_BY_HOTEL_CITY = "searchByHotelCity";
    String SEARCH_BY_HOTEL_DESCRIPTION = "searchByHotelDescription";
    String DEFAULT_LOCATION_IMAGE = "images/location.jpg";
    String DEFAULT_HOTEL_IMAGE = "images/hotel.jpg";
    String LOGO_IMAGE = "/CeylonJourney/images/logo.png";
    
    String LOCATION_COMMENT_MAP = "locationCommentMap";
    String ILLEGAL_ACCESS = "Someone illegally tries to access ";
}
