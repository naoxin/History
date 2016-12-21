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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.result.activity.Details_GileActivity;
import com.result.activity.R;
import com.result.bean.Gril;
import com.result.bean.GrilFirstEvent;
import com.result.bean.OKHttp;
import com.result.bean.RecyclerViewClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Request;


/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 妹纸页面
 */

public class GirlFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView recy;
    int a = 1;
    String pa = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/16/";
    private List<Gril.ResultsBean> url;
    GirlFragment.HomeAdapter mAdapter;
    private SwipeRefreshLayout gril_mSwipeRefreshLayout;
    private View view;
    private boolean isFirst=true;
    private List<Gril.ResultsBean> urls=new ArrayList<Gril.ResultsBean>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (isNetworkAvailable(getActivity()))
        {
            view = View.inflate(getActivity(), R.layout.girl_fragment, null);
            request(a,isFirst);
            recy = (RecyclerView) view.findViewById(R.id.re);
            final GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
            recy.setLayoutManager(gridLayoutManager);
            mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.girlFloatingActionButton);
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recy.smoothScrollToPosition(0);

                }
            });
            gril_mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.gril_mSwipeRefreshLayout);
            gril_mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    request(a,isFirst);
                    gril_mSwipeRefreshLayout.setRefreshing(false);
                    recy.setAdapter(mAdapter = new GirlFragment.HomeAdapter());
                }
            });
            recy.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (isSlideToBottom(recy)){
                        Toast.makeText(getActivity(),"滑到底部了", Toast.LENGTH_SHORT).show();
                        a++;
                        request(a,false);
                    }
                }
            });
        }
        else
        {
            view = View.inflate(getActivity(), R.layout.girl_fragment2, null);
        }
        return view;
    }

    private void setAdapter(Boolean isFirst) {
        if(isFirst){
            mAdapter = new GirlFragment.HomeAdapter();
            recy.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        recy.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), recy,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        startActivity(new Intent(getActivity(), Details_GileActivity.class));
                        EventBus.getDefault().postSticky(new GrilFirstEvent(url.get(position).getUrl()));
                    }
                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "长按", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    public void request(int a,final  boolean isFirs) {
        OKHttp.getAsync(pa + a, new OKHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i("ssssssss","kkkkkkk");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("TAG", "requestSuccess:+++++++++++++++++++++++++++++ " + result);
                Gson gson = new Gson();
                Gril gril = gson.fromJson(result, Gril.class);
                url = gril.getResults();
                urls.addAll(url);
                setAdapter(isFirs);
            }
        });
    }
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recy == null) return false;
        if (recy.computeVerticalScrollExtent() + recy.computeVerticalScrollOffset() >= recy.computeVerticalScrollRange())
            return true;
        return false;
    }
    class HomeAdapter extends RecyclerView.Adapter<GirlFragment.HomeAdapter.MyViewHolder> {

        @Override
        public GirlFragment.HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            GirlFragment.HomeAdapter.MyViewHolder holder = new GirlFragment.HomeAdapter.MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.layout_item_gril, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(GirlFragment.HomeAdapter.MyViewHolder holder, int position) {
            Glide.with(getActivity()).load(urls.get(position).getUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.im);
        }

        @Override
        public int getItemCount() {
            return urls.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private ImageView im;

            public MyViewHolder(View itemView) {
                super(itemView);
                im = (ImageView) itemView.findViewById(R.id.im);
            }
        }
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
