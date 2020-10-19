package com.fatmouf.fatmouf.activities;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fatmouf.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends MyAbstractActivity {

    @BindView(R.id.rv_chat_list)
    RecyclerView rv_chat_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
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