package org.spawny.dbflowtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by iaindownie on 07/11/2016.
 */
public class LocaleChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            MainActivity.getInstance().doTask("Locale Changed: Action being taken");
        } catch (Exception e) {

        }
    }
}