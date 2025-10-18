package com.overlord.ios.calculator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    ImageButton[] fnc = new ImageButton[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fnc[0] = findViewById(R.id.functions);
        fnc[1] = findViewById(R.id.functions2);

        for(ImageButton b: fnc)
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(MainActivity.this, b);
                    popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                    popup.setOnMenuItemClickListener(item -> {
                        int id = item.getItemId();

                        if(id == R.id.basic) {
                            Toast.makeText(MainActivity.this, "Basic", Toast.LENGTH_LONG).show();
                            return true;
                        }
                        return false;
                   });
                    popup.show();
                }
         });
    }
}