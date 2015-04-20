package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVGeoPoint;

/**
 * School
 * Desc:学校实体
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 7:48
 * Created by: Wooxxx
 */
@AVClassName(School.CLASS_NAME)
public class School extends Base {
    public static final String CLASS_NAME = "School";
    public static final String GEO_POINT_KEY = "geo_point";  //地理位置坐标
    public static final String LOGO_KEY = "logo"; //学校logo
    public static final String NAME_KEY = "name"; // 学校名称
    public static final String PROVINCE_KEY = "province"; // 学校所在省份
    public static final String CITY_KEY = "city"; // 学校所在城市
    public static final String TYPE_KEY = "type"; // 学校类型

    public AVGeoPoint getGeoPoint() {
        return this.getAVGeoPoint(GEO_POINT_KEY);
    }

    public void setGeoPoint(AVGeoPoint geoPoint) {
        this.put(GEO_POINT_KEY, geoPoint);
    }

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

    public int getProvince() {
        return this.getInt(PROVINCE_KEY);
    }

    public void setProvinceKey(int province) {
        this.put(PROVINCE_KEY, province);
    }

    public int getCity() {
        return this.getInt(CITY_KEY);
    }

    public void setCity(int city) {
        this.put(CITY_KEY, city);
    }

    public Type getType() {
        int typeIdx = this.getInt(TYPE_KEY);
        return Type.getType(typeIdx);
    }

    public void setType(Type type) {
        this.put(TYPE_KEY, type.getIndex());
    }

    public enum Type {
        College(1, "高校"),
        HighSchool(2, "高中"),
        JuniorHighSchool(3, "初中");

        private int index;
        private String name;

        Type(int index, String name) {
            this.index = index;
            this.name = name;
        }

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

}
