package org.spawny.dbflowtest;

/**
 * @author @iaindownie on 08/06/2021.
 */

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract model to require JSON handling methods in the object.
 */
public interface JsonModel {

    /**
     * Method that should overwrite instance variables with data from JSON object.
     *
     * Must be implemented for JSON support. Takes a JSON object to process and should update self.
     * Implementations of this method should only load the data and not save it if this is backed
     * by a database.
     *
     * @param jsonObject JSON object representation of this model
     * @throws JSONException Thrown when querying the JSON object fails
     */
    void loadFromJsonObject( JSONObject jsonObject ) throws JSONException;

}