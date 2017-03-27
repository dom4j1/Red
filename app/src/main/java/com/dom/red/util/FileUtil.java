package com.dom.red.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dom4j on 2017/3/27.
 */

public class FileUtil {
    public static Uri saveBitmapToFile(String url,Bitmap bitmap, View container){
        String fileName = url.substring(url.lastIndexOf("/"),url.lastIndexOf(".")) + ".png";
        File fileDir = new File(Constants.PATH_DATA);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        File imageFile = new File(fileDir,fileName);
        Uri uri = Uri.fromFile(imageFile);
        if (imageFile.exists()) {
            return uri;
        }
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean isCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            if (isCompress) {
                SnakerbarUtil.showShort(container,"保存妹纸成功n(*≧▽≦*)n");
            } else {
                SnakerbarUtil.showShort(container,"保存妹纸失败ヽ(≧Д≦)ノ");
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            SnakerbarUtil.showShort(container,"保存妹纸失败ヽ(≧Д≦)ノ");
        }
        return uri;
    }
}
