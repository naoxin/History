package com.result.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.result.activity.DetailsActivity;
import com.result.activity.R;
import com.result.bean.FirstEvent;
import com.result.bean.RecyclerViewClickListener;
import com.result.dao.ScUser;
import com.result.dao.ScUserDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 收藏页面
 */

public class CollectionFragment extends Fragment {

    private RecyclerView recy;
    MyScAdapter adapter;
    private ScUserDao dao;
    private View view;
    private List<ScUser> lis;
    private SwipeRefreshLayout mSwipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.collection_fragment, null);
        lis = new ArrayList<ScUser>();
        dao = new ScUserDao(getActivity());
        recy = (RecyclerView) view.findViewById(R.id.recy_collaps);
        cha();
        recy.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), recy,
                new RecyclerViewClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        startActivity(new Intent(getActivity(), DetailsActivity.class));
                        EventBus.getDefault().postSticky(new FirstEvent(lis.get(position).getE_id()));
                    }


                    @Override
                    public void onItemLongClick(View view, int position) {
                        int id = lis.get(position).getId();
                        dao.delete(id);
                        cha();
                        Toast.makeText(getActivity(), position + "删除", Toast.LENGTH_SHORT).show();
                    }
                }));
        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.recy_swipe);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cha();
                mSwipe.setRefreshing(false);
            }
        });
        return view;
    }

    public void cha() {
        lis = dao.select();
        adapter = new MyScAdapter();
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));// GridLayoutManager(getActivity(),2)
        recy.setAdapter(adapter);
    }


    class MyScAdapter extends RecyclerView.Adapter<MyScAdapter.MyViewHolder> {

        @Override
        public MyScAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.sc_item2, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyScAdapter.MyViewHolder holder, final int position) {
            holder.data.setText(lis.get(position).getData());
//            holder.title.setText(lis.get(position).getTitle());
            Glide.with(getActivity()).load(lis.get(position).getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return lis.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView data;
            //            TextView title;
            ImageView image;

            public MyViewHolder(View itemView) {
                super(itemView);
                data = (TextView) itemView.findViewById(R.id.te_ti);
//                title = (TextView) itemView.findViewById(R.id.te_con);
                image = (ImageView) itemView.findViewById(R.id.coll_im);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        cha();
    }
}
