package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.Table;


/**
 * Created by iaindownie on 23/06/2016.
 */

@Table(database = MyDatabase.class)
public class Sub extends ArchivableModel {

    @Column
    public String subId;

//    @Column
//    @ForeignKey(
//            references = {@ForeignKeyReference(columnName = "locId",
//                    columnType = String.class,
//                    foreignKeyColumnName = "locId")},
//            saveForeignKeyModel = false)
//    ForeignKeyContainer<Loc> locModel;

    @ForeignKey(tableClass = Loc.class, saveForeignKeyModel = false,
            references = {@ForeignKeyReference(columnName = "locId", foreignKeyColumnName = "_ID")})
    public String locId;

    @ForeignKey(tableClass = Portal.class, stubbedRelationship = true,
            references = {@ForeignKeyReference(columnName = "portalID", foreignKeyColumnName = "_ID")})
    public String portalID;

}
