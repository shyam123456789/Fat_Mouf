package com.example.fatmouf.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fatmouf.R;
import com.example.fatmouf.activities.MyAbstractActivity;
import com.example.fatmouf.fragments.CompetitiveFragment;
import com.example.fatmouf.fragments.NotificationFragment;
import com.example.fatmouf.fragments.ProfileFragment;
import com.example.fatmouf.fragments.TalkYoTalkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MyAbstractActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
        listners();


    }

    @Override
    public void initview() {
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(CompetitiveFragment.newInstance("", ""));
    }

    @Override
    public void listners() {

    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_competitive:
                            openFragment(CompetitiveFragment.newInstance("", ""));
                            return true;
                        case R.id.nav_tyt:
                            openFragment(TalkYoTalkFragment.newInstance("", ""));
                            return true;
                        case R.id.nav_profile:
                            openFragment(ProfileFragment.newInstance("", ""));
                            return true;
                        case R.id.nav_notifications:
                            openFragment(NotificationFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

}