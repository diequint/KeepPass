package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Credential extends AppCompatActivity {
    Button colourPicker, doneButton;
    int myColour, id;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credential);

        //Getting variables from last Activity
        loadPreferences();
        title = getIntent().getStringExtra("title");
        id = getIntent().getIntExtra("id",0);
        getSupportActionBar().setTitle(title);
        if (id != 0){
            //load from database
        }
        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
        colourPicker = findViewById(R.id.pickColour);
        doneButton = findViewById(R.id.doneButton);
        myColour = ContextCompat.getColor(this,R.color.blue_500);
        colourPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyKeys.class);
                startActivity(intent);
            }
        });

    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int appearance = Integer.parseInt(preferences.getString("visualise", "1"));
        if (appearance == 2 || appearance ==4) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, myColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(getApplicationContext(), getString(R.string.cancelled), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                myColour = color;
                colourPicker.setBackgroundColor(color);
                Toast.makeText(getApplicationContext(), getString(R.string.itemColour)+" "+myColour, Toast.LENGTH_LONG).show();
            }
        });
        ambilWarnaDialog.show();
    }
}