package com.fatmouf.fatmouf.Utilities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMore {
    public static void setupLoadMore(RecyclerView recyclerView, final OnScrollToEnd listener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItem == totalItemCount - 1) {
                    listener.scrolledToEnd(lastVisibleItem);
                }
            }

        });
    }

    public interface OnScrollToEnd {
        void scrolledToEnd(int lastVisibleItem);

    }

}
