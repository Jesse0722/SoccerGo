package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.JoinTeam;
import com.rangers.soccergo.entities.User;

import java.util.List;

/**
 * JoinTeamDao
 * Desc:加入球队Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:50
 * Created by: Wooxxx
 */
public class JoinTeamDao extends BaseDao {

    private static AVQuery<JoinTeam> query;

    @Override
    public String getClassName() {
        return JoinTeam.CLASS_NAME;
    }

    /**
     * 异步地查询出某用户参加球队情况
     *
     * @param user     指定用户
     * @param callback 查询回调接口
     */
    public static void findByUser(User user,
                                  final FindCallback<JoinTeam> callback) {
        query = AVQuery.getQuery(JoinTeam.class);
        query.whereEqualTo(JoinTeam.USER_KEY, user.getObjectId());
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出某用户参加球队情况
     *
     * @param user 指定用户
     * @return 参加球队列表
     */
    public static List<JoinTeam> findByUser(User user) {
        query = AVQuery.getQuery(JoinTeam.class);
        query.whereEqualTo(JoinTeam.USER_KEY, user.getObjectId());
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

}
