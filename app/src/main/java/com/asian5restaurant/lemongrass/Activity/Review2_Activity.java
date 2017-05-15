package com.asian5restaurant.lemongrass.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.Utils;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */
public class Review2_Activity extends Activity {
    Button nextBt;
    CheckBox lunch,dinner;
    TextView name,number,email;
    ImageView backIcon;

    SharedPreferences sharedPreferences;
    SharedPreferences pref;

    String sName,sEmail,sNumber,t_visit = "Lunch";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review1);

        setUIElements();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunch.setChecked(true);
                t_visit = "Lunch";

                dinner.setChecked(false);

            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dinner.setChecked(true);
                t_visit = "Dinner";

                lunch.setChecked(false);
            }
        });

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = name.getText().toString();
                sEmail = email.getText().toString();
                sNumber = number.getText().toString();

                if(sName.equals(""))
                {
                    name.requestFocus();
                    name.setError(Utils.errorText);
                }
                else if(sNumber.equals(""))
                {
                    number.requestFocus();
                    number.setError(Utils.errorText);
                }
                else if(sEmail.equals(""))
                {
                    email.requestFocus();
                    email.setError(Utils.errorText);
                }
                else if(sEmail.contains(" "))
                {
                    email.requestFocus();
                    email.setError("No space allowed in email");
                }
                else
                {
                    pref = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
                    String outletId = pref.getString(Utils.BRANCH_ID,"null");

                    sharedPreferences = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Utils.RV_NAME,sName);
                    editor.putString(Utils.RV_EMAIL,sEmail);
                    editor.putString(Utils.RV_NO,sNumber);
                    editor.putString(Utils.RV_OUTLET,outletId);
                    editor.putString(Utils.RV_TIME_VISIT,t_visit);
                    editor.putString(Utils.RV_TYPE_ORDER,"Dine in");
                    editor.commit();

                    Intent in = new Intent(getApplicationContext(),Review1_Activtiy.class);
                    startActivity(in);
                }
            }
        });
    }
    void setUIElements()
    {
        name = (TextView) findViewById(R.id.r1name);
        number = (TextView) findViewById(R.id.r1mobno);
        email = (TextView) findViewById(R.id.r1email);

        nextBt = (Button) findViewById(R.id.review_next);

        backIcon = (ImageView) findViewById(R.id.fdBackicon);

        lunch = (CheckBox) findViewById(R.id.r1_checkbox1);
        lunch.setChecked(true);
        dinner = (CheckBox) findViewById(R.id.r1_checkbox2);
    }
}
