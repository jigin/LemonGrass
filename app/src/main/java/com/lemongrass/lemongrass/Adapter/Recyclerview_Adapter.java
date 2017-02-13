package com.lemongrass.lemongrass.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemongrass.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 10-Feb-17.
 */

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder>
{
    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fooditem,null,false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder {

        public RecyclerviewHolder(View itemView) {
            super(itemView);
        }
    }
}
