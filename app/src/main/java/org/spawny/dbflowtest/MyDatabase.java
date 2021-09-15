package org.spawny.dbflowtest;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Index;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.property.IndexProperty;
import com.raizlabs.android.dbflow.sql.trigger.CompletedTrigger;
import com.raizlabs.android.dbflow.sql.trigger.Trigger;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

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
        createTriggers(getDatabase());
        DatabaseUtilities.createIndices(getDatabase());
    }

    public static void initializeDatabase() {
        if (myDatabase == null) {
            myDatabase = new MyDatabase();
        } else {
            throw new IllegalStateException("Database already initialized");
        }
    }

    /**
     * Returns the database instance, creating it if necessary
     *
     * @return Instance of the database
     */
    public static MyDatabase getInstance() {
        if (myDatabase == null) {
            initializeDatabase();
        }
        return myDatabase;
    }

    public static DatabaseWrapper getDatabase(){
        return FlowManager.getDatabase(NAME).getWritableDatabase();
    }

    public static DatabaseDefinition getDbDefinition(){
        return FlowManager.getDatabase(MyDatabase.NAME);
    }

    /**
     * Creates indexes on the species code columns for faster checklist filtering.
     */
//    private void createIndexes() {
//        try {
//            new Index<Loc>("LocLocIdIndex");
//            //Log.d("Created DB indexes");
//        } catch (Exception e) {
//            Log.e("ERROR", "Failed to create DB indexes");
//        }
//    }

    private void createTriggers(DatabaseWrapper db) {

        db.execSQL("PRAGMA recursive_triggers = OFF;");

        try {
            for (Class<? extends BaseModel> modelClass : DatabaseUtilities.getEBirdModelClassList()) {
                DatabaseUtilities.createdTimestampTrigger(db, modelClass);
                DatabaseUtilities.updatedTimestampTrigger(db, modelClass);
            }
        } catch (Exception e) {
            Log.d("ERROR", "Failed to create triggers");
        }
    }

//    public static CompletedTrigger createdTimestampTrigger(Class modelClass) {
//
//        String currentSQLTimeInMSecs = "CAST(strftime('%s','now') AS INTEGER) * 1000";
//
//        long now = System.currentTimeMillis();
//        Condition createdAt = Condition.column(NameAlias.builder("createdAt").build()).eq((Object) currentSQLTimeInMSecs);
//        Condition updatedAt = Condition.column(NameAlias.builder("updatedAt").build()).eq((Object) currentSQLTimeInMSecs);
//
//
//        return Trigger.create("CreatedAtTriggerOn" + modelClass.getSimpleName())
//                .after().insert(modelClass).begin(new Update<>(modelClass)
//                        .set(createdAt, updatedAt)
//                        .where(Condition.column(NameAlias.builder("createdAt").build()).isNull()));
//    }
//
//    public static CompletedTrigger updatedTimestampTrigger(Class modelClass) {
//
//        String currentSQLTimeInMSecs = "CAST(strftime('%s','now') AS INTEGER) * 1000";
//
//        Condition updatedAt = Condition.column(NameAlias.builder("updatedAt").build()).eq((Object) currentSQLTimeInMSecs);
//
//
//        return Trigger.create("UpdatedAtTriggerOn" + modelClass.getSimpleName())
//                .after().update(modelClass).begin(new Update<>(modelClass)
//                        .set(updatedAt)
//                        .where(Condition.column(NameAlias.builder("old._ID").build()).eq("_ID")));
//    }


//    public List<Class<?>> getEBirdModelClassList() {
//        List<Class<?>> eBirdModelClassList = new ArrayList<>();
//        for (String tableName : getDatabaseTableNameList()) {
//            try {
//                String className = "org.spawny.dbflowtest." + tableName;
//                Class c = Class.forName(className);
//
//                eBirdModelClassList.add(c);
//
//            } catch (ClassNotFoundException e) {
//                // No need to do anything. If the class isn't found it is a database table
//                // that has no corresponding model class.
//            }
//        }
//
//        return eBirdModelClassList;
//    }


//    public List<String> getDatabaseTableNameList() {
//
//        List<String> tableNames = new ArrayList<>();
//        tableNames.add("Loc");
//        tableNames.add("Sub");
//        return tableNames;
//    }

}