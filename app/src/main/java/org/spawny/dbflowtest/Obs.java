package org.spawny.dbflowtest;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * Created by iaindownie on 23/06/2016.
 */
@Table(database = MyDatabase.class)
public class Obs extends ArchivableModel {


    @Column
    String subId;

    @Column
    String obsId;

    @Column
    String species;

    public void setSpecies(String species) {
        this.species = species;
    }

    @Column
    String dummy;

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public Obs() {
    }

    public Obs(int id, String obsId, String subId) {
        this.subId = subId;
        this.obsId = obsId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public void setObsId(String obsId) {
        this.obsId = obsId;
    }


}
