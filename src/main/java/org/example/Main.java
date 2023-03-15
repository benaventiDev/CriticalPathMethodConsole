package org.example;

import org.example.cmp.activity.Activity;
import org.example.cmp.datasource.ActivityCVSReader;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;
import org.example.cmp.exceptions.MissingInitActivityException;
import org.example.cmp.interfaces.IActivityProvider;
import org.example.cmp.path.Path;
import org.example.cmp.path.PathManager;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("/home/benaventi/Documents/cpm.csv");
        IActivityProvider provider = new ActivityCVSReader(file);
        List<Activity> activityList;
        try {
            activityList = provider.retrieveActivityListManager().getActivities();
            for (Activity activity: activityList){
                System.out.printf("%s: ", activity.getName());
                for (Activity prevAct: activity.getPrecedentActivities()){
                    System.out.printf("\t%s", prevAct.getName());
                }
                System.out.println();
            }
        } catch (DuplicatedActivityException | MissingActivityException | DuplicatedActivityPathException e) {
            throw new RuntimeException(e);
        }
        /*
        if(activityList != null){
            PathManager pathManager = new PathManager(activityList);
            try {
                List<Path> paths = pathManager.getPaths();
                for(Path path:paths){
                    path.print();
                }
            } catch (MissingInitActivityException | DuplicatedActivityPathException e) {
                throw new RuntimeException(e);
            }
        }*/


    }
}