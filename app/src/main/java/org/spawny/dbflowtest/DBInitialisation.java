package org.spawny.dbflowtest;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by iaindownie on 23/06/2016.
 */
public class DBInitialisation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        MyDatabase.initializeDatabase();



        // Create loc
        Loc aLoc = new Loc(1, "LOC100", "Iain");
        aLoc.save();
        aLoc = new Loc(2, "LOC101", "Iain");
        aLoc.save();
        aLoc = new Loc(3, "LOC102", "Dave");
        aLoc.save();
/*
        // Create sub
        Sub aSub = new Sub(1, "SUB200", "LOC100");
        aSub.save();
        aSub = new Sub(2, "SUB201", "LOC100");
        aSub.save();
        aSub = new Sub(3, "SUB202", "LOC101");
        aSub.save();
        aSub = new Sub(4, "SUB204", "LOC102");
        aSub.save();



        // Create obs
        Obs anObs = new Obs(1, "OBS300", "SUB200");
        anObs.save();
        anObs = new Obs(2, "OBS301", "SUB200");
        anObs.save();
        anObs = new Obs(3, "OBS302", "SUB202");
        anObs.save();
        anObs = new Obs(4, "OBS303", "SUB203");
        anObs.save();


         */

    }
}