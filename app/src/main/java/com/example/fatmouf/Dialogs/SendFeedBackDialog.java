package com.example.fatmouf.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fatmouf.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendFeedBackDialog extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sendfeedback_dialog, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.tv_back)
    public void OnBack() {
        dismiss();
    }


}
