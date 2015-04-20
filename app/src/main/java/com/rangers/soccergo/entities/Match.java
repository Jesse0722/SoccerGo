package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;

import java.util.Date;

/**
 * Match
 * Desc: 比赛实体类
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 9:06
 * Created by: Wooxxx
 */
@AVClassName(Match.CLASS_NAME)
public class Match extends Base {
    public static final String CLASS_NAME = "Match";
    public static final String REMARK_KEY = "remark"; //比赛备注
    public static final String ASSEMBLY_PLACE_KEY = "assembly_place"; //集合场地
    public static final String SYSTEM_KEY = "system"; //赛制
    public static final String BEGIN_TIME_KEY = "begin_time"; // 开始时间
    public static final String TYPE_KEY = "type"; // 比赛类型
    public static final String GUEST_TEAM = "guest_team"; // 客场球队
    public static final String HOST_TEAM = "host_team"; // 主场球队
    public static final String STATUS_KEY = "status"; // 比赛状态
    public static final String HOST_GOAL_KEY = "host_goal"; // 主队进球数
    public static final String GUEST_GOAL_KEY = "guest_goal"; // 客队进球数

    public String getRemark() {
        return this.getString(REMARK_KEY);
    }

    public void setRemark(String remark) {
        this.put(REMARK_KEY, remark);
    }

    public String getAssemblyPlace() {
        return this.getString(ASSEMBLY_PLACE_KEY);
    }

    public void setAssemblyPlace(String assemblyPlace) {
        this.put(ASSEMBLY_PLACE_KEY, assemblyPlace);
    }

    public int getSystem() {
        return this.getInt(SYSTEM_KEY);
    }

    public void setSystem(int system) {
        this.put(SYSTEM_KEY, system);
    }

    public Date getBeginTime() {
        return this.getDate(BEGIN_TIME_KEY);
    }

    public void setBeginTime(Date beginTime) {
        this.put(BEGIN_TIME_KEY, beginTime);
    }

    public Type getType() {
        int typeIdx = this.getInt(TYPE_KEY);
        return Type.getType(typeIdx);
    }

    public void setType(Type type) {
        this.put(TYPE_KEY, type.getIndex());
    }

    public Team getGuestTeam() {
        try {
            return this.getAVObject(GUEST_TEAM, Team.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGuestTeam(Team guestTeam) {
        this.put(GUEST_TEAM, guestTeam);
    }

    public Team getHostTeam() {
        try {
            return this.getAVObject(HOST_TEAM, Team.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status getStatus() {
        int statusIdx = this.getInt(STATUS_KEY);
        return Status.getStatus(statusIdx);
    }

    public void setStatus(Status status) {
        this.put(STATUS_KEY, status.getIndex());
    }

    public int getHostGoal() {
        return this.getInt(HOST_GOAL_KEY);
    }

    public void setHostGoal(int hostGoal) {
        this.put(HOST_GOAL_KEY, hostGoal);
    }

    public int getGuestGoal() {
        return this.getInt(GUEST_GOAL_KEY);
    }

    public void setGuestGoal(int guestGoal) {
        this.put(GUEST_GOAL_KEY, guestGoal);
    }

    /**
     * 比赛类型枚举
     */
    public enum Type {
        Individual(1, "个人赛"),
        TeamPublic(2, "团体公开赛"),
        TeamInvitation(3, "团体邀请赛"),
        Cup(4, "杯赛"),
        League(5, "联赛");

        private int index;
        private String name;

        Type(int index, String name) {
            this.name = name;
            this.index = index;
        }

        //根据索引获得比赛类型
        public static Type getType(int index) {
            Type[] types = Type.values();
            for (Type type : types) {
                if (type.index == index)
                    return type;
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

    /**
     * 比赛状态枚举
     */
    public enum Status {
        NotStarted(1, "未开始"),
        UnderWay(2, "进行中"),
        Completed(3, "已结束");

        private int index;
        private String name;

        Status(int index, String name) {
            this.index = index;
            this.name = name;
        }

        //根据索引返回比赛状态
        public static Status getStatus(int index) {
            Status[] statuses = Status.values();
            for (Status status : statuses) {
                if (status.index == index)
                    return status;
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
}
