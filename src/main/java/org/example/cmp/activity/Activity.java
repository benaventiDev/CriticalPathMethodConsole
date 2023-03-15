package org.example.cmp.activity;

import lombok.Getter;
import lombok.Setter;
import org.example.cmp.util.TimeExtension;
import org.example.cmp.util.TimeUnitType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Activity {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter
    List<Activity> precedentActivities;
    @Getter
    List<Activity> nextActivities;
    @Getter @Setter
    TimeExtension regular_duration;
    public Activity(String name, String description, int regular_duration){
        this.name = name;
        this.description = description;
        this.regular_duration = new TimeExtension(regular_duration, TimeUnitType.NO_DIMENSION);
        precedentActivities = new ArrayList<>();
        nextActivities = new ArrayList<>();
    }
    public List<Activity> getActivities(){
        return Collections.unmodifiableList(precedentActivities);
    }
    public void addToPreviousActivities(Activity activity){
        if(!precedentActivities.contains(activity)){
            precedentActivities.add(activity);
        }
    }
    public void addToNextActivities(Activity activity){
        if(!nextActivities.contains(activity)){
            nextActivities.add(activity);
        }
    }
    public void removeFromPreviousActivities(Activity activity){
        precedentActivities.remove(activity);
    }
}
