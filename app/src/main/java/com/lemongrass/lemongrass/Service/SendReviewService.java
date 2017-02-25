package com.lemongrass.lemongrass.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.Model.ReviewModel;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.Utils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jigin on 2/22/2017.
 */

public class SendReviewService extends Service
{
    JSONParser jsonParser;
    AppDb db;
    Context context = this;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        db = new AppDb(this);
        jsonParser = new JSONParser();

        new SendReviewServiceThread().execute();

    }
    public class SendReviewServiceThread extends AsyncTask<String ,String,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            if (Utils.hasInternetConnection(getApplicationContext())) {
                try {
                    List<ReviewModel> list = db.getAllReview();

                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (Utils.isNetworkAvailable(context)) {
                            String ReviewUrl = "http://www.lemongrassrestaurants.com/androidapi/setreview.php";

                            List<NameValuePair> params = new ArrayList<>();
                            params.add(new BasicNameValuePair("name", list.get(i).getName()));
                            params.add(new BasicNameValuePair("mobile", list.get(i).getMob()));
                            params.add(new BasicNameValuePair("email", list.get(i).getEmail()));
                            params.add(new BasicNameValuePair("bvisited", list.get(i).getOutlet()));
                            params.add(new BasicNameValuePair("tyvisited", list.get(i).getOrder()));
                            params.add(new BasicNameValuePair("tvisited", list.get(i).getTime()));
                            params.add(new BasicNameValuePair("reco", list.get(i).getRecomnt()));
                            params.add(new BasicNameValuePair("fstar", list.get(i).getFstar()));
                            params.add(new BasicNameValuePair("sstar", list.get(i).getSstar()));
                            params.add(new BasicNameValuePair("astar", list.get(i).getAstar()));
                            params.add(new BasicNameValuePair("hfrom", list.get(i).getHear()));
                            params.add(new BasicNameValuePair("desc", list.get(i).getAdd()));
                            params.add(new BasicNameValuePair("subs", list.get(i).getSubscibe()));
                            params.add(new BasicNameValuePair("source", "Mobile"));

                            JSONObject jsonObject = jsonParser.makeHttpRequest(ReviewUrl, "GET", params);

                            try {
                                if (jsonObject.getInt("success") == 1) {
                                    db.delReviewById(list.get(i).getId());
                                } else if (jsonObject.getInt("success") == 0) {

                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "Check you internet connectivity!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
            else {

            }
            return null;
        }

    }
}
