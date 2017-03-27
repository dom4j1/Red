package com.dom.red.di.component;

import android.app.Activity;

import com.dom.red.di.ActivityScope;
import com.dom.red.di.module.ActivityModule;
import com.dom.red.ui.gank.activity.FuliActivity;
import com.dom.red.ui.gank.activity.HomeActivity;
import com.dom.red.ui.zhihu.activity.CommentActivity;
import com.dom.red.ui.zhihu.activity.ContentActivity;
import com.dom.red.ui.main.activity.MainActivity;
import com.dom.red.ui.zhihu.activity.SectionActivity;
import com.dom.red.ui.zhihu.activity.ThemeActivity;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(ContentActivity mainActivity);

    void inject(CommentActivity commentActivity);

    void inject(ThemeActivity themeActivity);

    void inject(HomeActivity homeActivity);

    void inject(FuliActivity fuliActivity);

    void inject(SectionActivity sectionActivity);

}
