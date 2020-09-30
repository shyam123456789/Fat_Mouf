package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.MyLog;
import com.example.fatmouf.Utilities.PreferenceManger;
import com.example.fatmouf.adapters.CompetitiveAdapter;
import com.example.fatmouf.models.HomePublicModel;
import com.example.fatmouf.models.HomePublicResponse;
import com.example.fatmouf.retrofit_provider.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.fatmouf.Utilities.AppConstants.ACCEPTEDCHALLENGE;
import static com.example.fatmouf.Utilities.AppConstants.COMPETITIVEDETAILS;
import static com.example.fatmouf.Utilities.AppConstants.FROM;
import static com.example.fatmouf.Utilities.AppConstants.VIEWMYCHALLENGE;

public class ActivitiesListActivity extends MyAbstractActivity {
    String from = null;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    ArrayList<HomePublicModel> publicList;
    private String TAG = "ActivitiesListActivity";
    CompetitiveAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_list);
        ButterKnife.bind(this);
        publicList = new ArrayList<>();
        initview();
        listners();
    }

    @Override
    public void initview() {
        from = getIntent().getStringExtra(FROM);
        switch (from) {
            case VIEWMYCHALLENGE:
                tv_title.setText("My Challenges");
                fab.hide();
                break;

            case ACCEPTEDCHALLENGE:
                tv_title.setText("Accepted Challenges");
                fab.hide();
                break;

            case COMPETITIVEDETAILS:
                tv_title.setText("Activities");
                fab.show();
                break;


        }


        adapter = new CompetitiveAdapter(this, publicList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void listners() {


        getActivities("challenge_id");
    }

    private void getActivities(String challenge_id) {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImUyZTQwY2E3M2Y2ZDI5NDcwYTJkMDViOWFjZjVjYzk1NTA5MjY4YjRhNDY2OTZmZTU5Njc0Nzk4NzNmY2M4MzgxYWZmYzQ2NGE2ZmY1MDkxIn0.eyJhdWQiOiIzIiwianRpIjoiZTJlNDBjYTczZjZkMjk0NzBhMmQwNWI5YWNmNWNjOTU1MDkyNjhiNGE0NjY5NmZlNTk2NzQ3OTg3M2ZjYzgzODFhZmZjNDY0YTZmZjUwOTEiLCJpYXQiOjE1OTkzMDI3MjgsIm5iZiI6MTU5OTMwMjcyOCwiZXhwIjoxNjMwODM4NzI4LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.5irK3xIpf8yjDRcyhx6QyD6ZC3iZHh4V4knAXQZxfTQ_h_TS4qke800oeMfT57ALcNupuJA98uKvzFUUD01BQApOn2LGcgJQ-dSl86_QSlSJQabwQhOAdgm0riQmhjNVwZR_3_CC8Nun1hXTemBkKoehoM26m7nyNPtuRsV1_YaW5BxrKtNsPXvPpINOT_zBDAjM73tbIcGake2PL_PKRU2Z_gPNUOXW7dcCNm_IN6cDX59HVrfBMRKUmyrleEtXGwC3MBTLzTi2x_zvXUIlQEkpXSCvg0zBqMY_jB7b_GcV-fTfJfJy1BYweQRfWLT4seXmWjt6naGgJrI_GPuMmk__Q3CwKmvvbLDuYY9Qq7HJ2Gmzhrvl9G9-m9WxVYHQnszy1oLF-jCGq_Ki1rQxfhAtE8JSasYTGv7A3PQHu0jXVScw31ACZu83sXvI5vU3y7iYVOXqWKEi7VTLMzZdWaTYs8xg6QW5VudKybD7gcVbNtFr8icLfm41eij2Fs7LIh8FLUf8BcSLQJhnni8TVmvAysLcqN8DhqmDA6vKqkj_rjzNXSQbzGZNpiMtI6duf3vP5hgLy_HGbomjXJU7DPL7i913Sl2wtucK66U_bFOIMjJeYyeSSRcaUuL_yEk9fhfXc3DzgSjQMogNuUAIUJhScvZO4nOazvopz0CnB30";
        String token = PreferenceManger.getPreferenceManger().getAuthToken();
        MyLog.LogE(TAG, ">>> getPuliclist  >>  " + token);

        if (token == null) {

        }
        getProgressDialog().show();
        Call<HomePublicResponse> call = RetrofitService.RetrofitService().public_list("Bearer " + token);
        call.enqueue(new Callback<HomePublicResponse>() {
            @Override
            public void onResponse(Call<HomePublicResponse> call, Response<HomePublicResponse> response) {
                MyLog.LogE(TAG, ">>> getPuliclist code >>  " + response.code());
                MyLog.LogE(TAG, ">>> getPuliclist errorBody >>  " + response.errorBody());
                MyLog.LogE(TAG, ">>> getPuliclist isSuccessful >>  " + response.isSuccessful());

                getProgressDialog().dismiss();
                MyLog.LogE(TAG, ">>> getPuliclist  onResponse  " + new Gson().toJson(response.body()));
                if (response.body() != null && !response.body().getData().isEmpty()) {
                    publicList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<HomePublicResponse> call, Throwable t) {
                getProgressDialog().dismiss();
                MyLog.LogE(TAG, ">>> getPuliclist onFailure " + t.getMessage());

            }
        });

    }

    @OnClick(R.id.fab)
    public void AddActivity() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);


    }

    public void Back(View view) {
        finish();
    }

    public void groupmenu(View view) {

    }

    public void menu(View view) {

    }
}