package com.dom.red.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by dom4j on 2017/3/26.
 */

public class SnakerbarUtil {

    public static void showShort(View parentView,String message){
        Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).setAction("DONE", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }

    public static void showShort(View parentView,String message, View.OnClickListener lisenter){
        Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT).setAction("DONE",lisenter).show();
    }
}
