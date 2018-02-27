package org.smartregister.configurableviews.helper;

import android.content.Context;

import org.smartregister.configurableviews.util.Constants;
import org.smartregister.util.Utils;

import static org.smartregister.configurableviews.util.Constants.LAST_VIEWS_SYNC_TIMESTAMP;
import static org.smartregister.configurableviews.util.Constants.VIEW_CONFIGURATION_PREFIX;
import static org.smartregister.util.Utils.getPreference;

/**
 * Created by samuelgithengi on 12/19/17.
 */

public class ECSyncHelper {

    private final Context context;

    private static ECSyncHelper instance;

    public static ECSyncHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ECSyncHelper(context);
        }
        return instance;
    }

    private ECSyncHelper(Context context) {
        this.context = context;
    }

    public long getLastViewsSyncTimeStamp() {
        return Long.parseLong(getPreference(context, LAST_VIEWS_SYNC_TIMESTAMP, "0"));
    }

    public void updateLastViewsSyncTimeStamp(long lastSyncTimeStamp) {
        Utils.writePreference(context, LAST_VIEWS_SYNC_TIMESTAMP, lastSyncTimeStamp + "");
    }


    public void updateLoginConfigurableViewPreference(String loginJson) {
        Utils.writePreference(context, VIEW_CONFIGURATION_PREFIX + Constants.CONFIGURATION.LOGIN, loginJson);
    }


}
