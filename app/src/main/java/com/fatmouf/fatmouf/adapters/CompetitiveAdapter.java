package com.fatmouf.fatmouf.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.activities.BaseActivity;
import com.fatmouf.fatmouf.activities.CompetitiveActivity;
import com.fatmouf.fatmouf.models.HomePublicModel;
import com.google.gson.Gson;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompetitiveAdapter extends RecyclerView.Adapter<CompetitiveAdapter.CHolder> {


    private Context context;
    ArrayList<HomePublicModel> publicList;
    String from;

    public CompetitiveAdapter(Context context, ArrayList<HomePublicModel> list, String from) {
        this.context = context;
        this.publicList = list;
        this.from = from;
    }

    @NonNull
    @Override
    public CHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.competitive_raw, parent, false);
        return new CHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHolder holder, int position) {
        HomePublicModel model = publicList.get(position);
        MyLog.LogE("TAG","MEDIA  "+new Gson().toJson(model.getMedia()));
        PostItemPagerAdapter adapter = new PostItemPagerAdapter(context, model.getMedia());
        holder.pager.setAdapter(adapter);
        holder.worm_dots_indicator.setViewPager(holder.pager);
        Glide.with(context).load(model.getUserInfo().get(0).getImage()).placeholder(R.drawable.ic_avatar).into(holder.iv_img);
        holder.tv_location.setText(model.getUserInfo().get(0).getCity());
        holder.tv_authername.setText(model.getUserInfo().get(0).getFirstName() + " " + model.getUserInfo().get(0).getFirstName());
        holder.tv_time.setText(model.getCreatedDate());
        holder.tv_title.setText(model.getTitle());
        holder.tv_des.setText(model.getDescription());
        holder.itemView.setOnClickListener(e -> {
            Intent intent = new Intent(context, CompetitiveActivity.class);
            intent.putExtra("COMPETITIVEDETAILS", model);
            context.startActivity(intent);
            try {
                BaseActivity activity = (BaseActivity) context;
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } catch (Exception e2) {
            }
        });

        holder.iv_menu.setOnClickListener(view -> {
            showPopupMenu(view);
        });

    }

    private void showPopupMenu(View view) {

        PopupMenu popup = new PopupMenu(context, view);

        popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());
        if (from.equalsIgnoreCase("from")) {
            popup.getMenu().findItem(R.id.action_delete).setVisible(false);
            popup.getMenu().findItem(R.id.action_update).setVisible(false);
            popup.getMenu().findItem(R.id.action_report).setVisible(true);
        } else {
            popup.getMenu().findItem(R.id.action_delete).setVisible(true);
            popup.getMenu().findItem(R.id.action_update).setVisible(true);
            popup.getMenu().findItem(R.id.action_report).setVisible(false);
        }
        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_delete:

                    break;
                case R.id.action_report:

                    break;
                case R.id.action_update:

                    break;
            }

            return false;
        });
        popup.show();
    }

    @Override
    public int getItemCount() {
        return publicList.size();

    }

    class CHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pager)
        ViewPager pager;

        @BindView(R.id.iv_img)
        CircleImageView iv_img;

        @BindView(R.id.tv_authername)
        AppCompatTextView tv_authername;

        @BindView(R.id.tv_location)
        AppCompatTextView tv_location;

        @BindView(R.id.tv_time)
        AppCompatTextView tv_time;

        @BindView(R.id.tv_title)
        AppCompatTextView tv_title;

        @BindView(R.id.tv_des)
        AppCompatTextView tv_des;

        @BindView(R.id.iv_menu)
        AppCompatImageView iv_menu;


        @BindView(R.id.worm_dots_indicator)
        WormDotsIndicator worm_dots_indicator;


        public CHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
