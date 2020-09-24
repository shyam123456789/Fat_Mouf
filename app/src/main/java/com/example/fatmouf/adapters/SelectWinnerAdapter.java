package com.example.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatmouf.R;

public class SelectWinnerAdapter extends RecyclerView.Adapter<SelectWinnerAdapter.SWHolder> {
    Context context;


    @NonNull
    @Override
    public SWHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pholder_raw, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SWHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SWHolder extends RecyclerView.ViewHolder {
        public SWHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
