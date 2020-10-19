package com.fatmouf.fatmouf.activities;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fatmouf.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengeMorePeopleActivity extends MyAbstractActivity {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_more_people);
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