package com.fatmouf.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.IHolder> {

    Context context;
    ArrayList<String> list;
    OnOpenPicker picker;

    public ImagePickerAdapter(Context context, ArrayList<String> list, OnOpenPicker picker) {
        this.context = context;
        this.list = list;
        this.picker = picker;
    }

    @NonNull
    @Override
    public IHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iv_raw, parent, false);
        return new IHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IHolder holder, int position) {
        if (list.size() == position && position <4) {
            holder.iv_add.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.GONE);
            holder.iv_add.setOnClickListener(i -> {
                picker.onPicker(position);
            });
        } else {
            Glide.with(context).load(list.get(position)).into(holder.iv);
            holder.iv_add.setVisibility(View.GONE);
            holder.iv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() == 4) {
            return list.size();
        } else {
            return list.size() == 0 ? 1 : (list.size() + 1);
        }
    }

    class IHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        AppCompatImageView iv;

        @BindView(R.id.iv_add)
        AppCompatImageView iv_add;

        public IHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnOpenPicker {
        void onPicker(int i);
    }
}
