package org.example.cmp.util;

public class TimeExtension {
    private TimeUnitType unit;
    private int time_duration;

    public TimeExtension(int time_duration, TimeUnitType unit){
        this.time_duration = time_duration;
        this.unit = unit;
    }
}

