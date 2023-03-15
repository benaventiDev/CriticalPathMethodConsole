package org.example.cmp.datasource;

import lombok.Getter;
import org.example.cmp.activity.ActivityFactory;
import org.example.cmp.activity.ActivityListManager;
import org.example.cmp.exceptions.DuplicatedActivityException;
import org.example.cmp.exceptions.DuplicatedActivityPathException;
import org.example.cmp.exceptions.MissingActivityException;
import org.example.cmp.interfaces.IActivityProvider;

import java.io.File;

public abstract class ActivityFileReader implements IActivityProvider {
    @Getter
    private File file;
    @Getter
    private ActivityFactory activityFactory;

    public ActivityFileReader(File file) {
        this.file = file;
        this.activityFactory = new ActivityFactory();
    }

    protected abstract ActivityListManager read() throws DuplicatedActivityException, MissingActivityException, DuplicatedActivityPathException;


    @Override
    public ActivityListManager retrieveActivityListManager()  throws DuplicatedActivityException, MissingActivityException, DuplicatedActivityPathException {
        return read();
    }
}
