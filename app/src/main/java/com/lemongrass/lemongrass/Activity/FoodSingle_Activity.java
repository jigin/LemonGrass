package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lemongrass.lemongrass.Fragment.All_Fragment;
import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.Model.ItemModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.Utils;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 14-Feb-17.
 */

public class FoodSingle_Activity extends AppCompatActivity
{
    ProgressDialog progressDialog;

    ImageView itemImage;
    TextView title,desc,nutri;
    Button price;

    String itemId = "";

    ItemModel im;

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

        /*Bitmap bitmap = BitmapFactory.decodeFile(im.getImageUrl());

        itemImage.setImageBitmap(bitmap);*/
        title.setText(im.getItemName());
        desc.setText(im.getDescription());
        price.setText("AED "+im.getPrice());
        nutri.setText(im.getIngredient());

        /*if(Utils.isNetworkConnectionAvailable(getApplicationContext()))
        {
            new GetSingleView().execute();
        }
        else {
            Toast.makeText(getApplicationContext(),R.string.noNetwork,Toast.LENGTH_LONG).show();
        }*/


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
    /*public class GetSingleView extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Picasso.with(getApplicationContext()).load(im.getImageUrl()).into(itemImage);
            title.setText(im.getItemName());
            desc.setText(im.getDescription());
            price.setText("AED "+im.getPrice());
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FoodSingle_Activity.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            List<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("itemid",itemId));
            JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.SINGLE_ITEM_URL,"GET",param);

            Log.e("lemongrasssingle",jsonObject.toString());

            try {
                if(jsonObject.getInt(Utils.SUCCESS)==1)
                {
                    JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);
                    Log.e("hello1",jArray.toString());

                    im = new ItemModel();
                    JSONObject ob = jArray.getJSONObject(0);

                    im.setImageUrl(ob.getString(Utils.IMAGE_URL));
                    im.setDescription(ob.getString(Utils.DESCRIPTION));
                    im.setItemName(ob.getString(Utils.ITEM_NAME));
                    im.setMenuGroup(ob.getString(Utils.MENU_GROUP));
                    im.setMenuId(ob.getString(Utils.MENU_ID));
                    im.setPrice(ob.getString(Utils.PRICE));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }*/
}
