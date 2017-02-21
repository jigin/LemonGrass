package com.lemongrass.lemongrass.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemongrass.lemongrass.R;

/**
 * Created by Jigin on 2/18/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewholder>
{
    @Override
    public newsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news,null,false);
        return new newsViewholder(view);
    }

    @Override
    public void onBindViewHolder(newsViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class newsViewholder extends RecyclerView.ViewHolder {
        public newsViewholder(View itemView) {
            super(itemView);
        }
    }
}
