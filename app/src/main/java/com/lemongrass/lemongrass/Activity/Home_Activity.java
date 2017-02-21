package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.Utils;

/**
 * Created by AMAL SAJU VARGHESE on 08-Feb-17.
 */
public class Home_Activity extends Activity{
    Button menu,review,news,subscribe;
    LinearLayout menuLayout,reviewLayout,newsLayout,subscribeLayout;
    //ImageView refresh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bome);

        setUIElements();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplication(),Tab_Activity.class);
                startActivity(in);
            }
        });
        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplication(),Tab_Activity.class);
                startActivity(in);
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(getApplicationContext(),Review2_Activity.class);
                startActivity(in);
            }
        });
        reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(getApplicationContext(),Review2_Activity.class);
                startActivity(in);
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Subscribe_Activity.class);
                startActivity(in);
            }
        });
        subscribeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Subscribe_Activity.class);
                startActivity(in);
            }
        });
        /*refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home_Activity.this);
                builder.setTitle("Warning!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent in = new Intent(getApplicationContext(),GetAllFood.class);
                        startActivity(in);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();

            }
        });*/
        /*news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),News_Activity.class);
                startActivity(in);
            }
        });
        newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),News_Activity.class);
                startActivity(in);
            }
        });*/

    }
    void setUIElements()
    {
        menu=(Button)findViewById(R.id.btn_menu);
        review = (Button) findViewById(R.id.btn_review);
        news = (Button) findViewById(R.id.btn_news);
        subscribe = (Button) findViewById(R.id.btn_suscribe);

        menuLayout = (LinearLayout) findViewById(R.id.menu_layout);
        reviewLayout = (LinearLayout) findViewById(R.id.review_layout);
        newsLayout = (LinearLayout) findViewById(R.id.news_layout);
        subscribeLayout = (LinearLayout) findViewById(R.id.subscribe_layout);

        /*refresh = (ImageView)findViewById(R.id.imageView3) ;*/

        Utils.setTypeface(getApplicationContext(),menu,"regular");
        Utils.setTypeface(getApplicationContext(),review,"bold");
        Utils.setTypeface(getApplicationContext(),news,"bold");
        Utils.setTypeface(getApplicationContext(),subscribe,"bold");
    }
}
