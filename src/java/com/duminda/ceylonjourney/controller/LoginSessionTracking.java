package com.duminda.ceylonjourney.controller;

import com.duminda.ceylonjourney.model.User;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author Duminda
 */
public class LoginSessionTracking implements HttpSessionAttributeListener {

    private static int noOfCurrentSessions;

    public static int getNoOfCurrentSession() {

        return noOfCurrentSessions;
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        if (event.getValue() instanceof User) {
            ++noOfCurrentSessions;
        }


    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        if (event.getValue() instanceof User) {
            --noOfCurrentSessions;
        }

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
