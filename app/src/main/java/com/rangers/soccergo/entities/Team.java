package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * Team
 * Desc:球队实体
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 20:59
 * Created by: Wooxxx
 */
@AVClassName(Team.CLASS_NAME)
public class Team extends Base {
    public static final String CLASS_NAME = "Team";
    public static final String LOGO_KEY = "logo"; //球队logo
    public static final String NAME_KEY = "name"; //球队名
    public static final String ABSTRACT_KEY = "abstract"; //球队简介
    public static final String POINTS_KEY = "points"; //球队积分
    public static final String HOST_KEY = "host"; //球队主场
    public static final String CAPTAIN_KEY = "captain"; //队长
    public static final String MEMBERS_KEY = "members"; //成员

    public AVFile getLogo() {
        return this.getAVFile(LOGO_KEY);
    }

    public void setLogo(AVFile logo) {
        this.put(LOGO_KEY, logo);
    }

    public String getName() {
        return this.getString(NAME_KEY);
    }

    public void setName(String name) {
        this.put(NAME_KEY, name);
    }

    public String getAbstract() {
        return this.getString(ABSTRACT_KEY);
    }

    public void setAbstract(String abstractText) {
        this.put(ABSTRACT_KEY, abstractText);
    }

    public int getPoints() {
        return this.getInt(POINTS_KEY);
    }

    public void setPoints(int points) {
        this.put(POINTS_KEY, points);
    }

    public School getHost() {
        try {
            return this.getAVObject(HOST_KEY, School.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHost(School host) {
        this.put(HOST_KEY, host);
    }

    public User getCaptain() {
        return this.getAVUser(CAPTAIN_KEY);
    }

    public void setCaptain(User captain) {
        this.put(CAPTAIN_KEY, captain);
    }

    /**
     * 异步地得到球队所有成员
     *
     * @param callback 查询完成的回调接口
     */
    public void getAllMembers(final FindCallback callback) {
        AVRelation<User> relation = this.getRelation(MEMBERS_KEY);
        relation.getQuery().findInBackground(callback);
    }


    /**
     * 添加成员
     *
     * @param member 待添加成员
     */
    public void addMember(User member) {
        AVRelation<User> relation = this.getRelation(MEMBERS_KEY);
        relation.add(member);
    }

    /**
     * 添加一组球员
     *
     * @param members 待添加成员列表
     */
    public void addMembers(List<User> members) {
        AVRelation<User> relation = this.getRelation(MEMBERS_KEY);
        relation.addAll(members);
    }

    /**
     * 删除一个成员
     *
     * @param member 待删除成员
     */
    public void removeMember(User member) {
        AVRelation<User> relation = this.getRelation(MEMBERS_KEY);
        relation.remove(member);
    }

}
