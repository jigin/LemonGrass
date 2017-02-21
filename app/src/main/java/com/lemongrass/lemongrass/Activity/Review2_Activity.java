package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.Utils;

import java.util.Locale;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */
public class Review2_Activity extends Activity {
    Button nextBt;
    //Spinner outlet;
    CheckBox lunch,dinner;
    TextView name,number,email;
    ImageView backIcon;

    SharedPreferences sharedPreferences;
    SharedPreferences pref;

    String sName,sEmail,sNumber,sOutlet,t_visit = "Lunch",ty_order="Dine in";
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

        String []adapterString = getResources().getStringArray(R.array.outlet);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,adapterString);

        //outlet.setAdapter(adapter);

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lunch.setChecked(true);
                t_visit = "Lunch";

                dinner.setChecked(false);
                /*dinein.setChecked(false);
                takeaway.setChecked(false);
                delivery.setChecked(false);*/

            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dinner.setChecked(true);
                t_visit = "Dinner";

                lunch.setChecked(false);
                /*dinein.setChecked(false);
                takeaway.setChecked(false);
                delivery.setChecked(false);*/
            }
        });
        /*dinein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dinein.setChecked(true);
                ty_order = "Dine In";

                *//*lunch.setChecked(false);
                dinner.setChecked(false);*//*
                takeaway.setChecked(false);
                delivery.setChecked(false);
            }
        });
        takeaway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeaway.setChecked(true);
                ty_order = "Take away";

                *//*lunch.setChecked(false);
                dinner.setChecked(false);*//*
                dinein.setChecked(false);
                delivery.setChecked(false);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.setChecked(true);
                ty_order = "Delivery";

                *//*lunch.setChecked(false);
                dinner.setChecked(false);*//*
                dinein.setChecked(false);
                takeaway.setChecked(false);
            }
        });*/

        nextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = name.getText().toString();
                sEmail = email.getText().toString();
                sNumber = number.getText().toString();
                //sOutlet = outlet.getSelectedItem().toString();

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
                /*else if(sOutlet.equals("Select Outlet"))
                {
                    //outlet.requestFocus();
                    Toast.makeText(getApplicationContext(),"please select an outlet",Toast.LENGTH_SHORT).show();
                }*/
                else
                {
                    /*Log.e("review2 name:",sName);
                    Log.e("review2 email:",sEmail);
                    Log.e("review2 number:",sNumber);
                    Log.e("review2 outlet:",sOutlet);
                    Log.e("review2 time:",t_visit);*/
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

        //outlet = (Spinner) findViewById(R.id.spinner);

        backIcon = (ImageView) findViewById(R.id.fdBackicon);

        lunch = (CheckBox) findViewById(R.id.r1_checkbox1);
        lunch.setChecked(true);
        dinner = (CheckBox) findViewById(R.id.r1_checkbox2);
        /*dinein = (CheckBox) findViewById(R.id.r1_checkbox3);
        dinein.setChecked(true);
        takeaway = (CheckBox) findViewById(R.id.r1_checkbox4);
        delivery = (CheckBox) findViewById(R.id.r1_checkbox5);*/
    }
}
