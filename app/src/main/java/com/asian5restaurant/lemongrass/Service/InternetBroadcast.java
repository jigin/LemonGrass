package com.asian5restaurant.lemongrass.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.asian5restaurant.lemongrass.Util.Utils;

/**
 * Created by Jigin on 2/22/2017.
 */

public class InternetBroadcast extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //Log.e("boadcast","inside review brodcast");
        if(Utils.isNetworkAvailable(context))
        {
            Intent background = new Intent(context,SendReviewService.class);
            context.startService(background);
        }
        else {

        }
    }
}
