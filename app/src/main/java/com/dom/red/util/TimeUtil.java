package com.dom.red.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dom4j on 2017/3/16.
 */

public class TimeUtil {
    public static String timeFormat(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }
}
