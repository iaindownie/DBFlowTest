package org.spawny.dbflowtest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * @author @iaindownie on 14/10/2021.
 */

@Table(database = MyDatabase.class, insertConflict = ConflictAction.REPLACE, updateConflict = ConflictAction.REPLACE)
public class Portal extends ArchivableModel {

    public static final String DEFAULT_PORTAL_ID = "EBIRD";

    /**
     * Portal ID of portal
     */
    @Column
    String portalID;

    /**
     * Name of portal
     */
    @Column
    String name;

    @JsonIgnore
    List<Sub> submissions;

//    @OneToMany(methods = {OneToMany.Method.DELETE}, variableName = "submissions")
//    public List<Sub> getSubmissions() {
//        if (submissions == null) {
//            submissions = new Select()
//                    .from(Sub.class)
//                    .where(Sub$Table.locId.eq(portalID))
//                    .queryList();
//        }
//        return submissions;
//    }

}