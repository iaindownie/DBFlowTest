package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * @author @iaindownie on 07/06/2021.
 */

@Migration(database = MyDatabase.class, version = 3)
public class M3AddDummyToObs


        extends AlterTableMigration<Obs> {


    public M3AddDummyToObs() {
        super(Obs.class);
    }

    @Override
    public void onPreMigrate() {
        addColumn(SQLiteType.TEXT, "Dummy");
    }


}