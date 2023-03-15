package org.example.cmp.exceptions;

import org.example.cmp.activity.Activity;

public class RepeatedPreviousActivityException extends Exception{
    public RepeatedPreviousActivityException(Activity currentActivity, Activity previousDuplicated){
        super("Activity " + previousDuplicated.getName() + " is found more than once as previous of: " + currentActivity.getName());
    }
}
