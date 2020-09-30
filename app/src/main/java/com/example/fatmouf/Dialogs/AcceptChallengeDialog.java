package com.example.fatmouf.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.fatmouf.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcceptChallengeDialog extends Dialog {
    AcceptChallenge acceptChallenge;

    public AcceptChallengeDialog(@NonNull Context context, AcceptChallenge acceptChallenge) {
        super(context);
        this.acceptChallenge = acceptChallenge;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_chanllenge);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_cancel)
    public void Cancel() {
        dismiss();
    }

    @OnClick(R.id.tv_confirm)
    public void Accept() {
        acceptChallenge.OnAccept();
        dismiss();
    }

    public interface AcceptChallenge {
        void OnAccept();
    }

}
