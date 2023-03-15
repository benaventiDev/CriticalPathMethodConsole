package org.example.cmp.exceptions;

public class DuplicatedActivityException extends Exception{
    public DuplicatedActivityException(String activityName){
        super("Activity " + activityName + " already exists!");
    }
}
