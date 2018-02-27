package org.smartregister.configurableviews.service;

import android.app.IntentService;
import android.content.Intent;

import org.smartregister.Context;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.helper.ECSyncHelper;
import org.smartregister.configurableviews.util.Utils;

import java.util.Calendar;

import static org.smartregister.configurableviews.util.Constants.INTENT_KEY.LAST_SYNC_TIME_STRING;
import static org.smartregister.util.Log.logError;

/**
 * Created by SGithengi on 19/10/2017.
 * An {@link IntentService} subclass for handling asynchronous tasks for fetching the user configurable views
 * <p>
 */
public class PullConfigurableViewsIntentService extends IntentService {
    public static final String VIEWS_URL = "/rest/viewconfiguration/sync";

    private static final String TAG = PullConfigurableViewsIntentService.class.getCanonicalName();

    protected PullConfigurableViewsServiceHelper pullConfigurableViewsServiceHelper;

    public PullConfigurableViewsIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                int count = pullConfigurableViewsServiceHelper.processIntent();
                if (count > 0) {
                    //Broadcast Language event
                }

                //Broadcast view configuration Sync event

                //update last sync time
                String lastSyncTime = Utils.formatDate(Calendar.getInstance().getTime(), "MMM dd HH:mm");
                Utils.writePrefString(this, LAST_SYNC_TIME_STRING, lastSyncTime);

            } catch (Exception e) {
                logError(TAG + " Error fetching configurable Views");
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = ConfigurableViewsLibrary.getInstance().getContext();
        pullConfigurableViewsServiceHelper = new PullConfigurableViewsServiceHelper(getApplicationContext(),
                ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository(), context.getHttpAgent(),
                context.configuration().dristhiBaseURL(), ECSyncHelper.getInstance(getApplicationContext()),
                ConfigurableViewsLibrary.getInstance().getPassword() != null);
    }

}