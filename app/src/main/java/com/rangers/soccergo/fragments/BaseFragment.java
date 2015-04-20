package com.rangers.soccergo.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BaseFragment
 * Desc:Fragment 基类
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 21:05
 * Created by: Wooxxx
 */
public class BaseFragment extends Fragment {
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews();
        setListeners();
        return rootView;
    }

    /**
     * 初始化各个组件
     */
    public void initViews() {

    }


    /**
     * 设置部分组件的监听
     */
    public void setListeners() {

    }
}
