package org.example.cmp.activity;

import lombok.Getter;
import lombok.Setter;
import org.example.cmp.util.TimeExtension;
import org.example.cmp.util.TimeUnitType;

public class FlexibleTimeActivity extends PricedActivity{
    @Setter @Getter
    TimeExtension minimumTime;
    @Setter @Getter
    double maximumCost;
    public FlexibleTimeActivity(String name, String description, int regular_duration, double regularCost, int minimumTime, double maximumCost) {
        super(name, description, regular_duration, regularCost);
        this.maximumCost = maximumCost;
        this.minimumTime = new TimeExtension(minimumTime, TimeUnitType.NO_DIMENSION);
    }
}
