package com.fatmouf.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NHolder> {


    private Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_raw, parent, false);
        return new NHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NHolder holder, int position) {
        Glide.with(context).load(R.drawable.ic_avatar).into(holder.civ_img);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        AppCompatTextView tv_date;

        @BindView(R.id.tv_title)
        AppCompatTextView tv_title;

        @BindView(R.id.tv_des)
        AppCompatTextView tv_des;

        @BindView(R.id.civ_img)
        CircleImageView civ_img;

        public NHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
