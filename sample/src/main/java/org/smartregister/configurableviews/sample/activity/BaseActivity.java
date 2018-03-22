package org.smartregister.configurableviews.sample.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.helper.ConfigurableViewsHelper;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.helper.RenderContactScreeningCardHelper;
import org.smartregister.configurableviews.sample.helper.RenderPatientDemographicCardHelper;
import org.smartregister.configurableviews.sample.helper.RenderPatientFollowupCardHelper;
import org.smartregister.configurableviews.sample.repository.SampleDataFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by ndegwamartin on 27/02/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private final Map<String, String> patientDetails = SampleDataFactory.getPatientDetails();

    private static final String TAG = BaseActivity.class.getCanonicalName();


    protected void renderDemographicsView(View view, Map<String, String> patientDetails) {

        RenderPatientDemographicCardHelper renderPatientDemographicCardHelper = new RenderPatientDemographicCardHelper(this, ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository());
        renderPatientDemographicCardHelper.renderView(view, patientDetails);

    }

    protected void renderFollowUpView(View view, Map<String, String> patientDetails) {

        RenderPatientFollowupCardHelper renderPatientFollowupCardHelper = new RenderPatientFollowupCardHelper(this, ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository());
        renderPatientFollowupCardHelper.renderView(view, patientDetails);

    }


    protected void renderContactScreeningView(View view, Map<String, String> patientDetails) {
        RenderContactScreeningCardHelper renderContactScreeningHelper = new RenderContactScreeningCardHelper(this, ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository());
        renderContactScreeningHelper.renderView(view, patientDetails);
    }

    protected int getCardViewIdentifierByConfiguration(String viewConfigurationIdentifier) {

        int res = 0;
        switch (viewConfigurationIdentifier) {
            case Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_DEMOGRAPHICS:
                res = R.id.clientDetailsCardView;
                break;
            case Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_CONTACT_SCREENING:
                res = R.id.clientContactScreeningCardView;
                break;
            case Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_FOLLOWUP:
                res = R.id.clientFollowupCardView;
                break;

            default:
                break;
        }
        return res;
    }

    protected void processViewConfigurations(View rootView) {
        try {
            String jsonString = ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository().getConfigurableViewJson(getViewConfigurationIdentifier());
            if (jsonString == null) {
                renderDefaultLayout(rootView);

            } else {
                ViewConfiguration detailsView = ConfigurableViewsLibrary.getInstance().getJsonSpecHelper().getConfigurableView(jsonString);
                List<org.smartregister.configurableviews.model.View> views = detailsView.getViews();
                if (!views.isEmpty()) {
                    Collections.sort(views, new Comparator<org.smartregister.configurableviews.model.View>() {
                        @Override
                        public int compare(org.smartregister.configurableviews.model.View viewA, org.smartregister.configurableviews.model.View viewB) {
                            return viewA.getResidence().getPosition() - viewB.getResidence().getPosition();
                        }
                    });

                    LinearLayout viewParent = (LinearLayout) rootView.findViewById(getContainerViewId());
                    for (org.smartregister.configurableviews.model.View componentView : views) {

                        try {
                            if (componentView.getResidence().getParent() == null) {
                                componentView.getResidence().setParent(detailsView.getIdentifier());
                            }

                            String jsonComponentString = ConfigurableViewsLibrary.getInstance().getConfigurableViewsRepository().getConfigurableViewJson(componentView.getIdentifier());
                            ViewConfiguration componentViewConfiguration = ConfigurableViewsLibrary.getJsonSpecHelper().getConfigurableView(jsonComponentString);
                            if (componentViewConfiguration != null) {

                                ConfigurableViewsHelper configurableViewsHelper = ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper();

                                View fallbackView = viewParent.findViewById(getCardViewIdentifierByConfiguration(componentViewConfiguration.getIdentifier()));
                                if (fallbackView != null) {
                                    viewParent.removeView(fallbackView);
                                }

                                View json2View = ConfigurableViewsLibrary.getJsonSpecHelper().isEnableJsonViews() ? configurableViewsHelper.inflateDynamicView(componentViewConfiguration, viewParent, fallbackView, componentView.isVisible()) : fallbackView;
                                if (componentView.isVisible()) {
                                    json2View.setTag(R.id.VIEW_CONFIGURATION_ID, getViewConfigurationIdentifier());

                                    if (!ConfigurableViewsLibrary.getJsonSpecHelper().isEnableJsonViews()) {
                                        viewParent.addView(json2View);
                                    }

                                    if (componentViewConfiguration.getIdentifier().equals(Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_DEMOGRAPHICS)) {

                                        renderDemographicsView(json2View, patientDetails);

                                    } else if (componentViewConfiguration.getIdentifier().equals(Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_FOLLOWUP)) {

                                        renderFollowUpView(json2View, patientDetails);
                                        Button followUpButton = (Button) json2View.findViewById(R.id.follow_up_button);
                                        followUpButton.setTag(R.id.CLIENT_ID, patientDetails.get(Constants.KEY._ID));
                                        followUpButton.setOnClickListener(this);
                                    } else if (componentViewConfiguration.getIdentifier().equals(Constants.CONFIGURATION.COMPONENTS.PATIENT_DETAILS_CONTACT_SCREENING)) {
                                        renderContactScreeningView(json2View, patientDetails);

                                        TextView addContactView = (TextView) json2View.findViewById(R.id.add_contact);
                                        addContactView.setTag(R.id.CLIENT_ID, patientDetails.get(Constants.KEY._ID));
                                        addContactView.setOnClickListener(this);

                                    }
                                }
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                } else {
                    renderDefaultLayout(rootView);
                }

            }
        } catch (Exception e) {

            Log.e(TAG, e.getMessage());
        }

    }

    protected void renderDefaultLayout(View rootView) {

        renderDemographicsView(rootView, patientDetails);
        renderContactScreeningView(rootView, patientDetails);
        renderFollowUpView(rootView, patientDetails);


    }

    protected abstract String getViewConfigurationIdentifier();

    private int getContainerViewId() {
        int id = 0;
        switch (getViewConfigurationIdentifier()) {
            case Constants.CONFIGURATION.PATIENT_DETAILS_INTREATMENT:
                id = R.id.content_intreatment_patient_detail_container;
                break;
            default:
                break;
        }
        return id;

    }


}