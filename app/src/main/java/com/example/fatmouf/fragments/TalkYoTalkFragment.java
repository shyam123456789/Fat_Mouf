package com.example.fatmouf.fragments;

import android.content.Context;
import android.content.Intent;
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
import com.example.fatmouf.activities.AddNewGroupActivity;
import com.example.fatmouf.activities.BaseActivity;
import com.example.fatmouf.activities.ChatActivity;
import com.example.fatmouf.adapters.TalkYoTalkAdapter;
import com.example.fatmouf.models.GroupListModel;
import com.example.fatmouf.models.GroupModel;
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

public class TalkYoTalkFragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    TalkYoTalkAdapter adapter;

    ArrayList<GroupModel> list = new ArrayList<>();
    private String TAG = "TalkYoTalkFragment";

    public static TalkYoTalkFragment newInstance(String param1, String param2) {
        TalkYoTalkFragment fragment = new TalkYoTalkFragment();
    /*    Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
    */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talk_yo_talk, container, false);
        ButterKnife.bind(this, view);
        adapter = new TalkYoTalkAdapter(getContext(), list, p -> {
            try {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            } catch (Exception e) {
            }
        });
        recyclerView.setAdapter(adapter);
        getList();
        return view;
    }


    BaseActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (activity == null) {
            activity = (BaseActivity) getActivity();
        }
    }

    public void getList() {
        activity.getProgressDialog().show();
        Call<GroupListModel> call = RetrofitService.RetrofitService().get_group_list("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken());
        call.enqueue(new Callback<GroupListModel>() {
            @Override
            public void onResponse(Call<GroupListModel> call, Response<GroupListModel> response) {
                activity.getProgressDialog().dismiss();
                if (response.body() != null) {
                    list.addAll(response.body().getList());
                    adapter.notifyDataSetChanged();
                }
                MyLog.LogE(TAG, ">>> onResponse  " + new Gson().toJson(response.body()));

            }

            @Override
            public void onFailure(Call<GroupListModel> call, Throwable t) {
                activity.getProgressDialog().dismiss();
                MyLog.LogE(TAG, ">>>  onFailure " + t.getMessage());

            }
        });

    }

    @OnClick(R.id.fab)
    public void AddGroup() {
        Intent intent = new Intent(getContext(), AddNewGroupActivity.class);
        startActivity(intent);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}