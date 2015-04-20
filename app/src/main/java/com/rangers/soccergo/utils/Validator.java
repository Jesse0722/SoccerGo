package com.rangers.soccergo.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Validator
 * Desc:正则类
 * Team: Rangers
 * Date: 2015/4/10
 * Time: 13:45
 * Created by: Wooxxx
 */
public class Validator {
    /**
     * 验证手机号
     *
     * @param phone 手机号
     * @return 是否匹配
     */
    public static boolean validatePhone(String phone) {
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(phone))
            return false;
        else
            return phone.matches(telRegex);
    }

    /**
     * 验证邮箱
     *
     * @param email 邮箱
     * @return 是否匹配
     */
    public static boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * 验证密码
     *
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean validatePassword(String password) {
        String regex = "^[0-9a-zA-Z]{6,16}$";
        if (TextUtils.isEmpty(password))
            return false;
        else
            return password.matches(regex);

    }
}
