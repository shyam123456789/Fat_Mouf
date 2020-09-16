package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotpasswordActivity extends MyAbstractActivity {

    @BindView(R.id.et_email)
    AppCompatEditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
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

    public void send_reset_link(View view) {

    }

    public void Back(View view) {
        finish();
    }
}