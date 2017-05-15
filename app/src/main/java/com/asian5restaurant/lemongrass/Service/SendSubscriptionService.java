package com.asian5restaurant.lemongrass.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.asian5restaurant.lemongrass.JSONParser;
import com.asian5restaurant.lemongrass.Model.SubscriptionModel;
import com.asian5restaurant.lemongrass.Util.AppDb;
import com.asian5restaurant.lemongrass.Util.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jigin on 2/22/2017.
 */

public class SendSubscriptionService extends Service
{
    JSONParser jsonParser;
    AppDb db ;
    Context context = this;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        new SendSubscribe().execute();
    }

    public class SendSubscribe extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {

            //Log.e("subscription service::","inside");
            db = new AppDb(context);
            jsonParser = new JSONParser();

            List<SubscriptionModel>list = db.getSubscription();
            int size = list.size();

            //Log.e("service db list size",list.size()+"");

            if (Utils.hasInternetConnection(getApplicationContext())) {

                for(int i =0; i<size;i ++) {
                    //Log.e("subscription json","inside forloop");
                    if (Utils.isNetworkAvailable(context)) {
                        //String SubscribeUrl = "http://www.lemongrassrestaurants.com/androidapi/setsubscribe.php";


                        List<NameValuePair> parammss = new ArrayList<>();
                        parammss.add(new BasicNameValuePair("name",list.get(i).getName()));
                        parammss.add(new BasicNameValuePair("email",list.get(i).getEmail()));

                        JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.SET_SUBSCRIBE_URL, "GET", parammss);

                        //Log.e("subscription json",jsonObject.toString());

                        try {
                            if (jsonObject.getInt("success") == 1) {
                                //Log.e("subsciribe jsonrep::",jsonObject.getInt("success")+"");
                                db.delSubscrById(list.get(i).getId());
                            } else if (jsonObject.getInt("success") == 0) {

                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            else {
            }
            return null;
        }
    }
}
