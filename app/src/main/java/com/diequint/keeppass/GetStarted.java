package com.diequint.keeppass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetStarted extends AppCompatActivity {
    EditText personName, passBox, confirmPassBox;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started);

        personName = findViewById(R.id.personName);
        passBox = findViewById(R.id.passBox);
        confirmPassBox = findViewById(R.id.confirmPassBox);
        errorMessage = findViewById(R.id.errorMessage);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                validate();
                break;
            case R.id.continueButton:
                Intent intent = new Intent(this, MyKeys.class);
                startActivity(intent);
                break;
        }
    }

    private void validate() {
        String username = (personName).getText().toString();
        String password = (passBox).getText().toString();
        String confirm = (confirmPassBox).getText().toString();
        if (password.equals(confirm) && password.length()>=8) {//User enter an appropiate password
            errorMessage.setVisibility(View.INVISIBLE);
            //Still have to encript and make shared preferences here
            savePreferences(username, password);
        } else if (password.length()<8){
            errorMessage.setVisibility(View.VISIBLE);   //Password entered is too short
            errorMessage.setText(R.string.passShort);
        } else {
            errorMessage.setVisibility(View.VISIBLE);   //Passwords doesn't match
            errorMessage.setText(R.string.passNoMatch);
        }
    }

    private void savePreferences(String username, String password) {
        //If file preferences.xml doesn't exist then it creates it
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
        Toast.makeText(getApplicationContext(), /*getString(R.string.done)*/"Done", Toast.LENGTH_LONG).show();
    }
}