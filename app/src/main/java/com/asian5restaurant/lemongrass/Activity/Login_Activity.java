package com.asian5restaurant.lemongrass.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.asian5restaurant.lemongrass.JSONParser;
import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.Utils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Login_Activity extends AppCompatActivity
{
    Button login;
    ImageView imageView;
    EditText username,password;
    String susername,spassword;
    JSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getSharedPreferences(Utils.PREF_LOGIN,MODE_PRIVATE);
        String branchId = pref.getString(Utils.BRANCH_ID,"no");

        if(!branchId.equals("no"))
        {
             Intent in = new Intent(getApplicationContext(),Home_Activity.class);
            startActivity(in);
        }
        else {
            setContentView(R.layout.activity_login);

            login = (Button) findViewById(R.id.btn_login);
            imageView = (ImageView) findViewById(R.id.imageView);
            username = (EditText) findViewById(R.id.editText);
            password = (EditText) findViewById(R.id.et_pwd);
            jsonParser = new JSONParser();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    susername = username.getText().toString();
                    spassword = password.getText().toString();
                    if (Utils.isNetworkAvailable(getApplicationContext())) {
                        new GetLogin().execute();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.noNetwork, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    public class GetLogin extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            if (Utils.hasInternetConnection(getApplicationContext())) {

                //String Url = "http://www.lemongrassrestaurants.com/androidapi/userlogin.php";

                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("userna", susername));
                list.add(new BasicNameValuePair("pass", spassword));
                JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.LOGIN_URL, "GET", list);

                try {
                    if (jsonObject.getInt("success") == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONObject j = jsonArray.getJSONObject(0);
                        String branchId = j.getString("branch_name");
                        //Log.e("branch id", branchId);

                        SharedPreferences pref = getSharedPreferences(Utils.PREF_LOGIN, MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(Utils.BRANCH_ID, branchId);
                        editor.commit();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                                Intent in = new Intent(getApplicationContext(), Home_Activity.class);
                                startActivity(in);
                            }
                        });
                    } else if (jsonObject.getInt("success") == 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login_Activity.this, R.string.invalidUser, Toast.LENGTH_LONG).show();
                                username.setText("");
                                password.setText("");
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),R.string.networkDown,Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

    }
}
