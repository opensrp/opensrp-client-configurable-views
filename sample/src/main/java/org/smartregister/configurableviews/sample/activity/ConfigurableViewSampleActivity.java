package org.smartregister.configurableviews.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.Utils;


public class ConfigurableViewSampleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurable_views_sample);
        View rootView = findViewById(R.id.sampleRootView);

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
}
