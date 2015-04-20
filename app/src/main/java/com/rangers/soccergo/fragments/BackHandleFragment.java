package com.rangers.soccergo.fragments;

import android.os.Bundle;

/**
 * BackHandleFragment
 * Desc:
 * Team: Rangers
 * Date: 2015/4/13
 * Time: 16:03
 * Created by: Wooxxx
 */
public abstract class BackHandleFragment extends BaseFragment {
    /**
     * BackHandledInterface
     * 该接口用于告知宿主Activity当前fragment
     */
    public interface BackHandledInterface {
        /**
         * 设置需要监听返回键的fragment
         * （当BackFragment正式开始后，通过getActivity()得到宿主Activity，
         * 并调用宿主Activity的setSelectedFragment()方法，告知自己正在
         * 显示，需要监听onPressed事件）
         *
         * @param selectedFragment 当前选中的fragment
         */
        public abstract void setSelectedFragment(BackHandleFragment selectedFragment);
    }

    protected BackHandledInterface mBackHandledInterface;

    /**
     * 子类Fragment重写onBackPressed()方法，完成自己的返回键逻辑
     */
    public abstract boolean onBackPressed();

    /**
     * 创建阶段，初始化BackHandledInterface对象
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
    }

    /**
     * Fragment真正开始阶段，告知宿主Activity当前自己正在显示
     */
    @Override
    public void onStart() {
        super.onStart();
        mBackHandledInterface.setSelectedFragment(this);
    }
}
