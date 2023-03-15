package org.example.cmp.interfaces;

import org.example.cmp.activity.ActivityListManager;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;

public interface IActivityProvider {
    public ActivityListManager retrieveActivityListManager()  throws DuplicatedActivityException, MissingActivityException, DuplicatedActivityPathException;
}
