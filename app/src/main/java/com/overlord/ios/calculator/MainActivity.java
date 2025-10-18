package com.overlord.ios.calculator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton fncButton;
    ImageButton[] fnc = new ImageButton[2];
    LinearLayout[] layouts = new LinearLayout[2];
    LinearLayout spacer;
    Boolean isBasic, isScientific;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fncButton = findViewById(R.id.functions);
        fnc[0] = findViewById(R.id.functions2);
        fnc[1] = findViewById(R.id.functions3);
        layouts[0] = findViewById(R.id.special);
        layouts[1] = findViewById(R.id.normal);
        isBasic = true;
        isScientific = false;

        fncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, fncButton);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                spacer = findViewById(R.id.spacer);

                LinearLayout.LayoutParams spacerParams = (LinearLayout.LayoutParams) spacer.getLayoutParams();
                LinearLayout.LayoutParams specialParams = (LinearLayout.LayoutParams) layouts[0].getLayoutParams();


                popup.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();

                    if(id == R.id.basic && !isBasic) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.10f, 0.19f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.4f, 0.0f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            spacerParams.weight = (Float) animation.getAnimatedValue();
                            spacer.setLayoutParams(spacerParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isBasic = true;
                        isScientific = false;
                        return true;
                    }
                    else if(id == R.id.scientific && !isScientific) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.19f, 0.10f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.0f, 0.4f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            spacerParams.weight = (Float) animation.getAnimatedValue();
                            spacer.setLayoutParams(spacerParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isScientific = true;
                        isBasic = false;
                        return true;
                    }
                    return false;
                });
                popup.show();
            }
        });



        for(ImageButton btn: fnc)
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, btn);
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                LinearLayout.LayoutParams specialParams = (LinearLayout.LayoutParams) layouts[0].getLayoutParams();
                LinearLayout.LayoutParams normalParams = (LinearLayout.LayoutParams) layouts[1].getLayoutParams();


                popup.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();

                    if(id == R.id.basic && !isBasic) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 0.5f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.5f, 0.0f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            normalParams.weight = (Float) animation.getAnimatedValue();
                            layouts[1].setLayoutParams(normalParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isBasic = true;
                        isScientific = false;
                        return true;
                    }
                    else if(id == R.id.scientific && !isScientific) {
                        ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 0.0f);
                        ValueAnimator animator2 = ValueAnimator.ofFloat(0.0f, 0.5f);

                        animator.setDuration(350);
                        animator2.setDuration(350);

                        animator.addUpdateListener(animation -> {
                            normalParams.weight = (Float) animation.getAnimatedValue();
                            layouts[1].setLayoutParams(normalParams);
                        });

                        animator2.addUpdateListener(animation -> {
                            specialParams.weight = (Float) animation.getAnimatedValue();
                            layouts[0].setLayoutParams(specialParams);
                        });

                        animator.start();
                        animator2.start();

                        isScientific = true;
                        isBasic = false;
                        return true;
                    }
                    return false;
                });
                popup.show();
            }
        });
    }
}