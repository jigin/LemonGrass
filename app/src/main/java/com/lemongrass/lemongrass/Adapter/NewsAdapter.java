package com.lemongrass.lemongrass.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lemongrass.lemongrass.R;

/**
 * Created by Jigin on 2/18/2017.
 */

public class NewsAdapter extends BaseAdapter
{
    Context context;
    public NewsAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View views = view;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_news,null);
        }

        TextView title,desc,date,month,year;
        ImageView img;
        Button viewMore;

        title = (TextView) view.findViewById(R.id.tv_title);
        desc = (TextView) view.findViewById(R.id.tv_desc);
        date = (TextView) view.findViewById(R.id.tv_date);
        month = (TextView) view.findViewById(R.id.tv_month);
        year = (TextView) view.findViewById(R.id.tv_year);

        img = (ImageView) view.findViewById(R.id.im_news_thumbnail);

        viewMore = (Button) view.findViewById(R.id.btn_news_viewmore);

        return view;
    }
}
/*public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewholder>
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
        return 3;
    }

    public class newsViewholder extends RecyclerView.ViewHolder {
        public newsViewholder(View itemView) {
            super(itemView);
        }
    }
}*/
