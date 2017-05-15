package com.asian5restaurant.lemongrass.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.asian5restaurant.lemongrass.JSONParser;


/**
 * Created by Jigin on 2/21/2017.
 */

public class FetchItems extends Service
{
    JSONParser jsonParser;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
            jsonParser = new JSONParser();







        /*try {
            List<NameValuePair> param = new ArrayList<>();
            JSONObject jsonObject = jsonParser.makeHttpRequest(Utils.ALL_FOOD_URL,"POST",param);

            if(jsonObject.getInt(Utils.SUCCESS)==1)
            {
                JSONArray jArray = jsonObject.getJSONArray(Utils.DATA);

                ItemModel im;
                List<ItemModel>list = new ArrayList<>();
                float size = jArray.length();

                for(int i = 0;i <jArray.length();i++)
                {
                    if (Utils.isNetworkConnectionAvailable(getApplicationContext()))
                    {
                        im = new ItemModel();
                        JSONObject ob = jArray.getJSONObject(i);
                        *//**
                         * saving image files internal storage.
                         **//*

                        bitmap = getBitmapFromURL(ob.getString(Utils.IMAGE_URL));
                        thumb = getBitmapFromURL(ob.getString(Utils.THUMB_IMAGE));


                        File path = new File(Environment.getExternalStorageDirectory(), "LemonGrass");
                        if (!path.exists()) {
                            path.mkdirs();
                        }
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
                            }

                            boolean flag1 = thumb.compress(Bitmap.CompressFormat.PNG, 100, out1);
                            if (flag1) {
                                im.setThumbUrl(file1.getAbsolutePath());
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


                        im.setDescription(ob.getString(Utils.DESCRIPTION));
                        im.setIngredient(ob.getString(Utils.INGREDIENT));
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
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"Sorry!no internet connection",Toast.LENGTH_LONG).show();
                                db.delDb();
                            }
                        });
                        break;
                    }
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
        }*/








        return null;
    }
}
