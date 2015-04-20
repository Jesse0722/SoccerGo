package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.JoinMatch;
import com.rangers.soccergo.entities.Match;
import com.rangers.soccergo.entities.User;

import java.util.List;

/**
 * JoinMatchDao
 * Desc:加入比赛Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:50
 * Created by: Wooxxx
 */
public class JoinMatchDao extends BaseDao {

    private static AVQuery<JoinMatch> query;

    @Override
    public String getClassName() {
        return JoinMatch.CLASS_NAME;
    }

    /**
     * 异步地查询出指定用户参加比赛情况
     *
     * @param user     指定用户
     * @param callback 查询回调接口
     */
    public static void findByUser(User user,
                                  final FindCallback<JoinMatch> callback) {
        query = AVQuery.getQuery(JoinMatch.class);
        query.whereEqualTo(JoinMatch.USER_KEY, user.getObjectId());
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出指定用户参加比赛情况
     *
     * @param user 指定用户
     * @return 参加比赛列表
     */
    public static List<JoinMatch> findByUser(User user) {
        query = AVQuery.getQuery(JoinMatch.class);
        query.whereEqualTo(JoinMatch.USER_KEY, user.getObjectId());
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步地查询出指定比赛的参加比赛情况
     *
     * @param match    指定比赛
     * @param callback 查询回调接口
     */
    public static void findByMatch(Match match,
                                   final FindCallback<JoinMatch> callback) {
        query = AVQuery.getQuery(JoinMatch.class);
        query.whereEqualTo(JoinMatch.MATCH_KEY, match.getObjectId());
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出指定比赛的参加比赛情况
     *
     * @param match 指定比赛
     * @return 参加比赛列表
     */
    public static List<JoinMatch> findByMatch(Match match) {
        query = AVQuery.getQuery(JoinMatch.class);
        query.whereEqualTo(JoinMatch.MATCH_KEY, match.getObjectId());
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }
}
