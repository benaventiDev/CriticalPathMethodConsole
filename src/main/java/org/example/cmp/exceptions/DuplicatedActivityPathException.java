package org.example.cmp.exceptions;

import org.example.cmp.activity.Activity;

public class DuplicatedActivityPathException extends Exception{
    public DuplicatedActivityPathException(Activity activity){
        super("Activity " + activity.getName() + " is found more than once in the same path.");
    }
}
