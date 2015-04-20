package com.rangers.soccergo.entities;

import android.app.Fragment;
import android.content.Context;

/**
 * FooterItem
 * Desc: 底部栏菜单项
 * Team: Rangers
 * Date: 2015/4/14
 * Time: 10:07
 * Created by: Wooxxx
 */
public class FooterItem extends ConfigableMenuItem {
    //待跳转fragment名称
    private String fragment;

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    /**
     * 由Fragment名称取得Fragment
     *
     * @return Fragment
     */
    public Fragment fetchFragment() {
        // 获得目标Fragment
        Fragment dst = null;
        try {
            dst = (Fragment) Class.forName(this.getFragment())
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dst;
    }

    /**
     * 获得当前底部选项图标
     *
     * @param ctx 上下文环境
     * @return 当前底部选项图标ID
     */
    public int fetchIconId(Context ctx) {
        return ctx.getResources()
                .getIdentifier(this.getIcon(), "drawable", ctx.getPackageName());
    }

    public int fetchActivedIconId(Context ctx) {
        return ctx.getResources()
                .getIdentifier(this.getIcon() + "_actived", "drawable", ctx.getPackageName());
    }

}
