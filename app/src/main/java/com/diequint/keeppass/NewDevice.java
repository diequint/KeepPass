package com.diequint.keeppass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NewDevice extends AppCompatActivity {
    ImageView step1, step2, step3;
    TextView confirmId, errPassword, syncKeys, phoneReady;
    EditText passwordBox;
    Button got_it_1, got_it_2, got_it_3, endNewDev;
    String qrData="";
    Boolean band=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_device);
        getSupportActionBar().setTitle(R.string.newDevice);

        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        step3 = findViewById(R.id.step3);
        got_it_1 = findViewById(R.id.got_it_1);
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
                if (!qrData.equals("")) {//Since this executes in background I leave it in an if
                    confirmId.setVisibility(View.VISIBLE);
                    passwordBox.setVisibility(View.VISIBLE);
                    got_it_2.setVisibility(View.VISIBLE);
                    step2.setVisibility(View.VISIBLE);
                    step1.setAlpha((float) 0.5);
                } else { //This means anything has been read yet
                    new IntentIntegrator(this)
                            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                            .setTorchEnabled(false)
                            .setBeepEnabled(true)
                            .setPrompt((String) getText(R.string.autoSnap))
                            .initiateScan();
                }
                break;
            case R.id.got_it_2:
                if (passwordBox.getText().toString().equals(qrData)) {
                    errPassword.setVisibility(View.INVISIBLE);
                    syncKeys.setVisibility(View.VISIBLE);
                    got_it_3.setVisibility(View.VISIBLE);
                    step3.setVisibility(View.VISIBLE);
                    step2.setAlpha((float) 0.5);
                    qrData = "";
                    band=true;
                } else {
                    errPassword.setVisibility(View.VISIBLE);
                    errPassword.setText(R.string.passNoMatch);
                }
                break;
            case R.id.got_it_3:
                if (!qrData.equals("")) {
                    phoneReady.setVisibility(View.VISIBLE);
                    endNewDev.setVisibility(View.VISIBLE);
                } else {
                    new IntentIntegrator(this)
                            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                            .setTorchEnabled(false)
                            .setBeepEnabled(true)
                            .setPrompt((String) getText(R.string.autoSnap))
                            .initiateScan();
                }
                break;
            case R.id.endNewDev:
                Intent intent = new Intent(this, MyKeys.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null) {
            if (result.getContents() != null) {
                qrData = result.getContents();
                autoClickButton();
            } else {
                Toast.makeText(getApplicationContext(), R.string.cancelled, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void autoClickButton() {
        if (band){
            got_it_3.performClick();
        }else {
            got_it_1.performClick();
        }
    }
}