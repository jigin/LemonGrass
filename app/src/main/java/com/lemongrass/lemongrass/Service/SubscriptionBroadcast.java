package com.lemongrass.lemongrass.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lemongrass.lemongrass.Util.Utils;

/**
 * Created by Jigin on 2/22/2017.
 */

public class SubscriptionBroadcast extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        //Log.e("boadcast","inside subcription brodcast");
        if(Utils.isNetworkAvailable(context))
        {
            Intent background = new Intent(context,SendSubscriptionService.class);
            context.startService(background);
        }
        else {

        }
    }
}
