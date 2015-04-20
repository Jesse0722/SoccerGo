package com.rangers.soccergo.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;
import com.rangers.soccergo.R;
import com.rangers.soccergo.activities.LogRegActivity;
import com.rangers.soccergo.entities.User;
import com.rangers.soccergo.utils.Validator;

/**
 * RegisterFragment
 * Desc: 注册Fragment
 * Team: Rangers
 * Date: 2015/4/13
 * Time: 16:05
 * Created by: Wooxxx
 */
public class RegisterFragment extends BackHandleFragment {
    private EditText usernameEditor;
    private EditText passwordEditor;
    private EditText rePasswordEditor;
    private EditText authCodeEditor;

    private Button getAuthCodeBtn;
    private ImageView submitBtn;

    private TextView toLoginTxt;
    private Resources rs;
    private String username;
    private String password;

    private User user;
    private TimeCount timer;


    // 定义验证错误代码
    private final int NO_ERROR = 0; //无错误
    private final int PHONE_ERROR = 1; // 手机号码格式错误
    private final int PASSWORD_ERROR = 2; // 密码格式错误
    private final int PASSWORD_MISMATCH = 3; // 两次输入密码不一致

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void initViews() {
        usernameEditor = (EditText) rootView.findViewById(R.id.username);
        passwordEditor = (EditText) rootView.findViewById(R.id.password);
        rePasswordEditor = (EditText) rootView.findViewById(R.id.re_password);
        authCodeEditor = (EditText) rootView.findViewById(R.id.auth_code);
        getAuthCodeBtn = (Button) rootView.findViewById(R.id.get_auth_code_btn);
        submitBtn = (ImageView) rootView.findViewById(R.id.submit_btn);
        toLoginTxt = (TextView) rootView.findViewById(R.id.to_login);
        rs = getResources();
    }

    @Override
    public void setListeners() {
        // 获得验证码前验证表单
        getAuthCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int errorCode = validateForm();
                switch (errorCode) {
                    case NO_ERROR:
                        //开始验证码倒计，并请求验证码
                        timer = new TimeCount(60000, 1000);
                        AVOSCloud.requestSMSCodeInBackgroud(
                                username,
                                rs.getString(R.string.app_name),
                                rs.getString(R.string.register),
                                1,
                                new RequestMobileCodeCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        if (e != null) {
                                            e.printStackTrace();
                                            Toast.makeText(getActivity(), "Bad Request", Toast.LENGTH_SHORT).show();
                                        } else {
                                            getAuthCodeBtn.setClickable(false);
                                            timer.start();
                                        }
                                    }
                                });
                        break;
                    case PHONE_ERROR:
                        usernameEditor.setError(rs.getString(R.string.register_phone_error));
                        usernameEditor.requestFocus();
                        break;
                    case PASSWORD_ERROR:
                        passwordEditor.setError(rs.getString(R.string.register_pwd_error));
                        passwordEditor.requestFocus();
                        break;
                    case PASSWORD_MISMATCH:
                        passwordEditor.setError(rs.getString(R.string.register_pwd_mismatch));
                        passwordEditor.requestFocus();
                        break;
                }
            }
        });

        // 提交注册
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提交注册时判断验证码是否正确
                String authCode = authCodeEditor.getText()
                        .toString();
                if (authCode.length() == 6) {
                    AVOSCloud.verifySMSCodeInBackground(authCode, username, new AVMobilePhoneVerifyCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                user = new User();
                                user.setUsername(username);
                                user.setPassword(password);
                                user.setMobilePhoneNumber(username);
                                user.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(AVException e) {
                                        if (e == null) {
                                            // 停止验证码计时
                                            timer.cancel();
                                            // 跳转到首页入口
                                            getActivity().startActivity(new Intent(
                                                    getActivity(), LogRegActivity.entry
                                            ));
                                        } else {
                                            e.printStackTrace();
                                            user = null;
                                            usernameEditor.setError(rs.getString(R.string.register_phone_exist));
                                            usernameEditor.requestFocus();
                                        }
                                    }
                                });

                            } else {
                                authCodeEditor.setError(
                                        rs.getString(R.string.register_ac_error)
                                );
                                authCodeEditor.requestFocus();
                            }
                        }
                    });
                }
            }
        });

        // 返回登陆页
        toLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment.this.onBackPressed();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        user = null;
        getFragmentManager().popBackStack();
        return true;
    }

    /**
     * 验证注册表单
     *
     * @return 返回错误代码
     */
    private int validateForm() {
        // 验证手机
        username = usernameEditor.getText()
                .toString();
        if (!Validator.validatePhone(username)) {
            return PHONE_ERROR;
        }

        // 验证密码
        password = passwordEditor.getText()
                .toString();
        String rePassword = rePasswordEditor.getText()
                .toString();
        if (!password.equals(rePassword))
            return PASSWORD_MISMATCH;
        else {
            if (!Validator.validatePassword(password))
                return PASSWORD_ERROR;
        }

        return NO_ERROR;
    }


    /**
     * 验证码倒计时类
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getAuthCodeBtn.setTextColor(
                    rs.getColor(R.color.default_disabled_font)
            );
            getAuthCodeBtn.setText(millisUntilFinished / 1000 + rs
                    .getString(R.string.register_ac_wait));
        }

        @Override
        public void onFinish() {
            getAuthCodeBtn.setTextColor(
                    rs.getColor(R.color.common_dark)
            );
            getAuthCodeBtn.setText(rs.getString(R.string.register_ac_reget));
            getAuthCodeBtn.setClickable(true);
        }
    }
}
