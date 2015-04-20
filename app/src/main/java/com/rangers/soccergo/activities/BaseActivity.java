package com.rangers.soccergo.activities;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.avos.avoscloud.AVUser;
import com.rangers.soccergo.fragments.BackHandleFragment;

/**
 * BaseActivity
 * Desc:Activity基类
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 20:59
 * Created by: Wooxxx
 */
public class BaseActivity extends FragmentActivity
        implements BackHandleFragment.BackHandledInterface {

    // 监听返回键的Fragment对象
    protected BackHandleFragment backHandledFragment;

    @Override
    public void setSelectedFragment(BackHandleFragment selectedFragment) {
        this.backHandledFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        /*
        *如果不存在BackHandledFragment对象或者该对象重写的onBackPressed方法
        则执行默认的返回键逻辑
         */
        if (backHandledFragment == null || !backHandledFragment.onBackPressed()) {
            if (getFragmentManager().getBackStackEntryCount() == 0)
                super.onBackPressed();
            else
                getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * 登出用户
     */
    public void logOut() {
        AVUser.logOut();
        startActivity(new Intent(this, LogRegActivity.class));
    }

}
