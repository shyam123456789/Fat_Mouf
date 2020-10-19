package com.fatmouf.fatmouf.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.adapters.ImagePickerAdapter;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.FILE_REQUEST_CODE;

public class SendFeedBackActivity extends MyAbstractActivity {

    ImagePickerAdapter adapter;
    @BindView(R.id.et_issue)
    AppCompatEditText et_issue;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private ArrayList<String> list = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sendfeedback_dialog);
        ButterKnife.bind(this);
        initview();
        listners();
        adapter = new ImagePickerAdapter(this, list, i -> {
            if (list.size() >= 4) {
                Toast.makeText(this, "Can't add more than four.", Toast.LENGTH_SHORT).show();
            }
            if (CheckStoragePermission()) {
                Intent intent = new Intent(this, FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                        .setShowImages(true)
                        .enableImageCapture(false)
                        .setMaxSelection(1)
                        .setSkipZeroSizeFiles(true)
                        .build());
                startActivityForResult(intent, FILE_REQUEST_CODE);
            }
        });
        rv_list.setAdapter(adapter);

    }


    @OnClick(R.id.tv_back)
    public void OnBack() {
        finish();
    }


    @OnClick(R.id.btn_send)
    public void btn_send() {
        if (et_issue.getText().toString().isEmpty()) {
            et_issue.setError("Please write your issue");
            return;
        }
        if (list.size() < 4) {
            Toast.makeText(this, "Please select atleast four image", Toast.LENGTH_SHORT).show();
        }
        File file1 = null;
        File file2 = null;
        File file3 = null;
        File file4 = null;

        if (list.size() > 0) {
            file1 = new File(list.get(0));
        }
        if (list.size() > 1) {
            file2 = new File(list.get(1));
        }
        if (list.size() > 2) {
            file3 = new File(list.get(2));
        }
        if (list.size() > 3) {
            file4 = new File(list.get(3));
        }

        MultipartBody.Part img1 = RetrofitService.getFilePart("image_one", file1);
        MultipartBody.Part img2 = RetrofitService.getFilePart("image_two", file2);
        MultipartBody.Part img3 = RetrofitService.getFilePart("image_three", file3);
        MultipartBody.Part img4 = RetrofitService.getFilePart("image_four", file4);

        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().add_feedback("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken()
                , RetrofitService.getRequestBody(et_issue.getText().toString())
                , img1
                , img2
                , img3
                , img4);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(SendFeedBackActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                Toast.makeText(SendFeedBackActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void initview() {

    }

    @Override
    public void listners() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (FILE_REQUEST_CODE == requestCode) {
            ArrayList<MediaFile> files = data.getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES);
            list.add(files.get(0).getPath());
            adapter.notifyDataSetChanged();
        }
    }
}
