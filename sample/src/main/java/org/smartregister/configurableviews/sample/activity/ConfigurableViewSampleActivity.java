package org.smartregister.configurableviews.sample.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.Utils;
import org.smartregister.configurableviews.service.PullConfigurableViewsIntentService;

public class ConfigurableViewSampleActivity extends BaseActivity {

    public static final String TAG = ConfigurableViewSampleActivity.class.getCanonicalName();
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurable_views_sample);
        rootView = findViewById(R.id.sampleRootView);

        processViewConfigurations(rootView);
    }


    @Override
    public void onClick(View view) {

        Utils.showToast(this, ((TextView) view).getText().toString());

    }

    @Override
    protected String getViewConfigurationIdentifier() {
        return Constants.CONFIGURATION.PATIENT_DETAILS_INTREATMENT;
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(syncCompleteMessageReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register observer(syncCompleteMessageReceiver) to receive the sync complete message via intent.
        // Filter with the action PullConfigurableViewsIntentService.EVENT_SYNC_COMPLETE
        LocalBroadcastManager.getInstance(this).registerReceiver(syncCompleteMessageReceiver, new IntentFilter(PullConfigurableViewsIntentService.EVENT_SYNC_COMPLETE));
    }

    // This Broadcast Receiver is the handler called whenever an Intent with an action named PullConfigurableViewsIntentService.EVENT_SYNC_COMPLETE is broadcast.
    private BroadcastReceiver syncCompleteMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Retrieve the extra data included in the Intent
            String lastSyncTime = intent.getStringExtra(org.smartregister.configurableviews.util.Constants.INTENT_KEY.LAST_SYNC_TIME_STRING);
            Log.d(TAG, "Last Sync time at " + lastSyncTime);
            processViewConfigurations(rootView);
            Utils.showToast(context, "Last Sync was at " + lastSyncTime);
        }
    };
}
