package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    RadioButton rb1, rb2, rb3, rb4;
    EditText num1, num2, currentPass, newPass, confirmPass;
    TextView errorMessage, errorMessage2;
    int visualise, maxAttempts, waitTime;
    String savedPass, newTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getSupportActionBar().setTitle(R.string.settings);

        rb1 = findViewById(R.id.iconFormat);
        rb2 = findViewById(R.id.textFormat);
        rb3 = findViewById(R.id.lightTheme);
        rb4 = findViewById(R.id.darkTheme);
        num1 = findViewById(R.id.newNum);
        num2 = findViewById(R.id.newNum2);
        currentPass = findViewById(R.id.currentPass);
        newPass = findViewById(R.id.newPass);
        confirmPass = findViewById(R.id.confirmPass);
        errorMessage = findViewById(R.id.errorMessage);
        errorMessage2 = findViewById(R.id.errorMessage2);
        loadPreferences();
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.lessButton:
                maxAttempts = Integer.parseInt(num1.getText().toString())-1;
                num1.setText(String.valueOf(maxAttempts));
                break;
            case R.id.moreButton:
                maxAttempts = Integer.parseInt(num1.getText().toString())+1;
                num1.setText(String.valueOf(maxAttempts));
                break;
            case R.id.lessButton2:
                waitTime = Integer.parseInt(num2.getText().toString())-1;
                num2.setText(String.valueOf(waitTime));
                break;
            case R.id.moreButton2:
                waitTime = Integer.parseInt(num2.getText().toString())+1;
                num2.setText(String.valueOf(waitTime));
                break;
            case R.id.saveConfig:
                validateRadioButton();
                savePreferences(changePass());
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        visualise = Integer.parseInt(preferences.getString("visualise", "1"));
        maxAttempts = Integer.parseInt(preferences.getString("maxAttempts", "5"));
        waitTime = Integer.parseInt(preferences.getString("waitTime", "5"));
        savedPass = preferences.getString("password", "");
        switch (visualise) {
            case 1:
                rb1.setChecked(true);
                rb3.setChecked(true);
                break;
            case 2:
                rb1.setChecked(true);
                rb4.setChecked(true);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 3:
                rb2.setChecked(true);
                rb3.setChecked(true);
                break;
            case 4:
                rb2.setChecked(true);
                rb4.setChecked(true);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
        num1.setText(String.valueOf(maxAttempts));
        num2.setText(String.valueOf(waitTime));
    }

    private void validateRadioButton() {
        if (rb1.isChecked()) {
            if (rb3.isChecked()) {
                visualise = 1;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                visualise = 2;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        } else {
            if (rb3.isChecked()) {
                visualise = 3;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                visualise = 4;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }

    private boolean changePass() {
        String currentTxt = currentPass.getText().toString();
        newTxt = newPass.getText().toString();
        String confirmTxt = confirmPass.getText().toString();

        if (!currentTxt.isEmpty()) {
            if (currentTxt.equals(savedPass)) {
                errorMessage.setVisibility(View.INVISIBLE);
                if (newTxt.equals(confirmTxt) && newTxt.length() >= 8) {//User enter an appropiate password
                    errorMessage2.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), newTxt, Toast.LENGTH_SHORT).show();
                    return true;
                } else if (newTxt.length() < 8) {
                    errorMessage2.setVisibility(View.VISIBLE);   //Password entered is too short
                    errorMessage2.setText(R.string.passShort);
                } else {
                    errorMessage2.setVisibility(View.VISIBLE);   //Passwords doesn't match
                    errorMessage2.setText(R.string.passNoMatch);
                }
            } else {
                errorMessage.setVisibility(View.VISIBLE);
                errorMessage.setText(R.string.passErr);
            }
        }
        return false;
    }

    private void savePreferences(Boolean change) {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (change){
            editor.putString("password", newTxt);
        }
        editor.putString("visualise", String.valueOf(visualise));
        editor.putString("maxAttempts", String.valueOf(maxAttempts));
        editor.putString("waitTime", String.valueOf(waitTime));
        editor.putString("timestamp", Long.toString(System.currentTimeMillis()/1000L));
        editor.commit();
        Toast.makeText(getApplicationContext(), getString(R.string.done), Toast.LENGTH_LONG).show();
    }
}