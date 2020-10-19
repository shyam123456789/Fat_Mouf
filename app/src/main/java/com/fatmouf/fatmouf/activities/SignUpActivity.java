package com.fatmouf.fatmouf.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.Dialogs.DialogUtilFragment;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.FILE_REQUEST_CODE;

public class SignUpActivity extends MyAbstractActivity {

    Location location;

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

    private FusedLocationProviderClient fusedLocationClient;
    private String dp_path = "";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initview() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Glide.with(this).load(R.drawable.ic_avatar).into(civ_dp);
        if (CheckLocationPermission()) {
            getLocation();
        }
        MyLog.LogE("LOCATION", ">>>   " + CheckLocationPermission());

    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                        SignUpActivity.this.location = location;
                        MyLog.LogE("LOCATION", ">>>  " + location.getLatitude());
                        MyLog.LogE("LOCATION", ">>>  " + location.getLongitude());
                    }
            );
        }
    }

    @Override
    public void listners() {
        Spannable wordtoSpan = new SpannableString("Agree to our Terms & Conditions and Privacy Policy");
        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtilFragment fragment = new DialogUtilFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TYPE", "TERMS_CONDITION");
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "");
            }
        }, 12, 31, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtilFragment fragment = new DialogUtilFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TYPE", "PRIVACY_POLICY");
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "");
            }
        }, 35, 50, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        agree_terms_condition.setText(wordtoSpan);
        agree_terms_condition.setMovementMethod(LinkMovementMethod.getInstance());


    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
        MyLog.LogE("TAG","FINISH>>>1");
    }

    public void Back(View view) {
//        onBackPressed();
        MyLog.LogE("TAG","FINISH>>>2");
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
                if (dp_path == null || dp_path.isEmpty()) {
                    Toast.makeText(this, "Please select profile picture", Toast.LENGTH_SHORT).show();
                    return;
                }
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

        Call<ResponseModel> call;
        if (location == null) {
            call = RetrofitService.RetrofitService().register(RetrofitService.getRequestBody(firstName), RetrofitService.getRequestBody(lastName), RetrofitService.getRequestBody(email), RetrofitService.getRequestBody(phone), RetrofitService.getRequestBody(password), RetrofitService.getRequestBody(address), RetrofitService.getRequestBody(""), RetrofitService.getRequestBody(""), RetrofitService.getRequestBody(FirebaseInstanceId.getInstance().getToken()), RetrofitService.getRequestBody("Android"), RetrofitService.getRequestBody(refercode), RetrofitService.getFilePart("", new File(dp_path)));
        } else {
            call = RetrofitService.RetrofitService().register(RetrofitService.getRequestBody(firstName), RetrofitService.getRequestBody(lastName), RetrofitService.getRequestBody(email), RetrofitService.getRequestBody(phone), RetrofitService.getRequestBody(password), RetrofitService.getRequestBody(address), RetrofitService.getRequestBody(location.getLatitude() + ""), RetrofitService.getRequestBody(location.getLongitude() + ""), RetrofitService.getRequestBody(FirebaseInstanceId.getInstance().getToken()), RetrofitService.getRequestBody("Android"), RetrofitService.getRequestBody(refercode), RetrofitService.getFilePart("", new File(dp_path)));
        }
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                MyLog.LogE("SIGNUP", ">>>   " + new Gson().toJson(response.body()));
                MyLog.LogE("SIGNUP", ">>>   " + response.code());
                MyLog.LogE("SIGNUP", ">>>   " + response.message());
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void OpenGallery(View view) {
        if (CheckStoragePermission()) {
            Intent intent = new Intent(this, FilePickerActivity.class);
            intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                    .setCheckPermission(true)
                    .setShowImages(true)
                    .setShowVideos(false)
                    .setShowAudios(false)
                    .setShowFiles(false)
                    .enableImageCapture(false)
                    .setMaxSelection(1)
                    .setSkipZeroSizeFiles(true)
                    .build());
            startActivityForResult(intent, FILE_REQUEST_CODE);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getLocation();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FILE_REQUEST_CODE == requestCode) {
            try {
                ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
                dp_path = files.get(0).getPath();
                Glide.with(this).load(dp_path).placeholder(R.drawable.ic_avatar).into(civ_dp);
            }catch (Exception e){}
        }
    }
}