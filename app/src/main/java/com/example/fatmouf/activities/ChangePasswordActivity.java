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
        String oldpass = et_old_password.getText().toString();
        String newpass = et_new_password.getText().toString();
        String confirmpass = et_confirm_password.getText().toString();
        if (AppUtils.ValidateText(oldpass, "Please fill password", et_old_password)
                && AppUtils.ValidateText(newpass, "Please fill password", et_new_password)
                && AppUtils.ValidateText(confirmpass, "Please fill password", et_confirm_password)
        ) {
            ChangePassword(oldpass, newpass, confirmpass);
        }
    }

    private void ChangePassword(String oldpass, String newpass, String confirmpass) {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().change_password("Bearer ", newpass, confirmpass);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                if (response.body() != null) {
                    Toast.makeText(ChangePasswordActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
            }
        });

    }

    public void Back(View view) {
        finish();
    }
}