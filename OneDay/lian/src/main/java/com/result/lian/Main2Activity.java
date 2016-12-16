package com.result.lian;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private Toolbar des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        des = (Toolbar) findViewById(R.id.desinn);
        des.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(des);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        des.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
