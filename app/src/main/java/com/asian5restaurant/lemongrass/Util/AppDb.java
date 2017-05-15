package com.asian5restaurant.lemongrass.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asian5restaurant.lemongrass.Model.ItemModel;
import com.asian5restaurant.lemongrass.Model.ReviewModel;
import com.asian5restaurant.lemongrass.Model.SubscriptionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jigin on 2/17/2017.
 */

public class AppDb extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "LEMONGRASSDB";
    private static final int DATABASE_VERSION = 1;


    /****************************** FOOD TABLE DETAILS *************************************/
    private static final String TABLE_ITEM = "TableItem";
    private static final String ITEM_ID = "itemid";
    private static final String ITEM_IMAGE_PATH = "itemimagepath";
    private static final String ITEM_THUMB_PATH = "itemthumbpath";
    private static final String ITEM_MENU_GROUP = "itemmenugroup";
    private static final String ITEM_PRICE = "itemprice";
    private static final String ITEM_NAME = "itemname";
    private static final String ITEM_DESCRIPTION = "itemdescription";
    private static final String ITEM_INGREDIENT = "itemingredient";
    private static final String ITEM_MENU_ID = "itemmenuid";


    /****************************** REVIEW TABLE DETAILS *************************************/
    private static final String TABLE_REVIEW = "TableReview";
    private static final String RV_ID = "rvid";
    private static final String RV_NAME = "rvname";
    private static final String RV_MOB = "rvmobile";
    private static final String RV_EMAIL = "rvemail";
    private static final String RV_OUTLET = "rvoutlet";
    private static final String RV_TIME = "rvtime";
    private static final String RV_ORDER = "rvorder";
    private static final String RV_FSTAR = "rvfstar";
    private static final String RV_SSTAR = "rvsstar";
    private static final String RV_ASTAR = "rvastar";
    private static final String RV_RECOMNT = "rvrecomment";
    private static final String RV_HEAR = "rvhear";
    private static final String RV_ADD = "rvadd";
    private static final String RV_SUBSCRIBE = "rvsubscribe";
    private static final String RV_SOURCE = "rvsource";

    /****************************** FOOD TABLE DETAILS *************************************/
    private static final String TABLE_SUBSCRIPTION = "TableSubscription";
    private static final String SB_ID = "sb_id";
    private static final String SB_NAME = "sb_name";
    private static final String SB_EMAIL = "sb_email";

    public AppDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableQuery = "CREATE TABLE "+TABLE_ITEM+"("+ITEM_ID+" INTEGER PRIMARY KEY,"+ITEM_IMAGE_PATH+" TEXT,"+ITEM_THUMB_PATH+" TEXT,"+ITEM_MENU_GROUP+" TEXT,"+ITEM_PRICE+" TEXT,"+ITEM_NAME+" TEXT,"+ITEM_DESCRIPTION+" TEXT,"+ITEM_INGREDIENT+" TEXT,"+ITEM_MENU_ID+" TEXT"+")";
        db.execSQL(createTableQuery);

        String createTableReview = "CREATE TABLE "+TABLE_REVIEW+"("+RV_ID+" INTEGER PRIMARY KEY,"+RV_NAME+" TEXT,"+RV_MOB+" TEXT,"+RV_EMAIL+" TEXT,"+RV_OUTLET+" TEXT,"+RV_TIME+" TEXT,"+RV_ORDER+" TEXT,"+RV_FSTAR+" TEXT,"+RV_SSTAR+" TEXT,"+RV_ASTAR+" TEXT,"+RV_RECOMNT+" TEXT,"+RV_HEAR+" TEXT,"+RV_ADD+" TEXT,"+RV_SUBSCRIBE+" TEXT,"+RV_SOURCE+" TEXT"+")";
        db.execSQL(createTableReview);

        String createTableSubscription = "CREATE TABLE "+TABLE_SUBSCRIPTION+"("+SB_ID+" INTEGER PRIMARY KEY,"+SB_NAME+" TEXT,"+SB_EMAIL+" TEXT"+")";
        db.execSQL(createTableSubscription);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        String updateQuery = "DROP TABLE IF EXISTS "+TABLE_ITEM;
        db.execSQL(updateQuery);

        String updateReview = "DROP TABLE IF EXISTS "+TABLE_REVIEW;
        db.execSQL(updateReview);

        String updateSubscription = "DROP TABLE IF EXISTS "+TABLE_SUBSCRIPTION;
        db.execSQL(updateSubscription);
    }

    /** ********************************** Food item table.. ********************************** */

    public long insertItem(ItemModel im)
    {
        long flag = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AppDb.ITEM_IMAGE_PATH,im.getImageUrl());
        //values.put(AppDb.ITEM_THUMB_PATH,im.getThumbUrl());
        values.put(AppDb.ITEM_MENU_GROUP,im.getMenuGroup());
        values.put(AppDb.ITEM_PRICE,im.getPrice());
        values.put(AppDb.ITEM_NAME,im.getItemName());
        values.put(AppDb.ITEM_DESCRIPTION,im.getDescription());
        //values.put(AppDb.ITEM_INGREDIENT,im.getIngredient());
        values.put(AppDb.ITEM_MENU_ID,im.getMenuId());

        flag = db.insert(TABLE_ITEM,null,values);
        return flag;
    }
    public List<ItemModel> getData()
    {
        List<ItemModel>list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String Query = "SELECT * FROM "+TABLE_ITEM;

        Cursor c = db.rawQuery(Query,null);
        ItemModel im;
        if(c.moveToFirst())
        {
            do {
                im = new ItemModel();

                im.setId(c.getInt(0));
                im.setImageUrl(c.getString(1));
                im.setThumbUrl(c.getString(2));
                im.setMenuGroup(c.getString(3));
                im.setPrice(c.getString(4));
                im.setItemName(c.getString(5));
                im.setDescription(c.getString(6));
                im.setIngredient(c.getString(7));
                im.setMenuId(c.getString(8));

                list.add(im);
            }while (c.moveToNext());
        }
        return list;
    }
    public List<ItemModel> getItemByCat(String catId)
    {
        List<ItemModel>list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String Query = "SELECT * FROM "+TABLE_ITEM+" WHERE "+ITEM_MENU_GROUP+"='"+catId+"'";

        Cursor c = db.rawQuery(Query,null);
        ItemModel im;
        if(c.moveToFirst())
        {
            do {
                im = new ItemModel();

                im.setId(c.getInt(0));
                im.setImageUrl(c.getString(1));
                im.setThumbUrl(c.getString(2));
                im.setMenuGroup(c.getString(3));
                im.setPrice(c.getString(4));
                im.setItemName(c.getString(5));
                im.setDescription(c.getString(6));
                im.setIngredient(c.getString(7));
                im.setMenuId(c.getString(8));

                list.add(im);
            }while (c.moveToNext());
        }

        return list;
    }
    public ItemModel getSingleItem(String itemId)
    {
        ItemModel im = new ItemModel();
        SQLiteDatabase db = this.getReadableDatabase();

        String Query = "SELECT * FROM "+TABLE_ITEM+" WHERE "+ITEM_MENU_ID+"="+itemId;

        Cursor c = db.rawQuery(Query,null);
        if(c.moveToFirst())
        {
            im.setImageUrl(c.getString(1));
            im.setThumbUrl(c.getString(2));
            im.setMenuGroup(c.getString(3));
            im.setPrice(c.getString(4));
            im.setItemName(c.getString(5));
            im.setDescription(c.getString(6));
            im.setIngredient(c.getString(7));
            im.setMenuId(c.getString(8));
        }

        return im;
    }
    public void delDb ()
    {
        String Query = "DELETE FROM "+TABLE_ITEM;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Query);
    }

    /** ********************************** Review table.. ************************************ */
    public long insertReview(ReviewModel rm)
    {
        long flag = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(AppDb.RV_NAME,rm.getName());
        values.put(AppDb.RV_MOB,rm.getMob());
        values.put(AppDb.RV_EMAIL,rm.getEmail());
        values.put(AppDb.RV_OUTLET,rm.getOutlet());
        values.put(AppDb.RV_TIME,rm.getTime());
        values.put(AppDb.RV_ORDER,rm.getOrder());
        values.put(AppDb.RV_FSTAR,rm.getFstar());
        values.put(AppDb.RV_SSTAR,rm.getSstar());
        values.put(AppDb.RV_ASTAR,rm.getAstar());
        values.put(AppDb.RV_RECOMNT,rm.getRecomnt());
        values.put(AppDb.RV_HEAR,rm.getHear());
        values.put(AppDb.RV_ADD,rm.getAdd());
        values.put(AppDb.RV_SUBSCRIBE,rm.getSubscibe());
        values.put(AppDb.RV_SOURCE,rm.getSource());

        flag = db.insert(TABLE_REVIEW,null,values);

        return flag;
    }
    public List<ReviewModel> getAllReview()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ReviewModel>list = new ArrayList<>();
        ReviewModel rm ;

        String Query = "SELECT * FROM "+TABLE_REVIEW;
        Cursor c = db.rawQuery(Query,null);
        if(c.moveToFirst())
        {
            do {
                rm = new ReviewModel();
                rm.setId(c.getInt(0));
                rm.setName(c.getString(1));
                rm.setMob(c.getString(2));
                rm.setEmail(c.getString(3));
                rm.setOutlet(c.getString(4));
                rm.setTime(c.getString(5));
                rm.setOrder(c.getString(6));
                rm.setFstar(c.getString(7));
                rm.setSstar(c.getString(8));
                rm.setAstar(c.getString(9));
                rm.setRecomnt(c.getString(10));
                rm.setHear(c.getString(11));
                rm.setAdd(c.getString(12));
                rm.setSubscibe(c.getString(13));
                rm.setSource(c.getString(14));

                //Log.e("Appdb::",c.getString(3));

                list.add(rm);

            }while (c.moveToNext());
        }
        return list;
    }
    public void delAllReviewDb()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "DELETE FROM "+TABLE_REVIEW;
        db.execSQL(Query);
    }
    public void delReviewById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "DELETE FROM "+TABLE_REVIEW+" WHERE "+RV_ID+" = "+id;
        db.execSQL(Query);
    }

    /** *************************** SUBSCRIPTION ***************************** **/

    public long insertSubscription(SubscriptionModel sm)
    {
        long flag = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AppDb.SB_NAME,sm.getName());
        values.put(AppDb.SB_EMAIL,sm.getEmail());

        flag = db.insert(AppDb.TABLE_SUBSCRIPTION,null,values);

        return flag;
    }
    public List<SubscriptionModel> getSubscription()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<SubscriptionModel>list = new ArrayList<>();

        String Query = "SELECT * FROM "+TABLE_SUBSCRIPTION;
        Cursor c = db.rawQuery(Query,null);

        SubscriptionModel sb ;
        if(c.moveToFirst())
        {
            do {
                sb = new SubscriptionModel();

                sb.setId(c.getInt(0));
                sb.setName(c.getString(1));
                sb.setEmail(c.getString(2));

                list.add(sb);
            }while (c.moveToNext());
        }

       return list;
    }
    public void delSubscrById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "DELETE FROM "+TABLE_SUBSCRIPTION+" WHERE "+SB_ID+" = "+id;
        db.execSQL(Query);
    }

}
