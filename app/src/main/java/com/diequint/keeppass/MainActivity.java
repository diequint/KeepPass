package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView welcomeText, passErr, firsttimeText;
    EditText PasswordBox;
    Button loginButton, getStartedBtn, newDevButton;
    String savedPass;
    int count = 0, maxAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcomeText);
        PasswordBox = findViewById(R.id.PasswordBox);
        passErr = findViewById(R.id.passErr);
        loginButton = findViewById(R.id.loginButton);
        firsttimeText = findViewById(R.id.firsttimeText);
        getStartedBtn = findViewById(R.id.getStartedBtn);
        newDevButton = findViewById(R.id.newDevButton);
        loadPreferences();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                Toast.makeText(getApplicationContext(), "clicking button", Toast.LENGTH_SHORT).show();
                if (count <= maxAttempts) {
                    if (PasswordBox.getText().toString().equals(savedPass)) {
                        Toast.makeText(getApplicationContext(), "IF condition", Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(this, MyKeys.class);
                        startActivity(intent1);
                    } else {
                        passErr.setVisibility(View.VISIBLE);   //Password entered is wrong
                        passErr.setText(R.string.passErr);
                        count++;
                        Toast.makeText(getApplicationContext(), "Attempts left: "+(maxAttempts-count), Toast.LENGTH_LONG).show();
                    }
                } else {/*Wait 30 minutes or type private key*/}
                break;
            case R.id.getStartedBtn:
                Intent intent2 = new Intent(this, GetStarted.class);
                startActivity(intent2);
                break;
            case R.id.newDevButton:
                Intent intent3 = new Intent(this, NewDevice.class);
                startActivity(intent3);
                break;
        }
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        savedPass = preferences.getString("password", "");
        maxAttempts = Integer.parseInt(preferences.getString("maxAttempts", "5"));
        //Line below for development purposes only!
        Toast.makeText(getApplicationContext(), "Registered data:"+username+" "+savedPass+" "+maxAttempts, Toast.LENGTH_LONG).show();
        if (!username.equals("")) {
            welcomeText.append(", "+username);
            isRegistered();
            notRegistered();//Temporaly eable bth to be visible so that I can make adjustments easily
        } else {
            notRegistered();
        }
    }

    private void isRegistered() {
        PasswordBox.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
    }

    private void notRegistered() {
        firsttimeText.setVisibility(View.VISIBLE);
        getStartedBtn.setVisibility(View.VISIBLE);
        newDevButton.setVisibility(View.VISIBLE);
    }
}