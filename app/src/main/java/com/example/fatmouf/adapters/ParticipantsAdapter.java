package com.example.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatmouf.R;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.PHolder> {
    Context context;


    @NonNull
    @Override
    public PHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pholder_raw, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PHolder extends RecyclerView.ViewHolder {
        public PHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
