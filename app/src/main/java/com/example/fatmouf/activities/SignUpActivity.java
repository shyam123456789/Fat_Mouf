package com.example.fatmouf.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
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
import com.example.fatmouf.Utilities.AppUtils;
import com.example.fatmouf.models.ResponseModel;
import com.example.fatmouf.retrofit_provider.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends MyAbstractActivity {

    @BindView(R.id.civ_dp)
    CircleImageView civ_dp;

    @BindView(R.id.et_firstname)
    AppCompatEditText et_firstname;

    @BindView(R.id.et_lastname)
    AppCompatEditText et_lastname;

    @BindView(R.id.et_phone)
    AppCompatEditText et_phone;

    @BindView(R.id.et_email)
    AppCompatEditText et_email;

    @BindView(R.id.et_address)
    AppCompatEditText et_address;

    @BindView(R.id.et_city)
    AppCompatEditText et_city;

    @BindView(R.id.et_state)
    AppCompatEditText et_state;

    @BindView(R.id.et_zipcode)
    AppCompatEditText et_zipcode;

    @BindView(R.id.et_password)
    AppCompatEditText et_password;

    @BindView(R.id.et_confirmpassword)
    AppCompatEditText et_confirmpassword;

    @BindView(R.id.et_referral)
    AppCompatEditText et_referral;

    @BindView(R.id.agree_terms_condition)
    AppCompatTextView agree_terms_condition;

    @BindView(R.id.check)
    AppCompatCheckBox check;


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
        String firstName = et_firstname.getText().toString();
        String lastName = et_lastname.getText().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();
        String address = et_address.getText().toString();
        String city = et_city.getText().toString();
        String zipcode = et_zipcode.getText().toString();
        String state = et_state.getText().toString();
        String password = et_password.getText().toString();
        String c_password = et_confirmpassword.getText().toString();
        String referral = et_referral.getText().toString();

        if (AppUtils.ValidateText(firstName, "Please fill first name", et_firstname) &&
                AppUtils.ValidateText(lastName, "Please fill last name", et_lastname) &&
                AppUtils.ValidateText(city, "Please fill city", et_city) &&
                AppUtils.ValidateText(state, "Please fill state", et_state) &&
                AppUtils.ValidateText(address, "Please fill address", et_address) &&
                AppUtils.ValidateText(email, "Please fill email", et_email) &&
                AppUtils.ValidateText(phone, "Please fill phone", et_phone) &&
                AppUtils.ValidateText(zipcode, "Please fill zip code", et_zipcode) &&
                AppUtils.ValidateText(password, "Please fill password", et_password) &&
                AppUtils.ValidateText(c_password, "Please fill confirm password", et_confirmpassword) &&
                password.equalsIgnoreCase(c_password)
        ) {
            if (check.isChecked()) {
                signup(firstName, lastName, email, phone, address, city, state, zipcode, password, referral);
            } else {
                Toast.makeText(this, "Please Check T&C", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (password.equalsIgnoreCase(c_password)) {
                et_confirmpassword.setError("Password not matched!");
            } else {

            }
        }
    }

    private void signup(String firstName, String lastName, String email, String phone, String address, String city, String state, String zipcode, String password, String refercode) {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().register(firstName, lastName, email, phone, password, address, "", "", "", "Android", refercode);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null) {
                    Toast.makeText(SignUpActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                getProgressDialog().dismiss();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
            }
        });
    }

    public void OpenGallery(View view) {

    }


}