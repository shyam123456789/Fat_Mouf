package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.AppUtils;
import com.example.fatmouf.models.ResponseModel;
import com.example.fatmouf.retrofit_provider.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String email = et_email.getText().toString();
        if (AppUtils.ValidateText(email, "Please enter your email", et_email)) {
            ForgetPassword(email);
        }
    }

    private void ForgetPassword(String email) {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().forgot_password(email);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                if (response.body() != null) {
                    Toast.makeText(ForgotpasswordActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                finish();
            }
        });
    }

    public void Back(View view) {
        finish();
    }
}