package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;

/**
 * User
 * Desc:用户实体
 * Team: Rangers
 * Date: 2015/4/8
 * Time: 20:59
 * Created by: Wooxxx
 */
public class User extends AVUser {
    //定义Class名称
    public static final String CLASS_NAME = "_USER";
    // 定义所有Keys
    public static final String PREFERED_ROLE_KEY = "prefered_role"; //擅长位置
    public static final String NICKNAME_KEY = "nickname"; //昵称
    public static final String POINTS_KEY = "points";  //积分
    public static final String LEVEL_KEY = "level"; //等级
    public static final String HOST_KEY = "host"; //主场
    public static final String AVATAR_KEY = "avatar"; //头像

    /**
     * 擅长位置的枚举
     */
    public enum PreferedRole {
        Forward(1, "前锋"),
        Midfielder(2, "中场"),
        Defender(3, "后卫"),
        GoalKeeper(4, "门将");

        private int index; // 该位置index
        private String name; // 擅长位置名称

        PreferedRole(int index, String name) {
            this.name = name;
            this.index = index;
        }

        /**
         * 根据坐标返回擅长位置
         *
         * @param index 枚举坐标
         * @return 擅长位置名称
         */
        public static PreferedRole getRole(int index) {
            PreferedRole[] roles = PreferedRole.values();
            for (PreferedRole role : roles) {
                if (role.index == index)
                    return role;
            }
            return null;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }
    }
    //重写get，set方法

    public PreferedRole getPreferedRole() {
        int roleIdx = this.getInt(PREFERED_ROLE_KEY);
        return PreferedRole.getRole(roleIdx);
    }

    public void setPreferedRoleKey(PreferedRole role) {
        this.put(PREFERED_ROLE_KEY, role.getIndex());
    }

    public String getNickName() {
        return this.getString(NICKNAME_KEY);
    }

    public void setNicknameKey(String nickname) {
        this.put(NICKNAME_KEY, nickname);
    }

    public int getPoints() {
        return this.getInt(POINTS_KEY);
    }

    public void setPoints(int points) {
        this.put(POINTS_KEY, points);
    }

    public int getLevel() {
        return this.getInt(LEVEL_KEY);
    }

    public void setLevel(int level) {
        this.put(LEVEL_KEY, level);
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

    public AVFile getAvatar() {
        return this.getAVFile(AVATAR_KEY);
    }

    public void setAvatar(AVFile avatar) {
        this.put(AVATAR_KEY, avatar);
    }


}
