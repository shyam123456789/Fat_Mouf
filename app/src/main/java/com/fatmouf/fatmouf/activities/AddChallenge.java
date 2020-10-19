package com.fatmouf.fatmouf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.models.ResponseModel;
import com.fatmouf.fatmouf.models.UserDetail;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.gson.Gson;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fatmouf.fatmouf.Utilities.AppConstants.ADDPARTICIPANTS;
import static com.fatmouf.fatmouf.Utilities.AppConstants.FROM;

public class AddChallenge extends MyAbstractActivity {


    @BindView(R.id.spinner)
    AppCompatSpinner spinner;

    @BindView(R.id.et_title)
    AppCompatEditText et_title;

    @BindView(R.id.et_description)
    AppCompatEditText et_description;

    @BindView(R.id.et_favour)
    AppCompatEditText et_favour;

    @BindView(R.id.l_participants)
    LinearLayoutCompat l_participants;

    @BindView(R.id.et_startdate)
    AppCompatTextView et_startdate;

    @BindView(R.id.et_enddate)
    AppCompatTextView et_enddate;

    @BindView(R.id.iv_addpartincipants)
    AppCompatImageView iv_addpartincipants;
    private String startDate = null;
    private String endDate = null;
    private String participantslist = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        ButterKnife.bind(this);
        initview();
        listners();
    }

    @Override
    public void initview() {
        l_participants.setVisibility(View.GONE);
    }

    @Override
    public void listners() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (0 == i) {
                    l_participants.setVisibility(View.GONE);
                } else {
                    l_participants.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void post(View view) {
        String title = et_title.getText().toString();
        String des = et_description.getText().toString();
        String flaour = et_favour.getText().toString();

        if (AppUtils.ValidateText(title, "Please Enter Title", et_title) &&
                AppUtils.ValidateText(des, "Please Enter Description", et_description) &&
                AppUtils.ValidateText(flaour, "Please Enter Flaour", et_favour)) {
            if (!startDate.isEmpty()) {
                if (!endDate.isEmpty()) {
                    String C_type = (spinner.getSelectedItemPosition() == 1) ? "public" : "private";
                    if (C_type.equalsIgnoreCase("public")) {
                        AddPublicChallenge("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(), C_type, et_title.getText().toString(), startDate, endDate, et_description.getText().toString(), et_favour.getText().toString(), participantslist);
                    } else {
                        AddPrivateChallenge("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken(), C_type, et_title.getText().toString(), startDate, endDate, et_description.getText().toString(), et_favour.getText().toString());
                    }


                } else {
                    Toast.makeText(this, "Set End Date!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Set Start Date!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void AddPrivateChallenge(String s, String c_type, String toString, String startDate, String endDate, String toString1, String toString2) {
        MyLog.LogE("TAG", ">>>  private " + s);
        MyLog.LogE("TAG", ">>>  private " + c_type);
        MyLog.LogE("TAG", ">>>  private " + toString);
        MyLog.LogE("TAG", ">>>  private " + startDate);
        MyLog.LogE("TAG", ">>>  private " + endDate);
        MyLog.LogE("TAG", ">>>  private " + toString1);
        MyLog.LogE("TAG", ">>>  private " + toString2);

        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().add_challengePrivate(s, c_type, toString, startDate, endDate, toString1, toString2);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                MyLog.LogE("TAG", ">>> private  " + new Gson().toJson(response.body()));
                MyLog.LogE("TAG", ">>> private  " + response.message());
                MyLog.LogE("TAG", ">>>  private " + response.code());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                MyLog.LogE("TAG", ">>>   " + t.getMessage());
            }
        });

    }

    private void AddPublicChallenge(String s, String c_type, String toString, String startDate, String endDate, String toString1, String toString2, String participantslist) {

        MyLog.LogE("TAG", ">>>  public " + s);
        MyLog.LogE("TAG", ">>>  public " + c_type);
        MyLog.LogE("TAG", ">>>  public " + toString);
        MyLog.LogE("TAG", ">>>  public " + startDate);
        MyLog.LogE("TAG", ">>>  public " + endDate);
        MyLog.LogE("TAG", ">>>  public " + toString1);
        MyLog.LogE("TAG", ">>>  public " + toString2);
        MyLog.LogE("TAG", ">>>  public " + participantslist);


        getProgressDialog().show();
        Call<ResponseModel> call = RetrofitService.RetrofitService().add_challengePublic(s, c_type, toString, startDate, endDate, toString1, toString2,participantslist );
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                getProgressDialog().dismiss();
                MyLog.LogE("TAG", ">>>   " + new Gson().toJson(response.body()));
                MyLog.LogE("TAG", ">>>   " + response.message());
                MyLog.LogE("TAG", ">>>   " + response.code());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                getProgressDialog().dismiss();
                MyLog.LogE("TAG", ">>>   " + t.getMessage());
            }
        });

    }

    public void Cancel(View view) {
        finish();
    }

    public void StartDate(View view) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback((view1, year, monthOfYear, dayOfMonth) -> {
                    int m = monthOfYear + 1;
                    String month = monthOfYear < 10 ? "0" + m : m + "";
                    startDate = year + "-" + month + "-" + dayOfMonth;
                    et_startdate.setText(startDate);
                })
                .spinnerTheme(R.style.NumberPickerStyle)
                .showTitle(true)
                .showDaySpinner(true)
                .defaultDate(2020, 0, 1)
                .maxDate(2040, 0, 1)
                .minDate(2020, 0, 1)
                .build()
                .show();

    }

    public void EndDate(View view) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback((view1, year, monthOfYear, dayOfMonth) -> {
                    int m = monthOfYear + 1;
                    String month = monthOfYear < 10 ? "0" + m : m + "";
                    endDate = year + "-" + month + "-" + dayOfMonth;
                    et_enddate.setText(endDate);
                })
                .spinnerTheme(R.style.NumberPickerStyle)
                .showTitle(true)
                .showDaySpinner(true)
                .defaultDate(2020, 0, 1)
                .maxDate(2040, 0, 1)
                .minDate(2020, 0, 1)
                .build()
                .show();
    }

    public void AddParticipants(View view) {

        Intent intent = new Intent(this, ParticipantasActivity.class);
        intent.putExtra(FROM, ADDPARTICIPANTS);
        startActivityForResult(intent, 122);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 122 && data != null) {
            ArrayList<UserDetail> list = data.getParcelableArrayListExtra("SELECTEDUSER");
            for (int i = 0; i < list.size(); i++) {
                UserDetail e = list.get(i);
                if ((list.size() - 1) == i) {
                    participantslist = participantslist + e.getId();
                } else {
                    participantslist = participantslist + e.getId() + ", ";
                }
            }
            MyLog.LogE("TAG", ">>  participantslist " + participantslist);
        }
    }
}