package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by iaindownie on 23/06/2016.
 */

@Table(database = MyDatabase.class)
public class Sub extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    String subId;

    @Column
    String locId;

    public Sub() {
    }

    public Sub(int id, String subId, String locId) {
        this.id = id;
        this.subId = subId;
        this.locId = locId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
    }
}
