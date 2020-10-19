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
import com.fatmouf.fatmouf.models.UserDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.PHolder> {
    Context context;
    ArrayList<UserDetail> participantslist;


    public ParticipantsAdapter(Context context, ArrayList<UserDetail> participantslist) {
        this.context = context;
        this.participantslist = participantslist;
    }

    @NonNull
    @Override
    public PHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pholder_raw, parent, false);
        return new PHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PHolder holder, int position) {
        UserDetail detail = participantslist.get(position);
        holder.tv_name.setText(detail.getFirstName() + " " + detail.getLastName());
        holder.tv_location.setText(detail.getCity());
        Glide.with(context).load(detail.getImage()).placeholder(R.drawable.ic_avatar).into(holder.civ_img);
    }

    @Override
    public int getItemCount() {
        return participantslist.size();
    }

    class PHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_img)
        CircleImageView civ_img;

        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;

        @BindView(R.id.tv_location)
        AppCompatTextView tv_location;

        @BindView(R.id.iv_menu)
        AppCompatImageView iv_menu;

        public PHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
