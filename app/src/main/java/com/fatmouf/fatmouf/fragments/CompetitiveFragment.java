package com.fatmouf.fatmouf.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.activities.AddChallenge;
import com.fatmouf.fatmouf.activities.BaseActivity;
import com.fatmouf.fatmouf.adapters.CompetitiveAdapter;
import com.fatmouf.fatmouf.models.HomePublicModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class CompetitiveFragment extends Fragment {


    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toggle)
    RadioGroup toggle;

    @BindView(R.id.product)
    RadioButton rbtn_products;

    @BindView(R.id.post)
    RadioButton rbtn_post;
    private CompetitiveAdapter adapter;
    private ArrayList<HomePublicModel> privateList = new ArrayList<>();
    private ArrayList<HomePublicModel> publicList = new ArrayList<>();
    private String TAG = CompetitiveFragment.class.getSimpleName();

    public static CompetitiveFragment newInstance(String param1, String param2) {
        CompetitiveFragment fragment = new CompetitiveFragment();
    /*    Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
    public void onResume() {
        super.onResume();
        AppUtils.hideKeyboard(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_competitive, container, false);
        ButterKnife.bind(this, view);

        //   showFragment(new PublicHomeFragment());
        toggle.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.post:
                    rbtn_post.setTextColor(getResources().getColor(R.color.white));
                    rbtn_products.setTextColor(getResources().getColor(R.color.black));
                    showFragment(new PublicHomeFragment());

                    break;

                case R.id.product:
                    rbtn_post.setTextColor(getResources().getColor(R.color.black));
                    rbtn_products.setTextColor(getResources().getColor(R.color.white));
                    showFragment(new PrivateHomeFragment());

                    break;


            }
        });
        rbtn_post.setChecked(true);
        return view;
    }

    private void showFragment(Fragment fra) {
        getChildFragmentManager().beginTransaction().replace(R.id.container, fra).commit();
    }

    BaseActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (BaseActivity) getActivity();

    }

    @OnClick(R.id.fab)
    public void OnAddActivity() {
        Intent intent = new Intent(getContext(), AddChallenge.class);
        startActivity(intent);
        try {
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        } catch (Exception e) {
        }
    }


}