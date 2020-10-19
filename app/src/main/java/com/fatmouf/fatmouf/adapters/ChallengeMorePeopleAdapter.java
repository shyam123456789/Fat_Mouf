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

public class ChallengeMorePeopleAdapter extends RecyclerView.Adapter<ChallengeMorePeopleAdapter.CMPHolder> {
    Context context;
    ArrayList<UserDetail> list;
    RVListner listner;

    public ChallengeMorePeopleAdapter(Context context, ArrayList<UserDetail> list, RVListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public CMPHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmpholder_raw, parent, false);
        return new CMPHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CMPHolder holder, int position) {
        UserDetail model = list.get(position);
        holder.tv_name.setText(model.getFirstName() + " " + model.getLastName());
        holder.tv_location.setText(model.getCity());
        Glide.with(context).load(model.getImage()).placeholder(R.drawable.ic_avatar).into(holder.civ_img);

        if (model.isSelected()) {
            Glide.with(context).load(R.drawable.add_participant_check).into(holder.civ_check);
        } else {
            Glide.with(context).load(R.drawable.add_participant).into(holder.civ_check);

        }
        holder.civ_check.setOnClickListener(d -> {
            try {
                if (model.isSelected()) {
                    listner.OnAddParticipants(position,false);
                    model.setSelected(false);
                } else {
                    listner.OnAddParticipants(position,true);
                    model.setSelected(true);
                }
                notifyItemChanged(position);
            } catch (Exception e) {

            }
        });
        holder.itemView.setOnClickListener(f -> {
            listner.OnItemClick(model, position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CMPHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civ_img)
        CircleImageView civ_img;

        @BindView(R.id.tv_name)
        AppCompatTextView tv_name;

        @BindView(R.id.tv_location)
        AppCompatTextView tv_location;

        @BindView(R.id.civ_check)
        AppCompatImageView civ_check;

        public CMPHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface RVListner {

        void OnAddParticipants(int i,boolean selected);

        void OnItemClick(UserDetail detail, int position);

    }
}
