package com.result.fragment;

import android.content.Intent;
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
import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class GirlFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView recy;
    String pa="http://gank.io/api/data/%E7%A6%8F%E5%88%A9/16/1";
    private List<Gril.ResultsBean> url;
    GirlFragment.HomeAdapter mAdapter;
    private SwipeRefreshLayout gril_mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.girl_fragment,null);
        request();
        recy =(RecyclerView)view.findViewById(R.id.re);
        recy.setLayoutManager(new GridLayoutManager(getActivity(),2));

        recy.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), recy,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        startActivity(new Intent(getActivity(), Details_GileActivity.class));
                        EventBus.getDefault().postSticky( new GrilFirstEvent(url.get(position).getUrl()));
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getActivity(), "长按", Toast.LENGTH_SHORT).show();
                    }
                }));

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.girlFloatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gril_mSwipeRefreshLayout =(SwipeRefreshLayout)view.findViewById(R.id.gril_mSwipeRefreshLayout);
        gril_mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                request();
                gril_mSwipeRefreshLayout.setRefreshing(false);
                recy.setAdapter(mAdapter = new GirlFragment.HomeAdapter());
            }
        });
        return view;
    }
    public void request() {
        OKHttp.getAsync(pa,  new OKHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Log.e("TAG", "requestSuccess:+++++++++++++++++++++++++++++ "+result);
                Gson gson = new Gson();
                Gril gril=gson.fromJson(result,Gril.class);
                url =gril.getResults();
                recy.setAdapter(mAdapter = new GirlFragment.HomeAdapter());
            }
        });
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
            Glide.with(getActivity()).load(url.get(position).getUrl()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.im);
        }

        @Override
        public int getItemCount() {
            return url.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private  ImageView im;

            public MyViewHolder(View itemView) {
                super(itemView);
                im =(ImageView)itemView.findViewById(R.id.im);
            }
        }
    }
}
