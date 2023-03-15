package org.example.cmp.activity;

import org.example.cmp.datasource.ActivityData;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;
import org.example.cmp.interfaces.IActivityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityListManager implements IActivityManager {
    private List<ActivityData> activitiesData;
    private List<Activity> activities;
    public ActivityListManager() {
        this.activities = new ArrayList<>();
        this.activitiesData = new ArrayList<>();
    }
    public void addActivityData(ActivityData activityData) throws  DuplicatedActivityException{
        if(activityAlreadyAdded(this.activities, activityData.getActivity().getName())) throw new DuplicatedActivityException(activityData.getActivity().getName());
        this.activitiesData.add(activityData);
        this.activities.add(activityData.getActivity());
    }
    @Override
    public void setPreviousActivitiesForActivity(Activity activity) {


    }
    @Override
    public void  setAllPreviousActivities() throws MissingActivityException, DuplicatedActivityPathException {
        for(ActivityData activityData: activitiesData){
            String[] previousActivitiesString = activityData.getPreviousActivitiesString().split(" ");
            for(String pa: previousActivitiesString){
                Activity prevActivity = getActivityByName(pa);
                if(prevActivity == null){
                    throw new MissingActivityException(pa);
                }else if(prevActivity == activityData.getActivity()){
                    throw new DuplicatedActivityPathException(prevActivity);
                }
                activityData.getActivity().addToPreviousActivities(prevActivity);
                prevActivity.addToNextActivities(activityData.getActivity());
            }
        }
    }
    private boolean activityAlreadyAdded(List<Activity> activities, String name){
        for(Activity activity: activities){
            if(activity.getName().compareTo(name) == 0) return true;
        }
        return false;
    }
    public List<Activity> getActivities(){
        return Collections.unmodifiableList(activities);
    }
    public Activity getActivityByName(String name){
        for(Activity activity: activities){
            if(activity.getName().compareTo(name) == 0) return activity;
        }
        return null;
    }
}
