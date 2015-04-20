package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.Match;

import java.util.Date;
import java.util.List;

/**
 * MatchDao
 * Desc:
 * Team: Rangers
 * Date: 2015/4/10
 * Time: 10:03
 * Created by: Wooxxx
 */
public class MatchDao extends BaseDao {

    private static AVQuery<Match> query;

    @Override
    public String getClassName() {
        return Match.CLASS_NAME;
    }

    /**
     * 异步地查询某日的比赛
     *
     * @param beginTime 开始时间
     * @param callback  查询回调接口
     */
    public void findByBeginTime(Date beginTime,
                                final FindCallback<Match> callback) {
        query = AVQuery.getQuery(Match.class);
        query.whereEqualTo(Match.BEGIN_TIME_KEY, beginTime);
        query.findInBackground(callback);
    }

    /**
     * 同步地查询某日的比赛
     *
     * @param beginTime 开始时间
     * @return 比赛列表
     */
    public static List<Match> findByBeginTime(Date beginTime) {
        query = AVQuery.getQuery(Match.class);
        query.whereEqualTo(Match.BEGIN_TIME_KEY, beginTime);
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }


}
