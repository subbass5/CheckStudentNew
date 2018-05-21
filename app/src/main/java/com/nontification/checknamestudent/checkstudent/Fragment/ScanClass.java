package com.nontification.checknamestudent.checkstudent.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nontification.checknamestudent.checkstudent.Fragment_login;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_save_data;
import com.nontification.checknamestudent.checkstudent.R;
import com.nontification.checknamestudent.checkstudent.Retrofit.NetworkConnectionManeger;
import com.nontification.checknamestudent.checkstudent.Retrofit.OnNetworkCallbackSaveData;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.ResponseBody;

public class ScanClass extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    int tuem_num = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public ZXingScannerView  mScannerView;

    // In onCreate() method, we have set view as scannerview instead of xml file layout.
    // In onResume() method, we have set the resulthandler and started camera preview.
    // In onPause() method, we are stopping camera preview.

    // OnCreate () yönteminde, görünümü xml dosya düzeni yerine scannerview olarak ayarladık.
    // onResume () yönteminde resulandler ayarladık ve kamera önizlemesini başlattık.
    // OnPause () yönteminde kamera önizlemesini durduruyoruz.

    //camera permission is needed. getCameraPermission class ;)
    // kamera iznine ihtiyaç vardır. getCameraPermission class ;)


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.scancode_layout,container,false);
//
//        mScannerView = new ZXingScannerView (context);
//
//
//        view(mScannerView);
//        return view;
//    }
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = format.parse(dtStart);

    SimpleDateFormat sdf = new SimpleDateFormat("EEE");
    Date d = new Date();
    String dayOfTheWeek = sdf.format(d);




    OnNetworkCallbackSaveData onNetworkCallbackSaveData = new OnNetworkCallbackSaveData() {
        @Override
        public void onResponse(POJO_save_data save_data) {

        }

        @Override
        public void onBodyError(ResponseBody responseBodyError) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    };


//    String dayOfTheWeek = (String) DateFormat.format("EEEE", date);
//    String day          = (String) DateFormat.format("dd",   date);
//    String date1 = (String) android.text.format.DateFormat.format("dd", date);


//    return date;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Programmatically initialize the scanner view
        // Tarayıcı görünümünü programlı olarak başlatır
        mScannerView = new ZXingScannerView (ScanClass.this);
        // Set the scanner view as the content view
        // Tarayıcı görünümünü içerik görünümü olarak ayarlayın
        setContentView(mScannerView);


    }



    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

//    @Override
//    public void handleResult(Result result) {
//
//        // Do something with the result here
//        Log.v("kkkk", result.getText()); // Prints scan results - okunan veri
//        Log.v("uuuu", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.) - okunanın türü
//
//        QrCodeScannerActivity.tvtv_result.setText("Data : "+result.getText()+"\nFormat : "+result.getBarcodeFormat().toString());
//        onBackPressed();
//
//        // If you would like to resume scanning, call this method below:
//        // Taramaya devam etmek isterseniz, aşağıdaki yöntemi arayın:
//        mScannerView.resumeCameraPreview(this);
//    }



//As you can see in above code, we get result object which contains resulting Barcode or QRcode after scanning is completed.
//We will get final result by running result.getText() method and we have set it into TextView of MainActivity.

// Yukarıdaki kodda da görebileceğiniz gibi, tarama tamamlandıktan sonra ortaya çıkan Barkod veya QRcode içeren sonuç nesnesi elde ediyoruz.
// result.getText() yöntemini çalıştırarak nihai sonucu elde edeceğiz ve QrCodeScannerActivity'in TextView öğesine ayarladık.



    @Override
    public void handleResult(Result result) {

        Log.v("kkkk", result.getText()); // Prints scan results - okunan veri
        Log.v("uuuu", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.) - okunanın türü

//        QrCodeScannerActivity.tvtv_result.setText("Data : "+result.getText()/*+"\nFormat : "+result.getBarcodeFormat().toString()*/);


        sharedPreferences = getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        tuem_num = Integer.parseInt(sharedPreferences.getString(Fragment_login.KEY_term_num,null));

        new NetworkConnectionManeger().CallsaveData(onNetworkCallbackSaveData,Integer.parseInt(result.toString()),dayOfTheWeek,tuem_num);


//        onBackPressed();

        // If you would like to resume scanning, call this method below:
        // Taramaya devam etmek isterseniz, aşağıdaki yöntemi arayın:

//        Toast.makeText(getApplicationContext(),result.getText()+"      เช็คชื่อหมายเลขนี้สำเร็จแล้ว" , Toast.LENGTH_SHORT).show();

        mScannerView.resumeCameraPreview(this);

        Toast.makeText(this, ""+Integer.parseInt(result.toString())+""+dayOfTheWeek+""+tuem_num, Toast.LENGTH_SHORT).show();


        Toast.makeText(this, "สแกนให้หมายเลข  "+result.getText()+"    สำเร็จแล้ว", Toast.LENGTH_SHORT).show();

//        String new_number = result.getText().toString();



    }
}
