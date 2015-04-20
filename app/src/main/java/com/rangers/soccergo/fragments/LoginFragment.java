package com.rangers.soccergo.fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.rangers.soccergo.R;
import com.rangers.soccergo.activities.LogRegActivity;
import com.rangers.soccergo.entities.User;

/**
 * LoginFragment
 * Desc: 登陆注册Fragment
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 21:04
 * Created by: Wooxxx
 */
public class LoginFragment extends BackHandleFragment {
    private EditText usernameEditor;
    private EditText passwordEditor;
    private ImageView submitBtn;
    private TextView forgetPwdTxt;
    private TextView toRegTxt;
    private Resources rs;

    private ImageView appWeiXin, appQQ, appWeiBo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void initViews() {
        usernameEditor = (EditText) rootView.findViewById(R.id.username);
        passwordEditor = (EditText) rootView.findViewById(R.id.password);
        submitBtn = (ImageView) rootView.findViewById(R.id.submit_btn);
        forgetPwdTxt = (TextView) rootView.findViewById(R.id.login_forget_pwd);
        toRegTxt = (TextView) rootView.findViewById(R.id.login_to_reg);
        appWeiXin = (ImageView) rootView.findViewById(R.id.app_weixin);
        appWeiBo = (ImageView) rootView.findViewById(R.id.app_weibo);
        appQQ = (ImageView) rootView.findViewById(R.id.app_QQ);
        rs = getResources();
    }

    @Override
    public void setListeners() {
        // 登陆按钮按下事件监听
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // 第三方登陆按钮监听
        appWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        appQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        appWeiBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 跳转到注册
        toRegTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, new RegisterFragment());
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        // 忘记密码？
        forgetPwdTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    /**
     * 登录方法
     */
    private void login() {
        String username = usernameEditor.getText()
                .toString();
        String password = passwordEditor.getText()
                .toString();
        // 基本验证
        if (username.length() == 0) {
            usernameEditor.setError(
                    rs.getString(R.string.username_null));
            usernameEditor.requestFocus();
            return;
        }
        if (password.length() == 0) {
            passwordEditor.setError(
                    rs.getString(R.string.password_null));
            passwordEditor.requestFocus();
            return;
        }
        //进行登录
        AVUser.logInInBackground(username, password,
                new LogInCallback<User>() {
                    @Override
                    public void done(User user, AVException e) {
                        if (user == null) {
                            //登录失败进行提示
                            usernameEditor.setError(rs.getString(R.string.login_error));
                            usernameEditor.requestFocus();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), LogRegActivity.entry);
                            startActivity(intent);
                        }
                    }
                }, User.class);
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
