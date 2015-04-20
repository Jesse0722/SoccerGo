package com.rangers.soccergo.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rangers.soccergo.App;
import com.rangers.soccergo.R;
import com.rangers.soccergo.entities.FooterItem;

import java.util.List;

/**
 * FooterFragment
 * Desc: App底部Fragment
 * Team: Rangers
 * Date: 2015/4/14
 * Time: 9:32
 * Created by: Wooxxx
 */
public class FooterFragment extends BaseFragment {
    private LinearLayout footer;
    private FragmentManager fm;
    //底部栏高度
    private int height;
    // 当前按下的底栏选项
    private ImageView activedBtn = null;
    private FooterItem activedItem = null;

    /**
     * 监听底部栏切换
     */
    class SwitchListener implements View.OnClickListener {
        // 当前按下选项
        private FooterItem footerItem;

        SwitchListener(FooterItem footerItem) {
            this.footerItem = footerItem;
        }


        @Override
        public void onClick(View v) {
            swtiching((ImageView) v, footerItem);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(
                R.layout.fragment_footer,
                container,
                false
        );
        super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void initViews() {
        footer = (LinearLayout) rootView.findViewById(R.id.footer_container);
        fm = getFragmentManager();
        initMenus();
    }

    @Override
    public void setListeners() {
        super.setListeners();
    }

    /**
     * 初始化底部菜单
     */
    private void initMenus() {
        Context ctx = getActivity();
        List<FooterItem> footerItems = ((App) getActivity().getApplication()).getFooterItems();
        for (int i = 0; i < footerItems.size(); i++) {
            FooterItem item = footerItems.get(i);
            // 获得图标id
            int id = item.fetchIconId(ctx);
            //根据屏幕宽设置选项宽度
            int length = ctx.getResources().getDimensionPixelSize(R.dimen.footer_icon_size);
            LinearLayout layout = new LinearLayout(ctx);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
            );
            layout.setLayoutParams(lp);
            int padding = (int) ctx.getResources()
                    .getDimension(R.dimen.footer_icon_padding);
            layout.setPadding(
                    padding,
                    padding,
                    padding,
                    padding
            );
            ImageView button = new ImageView(ctx);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    length,
                    length,
                    Gravity.CENTER
            ));
            button.setImageResource(id);
            button.setScaleType(ImageView.ScaleType.FIT_CENTER);
            button.setOnClickListener(new SwitchListener(item));
            if (i == 0)
                button.performClick();
            layout.addView(button);
            footer.addView(layout);
        }
    }


    /**
     * 获得底栏高度，用以修正MainFragment
     *
     * @return 底栏高度
     */
    public int getHeight() {
        return footer.getMeasuredHeight();
    }

    /**
     * 切换按钮
     *
     * @param nowBtn  当前按下按钮
     * @param nowItem 当前按下选项
     */
    public void swtiching(ImageView nowBtn, FooterItem nowItem) {
        if (activedBtn != null) {
            //取消上次按钮状态
            activedBtn.setImageResource(
                    activedItem.fetchIconId(getActivity())
            );
        }
        // 更新按钮状态
        activedBtn = nowBtn;
        activedItem = nowItem;
        activedBtn.setImageResource(
                nowItem.fetchActivedIconId(getActivity())
        );
        //刷新fragment
        // 获得目标Fragment
        Fragment dst = nowItem.fetchFragment();
        try {
            dst = (Fragment) Class.forName(nowItem.getFragment())
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_fragment, dst, Fragment.class.toString());
        ft.commit();
    }

}
