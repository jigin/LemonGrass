package com.asian5restaurant.lemongrass.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.asian5restaurant.lemongrass.Adapter.NewsAdapter;
import com.asian5restaurant.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 16-Feb-17.
 */

public class News_Activity extends AppCompatActivity {
    /*RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;*/
    NewsAdapter adapter;
    ListView list ;

    //Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        list = (ListView) findViewById(R.id.listNews);

        adapter = new NewsAdapter(this);
        list.setAdapter(adapter);



        /*setUIElements();
        adapter = new NewsAdapter();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);*/
    }
    /*void setUIElements()
    {
        recyclerView = (RecyclerView) findViewById(R.id.news_recycler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon2));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }*/

}
