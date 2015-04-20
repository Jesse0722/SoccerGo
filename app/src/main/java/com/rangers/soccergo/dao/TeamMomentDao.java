package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.TeamMoment;

import java.util.Date;
import java.util.List;

/**
 * TeamMomentDao
 * Desc:球队风采Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:51
 * Created by: Wooxxx
 */
public class TeamMomentDao extends BaseDao {

    private static AVQuery<TeamMoment> query;

    @Override
    public String getClassName() {
        return TeamMoment.CLASS_NAME;
    }

    /**
     * 异步地查询如某日的球队风采
     *
     * @param date     指定查询日期
     * @param callback 回调接口
     */
    public static void findByDate(Date date,
                                  final FindCallback<TeamMoment> callback) {
        query = AVQuery.getQuery(TeamMoment.class);
        query.whereEqualTo("createdAt", date);
        query.findInBackground(callback);
    }

    /**
     * 同步地查询某日的球队风采
     *
     * @param date 指定查询日期
     * @return 对应日期的球队风采
     */
    public static List<TeamMoment> findByDate(Date date) {
        query = AVQuery.getQuery(TeamMoment.class);
        query.whereEqualTo("createdAt", date);
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }
}
