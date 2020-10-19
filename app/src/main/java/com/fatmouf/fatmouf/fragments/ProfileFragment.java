package com.fatmouf.fatmouf.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.activities.ActivitiesListActivity;
import com.fatmouf.fatmouf.activities.EditProfileActivity;
import com.fatmouf.fatmouf.models.UserDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.fatmouf.fatmouf.Utilities.AppConstants.ACCEPTEDCHALLENGE;
import static com.fatmouf.fatmouf.Utilities.AppConstants.FROM;
import static com.fatmouf.fatmouf.Utilities.AppConstants.VIEWMYCHALLENGE;

public class ProfileFragment extends Fragment {

    @BindView(R.id.iv_my)
    AppCompatImageView iv_my;

    @BindView(R.id.civ_dp)
    CircleImageView civ_dp;

    @BindView(R.id.iv_pc)
    AppCompatImageView iv_pc;

    @BindView(R.id.tv_name)
    AppCompatTextView tv_name;

    @BindView(R.id.tv_location)
    AppCompatTextView tv_location;

    @BindView(R.id.tv_email)
    AppCompatTextView tv_email;

    @BindView(R.id.tv_phone)
    AppCompatTextView tv_phone;

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
    /*    args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
    */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initView();


        return view;
    }

    private void initView() {
        Glide.with(getContext()).load(R.drawable.handshake).into(iv_my);
        Glide.with(getContext()).load(R.drawable.participant_challenge).into(iv_pc);
        UserDetail detail = PreferenceManger.getPreferenceManger().getUserdetail();
        if (detail != null) {
            Glide.with(getContext()).load(detail.getImage()).placeholder(R.drawable.ic_avatar).into(civ_dp);
            tv_name.setText(detail.getFirstName() + " " + detail.getLastName());
            tv_email.setText(detail.getEmail());
            tv_phone.setText(detail.getPhonenumber());
            tv_location.setText(detail.getCity() + ", " + detail.getState());

        }
    }


    @OnClick(R.id.iv_my)
    public void MyChallenge() {
        Intent intent = new Intent(getContext(), ActivitiesListActivity.class);
        intent.putExtra(FROM, VIEWMYCHALLENGE);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.iv_pc)
    public void AcceptedChallenge() {
        Intent intent = new Intent(getContext(), ActivitiesListActivity.class);
        intent.putExtra(FROM, ACCEPTEDCHALLENGE);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @OnClick(R.id.edit_profile)
    public void OnEditProfile() {
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}