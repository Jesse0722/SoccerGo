package com.rangers.soccergo.entities;

/**
 * SlidingItem
 * Desc: 侧滑菜单项
 * Team: Rangers
 * Date: 2015/4/14
 * Time: 10:07
 * Created by: Wooxxx
 */
public class SlidingItem extends ConfigableMenuItem {
    //侧滑待跳转地址名
    private String redirect;

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
