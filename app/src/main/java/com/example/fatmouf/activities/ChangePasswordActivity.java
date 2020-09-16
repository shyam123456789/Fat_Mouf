package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends MyAbstractActivity {

    @BindView(R.id.et_old_password)
    AppCompatEditText et_old_password;

    @BindView(R.id.et_new_password)
    AppCompatEditText et_new_password;

    @BindView(R.id.et_confirm_password)
    AppCompatEditText et_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
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

    public void send(View view) {
    }

    public void Back(View view) {
        finish();
    }
}