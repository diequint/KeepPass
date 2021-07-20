package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewDevice extends AppCompatActivity {
    ImageView step1, step2, step3;
    TextView confirmId, errPassword, syncKeys, phoneReady;
    EditText passwordBox;
    Button got_it_2, got_it_3, endNewDev;
    String savedPass="tamal123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_device);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        confirmId = findViewById(R.id.confirmId);
        passwordBox = findViewById(R.id.passwordBox);
        errPassword = findViewById(R.id.errPassword);
        got_it_2 = findViewById(R.id.got_it_2);
        syncKeys = findViewById(R.id.syncKeys);
        got_it_3 = findViewById(R.id.got_it_3);
        phoneReady = findViewById(R.id.phoneReady);
        endNewDev = findViewById(R.id.endNewDev);
    }

    public void onClick(View view) {
        int opacity = 100; // from 0 to 255
        switch (view.getId()) {
            case R.id.got_it_1:
                confirmId.setVisibility(View.VISIBLE);
                passwordBox.setVisibility(View.VISIBLE);
                /*if (password doesn't match) {
                    errPassword.setVisibility(View.VISIBLE);
                } the line below goes in if condition as well*/
                got_it_2.setVisibility(View.VISIBLE);
                step2.setVisibility(View.VISIBLE);
                step1.setAlpha((float) 0.5); //Sets opacity by half
                break;
            case R.id.got_it_2:
                if (passwordBox.getText().toString().equals(savedPass)) {
                    errPassword.setVisibility(View.INVISIBLE);
                    syncKeys.setVisibility(View.VISIBLE);
                    got_it_3.setVisibility(View.VISIBLE);
                    step3.setVisibility(View.VISIBLE);
                    step2.setAlpha((float) 0.5);
                } else {
                    errPassword.setVisibility(View.VISIBLE);
                    errPassword.setText(R.string.passNoMatch);
                }
                break;
            case R.id.got_it_3:
                phoneReady.setVisibility(View.VISIBLE);
                endNewDev.setVisibility(View.VISIBLE);
                break;
            case R.id.endNewDev:
                Intent intent = new Intent(this, MyKeys.class);
                startActivity(intent);
                break;
        }
    }
}