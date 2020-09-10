package com.example.fatmouf.Utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;


import com.example.fatmouf.R;

import butterknife.ButterKnife;

public class InternetConnectionError extends Dialog {


    public InternetConnectionError(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.internetconnectionerror);
        ButterKnife.bind(this);
        setCancelable(false);
    }


}
