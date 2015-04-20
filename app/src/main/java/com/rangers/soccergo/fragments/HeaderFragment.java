package com.rangers.soccergo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.rangers.soccergo.App;
import com.rangers.soccergo.R;
import com.rangers.soccergo.entities.SlidingItem;
import com.rangers.soccergo.uis.ViewHolder;

import java.util.List;

/**
 * HeaderFragment
 * Desc: App顶栏Fragment
 * Team: Rangers
 * Date: 2015/4/14
 * Time: 9:32
 * Created by: Wooxxx
 */
public class HeaderFragment extends BaseFragment {
    // 显示侧边栏Button
    private ImageView entryBtn;
    private SlidingMenu slidingMenu;
    private ListView menuList;
    private List<SlidingItem> slidingItems;
    private LinearLayout shadeLayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_header, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSlidingMenu();
    }

    @Override
    public void initViews() {
        entryBtn = (ImageView) rootView.findViewById(R.id.sliding_entry);
    }

    @Override
    public void setListeners() {
        // 打开侧边栏
        entryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
    }

    /**
     * 初始化侧滑菜单
     */
    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(getActivity());
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_behindOffset);
        slidingMenu.setShadowDrawable(R.drawable.sliding_shadow);
        slidingMenu.setSecondaryShadowDrawable(R.drawable.sliding_shadow);
        slidingMenu.setShadowWidth(10);
        slidingMenu.setDrawingCacheEnabled(true);
        slidingMenu.setMenu(R.layout.slidingmenu);
        slidingMenu.attachToActivity(getActivity(), SlidingMenu.SLIDING_CONTENT);
        menuList = (ListView) slidingMenu.findViewById(R.id.list);
        shadeLayer = (LinearLayout) getActivity().findViewById(R.id.shade_layer);
        // 遮罩层变化
        slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
                shadeLayer.setVisibility(View.VISIBLE);
            }
        });
        slidingMenu.setOnCloseListener(new SlidingMenu.OnCloseListener() {
            @Override
            public void onClose() {
                shadeLayer.setVisibility(View.GONE);
            }
        });

        initMenuList();
    }

    /**
     * 初始化侧滑菜单项
     */
    private void initMenuList() {
        slidingItems = ((App) getActivity().getApplication()).getSlidingItems();
        SlidingAdapter adapter = new SlidingAdapter();
        menuList.setAdapter(adapter);
    }

    /**
     * 为SlidingMenu中的菜单列表适配
     */
    public class SlidingAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return slidingItems.size();
        }

        @Override
        public SlidingItem getItem(int position) {
            return slidingItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SlidingItem item = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity())
                        .inflate(R.layout.item_slidingmenu, parent, false);
            }
            int imgId = getResources()
                    .getIdentifier(item.getIcon(), "drawable", getActivity().getPackageName());
            ImageView iconImg = ViewHolder.get(convertView, R.id.icon);
            int nameId = getResources()
                    .getIdentifier(item.getName(), "string", getActivity().getPackageName());
            TextView nameTxt = ViewHolder.get(convertView, R.id.name);
            iconImg.setBackgroundResource(imgId);
            nameTxt.setText(getString(nameId));
            return convertView;
        }
    }

}
