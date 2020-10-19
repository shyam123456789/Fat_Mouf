package com.fatmouf.fatmouf.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

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
import com.fatmouf.fatmouf.adapters.ParticipantsAdapter;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.models.UserDetail;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.ADDPARTICIPANTSGROUP;
import static com.fatmouf.fatmouf.Utilities.AppConstants.FILE_REQUEST_CODE;
import static com.fatmouf.fatmouf.Utilities.AppConstants.FROM;

public class AddNewGroupActivity extends MyAbstractActivity {

    @BindView(R.id.iv_grouimage)
    AppCompatImageView iv_grouimage;

    @BindView(R.id.et_title)
    AppCompatEditText et_title;

    @BindView(R.id.rv_memberlist)
    RecyclerView rv_memberlist;

    ParticipantsAdapter adapter;
    private String image = null;
    private String selecteduser = "";
    private ArrayList<UserDetail> list = new ArrayList<>();
    private String TAG = "AddNewGroupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);
        ButterKnife.bind(this);
        initview();
        listners();

    }

    @Override
    public void initview() {
        adapter = new ParticipantsAdapter(this, list);
        rv_memberlist.setAdapter(adapter);

    }

    @Override
    public void listners() {

    }

    public void Cancel(View view) {
        finish();
    }

    public void post(View view) {
        if (AppUtils.ValidateText(et_title.getText().toString(), "Please fill title", et_title)) {
            if (image != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == (list.size() - 1)) {
                        selecteduser = selecteduser + list.get(i).getId();
                    } else {
                        selecteduser = selecteduser + list.get(i).getId() + ", ";
                    }
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("group");
                myRef.setValue("");

                getProgressDialog().show();
                MyLog.LogE(TAG, ">>>  selecteduser " + selecteduser);
                MyLog.LogE(TAG, ">>>  AuthToken " + PreferenceManger.getPreferenceManger().getAuthToken());
                MyLog.LogE(TAG, ">>>  title " + et_title.getText().toString());

                Call<ResponseModel> call = RetrofitService.RetrofitService().add_group_chat("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(),
                        RetrofitService.getRequestBody(et_title.getText().toString()),
                        RetrofitService.getRequestBody(selecteduser),
                        RetrofitService.getFilePart("image", new File(image)));

                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        getProgressDialog().dismiss();
                        if (response.body() != null && response.body().getStatus()) {
                            Toast.makeText(AddNewGroupActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("group").child("members");
                        myRef.push();
                    }


                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        getProgressDialog().dismiss();
                    }
                });

            } else {
                Toast.makeText(this, "Please select Image", Toast.LENGTH_SHORT).show();
            }
        }
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
            image = files.get(0).getPath();
            Glide.with(this).load(image).into(iv_grouimage);
        } else if (requestCode == 321 && data != null) {
            ArrayList<UserDetail> details = data.getParcelableArrayListExtra("SELECTEDUSER");
            for (UserDetail de : details) {
                if (de.isSelected())
                    list.add(de);
            }
            adapter.notifyDataSetChanged();
        }
    }

    public void addMember(View view) {
        Intent intent = new Intent(this, ParticipantasActivity.class);
        intent.putExtra(FROM, ADDPARTICIPANTSGROUP);
        startActivityForResult(intent, 321);
    }


}