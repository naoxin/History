package com.result.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.result.activity.PagerActivity;
import com.result.activity.R;
import com.result.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class HistoryFragment extends Fragment {
    Activity mActivity;
    PagerActivity mPagerActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.history_fragment,null);
        mActivity=getActivity();
        mPagerActivity= (PagerActivity) mActivity;
        Toolbar mToolbar = (Toolbar)view. findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        mPagerActivity.setSupportActionBar(mToolbar);

        RecyclerView mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(i);
        }
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(getActivity(), datas));
        return view;
    }
}
