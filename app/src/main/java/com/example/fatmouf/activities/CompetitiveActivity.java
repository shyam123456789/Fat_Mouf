package com.example.fatmouf.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fatmouf.Dialogs.AcceptChallengeDialog;
import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.MyLog;
import com.example.fatmouf.Utilities.PreferenceManger;
import com.example.fatmouf.adapters.CompetitiveAdapter;
import com.example.fatmouf.adapters.ParticipantsAdapter;
import com.example.fatmouf.adapters.PostItemPagerAdapter;
import com.example.fatmouf.models.HomePublicModel;
import com.example.fatmouf.models.ResponseModel;
import com.example.fatmouf.retrofit_provider.RetrofitService;
import com.google.gson.Gson;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.fatmouf.Utilities.AppConstants.COMPETITIVEDETAILS;
import static com.example.fatmouf.Utilities.AppConstants.FROM;
import static com.example.fatmouf.Utilities.AppConstants.VIEWPARTICIPANTS;

public class CompetitiveActivity extends MyAbstractActivity {

    HomePublicModel model;

    @BindView(R.id.ll_more)
    LinearLayoutCompat ll_more;

    @BindView(R.id.ll_accept_challenge)
    LinearLayoutCompat ll_accept_challenge;

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.iv_img)
    CircleImageView iv_img;

    @BindView(R.id.tv_authername)
    AppCompatTextView tv_authername;

    @BindView(R.id.tv_location)
    AppCompatTextView tv_location;

    @BindView(R.id.tv_time)
    AppCompatTextView tv_time;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.tv_des)
    AppCompatTextView tv_des;

    @BindView(R.id.tv_des2)
    AppCompatTextView tv_des2;

    @BindView(R.id.tv_startdate)
    AppCompatTextView tv_startdate;

    @BindView(R.id.tv_enddate)
    AppCompatTextView tv_enddate;

    @BindView(R.id.iv_view_activity)
    AppCompatImageView iv_view_activity;

    @BindView(R.id.iv_view_participants)
    AppCompatImageView iv_view_participants;

    @BindView(R.id.iv_accept_challenge)
    AppCompatImageView iv_accept_challenge;

    @BindView(R.id.worm_dots_indicator)
    WormDotsIndicator worm_dots_indicator;
    private String TAG = CompetitiveActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitive);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @Override
    public void initview() {
        model = getIntent().getParcelableExtra("COMPETITIVEDETAILS");
        if (model == null) {
            finish();
        }
    }

    @Override
    public void listners() {

        PostItemPagerAdapter adapter = new PostItemPagerAdapter(this, model.getMedia());
        pager.setAdapter(adapter);
        worm_dots_indicator.setViewPager(pager);
        Glide.with(this).load(model.getUserInfo().get(0).getImage()).into(iv_img);
        tv_location.setText(model.getUserInfo().get(0).getCity());
        tv_authername.setText(model.getUserInfo().get(0).getFirstName() + " " + model.getUserInfo().get(0).getFirstName());
        tv_time.setText(model.getCreatedDate());
        tv_title.setText(model.getTitle());
        tv_des.setText(model.getDescription());
        tv_startdate.setText(model.getStartDate());
        tv_enddate.setText(model.getEndDate());
        Glide.with(this).load(R.drawable.handshake).into(iv_accept_challenge);
        Glide.with(this).load(R.drawable.view_activity).into(iv_view_activity);
        Glide.with(this).load(R.drawable.view_participants).into(iv_view_participants);
        if (model.getIsAccepted().equalsIgnoreCase("0")) {
            ll_more.setVisibility(View.GONE);
            ll_accept_challenge.setVisibility(View.VISIBLE);
        } else {
            ll_more.setVisibility(View.VISIBLE);
            ll_accept_challenge.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.iv_view_activity)
    public void ViewActivitiew() {
        Intent intent = new Intent(this, ActivitiesListActivity.class);
        intent.putExtra(FROM, "COMPETITIVEDETAILS");
        intent.putExtra("COMPETITIVEDETAILS", model);
        startActivity(intent);
    }

    @OnClick(R.id.iv_view_participants)
    public void View_Participants() {
        Intent intent = new Intent(this, ParticipantasActivity.class);
        intent.putExtra(FROM, VIEWPARTICIPANTS);
        intent.putExtra(COMPETITIVEDETAILS, model);

        startActivity(intent);
    }

    @OnClick(R.id.iv_accept_challenge)
    public void Accept_Challenge() {
        AcceptChallengeDialog dialog = new AcceptChallengeDialog(this, () -> {
            AcceptChallenge();

        });
        dialog.show();
    }

    private void AcceptChallenge() {
        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().accept_challenge("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(), model.getId(), "1");
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                ll_more.setVisibility(View.VISIBLE);
                ll_accept_challenge.setVisibility(View.GONE);
                MyLog.LogE(TAG, "AcceptChallenge   onResponse  " + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                Toast.makeText(CompetitiveActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Back(View view) {
        finish();
    }
}