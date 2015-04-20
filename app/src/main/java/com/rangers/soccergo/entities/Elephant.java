package com.rangers.soccergo.entities;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * Elephant
 * Desc:测试用的小大象类，萌萌哒
 * Team: Rangers
 * Date: 2015/4/9
 * Time: 16:44
 * Created by: Wooxxx
 */
@AVClassName(Elephant.CLASS_NAME)
public class Elephant extends Base {
    public static final String CLASS_NAME = "Elephant";
    public static final String NAME_KEY = "name"; //小象名字
    public static final String WEIGHT_KEY = "weight"; // 小象体重
    public static final String LIKES_KEY = "likes"; // 保存小象爱吃的水果

    public String getName() {
        return this.getString(NAME_KEY);
    }

    public void setName(String name) {
        this.put(NAME_KEY, name);
    }

    public Double getWeight() {
        return this.getDouble(WEIGHT_KEY);
    }

    public void setWeight(Double weight) {
        this.put(WEIGHT_KEY, weight);
    }

    /**
     * 获得小象爱吃的水果
     *
     * @param callback 查询后回调接口
     */
    public void getLikes(final FindCallback callback) {
        AVRelation<Fruit> relation = this.getRelation(LIKES_KEY);
        relation.getQuery().findInBackground(callback);
    }

    /**
     * 添加一个小象爱吃的水果
     *
     * @param fruit 待添加水果对象
     */
    public void addLike(Fruit fruit) {
        AVRelation<Fruit> relation = this.getRelation(LIKES_KEY);
        relation.add(fruit);
    }

    /**
     * 添加一串小象的水果
     *
     * @param fruits 待添加水果列表
     */
    public void addAllLikes(List<Fruit> fruits) {
        AVRelation<Fruit> relation = this.getRelation(LIKES_KEY);
        relation.addAll(fruits);
    }

    /**
     * 删除某个小象爱吃的水果
     *
     * @param fruit 待删除水果对象
     */
    public void removeLike(Fruit fruit) {
        AVRelation<Fruit> relation = this.getRelation(LIKES_KEY);
        relation.remove(fruit);
    }
}
