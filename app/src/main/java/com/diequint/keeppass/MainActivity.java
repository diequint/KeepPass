package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.loginButton:
                intent = new Intent(this, MyKeys.class);
                break;
            case R.id.getStartedBtn:
                intent = new Intent(this, GetStarted.class);
                break;
            case R.id.newDevButton:
                intent = new Intent(this, NewDevice.class);
                break;
        }
        startActivity(intent);
    }
}