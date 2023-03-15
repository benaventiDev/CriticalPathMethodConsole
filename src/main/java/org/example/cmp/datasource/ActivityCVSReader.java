package org.example.cmp.datasource;

import org.example.cmp.activity.Activity;
import org.example.cmp.activity.ActivityListManager;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.*;

public class ActivityCVSReader extends ActivityFileReader{


    public ActivityCVSReader(File file) {
        super(file);
    }

    @Override
    protected ActivityListManager read() throws DuplicatedActivityException, MissingActivityException, DuplicatedActivityPathException {
        ActivityListManager listActivityManager = new ActivityListManager();
        try (Reader reader = new FileReader(super.getFile());
             CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader)) {
            for (CSVRecord record : parser) {
                String name = record.get("Name");
                String description = record.get("Description");
                String previousActivities = record.get("Previous Activities");
                int regular_duration = Integer.parseInt(record.get("Time"));
                Double regularCost = 0.0d;
                Integer minimumTime = 0;
                Double extraCost = 0.0d;
                setOptionalValues(parser, record, regularCost, minimumTime, extraCost);


                // create an activity object and add it to the list
                Activity activity = super.getActivityFactory().createActivity(name, description, regular_duration, regularCost, minimumTime, extraCost);
                ActivityData activityData = new ActivityData(activity, previousActivities);
                listActivityManager.addActivityData(activityData);

            }
            listActivityManager.setAllPreviousActivities();
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return listActivityManager;
    }


    private void setOptionalValues(CSVParser parser, CSVRecord record, Double regularCost, Integer minimumTime,     Double extraCost){
        if(parser.getHeaderMap().containsKey("Regular Cost")) {
            regularCost = Double.parseDouble(record.get("Regular Cost"));
        }
        if(parser.getHeaderMap().containsKey("Minimum Time")) {
            minimumTime = Integer.parseInt(record.get("Minimum Time"));
        }
        if(parser.getHeaderMap().containsKey("Maximum Cost")) {
            extraCost = Double.parseDouble(record.get("Maximum Cost"));
        }
    }



}
