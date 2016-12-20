package com.result.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.result.activity.CalendarActivity;
import com.result.activity.DetailsActivity;
import com.result.activity.PagerActivity;
import com.result.activity.R;
import com.result.bean.Date;
import com.result.bean.FirstEvent;
import com.result.bean.FirstEvent_Rili;
import com.result.bean.OKHttp;
import com.result.bean.RecyclerViewClickListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import okhttp3.Request;

import static com.result.activity.R.id.xiangyou1;
import static com.result.activity.R.id.xiangzuo1;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class HistoryFragment extends Fragment {
    Activity mActivity;
    PagerActivity mPagerActivity;
    private FloatingActionButton mFloatingActionButton;
    private SwipeRefreshLayout mSwipe;
    private RecyclerView mRecyclerView;
    String path = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=";

    HomeAdapter mAdapter;
    private View view;
    private ImageView xiangzuo;
    private TextView data;
    private ImageView xiangyou;
    private SimpleDateFormat dateFormat;
    private Calendar mCalendar = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
    private List<Date.ResultBean> result1;
    private int month;
    private int day;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isNetworkAvailable(getActivity()))
        {
            view = View.inflate(getActivity(), R.layout.history_fragment, null);
            initView();
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            //卡片布局
            //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            //mRecyclerView点击事件
            mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), mRecyclerView,
                    new RecyclerViewClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(final View view, final int position) {
                            startActivity(new Intent(getActivity(), DetailsActivity.class));
                            EventBus.getDefault().postSticky(new FirstEvent(result1.get(position).getE_id()));
                        }

                        @Override
                        public void onItemLongClick(View view, int position) {
                            Toast.makeText(getActivity(), "长按", Toast.LENGTH_SHORT).show();
                        }
                    }));
            //点击跳转日历图标
            mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.FloatingActionButton);
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), CalendarActivity.class));
                }
            });
//mRecyclerView的下拉刷新
            mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
            mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    request(month,day);
                    mSwipe.setRefreshing(false);
                    mRecyclerView.setAdapter(mAdapter = new HomeAdapter(result1));
                }
            });

            EventBus.getDefault().register(this);
        }
        else
        {
            view = View.inflate(getActivity(), R.layout.history_freagment2, null);
        }







        return view;
    }




    private void initView() {

        //得到日期调整控件
        xiangzuo = (ImageView) view.findViewById(xiangzuo1);
        data = (TextView) view.findViewById(R.id.data);
        xiangyou = (ImageView) view.findViewById(xiangyou1);

        //改变日期方法
        changeDate(mCalendar);

        //设置箭头监听
        xiangzuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取当前日期的前一天.
                mCalendar.add(Calendar.DAY_OF_MONTH, -1);
                changeDate(mCalendar);
//                notifyAll();
            }
        });
        xiangyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取当前日期的前一天.
                mCalendar.add(Calendar.DAY_OF_MONTH, +1);
                changeDate(mCalendar);
            }
        });
    }

    private void changeDate(Calendar mCalendar) {
        //格式化日期
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        data.setText(dateFormat.format(mCalendar.getTime()));
        //获取月份，0表示1月份
        month = mCalendar.get(Calendar.MONTH) + 1;
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        Log.d("Data", month + day + "***999999999999");
        Toast.makeText(getActivity(), month + "月" + day + "日", Toast.LENGTH_SHORT).show();
        request(month,day);

    }


    public void request(int month,int day) {
        OKHttp.getAsync(path+month+"/"+day, new OKHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Date date = gson.fromJson(result, Date.class);
                result1 = date.getResult();
                mRecyclerView.setAdapter(new HomeAdapter(result1));
            }
        });
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private List<Date.ResultBean> result1;

        public HomeAdapter(List<Date.ResultBean> result1) {
            this.result1 = result1;
        }

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.layout_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {

            holder.te.setText(result1.get(position).getTitle());
            holder.te2.setText(result1.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            return result1.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView te;
            TextView te2;

            public MyViewHolder(View itemView) {
                super(itemView);
                te = (TextView) itemView.findViewById(R.id.item_tv);
                te2 = (TextView) itemView.findViewById(R.id.item_tv2);
            }
        }
    }
    @Subscribe(sticky = true)
    public void onEventMainThread(FirstEvent_Rili event) {
        month = event.getYear();
        day = event.getDay();
//        month=+1;
        data.setText("2016" + "-"+(month+1) + "-" +day );
        request(month+1,day);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

