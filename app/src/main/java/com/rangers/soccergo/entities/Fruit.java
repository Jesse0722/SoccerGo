package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;

/**
 * Fruit
 * Desc:测试用小水果类
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 16:52
 * Created by: Wooxxx
 */
@AVClassName(Fruit.CLASS_NAME)
public class Fruit extends Base {
    public static final String CLASS_NAME = "Fruit";
    public static final String NAME_KEY = "name"; // 水果名称

    public String getName() {
        return this.getString(NAME_KEY);
    }

    public void setName(String name) {
        this.put(NAME_KEY, name);
    }

}
