package com.lemongrass.lemongrass.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.lemongrass.lemongrass.Fragment.All_Fragment;
import com.lemongrass.lemongrass.JSONParser;
import com.lemongrass.lemongrass.Model.ItemModel;
import com.lemongrass.lemongrass.R;
import com.lemongrass.lemongrass.Util.AppDb;
import com.lemongrass.lemongrass.Util.FilePath;
import com.lemongrass.lemongrass.Util.Utils;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    Bitmap bitmap,thumb;
    //ProgressDialog progressDialog;
    JSONParser jsonParser;

    Bitmap capturedBitmap;
    FilePath filePath;

    String status = "";

    //ProgressBar pro;

    AppDb db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*SharedPreferences pref = getSharedPreferences(Utils.PREF_SYNC,MODE_PRIVATE);
        status = pref.getString(Utils.SYCN_STATUS,"no");
        if(status.equalsIgnoreCase("yes"))
        {
            finish();
            Intent in = new Intent(getApplicationContext(),Home_Activity.class);
            startActivity(in);
            Log.e("status",status);
        }
        else {
            setContentView(R.layout.activity_offline_food);

            imageView = (ImageView) findViewById(R.id.imageView3);
            progressBar = (ProgressBar) findViewById(R.id.loading_spinner);
            textPercent = (TextView) findViewById(R.id.loading_percnt);

            filePath = new FilePath();
            db = new AppDb(this);

            jsonParser = new JSONParser();

            if(isStoragePermissionGranted())
            {
                if(Utils.isNetworkConnectionAvailable(this))
                {
                    new GetAllFood().execute();
                }
                else {
                    Toast.makeText(this,R.string.noNetwork,Toast.LENGTH_LONG).show();
                }
            }
            else {

            }
        }*/

        /*imageView = (ImageView) findViewById(R.id.imageviewgetall);
        pro = (ProgressBar) findViewById(R.id.progressBar);*/

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
            if(Utils.isNetworkConnectionAvailable(this))
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
                Log.v("permission", "Permission is granted");
                return true;
            } else{

                Log.v("permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission", "Permission is granted");
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
                if(Utils.isNetworkConnectionAvailable(this))
                {
                    new GetAllFoodItems().execute();
                }
                else {
                    Toast.makeText(this,R.string.noNetwork,Toast.LENGTH_LONG).show();
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
            //progressDialog.dismiss();
            progressBar.setVisibility(View.INVISIBLE);
            finish();
            Intent in = new Intent(getApplicationContext(),Home_Activity.class);
            startActivity(in);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            /*progressDialog = new ProgressDialog(GetAll.this);
            progressDialog.setTitle("Please wait, it may take a while...");
            progressDialog.setCancelable(false);
            progressDialog.show();*/
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            /*progressDialog.setProgress(values[0]);*/
            //pro.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                List<NameValuePair> param = new ArrayList<>();
                JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.ALL_FOOD_URL,"POST",param);

                Log.e("helloandroid","json object : "+jsonObject.toString());
                if(jsonObject.getInt(Utils.SUCCESS)==1)
                {
                    JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);
                    Log.e("hello1","json array : "+jArray.toString());

                    ItemModel im;
                    List<ItemModel>list = new ArrayList<>();
                    float size = jArray.length();

                    for(int i = 0;i <jArray.length();i++) {


                        if (Utils.isNetworkConnectionAvailable(getApplicationContext())) {



                        /*if(i>0)
                            publishProgress((i/size)*100);*/

                            im = new ItemModel();
                            JSONObject ob = jArray.getJSONObject(i);
                            Log.e("json object", "json object : " + ob.toString());

                            /**
                             * saving image files internal storage.
                             **/

                            urlImage = ob.getString(Utils.IMAGE_URL);

                            bitmap = getBitmapFromURL(ob.getString(Utils.IMAGE_URL));
                            thumb = getBitmapFromURL(ob.getString(Utils.THUMB_IMAGE));


                            File path = new File(Environment.getExternalStorageDirectory(), "LemonGrass");
                            if (!path.exists()) {
                                path.mkdirs();
                            }
                            Log.e("path", path.toString());
                            //File file = new File(path);
                            File file = new File(path, i + "image.jpg");

                            File file1 = new File(path, i + "thumb.jpg");

                            FileOutputStream out = null;
                            FileOutputStream out1 = null;
                            try {
                                out = new FileOutputStream(file);
                                out1 = new FileOutputStream(file1);

                                boolean flag = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                                if (flag) {
                                    im.setImageUrl(file.getAbsolutePath());
                                    Log.e("main image path", file.getAbsolutePath());
                                }

                                boolean flag1 = thumb.compress(Bitmap.CompressFormat.PNG, 100, out1);
                                if (flag1) {
                                    im.setThumbUrl(file1.getAbsolutePath());
                                    Log.e("thumb image path", file1.getAbsolutePath());
                                }

                                out.flush();
                                out.close();

                                out1.flush();
                                out1.close();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }





                        /*String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, i+"title", null);
                        Uri uri = Uri.parse(url);
                        String imagePath = FilePath.getPath(GetAll.this,uri);
                        im.setImageUrl(imagePath);
                        Log.e("filepath"+i+": ",":"+imagePath);

                        String thumb_url = MediaStore.Images.Media.insertImage(getContentResolver(), thumb, i+"thumb", null);

                        Uri uri1 = Uri.parse(thumb_url);
                        String thumbPath = FilePath.getPath(GetAll.this,uri1);
                        im.setThumbUrl(thumbPath);
                        Log.e("thumbpath"+i+": ",":"+thumbPath);*/





                        /*if(bitmap!=null)
                        {
                            String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, i+"title", null);
                            Uri uri = Uri.parse(url);
                            String imagePath = FilePath.getPath(GetAll.this,uri);
                            im.setImageUrl(imagePath);
                            Log.e("filepath"+i+": ",imagePath);
                        }
                        else {
                            Log.e("mainimage not there","*********************************************************");
                            im.setImageUrl("sorry");
                        }
                        if(thumb!=null)
                        {
                            String thumb_url = MediaStore.Images.Media.insertImage(getContentResolver(), thumb, i+"title1", null);
                            Uri uri1 = Uri.parse(thumb_url);
                            String thumbPath = FilePath.getPath(GetAll.this,uri1);
                            im.setThumbUrl(thumbPath);
                            Log.e("thumbpath"+i+": ",thumbPath);
                        }
                        else {
                            Log.e("thumb not there","*********************************************************");
                            im.setThumbUrl("sorry");
                        }*/

                            im.setDescription(ob.getString(Utils.DESCRIPTION));
                            im.setIngredient(ob.getString(Utils.INGREDIENT));
                            im.setItemName(ob.getString(Utils.ITEM_NAME));
                            im.setMenuGroup(ob.getString(Utils.MENU_GROUP));
                            im.setMenuId(ob.getString(Utils.MENU_ID));
                            im.setPrice(ob.getString(Utils.PRICE));

                            //list.add(im);

                            db.insertItem(im);

                            Log.e("menugroup" + i + ": ", ":" + ob.getString(Utils.MENU_GROUP));
                            Log.e("menuid" + i + ": ", ":" + ob.getString(Utils.MENU_ID));
                            Log.e("itemname" + i + ": ", ":" + ob.getString(Utils.ITEM_NAME));

                            if (i > 0) {
                                float a = i / size;
                                percnt = a * 100;
                                p = (int) percnt;
                                Log.e("percent download", p + "");

                                Log.e("size :: ", size + "");
                                Log.e("a/size::", a + "");
                                Log.e("*100::", percnt + "");
                           /* Log.e("size::",size+"");
                            Log.e("a::",a+"");
                            Log.e("pp::",pp+"");
                            Log.e("percent::",percnt+"");*/
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso.with(getApplicationContext()).load(urlImage).into(imageView);
                                    //imageView.setImageBitmap(bitmap);
                                    textPercent.setText(p + "%");
                                }
                            });

                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });*/
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"some error has occured",Toast.LENGTH_SHORT).show();
                                    db.delDb();
                                }
                            });
                            break;
                        }

                    /*long flag = db.insertDatabase(list);
                    if(flag>0)
                    {
                        SharedPreferences pref = getSharedPreferences(Utils.PREF_SYNC,MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(Utils.SYCN_STATUS,"yes");
                        editor.commit();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent in = new Intent(getApplicationContext(),Login_Activity.class);
                                startActivity(in);
                                finish();
                            }
                        });
                    }
                    else {
                        SharedPreferences pref = getSharedPreferences(Utils.PREF_SYNC,MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(Utils.SYCN_STATUS,"no");
                        editor.commit();
                    }*/
                    }


                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),"json exception...",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"something went wroing...",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
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
    /*public void getImage(int flag,Bitmap bitmap)
    {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOutputStream = null;
        File file = new File(path + "/Captures/", flag+".jpg");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            fOutputStream = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOutputStream);

            fOutputStream.flush();
            fOutputStream.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    public void getImage2(Bitmap bitmap,int flag)
    {
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOutputStream = null;
        File file = new File(path + "/Captures/", flag+".jpg");
        if (!file.exists()) {
            file.mkdirs();
        }

        if (bitmap == null) {
            //mBitmap = Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);

        }
        //Canvas canvas = new Canvas(mBitmap);
        try {


            FileOutputStream mFileOutStream = new FileOutputStream(file);
            //v.draw(canvas);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
            //byte[] arr = baos.toByteArray();
            //resultimage = Base64.encodeToString(arr, Base64.DEFAULT);
            //Log.e("image", resultimage);
            mFileOutStream.flush();
            mFileOutStream.close();
            String url = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
            Log.v("log_tag", "url: " + url);
            Log.v("path", file + "");


        } catch (Exception e) {
            Log.v("log_tag", e.toString());
        }
    }*/
}
