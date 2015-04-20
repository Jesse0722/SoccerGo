package com.rangers.soccergo.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.rangers.soccergo.R;
import com.rangers.soccergo.fragments.FooterFragment;
import com.rangers.soccergo.fragments.HeaderFragment;

/**
 * MainActivity
 * Desc: 主Activity
 * Team: Rangers
 * Date: 2015/4/14
 * Time: 8:44
 * Created by: Wooxxx
 */
public class MainActivity extends BaseActivity {

    // 所容纳的三个Fragment
    private HeaderFragment header;
    private FooterFragment footer;

    private FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm =
                getFragmentManager();
        header = (HeaderFragment) fm.findFragmentById(R.id.header);
        footer = (FooterFragment) fm.findFragmentById(R.id.footer);
        mainContainer = (FrameLayout) findViewById(R.id.main_fragment);
    }


    @Override
    public void onBackPressed() {
    }

    /**
     * 矫正main fragment高度
     */
    private void adjustMainHeight() {
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, 0, 0, footer.getHeight());
        //矫正main_fragment 高度
        mainContainer.setPadding(0, 0, 0, footer.getHeight());
        //mainContainer.setLayoutParams(lp);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        adjustMainHeight();
    }
}
