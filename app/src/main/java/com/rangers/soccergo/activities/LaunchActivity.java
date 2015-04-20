package com.rangers.soccergo.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rangers.soccergo.App;
import com.rangers.soccergo.R;

/**
 * LaunchActivity
 * Desc:启动Activity，在展示启动画面的同时可以做一些策略
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 20:59
 * Created by: Wooxxx
 */
public class LaunchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //先进行登录判断
        Handler x = new Handler();
        x.postDelayed(new NeedLoginHandler(), 1500);
    }

    /**
     * 一个延迟任务用以判断是否需要登录
     */
    public class NeedLoginHandler implements Runnable {
        public void run() {
            //如果用户未登录，跳转到登陆页面
            if (!App.isLogged())
                startActivity(new Intent(LaunchActivity.this,
                        LogRegActivity.class));
            else
                startActivity(new Intent(LaunchActivity.this,
                        MainActivity.class));
        }
    }

}
