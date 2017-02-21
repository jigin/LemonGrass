package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lemongrass.lemongrass.Model.ItemModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.Utils;

import java.io.File;
import java.util.List;

/**
 * Created by Jigin on 2/20/2017.
 */

public class GetAllFood extends AppCompatActivity
{
    AppDb db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_food);

        db = new AppDb(this);
        List<ItemModel>list = db.getData();

        clearDb();

        Intent in = new Intent(getApplicationContext(),GetAll.class);
        startActivity(in);

    }
    void clearDb()
    {
        db.delDb();
        SharedPreferences pref = getSharedPreferences(Utils.PREF_SYNC,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
    /*void delData(List<ItemModel>list)
    {
        int size = list.size();
        Log.e("size : ",size+"");

        for(int i = 0;i<size ; i++)
        {
            String imageUrl = list.get(i).getImageUrl();
            String thumbUrl = list.get(i).getThumbUrl();

            Log.e("image Url : ",imageUrl+"");
            Log.e("thumb Url : ",thumbUrl+"");

            File mainImage = new File(imageUrl);
            File thumbFile = new File(thumbUrl);

            boolean flagMain = getApplicationContext().deleteFile(list.get(i).getImageUrl());
            boolean flagThumb = getApplicationContext().deleteFile(list.get(i).getThumbUrl());


            *//*boolean flagMain = mainImage.delete();
            boolean flagThumb = thumbFile.delete();*//*

            Log.e("flagMain : ",flagMain+"");
            Log.e("flagThumb : ",flagThumb+"");

        }
    }*/
}
