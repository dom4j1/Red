package com.dom.red.di.component;

import android.app.Activity;

import com.dom.red.di.ActivityScope;
import com.dom.red.di.module.ActivityModule;
import com.dom.red.ui.activity.CommentActivity;
import com.dom.red.ui.activity.ContentActivity;
import com.dom.red.ui.activity.MainActivity;
import com.dom.red.ui.activity.ThemeActivity;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(ContentActivity mainActivity);

    void inject(CommentActivity commentActivity);

    void inject(ThemeActivity themeActivity);
}
