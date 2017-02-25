package com.lemongrass.lemongrass.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.Model.ReviewModel;
import com.lemongrass.lemongrass.Model.SubscriptionModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
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

    AppDb db ;

    JSONParser jsonParser;

    ProgressDialog progressDialog;

    String s_name = "",s_email="";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        //viewReview();
        //viewSubscription();

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
                else if(s_email.contains(" "))
                {
                    email.requestFocus();
                    email.setError("No space allowed in email");
                }
                else {
                    if(Utils.isNetworkAvailable(getApplicationContext()))
                    {
                        new LoadSubscribe().execute();
                    }
                    else {
                        addToDb();
                        showDialog();
                    }
                }
            }
        });

    }
    /*void viewReview()
    {
        AppDb db = new AppDb(this);
        List<ReviewModel>list = db.getAllReview();

        Log.e("list string",list.toString());

        for(int i = 0 ;i<list.size();i++)
        {
            Log.e("appdb id::",list.get(i).getId()+"");
            Log.e("appdb name::",list.get(i).getName());
            Log.e("appdb mob::",list.get(i).getMob());
            Log.e("appdb email::",list.get(i).getEmail());
            Log.e("appdb outlet::",list.get(i).getOutlet());
            Log.e("appdb time::",list.get(i).getTime());
            Log.e("appdb order::",list.get(i).getOrder());
            Log.e("appdb fstar::",list.get(i).getFstar());
            Log.e("appdb sstar::",list.get(i).getSstar());
            Log.e("appdb astar::",list.get(i).getAstar());
            Log.e("appdb recomnt::",list.get(i).getRecomnt());
            Log.e("appdb hear::",list.get(i).getHear());
            Log.e("appdb add::",list.get(i).getAdd());
            Log.e("appdb subscribe::",list.get(i).getSubscibe());
            Log.e("appdb source::",list.get(i).getSource());

        }
    }*/
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

            if (Utils.hasInternetConnection(getApplicationContext())) {

                //String SubscribeUrl = "http://www.lemongrassrestaurants.com/androidapi/setsubscribe.php?name=" + s_name + "&email=" + s_email;
                String SubscribeUrl = "http://www.lemongrassrestaurants.com/androidapi/setsubscribe.php";

                List<NameValuePair> parammss = new ArrayList<>();
                parammss.add(new BasicNameValuePair("name",s_name));
                parammss.add(new BasicNameValuePair("email",s_email));

                JSONObject jsonObject = jsonParser.makeHttpRequest(SubscribeUrl, "GET", parammss);

                try {
                    if (jsonObject.getInt("success") == 1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDialog();
                            }
                        });
                    } else if (jsonObject.getInt("success") == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addToDb();
                                showDialog();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                addToDb();
                                showDialog();
                            }
                        });
                    }
                } catch (JSONException e) {
                    addToDb();
                    e.printStackTrace();
                }
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addToDb();
                        showDialog();
                    }
                });
            }
            return null;
        }
    }
    public long addToDb()
    {
        db = new AppDb(this);

        SubscriptionModel sb = new SubscriptionModel();
        sb.setName(s_name);
        sb.setEmail(s_email);

        return (db.insertSubscription(sb));
    }
    public void showDialog()
    {
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
                dialog.dismiss();
                Intent in = new Intent(getApplicationContext(), Home_Activity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
        });
        dialog.show();
    }
    /*void viewSubscription()
    {
        db = new AppDb(this);
        List<SubscriptionModel> list = db.getSubscription();
        for (int i= 0;i<list.size();i++)
        {
            Log.e("subscription name ",list.get(i).getName());
            Log.e("subscription email :: ",list.get(i).getEmail());
        }

    }*/
}
