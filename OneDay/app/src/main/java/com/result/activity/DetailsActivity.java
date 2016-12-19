package com.result.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.result.bean.Details;
import com.result.bean.FirstEvent;
import com.result.bean.OKHttp;

import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import okhttp3.Request;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar des;
    private AppBarLayout app_ba;
    private FloatingActionButton mDetails_FloatingActionButton;
    private ImageView deim;
    private TextView detetit;
    private String idmMsg ;
    String pp = "http://v.juhe.cn/todayOnhistory/queryDetail.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&e_id=";
    private List<Details.ResultBean> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        des = (Toolbar) findViewById(R.id.desinn);
        deim = (ImageView) findViewById(R.id.de_im);
        detetit = (TextView) findViewById(R.id.dete_titlt);

        EventBus.getDefault().register(this);


        des.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色

        //设置点击返回小箭头
        des.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //设置收藏按钮显示/隐藏
        mDetails_FloatingActionButton = (FloatingActionButton) findViewById(R.id.Details_FloatingActionButton);
        app_ba = (AppBarLayout) findViewById(R.id.app_bar);
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
        qing();
    }

    public void qing() {
        Log.d("TAG", "requestSuccess: +++++++++++++++++++++++++++++++++"+pp+idmMsg);
        OKHttp.getAsync(pp+idmMsg, new OKHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Details details = gson.fromJson(result, Details.class);
                content = details.getResult();
                des.setTitle(content.get(0).getTitle());
                setSupportActionBar(des);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                detetit.setText(content.get(0).getContent());
                Log.d("TAG", "requestSuccess: +++++++++++++++++++++++++++++++++"+content.get(0).getTitle());
                Glide.with(DetailsActivity.this).load(content.get(0).getPicUrl().get(0).getUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(deim);
            }
        });
    }

    @Subscribe(sticky = true)
    public void onEventMainThread(FirstEvent event) {
        idmMsg = event.getE_id();
       // Glide.with(this).load(imMsg).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(deim);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
