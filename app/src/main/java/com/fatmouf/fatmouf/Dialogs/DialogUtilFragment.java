package com.fatmouf.fatmouf.Dialogs;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.models.ContentModel;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogUtilFragment extends DialogFragment {

    @BindView(R.id.tv_back)
    AppCompatTextView tv_back;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;

    @BindView(R.id.bar)
    ProgressBar bar;

    private String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getString("TYPE");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ulit_dialog, container, false);
        ButterKnife.bind(this, view);
        switch (type) {
            case "PRIVACY_POLICY":
                tv_title.setText("Privacy Policy");

                break;
            case "TERMS_CONDITION":
                tv_title.setText("Terms & Condition");


                break;
            case "ABOUT_US":
                tv_title.setText("About Us");
                break;
        }

        getContent();
        return view;
    }

    private void getContent() {
        bar.setVisibility(View.VISIBLE);
        Call<ContentModel> contentModelCall = RetrofitService.RetrofitService().get_content_management("Bearer " + PreferenceManger.getPreferenceManger().getAuthToken());
        contentModelCall.enqueue(new Callback<ContentModel>() {
            @Override
            public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
                MyLog.LogE("TAG", ">>  " + new Gson().toJson(response.body()));
                MyLog.LogE("TAG", ">>  " + response.code());
                MyLog.LogE("TAG", ">>  " + response.message());
                bar.setVisibility(View.GONE);

                if (response.body() != null) {

                    switch (type) {
                        case "PRIVACY_POLICY":
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(2).getContent(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(2).getContent()));
                            }
                            break;
                        case "TERMS_CONDITION":
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(1).getContent(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(1).getContent()));
                            }

                            break;
                        case "ABOUT_US":
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(0).getContent(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                tv_content.setText(Html.fromHtml(response.body().getData().get(0).getContent()));
                            }
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<ContentModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });


    }


    @OnClick(R.id.tv_back)
    public void OnBack() {
        dismiss();
    }

}
