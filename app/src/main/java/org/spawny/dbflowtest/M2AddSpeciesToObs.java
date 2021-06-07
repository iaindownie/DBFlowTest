package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

/**
 * Created by iaindownie on 23/06/2016.
 */
@Migration(database = MyDatabase.class, version = 2)
public class M2AddSpeciesToObs extends AlterTableMigration<Obs> {

    public M2AddSpeciesToObs() {
        super(Obs.class);
    }

    @Override
    public void onPreMigrate() {
        addColumn(SQLiteType.TEXT, "Species");
    }

}