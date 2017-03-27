package com.dom.red.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dom4j on 2017/3/24.
 */

public class HomeViewPager extends FragmentPagerAdapter{

    private List<Fragment> dataList;
    private String[] titleList;

    public HomeViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setDataList(List<Fragment> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList[position];
    }

    public void setTitleList(String[] titleList) {
        this.titleList = titleList;
    }
}
