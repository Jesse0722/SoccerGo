package com.rangers.soccergo;

import android.app.Application;

import com.avos.avoscloud.AVUser;
import com.rangers.soccergo.entities.FooterItem;
import com.rangers.soccergo.entities.SlidingItem;
import com.rangers.soccergo.entities.User;
import com.rangers.soccergo.helpers.JSONHelper;
import com.rangers.soccergo.helpers.LeanCloudHelper;

import java.util.List;

/**
 * App
 * Desc:自定义应用环境
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 21:09
 * Created by: Wooxxx
 */
public class App extends Application {
    public static final String FOOTER_CONFIG = "config/footer_items.json";
    public static final String SLIDING_CONFIG = "config/sliding_items.json";

    private List<FooterItem> footerItems = null;
    private List<SlidingItem> slidingItems = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化LeanCloud
        LeanCloudHelper.initLeanCloud(this);
        //初始化侧边栏
        slidingItems = JSONHelper.parseJSONArray(
                this,
                App.SLIDING_CONFIG,
                SlidingItem.class
        );
        //初始化底部菜单项
        footerItems = JSONHelper.parseJSONArray(
                this,
                App.FOOTER_CONFIG,
                FooterItem.class
        );

    }


    public List<FooterItem> getFooterItems() {
        return footerItems;
    }

    public List<SlidingItem> getSlidingItems() {
        return slidingItems;
    }

    /**
     * 获得当前用户
     *
     * @return 当前用户
     */
    public static User getCurrentUser() {
        return AVUser.cast(AVUser.getCurrentUser(),
                User.class);
    }

    /**
     * 判断用户是否登陆
     *
     * @return 登陆与否
     */
    public static boolean isLogged() {
        return getCurrentUser() != null;
    }

}
