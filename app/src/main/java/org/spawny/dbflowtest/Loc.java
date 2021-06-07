package org.spawny.dbflowtest;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by iaindownie on 23/06/2016.
 */

@Table(database = MyDatabase.class)
public class Loc extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    String locId;

    @Column
    String userId;

    public Loc() {
    }

    public Loc(int id, String locId, String userId) {
        this.id = id;
        this.locId = locId;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}