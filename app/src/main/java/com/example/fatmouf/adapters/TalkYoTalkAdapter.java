package com.example.fatmouf.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalkYoTalkAdapter extends RecyclerView.Adapter<TalkYoTalkAdapter.TYTHolder> {


    @NonNull
    @Override
    public TYTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tyt_raw, parent, false);
        return new TYTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TYTHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class TYTHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_img)
        AppCompatImageView iv_img;

        @BindView(R.id.tv_title)
        AppCompatTextView tv_title;

        @BindView(R.id.iv_menu)
        AppCompatImageView iv_menu;

        public TYTHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
