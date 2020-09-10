package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends MyAbstractActivity {

    @BindView(R.id.civ_dp)
    CircleImageView civ_dp;

    @BindView(R.id.agree_terms_condition)
    AppCompatTextView agree_terms_condition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @Override
    public void initview() {
        Glide.with(this).load(R.drawable.ic_avatar).into(civ_dp);
    }

    @Override
    public void listners() {
        Spannable wordtoSpan = new SpannableString("Agree to our Terms & Conditions and Privacy Policy");
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLACK), 12, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLACK), 35, 48, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        agree_terms_condition.setText(wordtoSpan);


    }


    public void Back(View view) {
    }


    public void signup(View view) {
    }
}