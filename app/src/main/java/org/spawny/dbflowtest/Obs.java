package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by iaindownie on 23/06/2016.
 */
@Table(database = MyDatabase.class)
public class Obs extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    String subId;

    @Column
    String obsId;

    @Column
    String species;

    @Column
    public String dummy;

    public Obs() {
    }

    public Obs(int id, String obsId, String subId) {
        this.id = id;
        this.subId = subId;
        this.obsId = obsId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public void setObsId(String obsId) {
        this.obsId = obsId;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
