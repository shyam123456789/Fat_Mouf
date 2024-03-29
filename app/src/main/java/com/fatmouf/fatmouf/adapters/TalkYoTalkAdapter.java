package com.fatmouf.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.OnItemClickListner;
import com.fatmouf.fatmouf.models.GroupModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalkYoTalkAdapter extends RecyclerView.Adapter<TalkYoTalkAdapter.TYTHolder> {

    Context context;
    ArrayList<GroupModel> list;
    OnItemClickListner listner;

    public TalkYoTalkAdapter(Context context, ArrayList<GroupModel> list, OnItemClickListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public TYTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tyt_raw, parent, false);
        return new TYTHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TYTHolder holder, int position) {
        GroupModel model = list.get(position);
        Glide.with(context).load(model.getImage()).placeholder(R.drawable.ic_avatar).into(holder.iv_img);
        holder.tv_title.setText(model.getTitle());
        holder.itemView.setOnClickListener(view -> {
            listner.OnClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
