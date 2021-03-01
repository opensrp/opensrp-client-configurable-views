package org.smartregister.configurableviews.sample.service;

import android.app.IntentService;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.repository.ConfigurableViewsRepository;
import org.smartregister.configurableviews.sample.Utils;
import org.smartregister.configurableviews.service.PullConfigurableViewsIntentService;
import org.smartregister.configurableviews.util.Constants;

import java.util.Calendar;

import static org.smartregister.util.Log.logError;

/**
 * Created by ndegwamartin on 27/02/2018.
 * <p>
 * This Class is for Demo Purpose only. The Class you would normally use is PullConfigurableViewsIntentService contained in the opensrp-configurable-views library
 * The PullConfigurableViewsIntentService class is wired to fetch data from the '/rest/viewconfiguration/sync' endpoint. You could override this and also
 * add custom behaviour as we have done with this Sample/Demo class.
 * <p>
 * By default, the PullConfigurableViewsIntentService broadcasts a SyncComplete Event using LocalBroadcastManager once it fetches server data
 */

public class SampleService extends IntentService {

    private static final String TAG = SampleService.class.getCanonicalName();
    private ConfigurableViewsRepository repository;

    public SampleService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                JSONArray viewConfigData = populateExampleViewConfigData();


                if (viewConfigData.length() > 0) {

                    //Save the view configuration data locally
                    repository.saveConfigurableViews(viewConfigData);

                    //Here you can Broadcast event if new records were processed
                }

                //Here you can Broadcast view configuration Sync event has finished

                Intent broadCastIntent = new Intent(PullConfigurableViewsIntentService.EVENT_SYNC_COMPLETE); //broadcast useful meta data
                intent.putExtra(Constants.INTENT_KEY.SYNC_TOTAL_RECORDS, viewConfigData.length()); //broadcast total records

                //broadcast last sync time
                String lastSyncTime = org.smartregister.configurableviews.util.Utils.formatDate(Calendar.getInstance().getTime(), "MMM dd HH:mm");

                broadCastIntent.putExtra(Constants.INTENT_KEY.LAST_SYNC_TIME_STRING, lastSyncTime);
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadCastIntent);

            } catch (Exception e) {
                logError(TAG + " Error fetching configurable Views");
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repository = ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository();
    }


    /**
     * DEMO Purposes ONLY!
     * Method used to Mock data as would be fetched from the Server
     */
    private JSONArray populateExampleViewConfigData() {
        JSONArray jsonArray = new JSONArray();
        try {

            String[] viewConfigFiles = Utils.getAllJsonViewConfigFiles(this);

            jsonArray = new JSONArray();

            for (int i = 0; i < viewConfigFiles.length; i++) {
                String filename = viewConfigFiles[i];
                jsonArray.put(new JSONObject(Utils.getJsonFile(this, filename)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
