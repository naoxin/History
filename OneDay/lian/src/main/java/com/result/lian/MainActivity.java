package com.result.lian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.result.fragment.GuanyuFragment;
import com.result.fragment.LishiFragment;
import com.result.fragment.MeizhiFragment;
import com.result.fragment.SoucangFragment;

import static com.result.lian.R.id.mToolBar;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    LishiFragment lishiFragment=new LishiFragment();
    MeizhiFragment meizhiFragment=new MeizhiFragment();
    SoucangFragment soucangFragment=new SoucangFragment();
    GuanyuFragment guanyuFragment=new GuanyuFragment();
    private FragmentManager manager;
    private FragmentTransaction transaction;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar)findViewById(mToolBar); //ToolBar
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
        transaction.add(R.id.frame,lishiFragment).commit();
        navigationView = (NavigationView) findViewById(R.id.nav); //NavigationView导航栏
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                toolbar.setTitle(item.getTitle());
                Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.item1:
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.frame,lishiFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item2:
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.frame,meizhiFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item3:
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.frame,soucangFragment).commit();
                        item.setChecked(true);
                        ;break;
                    case R.id.item4:
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.frame,guanyuFragment).commit();
                        item.setChecked(true);
                        ;break;
                }
                mDrawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }
}
