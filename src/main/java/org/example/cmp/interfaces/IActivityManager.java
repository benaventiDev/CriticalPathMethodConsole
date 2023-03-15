package org.example.cmp.interfaces;

import org.example.cmp.activity.Activity;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;

public interface IActivityManager {
    public void  setAllPreviousActivities() throws MissingActivityException, DuplicatedActivityPathException;
    public void setPreviousActivitiesForActivity(Activity activity) throws MissingActivityException, DuplicatedActivityPathException;
}
