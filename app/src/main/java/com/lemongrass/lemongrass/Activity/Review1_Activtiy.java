package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lemongrass.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */

public class Review1_Activtiy extends Activity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review1);
        next=(Button)findViewById(R.id.review_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Review3_Activity.class);
                startActivity(in);
            }
        });
    }
}
