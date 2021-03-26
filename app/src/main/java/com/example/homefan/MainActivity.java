package com.example.homefan;
// Mohammad Ichwan - 191511022 - 26 - 03 - 2021
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.onOff);
        imageView = (ImageView) findViewById(R.id.fan);
        switchButton = (Switch) findViewById(R.id.Lights);
        seekBar = (SeekBar) findViewById(R.id.speed);

        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

//        if(savedInstanceState != null){
//            if (savedInstanceState.getBoolean("onOff")== true) {
//                rotateAnimator.setDuration(SPEED[savedInstanceState.getInt("speed")]);
//                rotateAnimator.start();
//            }
//            rotateAnimator.start();
//            if (savedInstanceState.getBoolean("lights")== true) {
//                gd.setColors(new int[]{Color.GREEN, Color.TRANSPARENT});
//                imageView.setBackground(gd);
//            }
//        }
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                    Log.d("rotate", "rotate start!");
                }else{
                    rotateAnimator.setDuration(SPEED[0]);
                    rotateAnimator.end();
                    Log.d("rotate", "rotate stop!");
                }
            }
        });
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchButton.isChecked()) {
                    gd.setColors(new int[]{ Color.GREEN , Color.TRANSPARENT });
                    imageView.setBackground(gd);
                    Log.d("Lights", "Light on!");
                }else {
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                    Log.d("Lights", "Light off!");
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (toggleButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.getBoolean("onOff",toggleButton.isChecked());
//        outState.getBoolean("light",switchButton.isChecked());
//        outState.getInt("speed",seekBar.getProgress());
//    }
}