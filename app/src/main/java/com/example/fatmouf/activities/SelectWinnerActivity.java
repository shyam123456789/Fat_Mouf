package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fatmouf.R;

import butterknife.ButterKnife;

public class SelectWinnerActivity extends MyAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_winner);
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