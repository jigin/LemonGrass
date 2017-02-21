package com.lemongrass.lemongrass.Util;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by AMAL SAJU VARGHESE on 14-Feb-17.
 */

public class Utils
{
    public static final String ALL_FOOD_URL = "http://www.webqua.com/lemongrass/androidapi/getmenuall.php";
    public static final String GET_MENU_URL = "http://www.webqua.com/lemongrass/androidapi/getmenu.php";
    public static final String SINGLE_ITEM_URL = "http://www.webqua.com/lemongrass/androidapi/getmenuitem.php";


    //**********************Preference for the review page......***************************//
    public static final String PREF_NAME = "sharedPreference";
    public static final String RV_NAME = "name";
    public static final String RV_NO = "number";
    public static final String RV_EMAIL = "email";
    public static final String RV_OUTLET = "outlet";
    public static final String RV_TIME_VISIT = "timevisit";
    public static final String RV_TYPE_ORDER = "typeorder";
    public static final String RV_FSTAR = "fstar";
    public static final String RV_SSTAR = "sstar";
    public static final String RV_ASTAR = "astar";
    public static final String RV_RECOMNT = "recomment";
    public static final String RV_HEARFORMUS = "hearfromus";
    public static final String RV_ADDANYTHING = "addanything";
    public static final String RV_SUBSCTIBE = "subscribe";

    //**********************Preference the sync status......***************************//
    public static final String PREF_SYNC = "syncPref";
    public static final String SYCN_STATUS = "syncStatus";

    //**********************Preference Login......***************************//
    public static final String PREF_LOGIN = "loginPref";
    public static final String BRANCH_ID = "branchId";

    public static final String errorText = "This field can't be empty!";



    public static final String DATA = "data";
    public static final String SUCCESS = "success";
    public static final String MENU_ID = "menuid";
    public static final String MENU_GROUP = "menugroup";
    public static final String ITEM_NAME = "itemname";
    public static final String IMAGE_URL = "image";
    public static final String THUMB_IMAGE = "thumb";
    public static final String DESCRIPTION = "description";
    public static final String INGREDIENT = "ingredient";
    public static final String PRICE = "price";

    public static boolean isNetworkConnectionAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public static void setTypeface(Context context,Button button,String style)
    {
        if(style.equalsIgnoreCase("bold"))
        {

        }
        else if(style.equalsIgnoreCase("regular"))
        {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            button.setTypeface(typeface);
        }
        else {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            button.setTypeface(typeface);
        }
    }
    public static void setTypeface(Context context,EditText editText,String style)
    {
        if(style.equalsIgnoreCase("bold"))
        {

        }
        else if(style.equalsIgnoreCase("regular"))
        {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            editText.setTypeface(typeface);
        }
        else {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            editText.setTypeface(typeface);
        }

    }
    public static void setTypeface(Context context,TextView textView,String style)
    {
        if(style.equalsIgnoreCase("bold"))
        {

        }
        else if(style.equalsIgnoreCase("regular"))
        {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            textView.setTypeface(typeface);
        }
        else {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"OpenSans-Regular.ttf");
            textView.setTypeface(typeface);
        }
    }
}
