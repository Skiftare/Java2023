package edu.project4.systeminteraction;

import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormatter {
    static String getCurrentDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm");
        return dateFormat.format(new Date());
    }
}
