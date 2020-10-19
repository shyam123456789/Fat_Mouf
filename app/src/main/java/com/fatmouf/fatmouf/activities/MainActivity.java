package com.fatmouf.fatmouf.activities;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.fragments.CompetitiveFragment;
import com.fatmouf.fatmouf.fragments.NotificationFragment;
import com.fatmouf.fatmouf.fragments.ProfileFragment;
import com.fatmouf.fatmouf.fragments.TalkYoTalkFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MyAbstractActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
        listners();
        setToolbar(toolbar);

    }

    @Override
    public void initview() {

        openFragment(CompetitiveFragment.newInstance("", ""));
        tv_title.setText(getString(R.string.title_competitive));
    /*    tabs.addTab(tabs.newTab().setCustomView(R.layout.menu_competitive).setText(getResources().getString(R.string.title_competitive)));
        tabs.addTab(tabs.newTab().setCustomView(R.layout.menu_talkyotalk).setText(getResources().getString(R.string.title_talkyotalk)));
        tabs.addTab(tabs.newTab().setCustomView(R.layout.menu_profile).setText(getResources().getString(R.string.title_profile)));
        tabs.addTab(tabs.newTab().setCustomView(R.layout.menu_notification).setText(getResources().getString(R.string.title_notifications)));
*/

        tabs.addTab(tabs.newTab().setIcon(R.drawable.menu_competitive).setText(getResources().getString(R.string.title_competitive)));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.menu_chat).setText(getResources().getString(R.string.title_talkyotalk)));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.menu_profile).setText(getResources().getString(R.string.title_profile)));
        tabs.addTab(tabs.newTab().setIcon(R.drawable.menu_notification).setText(getResources().getString(R.string.title_notifications)));
 /*       tabs.getTabAt(0).setText(getResources().getString(R.string.title_competitive));
        tabs.getTabAt(1).setText(getResources().getString(R.string.title_talkyotalk));
        tabs.getTabAt(2).setText(getResources().getString(R.string.title_profile));
        tabs.getTabAt(3).setText(getResources().getString(R.string.title_notifications));
*/
        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void listners() {
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //setTabColor(tab.getPosition(), tab);
                switch (tab.getPosition()) {
                    case 0:

                        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

                        openFragment(CompetitiveFragment.newInstance("", ""));
                        tv_title.setText(getString(R.string.title_competitive));
                        break;
                    case 1:

                        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

                        openFragment(TalkYoTalkFragment.newInstance("", ""));
                        tv_title.setText(getString(R.string.title_talkyotalk));
                        break;
                    case 2:
                        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

                        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        openFragment(ProfileFragment.newInstance("", ""));
                        tv_title.setText(getString(R.string.title_profile));

                        break;
                    case 3:
                        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        openFragment(NotificationFragment.newInstance("", ""));
                        tv_title.setText(getString(R.string.title_notifications));
                        break;
                    default: {
                        tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
                        openFragment(CompetitiveFragment.newInstance("", ""));
                        tv_title.setText(getString(R.string.title_competitive));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                startActivity(new Intent(this, SettingActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setTabColor(int position, TabLayout.Tab tab) {
        AppCompatImageView imageView_competitive = tab.getCustomView().findViewById(R.id.menu_competitive);
        AppCompatTextView tv_competitive = tab.getCustomView().findViewById(R.id.tv_title_competitive);
        AppCompatImageView imageView_taltyt = tab.getCustomView().findViewById(R.id.menu_talkyotalk);
        AppCompatTextView tv_talkyot = tab.getCustomView().findViewById(R.id.tv_title_talkyotalk);
        AppCompatImageView imageView_noti = tab.getCustomView().findViewById(R.id.menu_noti);
        AppCompatTextView tv_noti = tab.getCustomView().findViewById(R.id.tv_title_notifications);
        AppCompatImageView imageView_profile = tab.getCustomView().findViewById(R.id.menu_profile);
        AppCompatTextView tv_profile = tab.getCustomView().findViewById(R.id.tv_title_profile);
        final int GreenColor = getResources().getColor(R.color.green);
        final int WhiteColor = getResources().getColor(R.color.white);

        switch (tab.getPosition()) {
            case 0:
                imageView_competitive.setColorFilter(GreenColor, PorterDuff.Mode.SRC_ATOP);
                tv_competitive.setTextColor(GreenColor);

                imageView_profile.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_profile.setTextColor(WhiteColor);
                imageView_noti.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_noti.setTextColor(WhiteColor);
                imageView_taltyt.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_talkyot.setTextColor(WhiteColor);
                break;
            case 1:
                imageView_competitive.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_competitive.setTextColor(WhiteColor);

                imageView_profile.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_profile.setTextColor(WhiteColor);
                imageView_noti.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_noti.setTextColor(WhiteColor);
                imageView_taltyt.setColorFilter(GreenColor, PorterDuff.Mode.SRC_ATOP);
                tv_talkyot.setTextColor(GreenColor);
                break;
            case 2:
                imageView_competitive.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_competitive.setTextColor(WhiteColor);

                imageView_profile.setColorFilter(GreenColor, PorterDuff.Mode.SRC_ATOP);
                tv_profile.setTextColor(GreenColor);
                imageView_noti.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_noti.setTextColor(WhiteColor);
                imageView_taltyt.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_talkyot.setTextColor(WhiteColor);
                break;
            case 3:
                imageView_competitive.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_competitive.setTextColor(WhiteColor);

                imageView_profile.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_profile.setTextColor(WhiteColor);
                imageView_noti.setColorFilter(GreenColor, PorterDuff.Mode.SRC_ATOP);
                tv_noti.setTextColor(GreenColor);
                imageView_taltyt.setColorFilter(WhiteColor, PorterDuff.Mode.SRC_ATOP);
                tv_talkyot.setTextColor(WhiteColor);
                break;
        }

    }

}
