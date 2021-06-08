package org.spawny.dbflowtest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.raizlabs.android.dbflow.sql.language.Method.count;


public class MainActivity extends Activity {


    private String rawResponse = "";
    private Locale current;
    //private String localString = "en_US";

    SharedPreferences prefs;

    private static MainActivity ins;


    public static MainActivity getInstance() {
        return ins;
    }

    public void doTask(final String t) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(ins, t + ": " + java.util.Locale.getDefault().getLanguage(), Toast.LENGTH_SHORT).show();
                new DownloadBreedingCodesTask().execute();
                Log.d("INFO", "**** End of doTask");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("INFO", "**** onCreate");
        ins = this;

        current = getResources().getConfiguration().locale;
        //System.out.println("OnCreate:" + current.getDefault().toString());

        //prefs = getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = prefs.edit().putString(
        //        "MYLOCALE", current.getDefault().toString());
        //editor.apply();


        /*if(new Select().from(Obs.class).queryList().contains("null")) {
            SQLite.update(Obs.class)
                    .set(Obs_Table.species.eq("Wren"))
                    .where(Obs_Table.obsId.eq("OBS303"))
                    .execute();
            SQLite.update(Obs.class)
                    .set(Obs_Table.species.eq("Blackbird"))
                    .where(Obs_Table.obsId.eq("OBS301"))
                    .execute();
            SQLite.update(Obs.class)
                    .set(Obs_Table.species.eq("Blue Tit"))
                    .where(Obs_Table.obsId.eq("OBS302"))
                    .execute();
            SQLite.update(Obs.class)
                    .set(Obs_Table.species.eq("Great Tit"))
                    .where(Obs_Table.obsId.eq("OBS300"))
                    .execute();

        }*/

        /*Where update = new Update<>(Obs.class).set(Obs_Table.species.eq("Great Tit"))
                .where(Obs_Table.obsId.eq("OBS300"));
        update.execute();
        update = new Update<>(Obs.class).set(Obs_Table.species.eq("Blackbird"))
                .where(Obs_Table.obsId.eq("OBS301"));
        update.execute();
        update = new Update<>(Obs.class).set(Obs_Table.species.eq("Blue Tit"))
                .where(Obs_Table.obsId.eq("OBS302"));
        update.execute();
        update = new Update<>(Obs.class).set(Obs_Table.species.eq("Wren"))
                .where(Obs_Table.obsId.eq("OBS303"));
        update.execute();*/

        /*List<Obs> obs1 = new Select().from(Obs.class).queryList();
        for (Obs anObs : obs1) {
            if (anObs.obsId.equals("OBS300")) {
                anObs.species = "Wren";
                anObs.save();
            }
        }*/

        List<Loc> locs = new Select().from(Loc.class).queryList();
        //Log.i("INFO", "**** nLocs: " + locs.size());
        for (Loc loc : locs) {
            Log.i("INFO", "**** Loc: " + loc.locId);
        }

        List<Sub> subs = new Select().from(Sub.class).queryList();
        //Log.i("INFO", "**** nSubs: " + subs.size());
        for (Sub sub : subs) {
            Log.i("INFO", "**** Sub: " + sub.subId);
        }

        List<Obs> obs = new Select().from(Obs.class).queryList();
        //Log.i("INFO", "**** nObs: " + obs.size());
        for (Obs anObs : obs) {
            Log.i("INFO", "**** Obs: " + anObs.obsId);
        }

        List<Obs> aList = new Select().from(Obs.class).queryList();

        for (Obs o : aList) {
            //Log.i("INFO", "**** Species Obs: " + o.species);
        }

        List<Sub> bList = new Select()
                .from(Sub.class)
                .leftOuterJoin(Loc.class)
                .on(Sub_Table.locId.withTable().eq(Loc_Table.locId.withTable()))
                .where(Loc_Table.userId.withTable().eq("Dave"))
                .queryList();

        for (Sub sub : bList) {
            Log.i("INFO", "**** Refined Subs: " + sub.subId);
        }

        long locCount = SQLite.select(count(Loc_Table.locId))
                .from(Loc.class).count();

        Log.d("INFO", "**** Loc count: " + locCount);

        //new DownloadBreedingCodesTask().execute();
        //System.out.println("*****ISD:" + rawResponse.toString());

        List<BreedingCode> breedingCodes = new Select().from(BreedingCode.class)
                .queryList();

        for (BreedingCode bc : breedingCodes) {
            Log.i("INFO", "**** Breeding Codes: " + bc.getName());
        }

        System.out.println("**** isFieldExist: " + this.isFieldExist("BreedingCode", "code"));

        //For testing
        User user = createDummyUser();
        //System.out.println(breedingCodes.get(0).getCode());
        //this.writeJSON(breedingCodes.get(0));
        //System.out.println(breedingCodes.get(0).toString());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            System.out.println(jsonInString);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean isFieldExist(String tableName, String fieldName) {
        boolean isExist = true;
        DatabaseWrapper db = FlowManager.getDatabase("MyDatabase").getWritableDatabase();
        Cursor res = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);

        int value = res.getColumnIndex(fieldName);

        ArrayList a = new ArrayList();
        //int i = 0;
        while (res.moveToNext()) {
            a.add(res.getString(1));
            //i ++;
        }

        if (!a.contains(fieldName)) {
            isExist = false;
        }
        return isExist;
    }


    public void writeJSON(BreedingCode u) {

        ResourceConverter rc = new ResourceConverter(BreedingCode.class);

        try {
            System.out.println("*****" + new String(rc.writeObject(u)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        /*ResourceConverter converter = new ResourceConverter(BreedingCode.class);
        try {
            byte[] rawData = converter.writeObject(bc);
            System.out.println("**** " + rawData.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }


    private class DownloadBreedingCodesTask extends AsyncTask<String, Void, String> {

        // can use UI thread here
        protected void onPreExecute() {
            Log.d("INFO", "*****ISD2: Calling DownloadBreedingCodesTask");
            Delete.table(BreedingCode.class, BreedingCode_Table.code.isNull());
        }

        // automatically done on worker thread (separate from UI thread)
        protected String doInBackground(final String... args) {
            try {
                //String path = "https://test.ebird.org/ws2.0/breedingcodes?key=t23ijvu0tqhr&fmt=jsonapi";
                String path = urlForRequest();
                rawResponse = downloadBreedingCodesURL(path);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        // can use UI thread here
        protected void onPostExecute(final String result) {
            Log.d("INFO", "*****ISD2:" + rawResponse.toString());
            ResourceConverter converter = new ResourceConverter(BreedingCode.class);

            // To convert raw data into single POJO
            //JSONAPIDocument<BreedingCode> bcDocument = converter.readDocument(rawResponse.getBytes(), BreedingCode.class);
            //BreedingCode book = bcDocument.get();

            // To convert raw data into collection

            JSONAPIDocument<List<BreedingCode>> breedingCollection = converter.readDocumentCollection(rawResponse.getBytes(), BreedingCode.class);
            List<BreedingCode> breedCollection = breedingCollection.get();
            Log.d("INFO", breedCollection.size() + ": " + java.util.Locale.getDefault().getLanguage());
            for (BreedingCode bc : breedCollection) {
                //Log.i("INFO", "**** Loaded new breedingcodes: " + bc.getName());
                bc.save();
            }

            List<BreedingCode> breedingCodes = new Select().from(BreedingCode.class)
                    .queryList();

            for (BreedingCode bcs : breedingCodes) {
                Log.d("INFO", "**** BCode: " + bcs.getCode() + ":" + bcs.getName());
            }

        }
    }


    public String downloadBreedingCodesURL(String path) throws Exception {
        String str = "";
        HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpGet myConnection = new HttpGet(path);
        //myConnection.setHeader("X-eBirdApiToken","o3ql6potafe5");
        Hashtable heads = (Hashtable) getHeaders();
        ArrayList a = new ArrayList(heads.keySet());
        for (int i = 0; i < a.size(); i++) {
            String k = (String) a.get(i);
            myConnection.setHeader(k, (String) heads.get(k));
            System.out.println(k + " " + (String) heads.get(k));
        }
        Log.d("INFO", "MyConn:" + myConnection.getURI().toString());
        try {
            response = myClient.execute(myConnection);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                str = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                Log.e("ERROR", "Failed to extract codes...", new Throwable());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Creates a URI Builder and populates it with configuration shared by all API requests.
     *
     * @return URL Builder with the scheme, hostname, and shared path already set.
     */
    public static Uri.Builder uriBuilder(String apiVersion) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        if (apiVersion.equals("1.1")) {
            builder.authority("test.ebird.org");
            builder.appendPath("ws1.1");
        } else if (apiVersion.equals("2.0")) {
            builder.authority("test.ebird.org");
            builder.appendPath("ws2.0");
        }
        return builder;
    }

    private static String urlForRequest() {
        Uri.Builder builder = uriBuilder("2.0");
        //builder.appendPath("portals.json");
        builder.appendPath("breedingcodes.jsonapi");

        return builder.build().toString();
    }

    public Map<String, String> getHeaders() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put("X-eBirdApiToken", "o3ql6potafe5");
        headers.put("Accept-Language", java.util.Locale.getDefault().getLanguage());
        return headers;
    }

    private static User createDummyUser() {

        User user = new User();

        user.setName("mkyong");
        user.setAge(33);

        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");

        user.setMessages(msg);

        return user;

    }


}
