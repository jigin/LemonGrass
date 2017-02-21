package com.lemongrass.lemongrass.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lemongrass.lemongrass.Model.ItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jigin on 2/17/2017.
 */

public class AppDb extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "LEMONGRASSDB";
    private static final int DATABASE_VERSION = 1;


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



    public AppDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableQuery = "CREATE TABLE "+TABLE_ITEM+"("+ITEM_ID+"INTEGER PRIMARY KEY,"+ITEM_IMAGE_PATH+" TEXT,"+ITEM_THUMB_PATH+" TEXT,"+ITEM_MENU_GROUP+" TEXT,"+ITEM_PRICE+" TEXT,"+ITEM_NAME+" TEXT,"+ITEM_DESCRIPTION+" TEXT,"+ITEM_INGREDIENT+" TEXT,"+ITEM_MENU_ID+" TEXT"+")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String updateQuery = "DROP TABLE IF EXISTS "+TABLE_ITEM;
    }

    public long insertDatabase(List<ItemModel>list)
    {
        long flag = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        int size = list.size();
        ContentValues values;
        for(int i = 0 ; i<size ; i++)
        {
            values = new ContentValues();
            values.put(AppDb.ITEM_IMAGE_PATH,list.get(i).getImageUrl());
            values.put(AppDb.ITEM_THUMB_PATH,list.get(i).getThumbUrl());
            values.put(AppDb.ITEM_MENU_GROUP,list.get(i).getMenuGroup());
            values.put(AppDb.ITEM_PRICE,list.get(i).getPrice());
            values.put(AppDb.ITEM_NAME,list.get(i).getItemName());
            values.put(AppDb.ITEM_DESCRIPTION,list.get(i).getDescription());
            values.put(AppDb.ITEM_INGREDIENT,list.get(i).getIngredient());
            values.put(AppDb.ITEM_MENU_ID,list.get(i).getMenuId());

            flag = db.insert(TABLE_ITEM,null,values);
        }

        return flag;
    }
    public long insertItem(ItemModel im)
    {
        long flag = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AppDb.ITEM_IMAGE_PATH,im.getImageUrl());
        values.put(AppDb.ITEM_THUMB_PATH,im.getThumbUrl());
        values.put(AppDb.ITEM_MENU_GROUP,im.getMenuGroup());
        values.put(AppDb.ITEM_PRICE,im.getPrice());
        values.put(AppDb.ITEM_NAME,im.getItemName());
        values.put(AppDb.ITEM_DESCRIPTION,im.getDescription());
        values.put(AppDb.ITEM_INGREDIENT,im.getIngredient());
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

        String Query = "SELECT * FROM "+TABLE_ITEM+" WHERE "+ITEM_MENU_GROUP+"="+catId;

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

}
