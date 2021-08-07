package com.diequint.keeppass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SyncData extends AppCompatActivity {
    Button genCode, readCode, addCode;
    ImageView generatedQR;
    String qrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sync_data);
        getSupportActionBar().setTitle(R.string.synchronise);

        genCode = findViewById(R.id.genCode);
        readCode = findViewById(R.id.readCode);
        addCode = findViewById(R.id.addCode);
        generatedQR = findViewById(R.id.generatedQR);
        generatedQR.setScaleX(1.4f);
        generatedQR.setScaleY(1.4f);

        genCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter formatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = formatWriter.encode("sopita", BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder myEncoder = new BarcodeEncoder();
                    Bitmap bitmap = myEncoder.createBitmap(bitMatrix);
                    generatedQR.setImageBitmap(bitmap);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), getString(R.string.cancelled), Toast.LENGTH_LONG).show();
                }
            }
        });

        addCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter formatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = formatWriter.encode("sopita", BarcodeFormat.QR_CODE,500,500);
                    BarcodeEncoder myEncoder = new BarcodeEncoder();
                    Bitmap bitmap = myEncoder.createBitmap(bitMatrix);
                    generatedQR.setImageBitmap(bitmap);
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), getString(R.string.cancelled), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onClick(View view) {
        new IntentIntegrator(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setTorchEnabled(false)
                .setBeepEnabled(true)
                .setPrompt((String) getText(R.string.autoSnap))
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null) {
            if (result.getContents() != null) {
                qrData = result.getContents();
            } else {
                Toast.makeText(getApplicationContext(), R.string.cancelled, Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}