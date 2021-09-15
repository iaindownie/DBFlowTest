package org.spawny.dbflowtest;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Index;
import com.raizlabs.android.dbflow.annotation.IndexGroup;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;

/**
 * Created by iaindownie on 23/06/2016.
 */

@Table(database = MyDatabase.class,
        indexGroups = {
                @IndexGroup(number = 1, unique = true, name = "LocLocIdIndex")
        })
public class Loc extends ArchivableModel {

    @Index(indexGroups = 1)
    @Column
    String locId;

    @Column
    String userId;

    public Loc() {
    }

    public Loc(int id, String locId, String userId) {
        this.locId = locId;
        this.userId = userId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}