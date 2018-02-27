package org.smartregister.configurableviews.sample.helper;

import android.content.Context;
import android.view.ViewGroup;

import com.avocarrot.json2view.DynamicView;

import org.json.JSONObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.helper.JsonSpecHelper;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.configurableviews.repository.ConfigurableViewsRepository;
import org.smartregister.configurableviews.sample.R;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ndegwamartin on 27/02/2018.
 */

public class ConfigurableViewsHelper {
    private static final String TAG = ConfigurableViewsHelper.class.getCanonicalName();

    private final ConfigurableViewsRepository configurableViewsRepository;

    private final JsonSpecHelper jsonSpecHelper;

    private final Context context;

    private boolean isTabletSize;

    private boolean jsonViewsEnabled;

    public ConfigurableViewsHelper(ConfigurableViewsRepository configurableViewsRepository, JsonSpecHelper jsonSpecHelper, Context context) {
        this.configurableViewsRepository = configurableViewsRepository;
        this.jsonSpecHelper = jsonSpecHelper;
        this.context = context;
        isTabletSize = context.getResources().getBoolean(R.bool.isTablet);
        if (ConfigurableViewsLibrary.getJsonSpecHelper().getMainConfiguration() != null)
            jsonViewsEnabled = ConfigurableViewsLibrary.getJsonSpecHelper().getMainConfiguration().isEnableJsonViews();
    }

    private final Map<String, ViewConfiguration> viewConfigurations = new ConcurrentHashMap<>();

    public void registerViewConfigurations(List<String> viewIdentifiers) {
        for (String viewIdentifier : viewIdentifiers) {
            String jsonString = configurableViewsRepository.getConfigurableViewJson(viewIdentifier);
            if (jsonString == null)
                continue;
            else
                viewConfigurations.put(viewIdentifier, jsonSpecHelper.getConfigurableView(jsonString));
        }
    }

    public ViewConfiguration getViewConfiguration(String identifier) {
        return viewConfigurations.get(identifier);
    }


    public android.view.View getDynamicView(ViewConfiguration viewConfiguration, ViewGroup viewParent) {
        try {
            JSONObject jsonView = new JSONObject(viewConfiguration.getJsonView());
            android.view.View view = DynamicView.createView(context, jsonView, viewParent);
            return view;
        } catch (Exception e) {
            return null;
        }
    }

    public android.view.View inflateDynamicView(ViewConfiguration viewConfiguration, ViewGroup viewParent, android.view.View fallbackLayout, boolean isVisible) {

        android.view.View json2View = getDynamicView(viewConfiguration, viewParent);
        if (fallbackLayout != null) {
            viewParent.removeView(fallbackLayout);
        }
        if (isVisible) {
            json2View = json2View != null ? json2View : fallbackLayout;
            viewParent.addView(json2View);
        }

        return json2View;
    }
}
