package com.example.fatmouf.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.fatmouf.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitiveAdapter extends RecyclerView.Adapter<CompetitiveAdapter.CHolder> {


    private Context context;

    public CompetitiveAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.competitive_raw, parent, false);
        return new CHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHolder holder, int position) {

        PostItemPagerAdapter adapter = new PostItemPagerAdapter(context);
        holder.pager.setAdapter(adapter);
        holder.worm_dots_indicator.setViewPager(holder.pager);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class CHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pager)
        ViewPager pager;

        @BindView(R.id.worm_dots_indicator)
        WormDotsIndicator worm_dots_indicator;


        public CHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
