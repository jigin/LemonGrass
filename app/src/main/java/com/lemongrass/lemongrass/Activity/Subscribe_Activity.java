package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lemongrass.lemongrass.Fragment.All_Fragment;
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
 * Created by AMAL SAJU VARGHESE on 15-Feb-17.
 */

public class Subscribe_Activity extends AppCompatActivity
{
    EditText name,email;
    Button submit;

    JSONParser jsonParser;

    ProgressDialog progressDialog;

    String s_name = "",s_email="";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        name = (EditText) findViewById(R.id.sub_name);
        email = (EditText) findViewById(R.id.sub_email);
        submit = (Button) findViewById(R.id.sub_button);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_icon2));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        jsonParser = new JSONParser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s_name = name.getText().toString();
                s_email = email.getText().toString();

                if(s_name.equals("")|s_email.equals(""))
                {
                    if(s_name.equals(""))
                    {
                        name.requestFocus();
                        name.setError("Please provide your name");
                    }
                    else if(s_email.equals(""))
                    {
                        email.requestFocus();
                        email.setError("Please provide your email");
                    }

                }
                else {
                    if(Utils.isNetworkConnectionAvailable(getApplicationContext()))
                    {
                        new LoadSubscribe().execute();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),R.string.noNetwork,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    public class LoadSubscribe extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Subscribe_Activity.this);
            progressDialog.setTitle("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            name.setText("");
            email.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {

            String SubscribeUrl = "http://webqua.com/lemongrass/androidapi/setsubscribe.php?name="+s_name+"&email="+s_email;

            List<NameValuePair>parammss = new ArrayList<>();
            JSONObject jsonObject = jsonParser.makeHttpRequest(SubscribeUrl,"POST",parammss);

            try {
                if(jsonObject.getInt("success")==1)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(Subscribe_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText("Thank you for your time");
                            name.setText(s_name);
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
                else if(jsonObject.getInt("success")==0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final Dialog dialog = new Dialog(Subscribe_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText(R.string.wentwrong);
                            name.setText(s_name);
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
                            final Dialog dialog = new Dialog(Subscribe_Activity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_box);

                            TextView title = (TextView) dialog.findViewById(R.id.dialogTitle);
                            TextView name = (TextView) dialog.findViewById(R.id.dialogName);
                            Button close = (Button) dialog.findViewById(R.id.dialogClose);

                            title.setText(R.string.wentwrong);
                            name.setText(s_name);
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
