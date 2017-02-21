package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.Utils;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */

public class Review1_Activtiy extends Activity {
    Button nextBt;
    SeekBar foodReview,serviceReview,ambienceReview;
    String starFood="0",starService="0",starAmbience="0";
    ImageView happyFood,sadFood,happyService,sadService,happyAmbience,sadAmbience,backIcon;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        setUIElements();

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starFood = foodReview.getProgress()+"";
                starService = serviceReview.getProgress()+"";
                starAmbience = ambienceReview.getProgress()+"";

                sharedPreferences = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Utils.RV_FSTAR,starFood);
                editor.putString(Utils.RV_SSTAR,starService);
                editor.putString(Utils.RV_ASTAR,starAmbience);
                editor.commit();

                Intent in = new Intent(getApplicationContext(),Review3_Activity.class);
                startActivity(in);
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        foodReview.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>=3)
                {
                    happyFood.setImageDrawable(getResources().getDrawable(R.drawable.smielygreen));
                    sadFood.setImageDrawable(getResources().getDrawable(R.drawable.smielygreysad));
                }
                else if(progress<3)
                {
                    happyFood.setImageDrawable(getResources().getDrawable(R.drawable.smielygreyhappy));
                    sadFood.setImageDrawable(getResources().getDrawable(R.drawable.smielyred));
                }
                foodReview.setMax(5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        serviceReview.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>=3)
                {
                    happyService.setImageDrawable(getResources().getDrawable(R.drawable.smielygreen));
                    sadService.setImageDrawable(getResources().getDrawable(R.drawable.smielygreysad));
                }
                else if(progress<3)
                {
                    happyService.setImageDrawable(getResources().getDrawable(R.drawable.smielygreyhappy));
                    sadService.setImageDrawable(getResources().getDrawable(R.drawable.smielyred));
                }
                serviceReview.setMax(5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ambienceReview.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>=3)
                {
                    happyAmbience.setImageDrawable(getResources().getDrawable(R.drawable.smielygreen));
                    sadAmbience.setImageDrawable(getResources().getDrawable(R.drawable.smielygreysad));
                }
                else if(progress<3)
                {
                    happyAmbience.setImageDrawable(getResources().getDrawable(R.drawable.smielygreyhappy));
                    sadAmbience.setImageDrawable(getResources().getDrawable(R.drawable.smielyred));
                }
                ambienceReview.setMax(5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    void setUIElements()
    {
        nextBt = (Button) findViewById(R.id.send_bt);

        backIcon = (ImageView) findViewById(R.id.fdBackicon);

        foodReview = (SeekBar) findViewById(R.id.seekbar1);
        serviceReview = (SeekBar) findViewById(R.id.seekbar2);
        ambienceReview = (SeekBar) findViewById(R.id.seekbar3);

        happyFood = (ImageView) findViewById(R.id.img_happy1);
        happyService = (ImageView) findViewById(R.id.img_happy2);
        happyAmbience = (ImageView) findViewById(R.id.img_happy3);

        sadFood = (ImageView) findViewById(R.id.img_sad1);
        sadService = (ImageView) findViewById(R.id.img_sad2);
        sadAmbience = (ImageView) findViewById(R.id.img_sad3);

    }

    /*public void logShared()
    {
        SharedPreferences preferences = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
        Log.e("Pref."+Utils.RV_NAME,preferences.getString(Utils.RV_NAME,""));
        Log.e("Pref."+Utils.RV_EMAIL,preferences.getString(Utils.RV_EMAIL,""));
        Log.e("Pref."+Utils.RV_NO,preferences.getString(Utils.RV_NO,""));
        Log.e("Pref."+Utils.RV_OUTLET,preferences.getString(Utils.RV_OUTLET,""));
        Log.e("Pref."+Utils.RV_TIME_VISIT,preferences.getString(Utils.RV_TIME_VISIT,""));
    }*/
}
