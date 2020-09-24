package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;

import butterknife.ButterKnife;

public class AddActivity extends MyAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    public void Cancel(View view) {
        finish();
    }

    public void post(View view) {

    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {

    }
}