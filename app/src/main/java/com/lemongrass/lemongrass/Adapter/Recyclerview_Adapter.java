package com.lemongrass.lemongrass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lemongrass.lemongrass.Model.ItemModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.Utils;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 10-Feb-17.
 */

public class Recyclerview_Adapter extends RecyclerView.Adapter<Recyclerview_Adapter.RecyclerviewHolder>
{
    Context context;
    List<ItemModel>list;

    public Recyclerview_Adapter(Context context, List<ItemModel>list)
    {
        this.context = context;
        this.list = list;
    }
    @Override
    public RecyclerviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fooditem,null,false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerviewHolder holder, int position)
    {
        Utils.setTypeface(context,holder.title,"bold");
        Utils.setTypeface(context,holder.description,"regular");
        Utils.setTypeface(context,holder.price,"regular");

        /*Picasso.with(context).load(list.get(position).getImageUrl()).into(holder.image);*/

        File file = new File(list.get(position).getThumbUrl());
        Picasso.with(context).load(file).into(holder.image);

        holder.title.setText(list.get(position).getItemName());
        holder.description.setText(list.get(position).getDescription());
        holder.price.setText("AED "+list.get(position).getPrice());
        holder.itemId.setText(list.get(position).getMenuId()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerviewHolder extends RecyclerView.ViewHolder
    {
        RelativeLayout layout;
        ImageView image;
        TextView title,description,price,itemId;

        public RecyclerviewHolder(View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.fooditemLayout);

            image = (ImageView) itemView.findViewById(R.id.imageView2);

            title = (TextView) itemView.findViewById(R.id.textView);
            description = (TextView) itemView.findViewById(R.id.textView2);
            price = (TextView) itemView.findViewById(R.id.textView4);
            itemId = (TextView) itemView.findViewById(R.id.item_id);
        }
    }
}
