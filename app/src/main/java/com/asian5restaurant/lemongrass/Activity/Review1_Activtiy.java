package com.asian5restaurant.lemongrass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.Utils;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */

public class Review1_Activtiy extends Activity {
    Button nextBt;
    //SeekBar foodReview,serviceReview,ambienceReview;
    //String starFood="0",starService="0",starAmbience="0";
    //ImageView happyFood,sadFood,happyService,sadService,happyAmbience,sadAmbience,backIcon;

    SharedPreferences sharedPreferences;
    ImageView foodStar0,foodStar1,foodStar2,foodStar3,foodStar4,foodStar5;
    ImageView serviceStar0,serviceStar1,serviceStar2,serviceStar3,serviceStar4,serviceStar5;
    ImageView ambienceStar0,ambienceStar1,ambienceStar2,ambienceStar3,ambienceStar4,ambienceStar5;
    ImageView backIcon;

    String  foodStarValue = "0";
    String serviceStarValue = "0";
    String ambienceStarValue = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        setUIElements();

        foodStarAction();
        serviceStarAction();
        ambienceStarAction();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Utils.RV_FSTAR,foodStarValue);
                editor.putString(Utils.RV_SSTAR,serviceStarValue);
                editor.putString(Utils.RV_ASTAR,ambienceStarValue);
                editor.commit();

                Intent in = new Intent(getApplicationContext(),Review3_Activity.class);
                startActivity(in);
            }
        });

        /*setUIElements();

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
        });*/
    }
    void setUIElements()
    {
        foodStar0 = (ImageView) findViewById(R.id.food_star1);
        foodStar1 = (ImageView) findViewById(R.id.food_star2);
        foodStar2 = (ImageView) findViewById(R.id.food_star3);
        foodStar3 = (ImageView) findViewById(R.id.food_star4);
        foodStar4 = (ImageView) findViewById(R.id.food_star5);
        foodStar5 = (ImageView) findViewById(R.id.food_star6);

        serviceStar0 = (ImageView) findViewById(R.id.service_star1);
        serviceStar1 = (ImageView) findViewById(R.id.service_star2);
        serviceStar2 = (ImageView) findViewById(R.id.service_star3);
        serviceStar3 = (ImageView) findViewById(R.id.service_star4);
        serviceStar4 = (ImageView) findViewById(R.id.service_star5);
        serviceStar5 = (ImageView) findViewById(R.id.service_star6);

        ambienceStar0 = (ImageView) findViewById(R.id.ambience_star1);
        ambienceStar1 = (ImageView) findViewById(R.id.ambience_star2);
        ambienceStar2 = (ImageView) findViewById(R.id.ambience_star3);
        ambienceStar3 = (ImageView) findViewById(R.id.ambience_star4);
        ambienceStar4 = (ImageView) findViewById(R.id.ambience_star5);
        ambienceStar5 = (ImageView) findViewById(R.id.ambience_star6);

        nextBt = (Button) findViewById(R.id.send_bt);
        backIcon = (ImageView) findViewById(R.id.fdBackicon);
    }
    void foodStarAction()
    {
        foodStar0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "0";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        foodStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "1";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        foodStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "2";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        foodStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "3";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        foodStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "4";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        foodStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodStarValue = "5";
                foodStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                foodStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
            }
        });
    }
    void serviceStarAction()
    {
        serviceStar0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "0";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        serviceStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "1";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        serviceStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "2";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        serviceStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "3";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        serviceStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "4";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        serviceStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStarValue = "5";
                serviceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                serviceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
            }
        });
    }
    void ambienceStarAction()
    {
        ambienceStar0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "0";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        ambienceStar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "1";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        ambienceStar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "2";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        ambienceStar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "3";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        ambienceStar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "4";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_inactive));
            }
        });
        ambienceStar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambienceStarValue = "5";
                ambienceStar0.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar1.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar2.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar3.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar4.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
                ambienceStar5.setImageDrawable(getResources().getDrawable(R.drawable.star_active));
            }
        });
    }
    /*void setUIElements()
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

    }*/
}
