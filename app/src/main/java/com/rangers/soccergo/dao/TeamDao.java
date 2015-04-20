package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.School;
import com.rangers.soccergo.entities.Team;

import java.util.List;

/**
 * TeamDao
 * Desc:球队Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:50
 * Created by: Wooxxx
 */
public class TeamDao extends BaseDao {

    private static AVQuery<Team> query;

    @Override
    public String getClassName() {
        return Team.CLASS_NAME;
    }

    /**
     * 异步地查询出某个学校所有球队
     *
     * @param school   指定学校
     * @param callback 查询回调接口
     */
    public static void findBySchool(School school,
                                    final FindCallback<Team> callback) {
        query = AVQuery.getQuery(Team.class);
        query.whereEqualTo(Team.HOST_KEY, school.getObjectId());
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出某个学校所有球队
     *
     * @param school 指定学校
     * @return 球队列表
     */
    public static List<Team> findBySchool(School school) {
        query = AVQuery.getQuery(Team.class);
        query.whereEqualTo(Team.HOST_KEY, school.getObjectId());
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }
}
