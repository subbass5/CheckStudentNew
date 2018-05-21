package com.nontification.checknamestudent.checkstudent.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nontification.checknamestudent.checkstudent.R;

import java.util.ArrayList;
import java.util.Arrays;


public class QrCodeScannerActivity extends AppCompatActivity {

    public static TextView tvtv_result;
    Button btn_start;
//    public ListView list_item;
    public ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scancode_layout);



        final ListView listView = (ListView)findViewById(R.id.list_item);
        tvtv_result = (TextView) findViewById(R.id .txtv_result);
        btn_start = (Button) findViewById(R.id.btn_qr_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                // we wrote a class for camera permission control and we wrote a method
                //The isCameraPermission method, which returns a boolean value into the getCameraPermission class

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(QrCodeScannerActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }else{
                        Intent intent = new Intent(QrCodeScannerActivity.this, ScanClass.class);
                        startActivity(intent);

                    }
                }

//                if(getCameraPermission.isCameraPermission(QrCodeScannerActivity.this))
//                {
//                    //izin alındığına göre okuma sınıfını çalıştıralım
//                    // Permission is granted and approved, go ScanClass
//                    Intent intent = new Intent(QrCodeScannerActivity.this, ScanClass.class);
//                    startActivity(intent);
//
//                }

            }
        });

    }

}