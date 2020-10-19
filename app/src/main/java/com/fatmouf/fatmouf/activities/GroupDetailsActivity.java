package com.fatmouf.fatmouf.activities;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatmouf.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupDetailsActivity extends MyAbstractActivity {


    @BindView(R.id.iv_grouimage)
    AppCompatImageView iv_grouimage;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.rv_memberlist)
    RecyclerView rv_memberlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        ButterKnife.bind(this);

    }

    @Override
    public void initview() {

    }

    @Override
    public void listners() {


    }

    public void groupmenu(View view) {
        Toast.makeText(this, "menu clicked", Toast.LENGTH_SHORT).show();
    }

    public void Back(View view) {
        finish();
    }
}