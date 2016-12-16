package com.result.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.result.activity.CalendarActivity;
import com.result.activity.DetailsActivity;
import com.result.activity.PagerActivity;
import com.result.activity.R;
import com.result.bean.Date;
import com.result.bean.FirstEvent;
import com.result.bean.OKHttp;
import com.result.bean.RecyclerViewClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

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
    String path = "http://api.juheapi.com/japi/toh?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&v=1.0&month=11&day=1";
    private List<Date.ResultBean> result1;
    HomeAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.history_fragment, null);

        request();
        mActivity = getActivity();
        mPagerActivity = (PagerActivity) mActivity;
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        mPagerActivity.setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //卡片布局
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        List<Date> datas = new ArrayList<Date>();
        //mRecyclerView点击时间
//        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(getActivity(), datas));
        mRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), mRecyclerView,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        startActivity(new Intent(getActivity(), DetailsActivity.class));
                        EventBus.getDefault().post(new FirstEvent(result1));
                        Toast.makeText(getActivity(), result1.get(position)+"长按", Toast.LENGTH_SHORT).show();
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
                request();
                ;
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
            }
        });
        return view;
    }


    public void request() {
        OKHttp.getAsync(path,  new OKHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                Date date = gson.fromJson(result, Date.class);
                result1 = date.getResult();
                mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
            }
        });
    }
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


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
            holder.te2.setText(result1.get(position).getYear()+"年12月16日");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册，也就是取消该事件
    }
}

