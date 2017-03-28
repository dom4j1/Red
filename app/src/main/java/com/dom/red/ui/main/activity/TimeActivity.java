package com.dom.red.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.SimpleActivity;
import com.dom.red.ui.zhihu.fragment.ZhihuDaily;
import com.dom.red.util.Constants;
import com.dom.red.util.TimeUtil;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dom4j on 2017/3/25.
 */

public class TimeActivity extends SimpleActivity {

    @BindView(R.id.calendarView)
    MaterialCalendarView mTimer;
    @BindView(R.id.time_tv)
    TextView mConfirm;
    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private String mTime;

    @Override
    protected int getLayout() {
        return R.layout.activity_time;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolbar, "选择日期");
        mTimer.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(TimeUtil.getCurrentYear(), TimeUtil.getCurrentMonth(), TimeUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mTimer.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String year =  String.valueOf(date.getYear());
                String month = String.valueOf(date.getMonth()+1);
                if(month.length()<2) month = "0"+String.valueOf(date.getMonth()+1);
                String day = String.valueOf(date.getDay()+1);
                if(day.length()<2) day = "0"+String.valueOf(date.getDay()+1);
                mTime = year+month+day;
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(mTime);
                //ZhihuDaily.getInstance().onMessageEvent(mTime);
                finish();
            }
        });
    }

}
