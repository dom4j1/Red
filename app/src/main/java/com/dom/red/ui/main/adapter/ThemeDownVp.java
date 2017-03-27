package com.dom.red.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dom4j on 2017/3/23.
 */

public class ThemeDownVp extends FragmentPagerAdapter{

    private List<String> stringList;
    private List<Fragment> dateList;

    public ThemeDownVp(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return dateList.get(position);
    }

    @Override
    public int getCount() {
        return dateList.size();
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

    public void setDateList(List<Fragment> dateList) {
        this.dateList = dateList;
    }
}
