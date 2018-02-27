package org.smartregister.configurableviews.sample;

/**
 * Created by ndegwamartin on 27/02/2018.
 */

public class Constants {
    public static class CONFIGURATION {
        public static final String PATIENT_DETAILS_INTREATMENT = "patient_details_intreatment";

        public static class COMPONENTS {
            public static final String PATIENT_DETAILS_DEMOGRAPHICS = "component_patient_details_demographics";
            public static final String PATIENT_DETAILS_CONTACT_SCREENING = "component_patient_details_contact_screening";
            public static final String PATIENT_DETAILS_FOLLOWUP = "component_patient_details_followup";
        }
    }

    public static final class GENDER {


        public static final String MALE = "male";
        public static final String FEMALE = "female";
        public static final String TRANSGENDER = "transgender";
    }

    public static final class SCREEN_STAGE {

        public static final String DIAGNOSED = "diagnosed";
        public static final String SCREENED = "screened";
        public static final String INTREATMENT = "intreatment";
    }

    public static final class KEY {
        public static final String _ID = "_id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_ENCOUNTER = "first_encounter";
        public static final String DOB = "dob";
        public static final String GENDER = "gender";
        public static final String PARTICIPANT_ID = "participant_id";
        public static final String NEXT_VISIT_DATE = "next_visit_date";
        public static final String PATIENT_TYPE = "patient_type";
        public static final String SITE_OF_DISEASE = "site_of_disease";

    }

    public static final String PTB = "ptb";
    public static final String EPTB = "eptb";
    public static final String PULMONARY = "Pulmonary";
    public static final String EXTRA_PULMONARY = "Extra Pulmonary";


    public static class CHAR {
        public static final String SPACE = " ";
        public static final String HASH = "#";
        public static final String NO_CHAR = "";
        public static final String UNDERSCORE = "_";
    }

}
