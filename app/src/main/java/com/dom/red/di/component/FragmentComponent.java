package com.dom.red.di.component;

import android.app.Activity;

import com.dom.red.di.FragmentScope;
import com.dom.red.di.module.FragmentModule;
import com.dom.red.ui.fragment.ThemeFragment;

import dagger.Component;

/**
 * Created by dom4j on 2017/3/7.
 */
@FragmentScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface FragmentComponent {
     Activity getActivity();

     void inject(ThemeFragment themeFragment);
}
