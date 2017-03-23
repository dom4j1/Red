package com.dom.red.di.module;

import android.app.Activity;

import com.dom.red.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dom4j on 2017/3/7.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity providerActivity(){
        return mActivity;
    }
}
