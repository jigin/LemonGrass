package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */

public class Review3_Activity extends Activity
{
    Button sendBt;
    Spinner spinner;
    CheckBox recomntYes,recomntNo,subscribeYes,subscribeNo;
    String recomnt="Yes",subscribe="Yes",anythingAdd="",uForm="";
    ImageView backIcon;
    EditText anyAdd;

    JSONParser jsonParser;

    ProgressDialog progressDialog;

    String name,mobno,email,outlet,time,fstar,sstar,astar,recommnt,hear,add,subscribe1,ty_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review2);

        setUIElements();

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String []spinnerArray = getResources().getStringArray(R.array.hearfrom);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,spinnerArray);

        spinner.setAdapter(adapter);

        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uForm = spinner.getSelectedItem().toString();
                anythingAdd = anyAdd.getText().toString();

                if(uForm.equalsIgnoreCase("Select"))
                {
                    spinner.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please provide all fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences preferences = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(Utils.RV_RECOMNT,recomnt);
                    editor.putString(Utils.RV_HEARFORMUS,uForm);
                    editor.putString(Utils.RV_ADDANYTHING,anythingAdd);
                    editor.putString(Utils.RV_SUBSCTIBE,subscribe);
                    editor.commit();

                    gettingShared();

                    if(Utils.isNetworkConnectionAvailable(getApplicationContext()))
                    {
                        new SendReview().execute();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),R.string.noNetwork,Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        recomntYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recomntYes.setChecked(true);
                recomntNo.setChecked(false);
                recomnt = "Yes";
            }
        });
        recomntNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recomntYes.setChecked(false);
                recomntNo.setChecked(true);
                recomnt = "No";
            }
        });

        subscribeYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribeYes.setChecked(true);
                subscribe = "Yes";
                subscribeNo.setChecked(false);
            }
        });
        subscribeNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subscribeNo.setChecked(true);
                subscribe = "No";
                subscribeYes.setChecked(false);
            }
        });


    }
    void setUIElements()
    {
        jsonParser = new JSONParser();

        sendBt = (Button) findViewById(R.id.review2_send);
        spinner = (Spinner) findViewById(R.id.spinner);

        anyAdd = (EditText) findViewById(R.id.r2anytingAdd);

        backIcon = (ImageView) findViewById(R.id.fdBackicon);

        recomntYes = (CheckBox) findViewById(R.id.r2checkbox1);
        recomntYes.setChecked(true);
        recomntNo = (CheckBox) findViewById(R.id.r2checkbox2);

        subscribeYes = (CheckBox) findViewById(R.id.r2checkbox3);
        subscribeYes.setChecked(true);
        subscribeNo = (CheckBox) findViewById(R.id.r2checkbox4);
    }
    void gettingShared()
    {
        SharedPreferences pref = getSharedPreferences(Utils.PREF_NAME,MODE_PRIVATE);
        SharedPreferences pref1 = getSharedPreferences(Utils.PREF_LOGIN,MODE_PRIVATE);
        name = pref.getString(Utils.RV_NAME,"");
        mobno = pref.getString(Utils.RV_NO,"");
        email = pref.getString(Utils.RV_EMAIL,"");
        outlet = pref1.getString(Utils.BRANCH_ID,"");
        time = pref.getString(Utils.RV_TIME_VISIT,"");
        ty_order = pref.getString(Utils.RV_TYPE_ORDER,"");
        fstar = pref.getString(Utils.RV_FSTAR,"");
        sstar = pref.getString(Utils.RV_SSTAR,"");
        astar = pref.getString(Utils.RV_ASTAR,"");
        recommnt = pref.getString(Utils.RV_RECOMNT,"");
        hear = pref.getString(Utils.RV_HEARFORMUS,"");
        add = pref.getString(Utils.RV_ADDANYTHING,"");
        subscribe1 = pref.getString(Utils.RV_SUBSCTIBE,"");
    }
    public class SendReview extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Review3_Activity.this);
            progressDialog.setTitle("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            /*Intent in = new Intent(getApplicationContext(),Home_Activity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
            finish();*/
        }

        @Override
        protected String doInBackground(String... strings) {

/*
            String ReviewUrl = "http://webqua.com/lemongrass/androidapi/setreview.php?name="+name+"&mobile="+mobno+"&email="+email+"&bvisited="+outlet+"&tvisited=null&tyvisited="+time+"&reco="+recommnt+"&fstar="+fstar+"&sstar="+sstar+"&astar="+astar+"&hfrom="+hear+"&desc="+add+"&subs="+subscribe1;
*/
            String ReviewUrl = "http://webqua.com/lemongrass/androidapi/setreview.php";

            Log.e("valuess",ReviewUrl);

            Log.e("review details : ",name);
            Log.e("review details : ",mobno);
            Log.e("review details : ",email);
            Log.e("review details : ",outlet);
            Log.e("review details : ",ty_order);
            Log.e("review details : ",recommnt);
            Log.e("review details : ",fstar);
            Log.e("review details : ",sstar);
            Log.e("review details : ",astar);
            Log.e("review details : ",add);
            Log.e("review details : ",subscribe1);


            List<NameValuePair>params = new ArrayList<>();
            params.add(new BasicNameValuePair("name",name));
            params.add(new BasicNameValuePair("mobile",mobno));
            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("bvisited",outlet));
            params.add(new BasicNameValuePair("tyvisited",ty_order));
            params.add(new BasicNameValuePair("tvisited",time));
            params.add(new BasicNameValuePair("reco",recommnt));
            params.add(new BasicNameValuePair("fstar",fstar));
            params.add(new BasicNameValuePair("sstar",sstar));
            params.add(new BasicNameValuePair("astar",astar));
            params.add(new BasicNameValuePair("hfrom",hear));
            params.add(new BasicNameValuePair("desc",add));
            params.add(new BasicNameValuePair("subs",subscribe1));
            params.add(new BasicNameValuePair("source","Mobile"));


            JSONObject jsonObject = jsonParser.makeHttpRequest(ReviewUrl,"GET",params);

            Log.e("responceJson",jsonObject.toString());

            try {
                if(jsonObject.getInt("success")==1)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(Review3_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name_d = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText("Thank you for your time");
                            name_d.setText(name);
                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                    Intent in = new Intent(getApplicationContext(),Home_Activity.class);
                                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(in);
                                }
                            });
                            dialog.show();
                        }
                    });
                }
                else if(jsonObject.getInt("success")==0)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(Review3_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name_d = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText(R.string.wentwrong);
                            name_d.setText(name);
                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(Review3_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name_d = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText(R.string.wentwrong);
                            name_d.setText(name);
                            close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }
    }
}
