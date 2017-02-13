package com.lemongrass.lemongrass.Activity;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.lemongrass.lemongrass.R;

/**
 * Created by AMAL SAJU VARGHESE on 11-Feb-17.
 */

public class Review3_Activity extends Activity
{
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review2);
        seekBar = (SeekBar) findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("seekbar","OnProgresschanged");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("seekbar","OnStartTraching");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("seekbar","OnStopTraching");
            }
        });
    }
}
