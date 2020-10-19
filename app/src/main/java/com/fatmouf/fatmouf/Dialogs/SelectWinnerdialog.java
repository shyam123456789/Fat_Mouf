package com.fatmouf.fatmouf.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.fatmouf.fatmouf.R;

public class SelectWinnerdialog extends Dialog {
    public SelectWinnerdialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_winner_dialog);

    }
}
