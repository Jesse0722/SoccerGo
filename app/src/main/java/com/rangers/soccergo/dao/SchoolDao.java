package com.rangers.soccergo.dao;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.rangers.soccergo.entities.School;

import java.util.List;

/**
 * SchoolDao
 * Desc:学校Dao
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:50
 * Created by: Wooxxx
 */
public class SchoolDao extends BaseDao {

    private static AVQuery<School> query;

    @Override
    public String getClassName() {
        return School.CLASS_NAME;
    }

    /**
     * 异步地查询出省份索引对应的所有学校
     *
     * @param provinceIdx 省份索引
     * @param callback    查询回调接口
     */
    public static void findByProvince(int provinceIdx,
                                      final FindCallback<School> callback) {
        query = AVQuery.getQuery(School.class);
        query.whereEqualTo(School.PROVINCE_KEY, provinceIdx);
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出省份索引对应的所有学校
     *
     * @param provinceIdx 省份索引
     * @return 学校列表
     */
    public static List<School> findByProvince(int provinceIdx) {
        query = AVQuery.getQuery(School.class);
        query.whereEqualTo(School.PROVINCE_KEY, provinceIdx);
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步地查询出城市索引对应的所有学校
     *
     * @param cityIdx  城市索引
     * @param callback 查询回调接口
     */
    public static void findByCity(int cityIdx,
                                  final FindCallback<School> callback) {
        query = AVQuery.getQuery(School.class);
        query.whereEqualTo(School.CITY_KEY, cityIdx);
        query.findInBackground(callback);
    }

    /**
     * 同步地查询出城市索引对应的所有学校
     *
     * @param cityIdx 城市索引
     * @return 学校列表
     */
    public static List<School> findByCity(int cityIdx) {
        query = AVQuery.getQuery(School.class);
        query.whereEqualTo(School.CITY_KEY, cityIdx);
        try {
            return query.find();
        } catch (AVException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步地根据地理坐标获得附近学校
     *
     * @param geoPoint 地理位置
     * @param callback 查询回调接口
     */
    public static void findNearest(AVGeoPoint geoPoint,
                                   final FindCallback<School> callback) {
    }

    /**
     * 同步地根据地理坐标获得附近学校
     *
     * @param geoPoint
     * @return 附近最近学校
     */
    public static School findNearest(AVGeoPoint geoPoint) {
        return null;
    }


}
