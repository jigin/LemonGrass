package com.asian5restaurant.lemongrass.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.asian5restaurant.lemongrass.JSONParser;
import com.asian5restaurant.lemongrass.Model.ItemModel;
import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.AppDb;
import com.asian5restaurant.lemongrass.Util.Utils;
import com.squareup.picasso.Picasso;
import java.io.File;

/**
 * Created by AMAL SAJU VARGHESE on 14-Feb-17.
 */

public class FoodSingle_Activity extends AppCompatActivity
{
    ImageView itemImage;
    TextView title,desc,nutri;
    Button price;
    String itemId = "";
    JSONParser jsonParser ;
    AppDb db;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_single);

        setUIElements();

        itemId = getIntent().getStringExtra(Utils.MENU_ID);

        db = new AppDb(this);
        ItemModel im = db.getSingleItem(itemId);
        File file = new File(im.getImageUrl());
        Picasso.with(this).load(file).into(itemImage);
        title.setText(im.getItemName());
        desc.setText(im.getDescription());
        price.setText("AED "+im.getPrice());
        nutri.setText(im.getIngredient());


    }
    void setUIElements()
    {
        itemImage = (ImageView) findViewById(R.id.itemSingleImage);
        title = (TextView) findViewById(R.id.itemTitle);
        desc = (TextView) findViewById(R.id.itemDesc);
        price = (Button) findViewById(R.id.itemPrice);
        nutri = (TextView) findViewById(R.id.itemNuti);

        Utils.setTypeface(this,title,"bold");
        Utils.setTypeface(this,desc,"regular");
        Utils.setTypeface(this,price,"regular");

        jsonParser = new JSONParser();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon2));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
