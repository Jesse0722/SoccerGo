package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.User;

import java.util.List;

/**
 * UserDao
 * Desc:用户Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:51
 * Created by: Wooxxx
 */
public class UserDao extends BaseDao {
    private static AVQuery<User>
            query;

    @Override
    public String getClassName() {
        return User.CLASS_NAME;
    }

    /**
     * 异步地根据用户名查找用户
     *
     * @param username 待查询用户名
     * @param callback 回调接口
     */
    public static void findUserByUsername(String username,
                                          final FindCallback<User> callback) {
        query = AVQuery.getQuery(User.class);
        query.whereEqualTo("username", username);
        query.findInBackground(callback);
    }

    /**
     * 同步地根据用户名查找用户
     *
     * @param username 待查询用户名
     * @return callback 回调接口
     */
    public static List<User> findUserByUsername(String username) {
        query = AVQuery.getQuery(User.class);
        query.whereEqualTo("username", username);
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }


}
