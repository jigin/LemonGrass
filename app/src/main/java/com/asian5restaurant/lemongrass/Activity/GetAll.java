package com.asian5restaurant.lemongrass.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.asian5restaurant.lemongrass.JSONParser;
import com.asian5restaurant.lemongrass.Model.ItemModel;
import com.asian5restaurant.lemongrass.R;
import com.asian5restaurant.lemongrass.Util.AppDb;
import com.asian5restaurant.lemongrass.Util.FilePath;
import com.asian5restaurant.lemongrass.Util.Utils;
import com.squareup.picasso.Picasso;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jigin on 2/17/2017.
 */

public class GetAll extends AppCompatActivity
{
    ImageView imageView;
    ProgressBar progressBar;
    TextView textPercent;

    static String urlImage = "";

    static float percnt = 0;
    static int p = 0;

    Bitmap bitmap;
    //Bitmap thumb;
    JSONParser jsonParser;

    FilePath filePath;

    AppDb db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_food);

        db = new AppDb(this);
        db.delDb();

        imageView = (ImageView) findViewById(R.id.imageView3);
        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
        textPercent = (TextView) findViewById(R.id.loading_percnt);

        filePath = new FilePath();

        jsonParser = new JSONParser();

        if(isStoragePermissionGranted())
        {
            if(Utils.isNetworkAvailable(this))
            {
                new GetAllFoodItems().execute();
            }
            else {
                Toast.makeText(this,R.string.noNetwork,Toast.LENGTH_LONG).show();
            }
        }
        else {

        }
    }
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v("permission", "Permission is granted");
                return true;
            } else{

                //Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Log.v("permission", "Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if(requestCode == 1)
        {
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                //resume tasks needing this permission
                if(Utils.isNetworkAvailable(this))
                {
                    new GetAllFoodItems().execute();
                }
                else {
                    Toast.makeText(this,R.string.networkDown,Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permission not granded",Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public class GetAllFoodItems extends AsyncTask<Void,Integer,String>
    {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            finish();
            Intent in = new Intent(getApplicationContext(),Home_Activity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected String doInBackground(Void... params) {
            if (Utils.hasInternetConnection(getApplicationContext()))
            {
                try {
                    List<NameValuePair> param = new ArrayList<>();
                    JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.ALL_FOOD_URL, "POST", param);

                    if (jsonObject.getInt(Utils.SUCCESS) == 1) {
                        JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);

                        ItemModel im;
                        List<ItemModel> list = new ArrayList<>();
                        float size = jArray.length();

                        for (int i = 0; i < jArray.length(); i++) {
                            if (Utils.isNetworkAvailable(getApplicationContext())) {
                                im = new ItemModel();
                                JSONObject ob = jArray.getJSONObject(i);
                                /**
                                 * saving image files internal storage.
                                 **/
                                urlImage = ob.getString(Utils.IMAGE_URL);

                                bitmap = getBitmapFromURL(ob.getString(Utils.IMAGE_URL));
                                //thumb = getBitmapFromURL(ob.getString(Utils.THUMB_IMAGE));


                                File path = new File(Environment.getExternalStorageDirectory(), "Asian5");
                                if (!path.exists()) {
                                    path.mkdirs();
                                }
                                File file = new File(path, i + "image.jpg");

                                //File file1 = new File(path, i + "thumb.jpg");

                                FileOutputStream out = null;
                                FileOutputStream out1 = null;
                                try {
                                    out = new FileOutputStream(file);
                                    //out1 = new FileOutputStream(file1);

                                    boolean flag = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                                    if (flag) {
                                        im.setImageUrl(file.getAbsolutePath());
                                    }

                                    //boolean flag1 = thumb.compress(Bitmap.CompressFormat.PNG, 100, out1);
                                    /*if (flag1) {
                                        im.setThumbUrl(file1.getAbsolutePath());
                                    }*/

                                    out.flush();
                                    out.close();

                                    out1.flush();
                                    out1.close();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                im.setDescription(ob.getString(Utils.DESCRIPTION));
                                //im.setIngredient(ob.getString(Utils.INGREDIENT));
                                im.setItemName(ob.getString(Utils.ITEM_NAME));
                                im.setMenuGroup(ob.getString(Utils.MENU_GROUP));
                                im.setMenuId(ob.getString(Utils.MENU_ID));
                                im.setPrice(ob.getString(Utils.PRICE));


                                db.insertItem(im);

                                if (i > 0) {
                                    float a = i / size;
                                    percnt = a * 100;
                                    p = (int) percnt;
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Picasso.with(getApplicationContext()).load(urlImage).into(imageView);
                                        textPercent.setText(p + "%");
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), R.string.networkDown, Toast.LENGTH_LONG).show();
                                        db.delDb();
                                    }
                                });
                                break;
                            }
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "json exception...", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "something went wroing...", Toast.LENGTH_SHORT).show();
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
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (Exception e) {
            return null;
        }
    }
}
