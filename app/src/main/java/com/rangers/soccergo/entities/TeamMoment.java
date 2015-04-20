package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;

/**
 * TeamMoment
 * Desc:球队风采实体
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:48
 * Created by: Wooxxx
 */
@AVClassName(TeamMoment.CLASS_NAME)
public class TeamMoment extends Base {
    public static final String CLASS_NAME = "TeamMoment";
    public static final String PHOTO_KEY = "photo"; //图像
    public static final String TEAM_KEY = "team"; // 所属球队
    public static final String USER_KEY = "user"; // 发布者
    public static final String TEXT_KEY = "text"; // 文本

    public AVFile getPhoto() {
        return this.getAVFile(PHOTO_KEY);
    }

    public void setPhoto(AVFile photo) {
        this.put(PHOTO_KEY, photo);
    }

    public Team getTeam() {
        try {
            return this.getAVObject(TEAM_KEY, Team.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    public String getText() {
        return this.getString(TEXT_KEY);
    }

    public void setText(String text) {
        this.put(TEXT_KEY, text);
    }
}
