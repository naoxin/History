package com.result.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.result.fragment.AboutFragment;
import com.result.fragment.CollectionFragment;
import com.result.fragment.GirlFragment;
import com.result.fragment.HistoryFragment;

public class PagerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private NavigationView navigationView;
    HistoryFragment historyFragment=new HistoryFragment();
    CollectionFragment collectionFragment=new CollectionFragment();
    GirlFragment girlFragment=new GirlFragment();
    AboutFragment aboutFragment=new AboutFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        toolbar =(Toolbar)findViewById(R.id.mToolBar); //ToolBar
        mDrawer = (DrawerLayout) findViewById(R.id.myDrawer); //DrawerLayout

        toolbar.setTitle("历史上的今天");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar);

        //设置左上角的图标响应
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle); //设置侧滑监听

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        manager.beginTransaction().add(R.id.frame,historyFragment).commit();
        navigationView = (NavigationView) findViewById(R.id.nav);
        //菜单默认选中
        navigationView.setCheckedItem(R.id.item1);
        //侧滑菜单点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                toolbar.setTitle(item.getTitle());
                Toast.makeText(PagerActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.item1:
                        manager.beginTransaction().replace(R.id.frame,historyFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item2:
                        manager.beginTransaction().replace(R.id.frame,girlFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item3:
                        manager.beginTransaction().replace(R.id.frame,collectionFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item4:
                        manager.beginTransaction().replace(R.id.frame,aboutFragment).commit();
                        item.setChecked(true);
                        ;break;
                }
                mDrawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }
    //窗口判断是否退出
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        PagerActivity.this.finish();

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }
}
