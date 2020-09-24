package com.example.fatmouf.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherUserProfileDialog extends DialogFragment {

    @BindView(R.id.tv_back)
    AppCompatTextView tv_back;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.webview)
    WebView webview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_profile, container, false);
        ButterKnife.bind(this, view);


        return view;
    }


    @OnClick(R.id.tv_back)
    public void OnBack() {
        dismiss();
    }

  @OnClick(R.id.menu)
    public void OnMenu() {
        dismiss();
    }


}
