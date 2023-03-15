package org.example.cmp.activity;

import lombok.Getter;
import lombok.Setter;

public class PricedActivity extends Activity{

    @Getter @Setter
    private double regularCost;
    public PricedActivity(String name, String description, int regular_duration, double regularCost) {
        super(name, description, regular_duration);
        this.regularCost = regularCost;

    }
}
