package com.fatmouf.fatmouf.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.models.UpdateUserModel;
import com.fatmouf.fatmouf.models.UserDetail;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
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

public class EditProfileActivity extends MyAbstractActivity {

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
    private String dp_path = null;
    private String deviceType = "android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
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
        UserDetail detail = PreferenceManger.getPreferenceManger().getUserdetail();
        if (detail != null) {
            Glide.with(this).load(detail.getImage()).placeholder(R.drawable.ic_avatar).into(civ_dp);
            et_firstname.setText(detail.getFirstName());
            et_lastname.setText(detail.getLastName());
            et_email.setText(detail.getEmail());
            et_phone.setText(detail.getPhonenumber());
            et_city.setText(detail.getCity());
            et_state.setText(detail.getState());
            et_address.setText(detail.getAddress());
            dp_path = detail.getImage();
        }
    }


    public void Back(View view) {
        finish();
    }


    public void Save(View view) {
        String firstName = et_firstname.getText().toString();
        String lastName = et_lastname.getText().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();
        String address = et_address.getText().toString();
        String city = et_city.getText().toString();
        String zipcode = et_zipcode.getText().toString();
        String state = et_state.getText().toString();

        if (AppUtils.ValidateText(firstName, "Please fill first name", et_firstname) &&
                AppUtils.ValidateText(lastName, "Please fill last name", et_lastname) &&
                AppUtils.ValidateText(city, "Please fill city", et_city) &&
                AppUtils.ValidateText(state, "Please fill state", et_state) &&
                AppUtils.ValidateText(address, "Please fill address", et_address) &&
                AppUtils.ValidateText(email, "Please fill email", et_email) &&
                AppUtils.ValidateText(phone, "Please fill phone", et_phone) &&
                AppUtils.ValidateText(zipcode, "Please fill zip code", et_zipcode)
        ) {
            SaveProfile(firstName, lastName, email, phone, address, city, state, zipcode);
        }
    }

    private void SaveProfile(String firstName, String lastName, String email, String phone, String address, String city, String state, String zipcode) {
        if (dp_path == null && dp_path.isEmpty()) {
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return;
        }
        getProgressDialog().show();
        Call<UpdateUserModel> call = RetrofitService.RetrofitService().update_profile("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(),
                RetrofitService.getRequestBody(firstName),
                RetrofitService.getRequestBody(lastName),
                RetrofitService.getRequestBody(address),
                RetrofitService.getRequestBody(city),
                RetrofitService.getRequestBody(state),
                RetrofitService.getRequestBody(""),
                RetrofitService.getRequestBody(phone),
                RetrofitService.getRequestBody(""),
                RetrofitService.getRequestBody(""),
                RetrofitService.getRequestBody(""),
                RetrofitService.getRequestBody(""),
                RetrofitService.getFilePart("image", new File(dp_path))
        );

        call.enqueue(new Callback<UpdateUserModel>() {
            @Override
            public void onResponse(Call<UpdateUserModel> call, Response<UpdateUserModel> response) {
                MyLog.LogE("TAG", ">>>  onResponse  " + new Gson().toJson(response.body()));
                getProgressDialog().dismiss();
                if (response.body() != null && response.body().getStatus())
                    PreferenceManger.getPreferenceManger().setUserdetail(response.body().getDetail());
            }

            @Override
            public void onFailure(Call<UpdateUserModel> call, Throwable t) {
                MyLog.LogE("TAG", ">>  onFailure  " + t.getMessage());
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FILE_REQUEST_CODE == requestCode) {
            ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            dp_path = files.get(0).getPath();
        }
    }
}