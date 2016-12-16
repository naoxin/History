package com.result.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.result.bean.FirstEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar des;
    private AppBarLayout app_ba;
    private FloatingActionButton mDetails_FloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        EventBus.getDefault().register(this);
        des = (Toolbar) findViewById(R.id.desinn);
        des.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(des);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置点击返回小箭头
        des.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置收藏按钮显示/隐藏
        mDetails_FloatingActionButton =(FloatingActionButton)findViewById(R.id.Details_FloatingActionButton);
        app_ba =(AppBarLayout)findViewById(R.id.app_bar);
        app_ba.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    appBarLayout.setEnabled(false);
                    mDetails_FloatingActionButton.setVisibility(View.INVISIBLE);
                } else {
                    appBarLayout.setEnabled(true);
                    mDetails_FloatingActionButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Subscribe(sticky = true)
    public void onEventMainThread(FirstEvent event) {
        String tg=event.getMsg().get(0).getTitle();
        des.setTitle(tg);
        des.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(des);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置点击返回小箭头
        des.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d("harvic", "onEventMainThread收到了消息：++++++++++++++++++++++++++++++++++++++++" + event.getMsg());
    }
}
