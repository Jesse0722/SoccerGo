package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;

/**
 * JoinTeam
 * Desc:加入球队实体
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:49
 * Created by: Wooxxx
 */
@AVClassName(JoinTeam.CLASS_NAME)
public class JoinTeam extends Base {
    public static final String CLASS_NAME = "JoinTeam";
    public static final String TEAM_KEY = "team"; //所加入球队
    public static final String USER_KEY = "user"; // 加入者

    public Team getTeam() {
        try {
            return this.getAVObject(TEAM_KEY, Team.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTeam(Team team) {
        this.put(TEAM_KEY, team);
    }

    public User getUser() {
        return this.getAVUser(USER_KEY);
    }

    public void setUser(User user) {
        this.put(USER_KEY, user);
    }
}
