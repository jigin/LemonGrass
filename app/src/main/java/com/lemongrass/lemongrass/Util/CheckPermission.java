package com.lemongrass.lemongrass.Util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by AMAL SAJU VARGHESE on 15-Feb-17.
 */

public class CheckPermission extends Activity
{
    /*public int viewPermission(Context context)
    {
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED);
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, android.Manifest.permission.ACCESS_NETWORK_STATE))
            {

            }
            else {
                ActivityCompat.requestPermissions(context,new String[]{android.Manifest.permission.ACCESS_NETWORK_STATE},PERMISSION_REQUEST_CODE);
            }
        }
    }*/

}
