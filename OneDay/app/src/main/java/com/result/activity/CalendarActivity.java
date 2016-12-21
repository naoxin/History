package com.result.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.result.bean.FirstEvent_Rili;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import de.greenrobot.event.EventBus;

import static com.result.activity.R.id.textView;

/**
 * autour: 李延
 * date: 2016/12/21 20:33
 * update: 2016/12/21
 * 日历页面
 */
public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private TextView text;
    private int year;
    private int day;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        text = (TextView) findViewById(textView);

        final MaterialCalendarView widget = (MaterialCalendarView) findViewById(R.id.calendarView);
        //设置点击选择日期改变事件
        widget.setOnDateChangedListener(this);
        //设置滑动选择改变月份事件
        widget.setOnMonthChangedListener(this);
        //点击文本内容清除之前的选择
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                widget.clearSelection();
            }
        });
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        year = date.getMonth();
        day = date.getDay();
        EventBus.getDefault().postSticky(new FirstEvent_Rili(year, day));
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        Toast.makeText(this, FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
    }
}
