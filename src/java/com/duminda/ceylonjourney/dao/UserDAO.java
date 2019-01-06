package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.User;
import java.util.List;

/**
 * It is the DAO interface for User object. The UserDAO provides eight methods
 * to deal with the User database.
 *
 * @author Duminda
 */
public interface UserDAO {

    /**
     * Reads the User object and check its validity. If the User object has the
     * authority to login then mark its status as logged-in otherwise mark it as
     * invalid-login.
     *
     * @param user an absolute User object which requests for login.
     * @return return the same User account with modified user status.
     */
    public User login(User user);

    /**
     * Send email to the given email address.
     *
     * @param emailAddress a String representation of an email address.
     * @return a String message which tells the success or fail story of sending
     * email
     */
    public String sendEmail(String emailAddress);

    /**
     * Deletes an existing user type in the database.
     *
     * @param object an Object which can be an Admin, a HotelOfficer, a
     * InternalOfficer or a Member.
     * @return a String message which tells the success or fail story of this
     * operation. If the object is not a valid user type then returns a null
     * pointer.
     */
    public String deleteUserType(Object object);

    /**
     * Adds a new User object in the database.
     *
     * @param user an absolute User object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String addUser(User user);

    /**
     * Updates a new User object in the database.
     *
     * @param user an absolute User object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String updateUser(User user);

    /**
     * Deletes a new User object in the database.
     *
     * @param user an absolute User object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    public String deleteUser(User user);

    /**
     * Searches the database for a given user name and returns the User. If the
     * User is not found in the database then a null pointer will be returned.
     *
     * @param username a String representation of the user name.
     * @return a User object if the User is found otherwise returns a null
     * pointer.
     */
    public User viewUser(String username);

    /**
     * Reads all the User objects from the database and return them to the user.
     *
     * @return returns a List of Users.
     * @see List
     */
    public List<User> viewAllUsers();
}
