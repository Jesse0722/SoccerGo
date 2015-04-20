package com.rangers.soccergo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rangers.soccergo.R;

/**
 * UCFragment
 * Desc: 用户中心Fragment
 * Team: Rangers
 * Date: 2015/4/18
 * Time: 16:09
 * Created by: Wooxxx
 */
public class UCFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(
                R.layout.fragment_uc,
                container,
                false
        );
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
