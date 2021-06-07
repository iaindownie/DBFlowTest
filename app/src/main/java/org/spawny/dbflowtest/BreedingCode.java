package org.spawny.dbflowtest;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by iaindownie on 06/30/2016.
 * Class to hold BreedingCode and BreedingText
 */
@Table(database = MyDatabase.class)
public class BreedingCode extends BaseModel {

    @Column
    @PrimaryKey
    String id;

    @Column
    @Unique
    String code;

    @Column
    String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}