package com.fatmouf.fatmouf.activities;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatmouf.fatmouf.BuildConfig;
import com.fatmouf.fatmouf.Dialogs.DialogUtilFragment;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;

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
        initview();
        listners();


    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {
        MyLog.LogE("APPVERSION", ">> " + BuildConfig.VERSION_NAME);
        tv_appverstion.setText("Version " + BuildConfig.VERSION_NAME);
    }

    public void Back(View view) {
        finish();
    }


    public void logout(View view) {

        PreferenceManger.getPreferenceManger().LogOut();
        startActivity(new Intent(this, SplashActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void delete(View view) {

    }

    public void share(View view) {
        String msg =
                getResources().getString(R.string.app_name) +
                        "\n\n Hey check out my app at: \n\n https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;

        AppUtils.share(this, getResources().getString(R.string.app_name), msg);
    }

    public void feedback(View view) {
        Intent intent = new Intent(this, SendFeedBackActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    public void privacypolicy(View view) {

        DialogUtilFragment fragment = new DialogUtilFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", "PRIVACY_POLICY");
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "");
    }

    public void termscondition(View view) {

        DialogUtilFragment fragment = new DialogUtilFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", "TERMS_CONDITION");
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "");
    }

    public void aboutus(View view) {
        DialogUtilFragment fragment = new DialogUtilFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TYPE", "ABOUT_US");
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "");
    }

    public void adfree(View view) {

    }


    public void changepassword(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}