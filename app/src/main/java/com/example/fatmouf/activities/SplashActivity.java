package com.example.fatmouf.activities;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.PreferenceManger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends MyAbstractActivity {

    @BindView(R.id.iv)
    AppCompatImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initview();
        listners();

    }

    @Override
    public void initview() {
        Glide.with(this).load(R.drawable.splash).into(iv);
        new Handler().postDelayed(() -> {
            if (PreferenceManger.getPreferenceManger().getUserdetail() != null && PreferenceManger.getPreferenceManger().getAuthToken() != null) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 2000);
    }

    @Override
    public void listners() {

    }
}