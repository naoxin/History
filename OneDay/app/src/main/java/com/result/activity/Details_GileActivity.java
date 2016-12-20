package com.result.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.result.bean.GrilFirstEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class Details_GileActivity extends AppCompatActivity {

    private Toolbar griltoo;
    private ImageView grilim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__gile);
        griltoo = (Toolbar) findViewById(R.id.gril_toolBar);
        griltoo.setTitle("妹纸");
        griltoo.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(griltoo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置点击返回小箭头
        griltoo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        grilim =(ImageView)findViewById(R.id.gril_im);

        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true)
    public void onEventMainThread(GrilFirstEvent event) {
        String msg =event.getMsg();
        Log.d("harvic", msg);
        Glide.with(this).load(msg).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(grilim);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

}
