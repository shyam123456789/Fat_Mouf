package com.fatmouf.fatmouf.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.models.AddActivityModel;
import com.fatmouf.fatmouf.models.HomePublicModel;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.gson.Gson;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.FILE_REQUEST_CODE;

public class AddActivity extends MyAbstractActivity {

    @BindView(R.id.et_description)
    AppCompatEditText et_description;


    @BindView(R.id.iv)
    AppCompatImageView iv;

    private String TAG = "AddActivity";
    private String videoPath = null;
    private String imagePath = null;
    private HomePublicModel model;
    private String activity_id = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    public void Cancel(View view) {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void post(View view) {
        MyLog.LogE("TAG", ">>>> post  ");
        if (et_description.getText().toString().isEmpty()) {
            et_description.setError("describe your activity");
            return;
        }
        AddAcitvity(et_description.getText().toString());

    }

    private void AddAcitvity(String toString) {
        getProgressDialog().show();
        Call<AddActivityModel> call = RetrofitService.RetrofitService().add_activity("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(), model.getId(), toString);
        call.enqueue(new Callback<AddActivityModel>() {
            @Override
            public void onResponse(Call<AddActivityModel> call, Response<AddActivityModel> response) {
                if (response.body() != null) {
                    Toast.makeText(AddActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getStatus()) {
                        activity_id = response.body().getData().getActivity_id();
                    }
                }
                getProgressDialog().dismiss();

            }

            @Override
            public void onFailure(Call<AddActivityModel> call, Throwable t) {
                getProgressDialog().dismiss();

            }
        });

    }

    private void AddImage(String challenge_id, String activity_id, String imagepath) {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().add_activity_mediaIMAGE("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(),
                RetrofitService.getRequestBody(challenge_id),
                RetrofitService.getRequestBody("1"),
                RetrofitService.getRequestBody(activity_id),
                RetrofitService.getFilePart("image", new File(imagepath))
        );

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null) {
                    Toast.makeText(AddActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    MyLog.LogE(TAG, new Gson().toJson(response.body()));
                    MyLog.LogE(TAG, "code  " + response.code());
                }
                getProgressDialog().dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                Toast.makeText(AddActivity.this, "Field!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void AddVideo(String challenge_id, String activity_id, String videopath) {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().add_activity_mediaVIDEO("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(),
                RetrofitService.getRequestBody(challenge_id),
                RetrofitService.getRequestBody("2"),
                RetrofitService.getRequestBody(activity_id),
                RetrofitService.getFilePart("image", new File(videopath))
        );

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null) {
                    Toast.makeText(AddActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    MyLog.LogE(TAG, new Gson().toJson(response.body()));
                    MyLog.LogE(TAG, "code  " + response.code());
                }
                getProgressDialog().dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                Toast.makeText(AddActivity.this, "Field!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    @Override
    public void initview() {
        model = getIntent().getParcelableExtra("COMPETITIVEDETAILS");


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void listners() {


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FILE_REQUEST_CODE == requestCode) {
            ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            String mimeType = getContentResolver().getType(files.get(0).getUri());
            MyLog.LogE("TAG", "MIME mimeType  " + mimeType);
            if (mimeType.contains("image")) {
                imagePath = files.get(0).getPath();
            } else {
                videoPath = files.get(0).getPath();
            }
            if (activity_id == null) {
                Toast.makeText(this, "Please Add Acivity first.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (imagePath == null) {
                    AddVideo(model.getId(), activity_id, videoPath);
                } else {
                    AddImage(model.getId(), activity_id, imagePath);
                }
            }
//            Glide.with(this).load(files.get(0).getPath()).into(iv);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.iv)
    public void OpenGallery() {
        if (CheckStoragePermission()) {
            Intent intent = new Intent(this, FilePickerActivity.class);
            intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                    .setCheckPermission(true)
                    .setShowImages(true)
                    .setShowVideos(true)
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
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

}