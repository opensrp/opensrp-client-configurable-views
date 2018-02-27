package org.smartregister.configurableviews.sample;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.lang3.CharEncoding;
import org.joda.time.DateTime;
import org.smartregister.util.DateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ndegwamartin on 26/02/2018.
 */

public class Utils {

    private static final String TAG = Utils.class.getCanonicalName();
    private static String BASE_PATH = "configs/views";

    public static String getJsonFile(Context context, String fileName) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(BASE_PATH + "/" + fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, CharEncoding.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public static String[] getAllJsonViewConfigFiles(Context context) {
        try {
            String[] langFiles = context.getResources().getAssets().list(BASE_PATH);


            return langFiles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatDateFromLong(long datetimeInMilliseconds, String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetimeInMilliseconds);
        return formatDate(calendar.getTime(), pattern);
    }


    public static String getFormattedAgeString(String dobString) {
        String formattedAge = "";
        if (!TextUtils.isEmpty(dobString)) {
            DateTime dateTime = new DateTime(dobString);
            Date dob = dateTime.toDate();
            long timeDiff = Calendar.getInstance().getTimeInMillis() - dob.getTime();

            if (timeDiff >= 0) {
                formattedAge = DateUtil.getDuration(timeDiff);
            }
        }
        return formattedAge.contains("y") ? formattedAge.substring(0, formattedAge.indexOf('y')) : formattedAge;
    }

    public static String getInitials(String fullname) {
        try {
            StringBuilder initials = new StringBuilder();
            for (String s : fullname.split(Constants.CHAR.SPACE)) {
                initials.append(s.charAt(0));
            }
            return initials.toString().toUpperCase();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    public static String getShortInitials(String fullname) {
        String initials = getInitials(fullname);
        return initials.length() > 2 ? initials.substring(0, 2) : initials;
    }


    public static String getTBTypeByCode(String tbCode) {
        if (tbCode.equals(Constants.PTB)) {
            return Constants.PULMONARY;
        } else if (tbCode.equals(Constants.EPTB)) {
            return Constants.EXTRA_PULMONARY;
        } else {
            return "";
        }
    }
    public static String formatIdentifier(String identifier) {
        if (identifier != null && !identifier.isEmpty()) {
            String cleanIdentifier = identifier.contains(Constants.CHAR.HASH) ? identifier.replaceAll(Constants.CHAR.HASH, Constants.CHAR.NO_CHAR) : identifier;
            return Constants.CHAR.HASH + cleanIdentifier;
        } else return Constants.CHAR.NO_CHAR;
    }

}
