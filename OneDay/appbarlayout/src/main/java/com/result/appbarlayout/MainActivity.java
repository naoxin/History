package com.result.appbarlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色

        setSupportActionBar(mToolbar);



        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i);
        }
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(MainActivity.this, mRecyclerView,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "长按", Toast.LENGTH_SHORT).show();
                    }
                }));

    }
}
