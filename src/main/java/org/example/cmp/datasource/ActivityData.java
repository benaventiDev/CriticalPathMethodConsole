package org.example.cmp.datasource;

import lombok.Getter;
import lombok.Setter;
import org.example.cmp.activity.Activity;

public class ActivityData {
    @Setter @Getter
    private Activity activity;
    @Setter @Getter
    private String previousActivitiesString;

    public ActivityData(Activity activity, String previousActivitiesString) {
        this.activity = activity;
        this.previousActivitiesString = previousActivitiesString;
    }

}
