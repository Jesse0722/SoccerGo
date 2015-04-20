package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;

/**
 * JoinMatch
 * Desc:加入比赛实体
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:49
 * Created by: Wooxxx
 */
@AVClassName(JoinMatch.CLASS_NAME)
public class JoinMatch extends Base {
    public static final String CLASS_NAME = "JoinMatch";
    public static final String MATCH_KEY = "match"; // 所加入比赛
    public static final String USER_KEY = "user"; // 参加用户

    public Match getMatch() {
        try {
            return this.getAVObject(MATCH_KEY, Match.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setMatch(Match match) {
        this.put(MATCH_KEY, match);
    }

    public User getUser() {
        return this.getAVUser(USER_KEY);
    }

    public void setUser(User user) {
        this.put(USER_KEY, user);
    }
}
