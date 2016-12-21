package com.result.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.result.activity.R;

/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 关于页面
 */
public class AboutFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.about_fragment,null);
    }
}
