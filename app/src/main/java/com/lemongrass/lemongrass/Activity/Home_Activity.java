package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lemongrass.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 08-Feb-17.
 */
public class Home_Activity extends Activity{
    Button menu,review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bome);
        menu=(Button)findViewById(R.id.btn_menu);
        review = (Button) findViewById(R.id.btn_review);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplication(),Tab_Activity.class);
                startActivity(in);
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in  = new Intent(getApplicationContext(),Review1_Activtiy.class);
                startActivity(in);
            }
        });
    }
}
