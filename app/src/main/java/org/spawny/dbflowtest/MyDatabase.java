package org.spawny.dbflowtest;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.trigger.CompletedTrigger;
import com.raizlabs.android.dbflow.sql.trigger.Trigger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iaindownie on 23/06/2016.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION, generatedClassSeparator = "$")
public class MyDatabase {

    public static final String NAME = "MyDatabase"; // we will add the .db extension
    public static final int VERSION = 3;
    private static MyDatabase myDatabase;

    private MyDatabase() {
        super();
        createTriggers();
    }

    public static void initializeDatabase() {
        if (myDatabase == null) {
            myDatabase = new MyDatabase();
        } else {
            throw new IllegalStateException("Database already initialized");
        }
    }

    private void createTriggers() {
        try {
            for (Class<?> modelClass : getEBirdModelClassList()) {
                CompletedTrigger createTrigger = createdTimestampTrigger(modelClass);
                createTrigger.disable();
                createTrigger.enable();
            }

            Log.d("INFO", "Created triggers");
        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }

    public static CompletedTrigger createdTimestampTrigger(Class modelClass) {
        String currentSQLTimeInMSecs = "CAST(strftime('%s','now') AS INTEGER) * 1000";


        return Trigger.create("SomeTrigger")
                .after().insert(modelClass).begin(new Update<>(modelClass)
                        .set(Condition.column(NameAlias.builder("userId").build()).eq(currentSQLTimeInMSecs))
                        .where(Condition.column(NameAlias.builder("userId").build()).isNull()));
    }

    public List<Class<?>> getEBirdModelClassList() {
        List<Class<?>> eBirdModelClassList = new ArrayList<>();
        for (String tableName : getDatabaseTableNameList()) {
            try {
                String className = "org.spawny.dbflowtest." + tableName;
                Class c = Class.forName(className);

                    eBirdModelClassList.add(c);

            } catch (ClassNotFoundException e) {
                // No need to do anything. If the class isn't found it is a database table
                // that has no corresponding model class.
            }
        }

        return eBirdModelClassList;
    }


    public List<String> getDatabaseTableNameList() {

        List<String> tableNames = new ArrayList<>();
        tableNames.add("Loc");
        return tableNames;
    }

}