package com.dom.red.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.dom.red.di.component.AppComponent;
import com.dom.red.di.component.DaggerAppComponent;
import com.dom.red.di.module.AppModule;
import com.dom.red.util.Constants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dom4j on 2017/3/7.
 */

public class App extends Application{

    private static App instance;

    private Set<Activity> allActivities;
    private static AppComponent appComponent;
    private static SharedPreferences mSp;


    public static synchronized App getInstance(){return  instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);//初始化框架
        instance = this;
        mSp = App.getInstance().getSharedPreferences(Constants.SP, Activity.MODE_PRIVATE);
        QbSdk.initX5Environment(instance,null);

    }

    public void addActivity(Activity act){
        if(allActivities == null){
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act){
         if(allActivities != null){
             allActivities.remove(act);
         }
    }

    public void exitApp(){
        if(allActivities != null){
            synchronized (allActivities){
                for(Activity act : allActivities){
                    act.finish();
                }
            }
        }
        /**
         * 绕过Activity 生命周期  强制关闭
         */
        //android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if(appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

    public static SharedPreferences getSp(){
        return mSp;
    }

}
