package com.example.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.fatmouf.R;
import com.example.fatmouf.Utilities.AppUtils;
import com.example.fatmouf.models.HomePublicModel;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CompetitiveAdapter extends RecyclerView.Adapter<CompetitiveAdapter.CHolder> {


    private Context context;
    ArrayList<HomePublicModel> publicList;

    public CompetitiveAdapter(Context context, ArrayList<HomePublicModel> list) {
        this.context = context;
        this.publicList = list;
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
        PostItemPagerAdapter adapter = new PostItemPagerAdapter(context,model.getMedia());
        holder.pager.setAdapter(adapter);
        holder.worm_dots_indicator.setViewPager(holder.pager);
        Glide.with(context).load(model.getUserInfo().get(0).getImage()).into(holder.iv_img);
        holder.tv_location.setText(model.getUserInfo().get(0).getCity());
        holder.tv_authername.setText(model.getUserInfo().get(0).getFirstName() + " " + model.getUserInfo().get(0).getFirstName());
        holder.tv_time.setText(model.getCreatedDate());
        holder.tv_title.setText(model.getTitle());
        holder.tv_des.setText(model.getDescription());
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


        @BindView(R.id.worm_dots_indicator)
        WormDotsIndicator worm_dots_indicator;


        public CHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
