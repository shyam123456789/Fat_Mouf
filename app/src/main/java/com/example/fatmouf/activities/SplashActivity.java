package com.example.fatmouf.activities;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.example.fatmouf.R;

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
        Glide.with(this).load(R.drawable.splash_screen).into(iv);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }, 2000);
    }

    @Override
    public void listners() {

    }
}