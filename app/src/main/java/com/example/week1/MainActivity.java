package com.example.week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText phoneNum;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = findViewById(R.id.phoneNum);
        btnCall = findViewById(R.id.call);

        btnCall.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(Intent.ACTION_CALL);

            @Override
            public void onClick(View v) {
                String num = phoneNum.getText().toString().trim();
                if (num.isEmpty()){
                    Toast.makeText(MainActivity.this, "please type number", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, num, Toast.LENGTH_LONG).show();
                    intent.setData(Uri.parse("tel:"+num));
                }

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "No permission", Toast.LENGTH_LONG).show();
                    requestPermission();
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
}