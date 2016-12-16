package com.result.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Handler handler=new Handler();
    private MyCountDownTimer mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv =(TextView)findViewById(R.id.start);
        mc = new MyCountDownTimer(4000, 1000);
        mc.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,PagerActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         * 表示以毫秒为单位 倒计时的总数
         *
         * 例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         * 表示 间隔 多少微秒 调用一次 onTick 方法
         *
         * 例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onFinish() {
            tv.setText("正在跳转");
        }
        public void onTick(long millisUntilFinished) {
            tv.setText("倒计时(" + millisUntilFinished / 1000 + ")");
        }
    }
}
