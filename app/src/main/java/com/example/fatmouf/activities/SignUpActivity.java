package com.example.fatmouf.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fatmouf.Dialogs.DialogUtilFragment;
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
        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtilFragment fragment = new DialogUtilFragment();
                fragment.show(getSupportFragmentManager(), "");
            }
        }, 12, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtilFragment fragment = new DialogUtilFragment();
                fragment.show(getSupportFragmentManager(), "");
            }
        }, 35, 50, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        agree_terms_condition.setText(wordtoSpan);
        agree_terms_condition.setMovementMethod(LinkMovementMethod.getInstance());


    }


    public void Back(View view) {
        finish();
    }


    public void signup(View view) {
    }

    public void OpenGallery(View view) {

    }


}