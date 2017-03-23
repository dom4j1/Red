package com.dom.red.util;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dom.red.R;
import com.dom.red.app.App;


/**
 * Created by dom4j on 2017/3/7.
 */
public class ToastUtil {

    private static Toast mToast;
    private static TextView mText;
    private static Toast mToast_image;
    private static TextView mImage;

    public static void init(){
        if(mToast == null){
            View view = View.inflate(App.getInstance(),R.layout.toast_white,null);
            mText = (TextView) view.findViewById(R.id.tv_toast_msg);
            mToast = new Toast(App.getInstance());
            mToast.setView(view);
            mToast.setGravity(Gravity.BOTTOM,0,50);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
    }

    public static void shortToast(String msg){
        init();
        mText.setText(msg);
        mToast.show();
    }

    public static void showImageToast(int msg){
          if(mToast_image == null){
              View view  = View.inflate(App.getInstance(),R.layout.toast_image,null);
              mImage = (TextView) view.findViewById(R.id.tv_toast_image);
              mToast_image = new Toast(App.getInstance());
              mImage.setText(" " + msg +" + 1");
              mToast_image.setView(view);
              mToast_image.setGravity(Gravity.BOTTOM,0,200);
              mToast_image.setDuration(Toast.LENGTH_SHORT);
          }
        mToast_image.show();
    }
}
