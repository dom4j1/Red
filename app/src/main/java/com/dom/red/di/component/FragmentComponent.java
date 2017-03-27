package com.dom.red.di.component;

import android.app.Activity;

import com.dom.red.di.FragmentScope;
import com.dom.red.di.module.FragmentModule;
import com.dom.red.presenter.contract.ZhiDailyContract;
import com.dom.red.presenter.contract.ZhiSectionContract;
import com.dom.red.ui.gank.fragment.HomeFragment;
import com.dom.red.ui.zhihu.fragment.ThemeFragment;
import com.dom.red.ui.zhihu.fragment.ZhihuDaily;
import com.dom.red.ui.zhihu.fragment.ZhihuHot;
import com.dom.red.ui.zhihu.fragment.ZhihuSection;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
     Activity getActivity();

     void inject(ThemeFragment themeFragment);

     void inject(HomeFragment homeFragment);

     void inject(ZhihuDaily homeFragment);

     void inject(ZhihuHot homeFragment);

     void inject(ZhihuSection homeFragment);
}
