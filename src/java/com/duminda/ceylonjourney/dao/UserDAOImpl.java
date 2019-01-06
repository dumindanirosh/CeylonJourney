package com.duminda.ceylonjourney.dao;

import com.duminda.ceylonjourney.model.Admin;
import com.duminda.ceylonjourney.model.HotelOfficer;
import com.duminda.ceylonjourney.model.InternalOfficer;
import com.duminda.ceylonjourney.model.Member;
import com.duminda.ceylonjourney.model.User;
import com.duminda.ceylonjourney.util.BackendConstants;
import com.duminda.ceylonjourney.util.CeylonHibernateUtil;
import com.duminda.ceylonjourney.util.Log4jUtil;
import com.duminda.ceylonjourney.util.MailUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The UserDAOImpl is the direct implementation of the UserDAO interface. It
 * overrides all the eight methods of UserDAO interface to deal with the User
 * database.
 *
 * @author Duminda
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Hibernate Session Object which provides the functions for database
     * handling. The initial value is null.
     */
    private Session session = null;
    /**
     * A String reference which is used to store Log messages and finally it
     * will passes to Log4jUtil object.
     */
    private String LOG_MESSAGE;

    /**
     * Reads the User object and check its validity. If the User object has the
     * authority to login then mark its status as logged-in otherwise mark it as
     * invalid-login.
     *
     * @param user an absolute User object which requests for login.
     * @return return the same User account with modified user status.
     */
    @Override
    public User login(User user) {

        LOG_MESSAGE = "public User login(User user) : ";

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM User u WHERE u.username=:username AND u.password=:password";

                Query userQuery = session.createQuery(HQLQuery);

                userQuery.setParameter("username", user.getUsername());
                userQuery.setParameter("password", user.getPassword());

                User userTmp = (User) userQuery.uniqueResult();
                
                if (userTmp != null && (userTmp.getUsername().equals(user.getUsername())&&userTmp.getPassword().equals(user.getPassword()))) { // Login Success
                    
                    user = userTmp;
                    boolean userStatus = user.getUserStatus();

                    if (userStatus) { // User Active
                        Date loggedTime = new Date(System.currentTimeMillis());
                        LOG_MESSAGE = user.getUsername() + BackendConstants.LOGGED + loggedTime;

                        user.setLoginStatus(BackendConstants.SUCCESS);


                    } else { // User Disabled
                        Date loggedTime = new Date(System.currentTimeMillis());
                        LOG_MESSAGE = user.getUsername() + BackendConstants.DISABLED + loggedTime;

                        user.setLoginStatus(BackendConstants.DISABLED);
                    }

                } else { // Incorrect Login
                    Date loggedTime = new Date(System.currentTimeMillis());
                    LOG_MESSAGE = user.getUsername() + BackendConstants.INVALID_LOGIN + loggedTime;

                    user.setLoginStatus(BackendConstants.INVALID_LOGIN);

                }

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                // Close Session object

                return user;

            } catch (Exception ex) {
                // Write info Log4j File
                LOG_MESSAGE = LOG_MESSAGE + ex.toString();
                Log4jUtil.logFatalMessage(LOG_MESSAGE);
                user.setLoginStatus(BackendConstants.ERROR);
                return user;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            user.setLoginStatus(BackendConstants.ERROR);
            return user;
        }

    }

    /**
     * Send email to the given email address.
     *
     * @param emailAddress a String representation of an email address.
     * @return a String message which tells the success or fail story of sending
     * email
     */
    @Override
    public String sendEmail(String emailAddress) {

        LOG_MESSAGE = "public User login(User user) : ";

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM Member m WHERE m.emailAddress=:emailAddress";

                Query userQuery = session.createQuery(HQLQuery);

                userQuery.setParameter("emailAddress", emailAddress);

                Member member = (Member) userQuery.uniqueResult();

                if (member != null) { // Login Success

                    String password = member.getUser().getPassword();
                    MailUtil mailUtil = new MailUtil();
                    // mailUtil.sendEmail(emailAddress, "Ceylon Journey Password Recovery"
                    //        , "Your Password is : " + password);
                } else { // Incorrect Login
                    Date loggedTime = new Date(System.currentTimeMillis());
                    LOG_MESSAGE = member.getEmailAddress()
                            + BackendConstants.INVALID_EMAIL_ADDRESS + loggedTime;

                }

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                // Close Session object

                return BackendConstants.SUCCESS;

            } catch (Exception ex) {
                // Write info Log4j File
                LOG_MESSAGE = LOG_MESSAGE + ex.toString();
                Log4jUtil.logFatalMessage(LOG_MESSAGE);

                return BackendConstants.ERROR;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);

            return BackendConstants.ERROR;
        }


    }

    /**
     * Adds a new User object in the database.
     *
     * @param user an absolute User object which is going to be added
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String addUser(User user) {

        LOG_MESSAGE = "public String addUser(User user)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save User Details 
                session.save(user);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = user.getUsername() + " " + BackendConstants.DATA_ADDED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Updates a new User object in the database.
     *
     * @param user an absolute User object which is going to be updated
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String updateUser(User user) {
        LOG_MESSAGE = "public String updateUser(User user)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                // Save User Details 
                session.update(user);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = user.getUsername() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Deletes a new User object in the database.
     *
     * @param user an absolute User object which is going to be deleted
     * @return a String message which tells the success or fail story of this
     * operation
     */
    @Override
    public String deleteUser(User user) {
        LOG_MESSAGE = "public String deleteUser(User user)  : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            Transaction transaction = null;

            try {
                // Begin Transaction Object
                transaction = session.beginTransaction();

                User deleteUser = (User) session.get(User.class, user.getUsername());

                // Save User Details 
                session.delete(deleteUser);
                session.flush();
                transaction.commit();

                // Create Log4j Message
                LOG_MESSAGE = user.getUsername() + " " + BackendConstants.DATA_UPDATED_SUCCESSFULLY;

                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE);

                return BackendConstants.SUCCESS;
            } catch (Exception ex) {
                // Rollback all inserted data
                if (transaction != null && transaction.wasCommitted()) {
                    transaction.rollback();
                    Log4jUtil.logWarnMessage(session);
                }
                // Write info Log4j File
                Log4jUtil.logFatalMessage(ex);
                return BackendConstants.ERROR;
            } finally {
                // close session object

                session.close();
            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Searches the database for a given user name and returns the User. If the
     * User is not found in the database then a null pointer will be returned.
     *
     * @param username a String representation of the user name.
     * @return a User object if the User is found otherwise returns a null
     * pointer.
     */
    @Override
    public User viewUser(String username) {

        LOG_MESSAGE = "public User viewUser(String username) : ";

        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                User user = (User) session.get(User.class, username);



                return user;

            } catch (Exception ex) {
                // Write info Log4j File
                LOG_MESSAGE = LOG_MESSAGE + ex.toString();
                Log4jUtil.logFatalMessage(LOG_MESSAGE);
                return null;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }

    }

    /**
     * Reads all the User objects from the database and return them to the user.
     *
     * @return returns a List of Users.
     * @see List
     */
    @Override
    public List<User> viewAllUsers() {

        LOG_MESSAGE = "public List<User> viewAllUsers() : ";
        // Retrieve Hibernate Session Object
        session = CeylonHibernateUtil.getSessionFactory().openSession();

        if (session != null) {

            try {

                String HQLQuery = "FROM User u ";

                Query loginQuery = session.createQuery(HQLQuery);

                List<User> usersList = loginQuery.list();


                // Write info Log4j File
                Log4jUtil.logInfoMessage(LOG_MESSAGE.concat(BackendConstants.SUCCESS));

                // Close Session object
                return usersList;

            } catch (Exception ex) {
                // Write info Log4j File

                Log4jUtil.logFatalMessage(LOG_MESSAGE.concat(ex.toString()));

                return null;
            } finally {
                if (session != null) {
                    session.close();
                }

            }

        } else { // Session Object Null
            LOG_MESSAGE = BackendConstants.SESSION_NULL;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }

    /**
     * Deletes an existing user type in the database.
     *
     * @param object an Object which can be an Admin, a HotelOfficer, a
     * InternalOfficer or a Member.
     * @return a String message which tells the success or fail story of this
     * operation. If the object is not a valid user type then returns a null
     * pointer.
     */
    @Override
    public String deleteUserType(Object object) {
        LOG_MESSAGE = "public String deleteUserType(UserType userType)  : ";
        boolean isValid = false;
        if (object instanceof Admin) {
            isValid = true;
        } else if (object instanceof HotelOfficer) {
            isValid = true;
        } else if (object instanceof InternalOfficer) {
            isValid = true;
        } else if (object instanceof Member) {
            isValid = true;
        }
        if (isValid) {
            // Retrieve Hibernate Session Object
            session = CeylonHibernateUtil.getSessionFactory().openSession();
            if (session != null) {
                Transaction transaction = null;
                try {
                    // Begin Transaction Object
                    transaction = session.beginTransaction();

                    session.delete(object);
                    session.flush();
                    transaction.commit();
                    // Create Log4j Message
                    LOG_MESSAGE = "User Type" + " " + BackendConstants.DATA_DELETED_SUCCESSFULLY;

                    // Write info Log4j File
                    Log4jUtil.logInfoMessage(LOG_MESSAGE);

                    return BackendConstants.SUCCESS;
                } catch (Exception ex) {
                    // Rollback all inserted data
                    if (transaction != null && transaction.wasCommitted()) {
                        transaction.rollback();
                        Log4jUtil.logWarnMessage(session);
                    }
                    // Write info Log4j File
                    Log4jUtil.logFatalMessage(ex);
                    return BackendConstants.ERROR;
                } finally {
                    // close session object
                    session.close();
                }
            } else { // Session Object Null
                LOG_MESSAGE = BackendConstants.SESSION_NULL;
                Log4jUtil.logErrorMessage(LOG_MESSAGE);
                return null;
            }
        } else {
            //Invalid object
            LOG_MESSAGE = BackendConstants.INVALID_USER_TYPE_OBJECT;
            Log4jUtil.logErrorMessage(LOG_MESSAGE);
            return null;
        }
    }
}
