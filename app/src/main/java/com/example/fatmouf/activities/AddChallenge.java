package com.example.fatmouf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.fatmouf.R;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddChallenge extends MyAbstractActivity {


    @BindView(R.id.et_title)
    AppCompatEditText et_title;

    @BindView(R.id.et_description)
    AppCompatEditText et_description;

    @BindView(R.id.et_favour)
    AppCompatEditText et_favour;

    @BindView(R.id.et_startdate)
    AppCompatTextView et_startdate;

    @BindView(R.id.et_enddate)
    AppCompatTextView et_enddate;

    @BindView(R.id.iv_addpartincipants)
    AppCompatImageView iv_addpartincipants;


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


    }

    @Override
    public void listners() {


    }

    public void post(View view) {


    }

    public void Cancel(View view) {
        finish();
    }

    public void StartDate(View view) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback((view1, year, monthOfYear, dayOfMonth) -> {

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
        Intent intent = new Intent(this, AddParticipants.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}