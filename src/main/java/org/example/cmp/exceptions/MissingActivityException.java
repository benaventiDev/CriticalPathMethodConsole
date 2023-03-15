package org.example.cmp.exceptions;

public class MissingActivityException extends Exception{
    public MissingActivityException(String activityName){
        super("Activity with name: " + activityName + " not found.\n");
    }
}
