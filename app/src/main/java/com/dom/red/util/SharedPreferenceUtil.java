package com.dom.red.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.dom.red.app.App;

/**
 * Created by dom4j on 2017/3/10.
 */

public class SharedPreferenceUtil {

    private static final String SHAREDPREFERENCES_NAME = "red";

    public static SharedPreferences getAppSp() {
        return App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean getBoolean(String key,boolean defValue){
        return App.getSp().getBoolean(key,defValue);
    }

    public static void putBoolean(String key,boolean value){
        App.getSp().edit().putBoolean(key,value).commit();
    }


}
