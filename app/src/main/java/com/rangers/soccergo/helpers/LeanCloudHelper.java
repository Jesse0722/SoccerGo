package com.rangers.soccergo.helpers;

import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.rangers.soccergo.entities.Elephant;
import com.rangers.soccergo.entities.Fruit;
import com.rangers.soccergo.entities.JoinMatch;
import com.rangers.soccergo.entities.JoinTeam;
import com.rangers.soccergo.entities.Match;
import com.rangers.soccergo.entities.School;
import com.rangers.soccergo.entities.Team;
import com.rangers.soccergo.entities.TeamMoment;

/**
 * LeanCloudHelper
 * Desc:LeanCloud帮助类
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 21:08
 * Created by: Wooxxx
 */
public class LeanCloudHelper {


    private static final String APP_ID = "z9d925kj7ws3ypepkr1cqa42h00iw22spmc8lik28w7cokx5";
    private static final String APP_KEY = "o6ltk67iv7cxqntxf6xpp91jgjj60qpgxl7qushxl1lxxst3";

    /**
     * 初始化Leancloud
     *
     * @param ctx
     */
    public static void initLeanCloud(Context ctx) {
        //注册子类
        registerSubclass();
        AVOSCloud.initialize(ctx, APP_ID, APP_KEY);
        AVOSCloud.setDebugLogEnabled(true);
    }

    /**
     * 注册实体子类
     */
    private static void registerSubclass() {
        AVObject.registerSubclass(JoinMatch.class);
        AVObject.registerSubclass(JoinTeam.class);
        AVObject.registerSubclass(School.class);
        AVObject.registerSubclass(TeamMoment.class);
        AVObject.registerSubclass(Team.class);
        AVObject.registerSubclass(Match.class);
        //下面是测试用类
        AVObject.registerSubclass(Elephant.class);
        AVObject.registerSubclass(Fruit.class);

    }

}
