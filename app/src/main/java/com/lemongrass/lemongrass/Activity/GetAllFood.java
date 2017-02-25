package com.lemongrass.lemongrass.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.Utils;

/**
 * Created by Jigin on 2/20/2017.
 */

public class GetAllFood extends AppCompatActivity
{
    AppDb db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_food);

        db = new AppDb(this);

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
}
