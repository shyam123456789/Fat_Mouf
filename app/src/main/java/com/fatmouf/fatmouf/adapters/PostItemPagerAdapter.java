package com.fatmouf.fatmouf.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.activities.VideoActivity;
import com.fatmouf.fatmouf.models.Medium;

import java.util.ArrayList;

public class PostItemPagerAdapter extends PagerAdapter {

    Context context;
    ArrayList<Medium> media = new ArrayList<>();

    public PostItemPagerAdapter(Context context, ArrayList<Medium> media) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return media.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        Medium model = media.get(position);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.pager_item, container, false);
        AppCompatImageView imageView = layout.findViewById(R.id.iv);
        Glide.with(context).load(model.getImage()).into(imageView);
        imageView.setOnClickListener(s -> {
            MyLog.LogE("PostItemPagerAdapter",">>  OnClick");
            openVideo(model);
        });
        container.addView(layout);
        return layout;
    }

    private void openVideo(Medium model) {
        if (model.getVideo() == null && model.getVideo().isEmpty()) {
            Toast.makeText(context, "No Video Found", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("VIDEO", model.getVideo());
        context.startActivity(intent);

    }
}
