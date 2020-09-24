package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitiesListActivity extends MyAbstractActivity {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_list);
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