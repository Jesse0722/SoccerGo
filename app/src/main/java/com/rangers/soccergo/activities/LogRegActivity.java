package com.rangers.soccergo.activities;

import android.os.Bundle;

import com.rangers.soccergo.R;
import com.rangers.soccergo.fragments.LoginFragment;

/**
 * LogRegActivity
 * Desc:登陆注册Activity
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 20:59
 * Created by: Wooxxx
 */
public class LogRegActivity extends BaseActivity {
    //注册及登录出口
    public static final Class entry = MainActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        getFragmentManager().beginTransaction()
                .replace(R.id.main_container, new LoginFragment()).commit();
    }
}
