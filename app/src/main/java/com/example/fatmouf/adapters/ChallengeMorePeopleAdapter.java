package com.example.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatmouf.R;

import java.util.ArrayList;

public class ChallengeMorePeopleAdapter extends RecyclerView.Adapter<ChallengeMorePeopleAdapter.CMPHolder> {
    Context context;


    @NonNull
    @Override
    public CMPHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmpholder_raw, parent, false);
        return new CMPHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CMPHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CMPHolder extends RecyclerView.ViewHolder {
        public CMPHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
