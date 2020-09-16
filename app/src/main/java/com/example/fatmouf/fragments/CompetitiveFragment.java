package com.example.fatmouf.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.AppUtils;
import com.example.fatmouf.adapters.CompetitiveAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompetitiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompetitiveFragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toggle)
    RadioGroup toggle;

    @BindView(R.id.product)
    RadioButton rbtn_products;

    @BindView(R.id.post)
    RadioButton rbtn_post;
    private CompetitiveAdapter adapter;

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
        adapter = new CompetitiveAdapter(getContext());
        rv_list.setAdapter(adapter);
        toggle.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.post:
                    rbtn_post.setTextColor(getResources().getColor(R.color.white));
                    rbtn_products.setTextColor(getResources().getColor(R.color.black));
                    //      getFragmentManager().beginTransaction().replace(R.id.placeholder_1, new HomeFeaturedFragment()).commit();
                    break;

                case R.id.product:
                    rbtn_post.setTextColor(getResources().getColor(R.color.black));
                    rbtn_products.setTextColor(getResources().getColor(R.color.white));

                    //      getFragmentManager().beginTransaction().replace(R.id.placeholder_1, new FavoriteProductFragment()).commit();
                    break;


            }
        });
        rbtn_post.setChecked(true);
        return view;
    }
}