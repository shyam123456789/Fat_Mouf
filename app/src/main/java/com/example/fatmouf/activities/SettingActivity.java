package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends MyAbstractActivity {

    @BindView(R.id.switch_notification)
    SwitchCompat switch_notification;

    @BindView(R.id.tv_share)
    AppCompatTextView tv_share;

    @BindView(R.id.tv_appverstion)
    AppCompatTextView tv_appverstion;

    @BindView(R.id.tv_adfree)
    AppCompatTextView tv_adfree;

    @BindView(R.id.tv_aboutus)
    AppCompatTextView tv_aboutus;

    @BindView(R.id.tv_termsncondition)
    AppCompatTextView tv_termsncondition;

    @BindView(R.id.tv_privacypolicy)
    AppCompatTextView tv_privacypolicy;

    @BindView(R.id.tv_feedback)
    AppCompatTextView tv_feedback;

    @BindView(R.id.tv_logout)
    AppCompatTextView tv_logout;

    @BindView(R.id.tv_delete)
    AppCompatTextView tv_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);


    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            tv_appverstion.setText("Version " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void Back(View view) {
        finish();
    }


    public void logout(View view) {

    }

    public void delete(View view) {

    }

    public void share(View view) {

    }

    public void feedback(View view) {
    }

    public void privacypolicy(View view) {

    }

    public void termscondition(View view) {

    }

    public void aboutus(View view) {

    }

    public void adfree(View view) {

    }


    public void changepassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}