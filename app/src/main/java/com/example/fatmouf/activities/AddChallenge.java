package com.example.fatmouf.activities;

import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;

public class AddChallenge extends MyAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        initview();
        listners();
    }

    @Override
    public void initview() {



    }

    @Override
    public void listners() {



    }

    public void post(View view) {


    }

    public void Cancel(View view) {
        finish();   
    }

    public void StartDate(View view) {
    }

    public void EndDate(View view) {

    }

    public void AddParticipants(View view) {

    }
}