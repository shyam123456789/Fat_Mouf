package com.example.fatmouf.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.MyLog;
import com.example.fatmouf.Utilities.PreferenceManger;
import com.example.fatmouf.activities.BaseActivity;
import com.example.fatmouf.adapters.CompetitiveAdapter;
import com.example.fatmouf.models.HomePublicModel;
import com.example.fatmouf.models.HomePublicResponse;
import com.example.fatmouf.retrofit_provider.RetrofitService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivateHomeFragment extends Fragment {

    @BindView(R.id.rv_list_private)
    RecyclerView rv_list_private;
    CompetitiveAdapter adapter;
    private String TAG = "PrivateHomeFragment";
    private ArrayList<HomePublicModel> privateList = new ArrayList<>();
    private BaseActivity activity;

    public static PrivateHomeFragment newInstance(String param1, String param2) {
        PrivateHomeFragment fragment = new PrivateHomeFragment();
/*
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private_home, container, false);
        ButterKnife.bind(this, view);
        adapter = new CompetitiveAdapter(getContext(), privateList);
        rv_list_private.setAdapter(adapter);
        getPrivatelist();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (activity == null) {
            activity = (BaseActivity) getActivity();
        }
    }

    private void getPrivatelist() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImUyZTQwY2E3M2Y2ZDI5NDcwYTJkMDViOWFjZjVjYzk1NTA5MjY4YjRhNDY2OTZmZTU5Njc0Nzk4NzNmY2M4MzgxYWZmYzQ2NGE2ZmY1MDkxIn0.eyJhdWQiOiIzIiwianRpIjoiZTJlNDBjYTczZjZkMjk0NzBhMmQwNWI5YWNmNWNjOTU1MDkyNjhiNGE0NjY5NmZlNTk2NzQ3OTg3M2ZjYzgzODFhZmZjNDY0YTZmZjUwOTEiLCJpYXQiOjE1OTkzMDI3MjgsIm5iZiI6MTU5OTMwMjcyOCwiZXhwIjoxNjMwODM4NzI4LCJzdWIiOiIyIiwic2NvcGVzIjpbXX0.5irK3xIpf8yjDRcyhx6QyD6ZC3iZHh4V4knAXQZxfTQ_h_TS4qke800oeMfT57ALcNupuJA98uKvzFUUD01BQApOn2LGcgJQ-dSl86_QSlSJQabwQhOAdgm0riQmhjNVwZR_3_CC8Nun1hXTemBkKoehoM26m7nyNPtuRsV1_YaW5BxrKtNsPXvPpINOT_zBDAjM73tbIcGake2PL_PKRU2Z_gPNUOXW7dcCNm_IN6cDX59HVrfBMRKUmyrleEtXGwC3MBTLzTi2x_zvXUIlQEkpXSCvg0zBqMY_jB7b_GcV-fTfJfJy1BYweQRfWLT4seXmWjt6naGgJrI_GPuMmk__Q3CwKmvvbLDuYY9Qq7HJ2Gmzhrvl9G9-m9WxVYHQnszy1oLF-jCGq_Ki1rQxfhAtE8JSasYTGv7A3PQHu0jXVScw31ACZu83sXvI5vU3y7iYVOXqWKEi7VTLMzZdWaTYs8xg6QW5VudKybD7gcVbNtFr8icLfm41eij2Fs7LIh8FLUf8BcSLQJhnni8TVmvAysLcqN8DhqmDA6vKqkj_rjzNXSQbzGZNpiMtI6duf3vP5hgLy_HGbomjXJU7DPL7i913Sl2wtucK66U_bFOIMjJeYyeSSRcaUuL_yEk9fhfXc3DzgSjQMogNuUAIUJhScvZO4nOazvopz0CnB30";
          String token = PreferenceManger.getPreferenceManger().getAuthToken();
        MyLog.LogE(TAG, ">>> getPrivatelist  >>  " + token);
        if (token == null) {

        }
        activity.getProgressDialog().show();
        Call<HomePublicResponse> call = RetrofitService.RetrofitService().private_list("Bearer " + token);
        call.enqueue(new Callback<HomePublicResponse>() {
            @Override
            public void onResponse(Call<HomePublicResponse> call, Response<HomePublicResponse> response) {
                MyLog.LogE(TAG, ">>> getPrivatelist code >>  " + response.code());
                MyLog.LogE(TAG, ">>> getPrivatelist errorBody >>  " + response.errorBody());
                MyLog.LogE(TAG, ">>> getPrivatelist isSuccessful >>  " + response.isSuccessful());
                activity.getProgressDialog().dismiss();
                if (response.body() != null && !response.body().getData().isEmpty()) {
                    privateList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<HomePublicResponse> call, Throwable t) {
                activity.getProgressDialog().dismiss();
                MyLog.LogE(TAG, ">>> getPrivatelist  onFailure  " + t.getMessage());

            }
        });

    }

}