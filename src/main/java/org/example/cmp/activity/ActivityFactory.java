package org.example.cmp.activity;

public class ActivityFactory {
    public Activity createActivity(String name, String description, int regular_duration, double regularCost, int minimumTime, double extraCost) {
        if (extraCost != 0) {
            return new FlexibleTimeActivity(name, description, regular_duration, regularCost, minimumTime, extraCost);
        } else if (regularCost != 0) {
            return new PricedActivity(name, description, regular_duration, regularCost);
        } else {
            return new Activity(name, description, regular_duration);
        }
    }
}
