package org.smartregister.configurableviews.helper;

import android.content.Context;
import android.preference.PreferenceManager;

import org.smartregister.configurableviews.util.Constants;

import static org.smartregister.configurableviews.util.Constants.LAST_VIEWS_SYNC_TIMESTAMP;
import static org.smartregister.configurableviews.util.Constants.VIEW_CONFIGURATION_PREFIX;

/**
 * Created by ndegwamartin on 21/02/2018.
 */

public class PreferenceHelper implements PrefsHelper {

    private final Context context;

    private static PreferenceHelper instance;

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    private PreferenceHelper(Context context) {
        this.context = context;
    }

    public long getLastViewsSyncTimeStamp() {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(LAST_VIEWS_SYNC_TIMESTAMP, 0);
    }

    public void updateLastViewsSyncTimeStamp(long lastSyncTimeStamp) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(LAST_VIEWS_SYNC_TIMESTAMP, lastSyncTimeStamp).commit();
    }


    public void updateLoginConfigurableViewPreference(String loginJson) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(VIEW_CONFIGURATION_PREFIX + Constants.CONFIGURATION.LOGIN, loginJson).commit();
    }


}
