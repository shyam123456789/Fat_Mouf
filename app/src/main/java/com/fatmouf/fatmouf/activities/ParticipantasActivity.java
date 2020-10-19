package com.fatmouf.fatmouf.activities;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.adapters.ChallengeMorePeopleAdapter;
import com.fatmouf.fatmouf.adapters.ParticipantsAdapter;
import com.fatmouf.fatmouf.models.HomePublicModel;
import com.fatmouf.fatmouf.models.ParticipantasListModel;
import com.fatmouf.fatmouf.models.UserDetail;
import com.fatmouf.fatmouf.models.UserListModel;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.ADDPARTICIPANTS;
import static com.fatmouf.fatmouf.Utilities.AppConstants.ADDPARTICIPANTSGROUP;
import static com.fatmouf.fatmouf.Utilities.AppConstants.COMPETITIVEDETAILS;
import static com.fatmouf.fatmouf.Utilities.AppConstants.FROM;
import static com.fatmouf.fatmouf.Utilities.AppConstants.VIEWPARTICIPANTS;

public class ParticipantasActivity extends MyAbstractActivity {

    HomePublicModel model;

    String from = "";

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.tv_post)
    AppCompatTextView tv_post;

    ParticipantsAdapter participantsAdapter;
    ChallengeMorePeopleAdapter challengeMorePeopleAdapter;

    @BindView(R.id.rv_list)
    RecyclerView recyclerView;
    private String TAG = "ParticipantasActivity";
    private ArrayList<UserDetail> participantslist = new ArrayList<>();
    private ArrayList<UserDetail> userlist = new ArrayList<>();
    private ArrayList<UserDetail> selecteduserlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantas);
        ButterKnife.bind(this);
        initview();
        listners();


    }

    @Override
    public void initview() {

        from = getIntent().getStringExtra(FROM);
        switch (from) {
            case ADDPARTICIPANTS:
                fab.hide();
                tv_post.setVisibility(View.VISIBLE);
                tv_title.setText("Challenge More Peoples");
                challengeMorePeopleAdapter = new ChallengeMorePeopleAdapter(this, userlist, new ChallengeMorePeopleAdapter.RVListner() {
                    @Override
                    public void OnAddParticipants(int p, boolean b) {
                        try {
                            if (b) {
                                userlist.get(p).setSelected(true);
                            } else {
                                userlist.get(p).setSelected(false);
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void OnItemClick(UserDetail detail, int position) {

                    }
                });
                recyclerView.setAdapter(challengeMorePeopleAdapter);
                getUserList();
                break;
            case ADDPARTICIPANTSGROUP:
                fab.hide();
                tv_post.setVisibility(View.VISIBLE);
                tv_title.setText("Challenge More Peoples");
                challengeMorePeopleAdapter = new ChallengeMorePeopleAdapter(this, userlist, new ChallengeMorePeopleAdapter.RVListner() {
                    @Override
                    public void OnAddParticipants(int p, boolean b) {
                        try {
                            if (b) {
                                userlist.get(p).setSelected(true);
                            } else {
                                userlist.get(p).setSelected(false);
                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void OnItemClick(UserDetail detail, int position) {

                    }
                });
                recyclerView.setAdapter(challengeMorePeopleAdapter);
                getUserList();
                break;
            case VIEWPARTICIPANTS:
                tv_post.setVisibility(View.GONE);
                tv_title.setText("Participants");
                model = getIntent().getParcelableExtra(COMPETITIVEDETAILS);
                participantsAdapter = new ParticipantsAdapter(this, participantslist);
                recyclerView.setAdapter(participantsAdapter);
                getPaticipantsList();
                break;
        }


    }

    private void getPaticipantsList() {
        getProgressDialog().show();
        Call<ParticipantasListModel> call = RetrofitService.RetrofitService().get_participant_list("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(), model.getId());
        call.enqueue(new Callback<ParticipantasListModel>() {
            @Override
            public void onResponse(Call<ParticipantasListModel> call, Response<ParticipantasListModel> response) {
                MyLog.LogE(TAG, "getUserList onResponse" + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus() && !response.body().getData().isEmpty()) {
                    participantslist.addAll(response.body().getData().get(0).getUserInfo());
                    participantsAdapter.notifyDataSetChanged();
                }
                getProgressDialog().dismiss();

            }

            @Override
            public void onFailure(Call<ParticipantasListModel> call, Throwable t) {
                MyLog.LogE(TAG, "getUserList onFailure" + t.getMessage());
                getProgressDialog().dismiss();
            }
        });


    }

    private void getUserList() {

        getProgressDialog().show();
        Call<UserListModel> call = RetrofitService.RetrofitService().user_list("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken());
        call.enqueue(new Callback<UserListModel>() {
            @Override
            public void onResponse(Call<UserListModel> call, Response<UserListModel> response) {
                MyLog.LogE(TAG, "getUserList onResponse" + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus() && !response.body().getList().isEmpty()) {
                    userlist.addAll(response.body().getList());
                    challengeMorePeopleAdapter.notifyDataSetChanged();
                }
                getProgressDialog().dismiss();
            }

            @Override
            public void onFailure(Call<UserListModel> call, Throwable t) {
                MyLog.LogE(TAG, "getUserList onFailure" + t.getMessage());
                getProgressDialog().dismiss();
            }
        });

    }

    @Override
    public void listners() {

    }


    @OnClick(R.id.fab)
    public void ChallengeMorePeople() {
        Intent intent = new Intent(this, ChallengeMorePeopleActivity.class);

        startActivity(intent);
    }

    public void Back(View view) {
        finish();
    }

    public void post(View view) {
        if (from.equalsIgnoreCase(ADDPARTICIPANTSGROUP)) {
            Intent intent = new Intent();
            intent.putExtra("SELECTEDUSER", userlist);
            setResult(321, intent);
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra("SELECTEDUSER", userlist);
            setResult(122, intent);
            finish();
        }
    }

}