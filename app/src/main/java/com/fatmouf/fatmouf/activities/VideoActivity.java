package com.fatmouf.fatmouf.activities;

import android.os.Bundle;

import com.fatmouf.fatmouf.R;

import butterknife.ButterKnife;

public class VideoActivity extends MyAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {

    }

}