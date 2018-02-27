package org.smartregister.configurableviews.sample.helper;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.apache.commons.lang3.text.WordUtils;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.Utils;
import org.smartregister.configurableviews.sample.model.ScreenContact;
import org.smartregister.configurableviews.sample.repository.SampleDataFactory;
import org.smartregister.repository.BaseRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by ndegwamartin on 23/11/2017.
 */

public class RenderContactScreeningCardHelper extends BaseRenderHelper {

    private static String TAG = RenderContactScreeningCardHelper.class.getCanonicalName();

    public RenderContactScreeningCardHelper(Context context, BaseRepository detailsRepository) {
        super(context, detailsRepository);
    }

    @Override
    public void renderView(final View view, final Map<String, String> metadata) {
        new Handler().post(new Runnable() {

            @Override
            public void run() {

                FrameLayout contactViewTemplate = (FrameLayout) view.findViewById(R.id.clientContactFrameLayout);
                if (contactViewTemplate != null) {
                    ViewGroup contactsHolderView = (ViewGroup) view.findViewById(R.id.contactScreeningViewContactsHolder);
                    contactViewTemplate.setVisibility(View.GONE);
                    contactsHolderView.removeAllViews();
                    contactsHolderView.addView(contactViewTemplate);//Reinstate default guy for next reuse

                    List<ScreenContact> contacts = SampleDataFactory.getScreenContactSampleData();

                    FrameLayout contactView;

                    for (int i = 0; i < contacts.size(); i++) {
                        ScreenContactViewHelper screenContactViewHelper = new ScreenContactViewHelper(context, contactViewTemplate, contacts.get(i));
                        contactView = screenContactViewHelper.getScreenContactView();
                        contactView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Utils.showToast(context, getContactScreenMessage(((ScreenContact) view.getTag(R.id.CONTACT))));

                            }
                        });

                        contactsHolderView.addView(contactView);

                    }
                } else {
                    Log.e(TAG, "No Frame Layout contactViewTemplate found");
                }
            }

        });

    }

    private String getContactScreenMessage(ScreenContact screenContact) {
        String gender = screenContact.getGender();
        gender = gender == "male" ? "Man" : "Woman";
        gender = screenContact.getGender().equals("transgender") ? WordUtils.capitalize(screenContact.getGender()) : gender;
        return screenContact.getName() + ", a " + gender + " who is currently " + (screenContact.isNegative() ? "Negative" : WordUtils.capitalize(screenContact.getStage()));

    }
}