package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by iaindownie on 23/06/2016.
 */
@Database(name = MyDatabase.NAME, version = 1)
public class MyDatabase {

    public static final String NAME = "MyDatabase"; // we will add the .db extension

}