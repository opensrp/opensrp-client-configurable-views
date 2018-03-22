package org.smartregister.configurableviews.sample.repository;

import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.configurableviews.sample.model.ScreenContact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.smartregister.configurableviews.sample.Constants.EPTB;

/**
 * Created by ndegwamartin on 27/02/2018.
 */

public class SampleDataFactory {
    public static List<ScreenContact> getScreenContactSampleData() {
        List<ScreenContact> contacts = new ArrayList<>();
        contacts.add(new ScreenContact("1", "MN", Constants.GENDER.MALE, Constants.SCREEN_STAGE.SCREENED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("2", "EK", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("3", "LL", Constants.GENDER.MALE, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("4", "JZ", Constants.GENDER.MALE, Constants.SCREEN_STAGE.INTREATMENT, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("5", "ES", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.SCREENED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("6", "ZP", Constants.GENDER.TRANSGENDER, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("7", "MB", Constants.GENDER.MALE, Constants.SCREEN_STAGE.SCREENED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("8", "PL", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.INTREATMENT, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("9", "MT", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.INTREATMENT, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("10", "NI", Constants.GENDER.MALE, Constants.SCREEN_STAGE.SCREENED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("11", "MZ", Constants.GENDER.MALE, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("12", "TO", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.SCREENED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("13", "KK", Constants.GENDER.TRANSGENDER, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("14", "OB", Constants.GENDER.MALE, Constants.SCREEN_STAGE.DIAGNOSED, !isPrimeNumber(contacts.size())));
        contacts.add(new ScreenContact("15", "SG", Constants.GENDER.FEMALE, Constants.SCREEN_STAGE.INTREATMENT, !isPrimeNumber(contacts.size())));
        return contacts;
    }

    private static boolean isPrimeNumber(int number) {

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, String> getPatientDetails() {
        Map<String, String> patientDetails = new HashMap<>();

        patientDetails.put(Constants.KEY.PARTICIPANT_ID, "23232");
        patientDetails.put(Constants.KEY.SITE_OF_DISEASE, EPTB);
        patientDetails.put(Constants.KEY.PATIENT_TYPE, "new");
        patientDetails.put(Constants.KEY.FIRST_NAME, "Marion");
        patientDetails.put(Constants.KEY.LAST_NAME, "Wakanda");
        patientDetails.put(Constants.KEY.GENDER, "transgender");
        patientDetails.put(Constants.KEY.DOB, "1984-01-30T21:00:00.000Z");
        patientDetails.put(Constants.KEY.FIRST_ENCOUNTER, "2018-01-31T06:55:50.123Z");
        patientDetails.put(Constants.KEY.NEXT_VISIT_DATE, "2018-02-08");

        return patientDetails;

    }
}
