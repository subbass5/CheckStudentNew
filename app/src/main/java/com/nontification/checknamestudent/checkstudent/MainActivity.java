package com.nontification.checknamestudent.checkstudent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;
import com.nontification.checknamestudent.checkstudent.Retrofit.NetworkConnectionManeger;
import com.nontification.checknamestudent.checkstudent.Retrofit.OnNetworkCallbackLoginListener;

import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_user,et_pass;
    String str_user,str_pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    Context context;

    public static String BASE_URL = "http://webapp.ctn-phrae.com/";

    public static final String MyPer = "myPer";
    public static final String KEY_member_type = "member_type";
    public static final String KEY_member_id = "member_id";
    public static final String KEY_member_firstname = "member_firstname";
    public static final String KEY_member_lastname = "member_lastname";
    public static final String KEY_term_num = "term_num";
    public static final String KEY_term_year = "term_year";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

       init();

    }

    private void init(){

        context = MainActivity.this;

        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);

        et_user.setText("kritsana_ro");
        et_pass.setText("admin");

        sharedPreferences = getSharedPreferences(MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        findViewById(R.id.btn_login).setOnClickListener(this);


        getSupportActionBar().hide();

    }



    private void ExitApps(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("คำเตือน");
        builder.setMessage("คุณต้องการออกจาก Application");

        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {

        ExitApps();

    }
    private void login(){

        str_user = et_user.getText().toString().trim();
        str_pass = et_pass.getText().toString().trim();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(getString(R.string.msg_loading));
        progressDialog.show();


        if (TextUtils.isEmpty(et_user.getText().toString().trim())|| TextUtils.isEmpty(et_pass.getText().toString().trim())){
            et_user.setError("โปรดกรอกให้ถูกต้อง");
            et_pass.setError("โปรดกรอกรหัสผ่านให้ถูกต้อง");

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }else {

            new NetworkConnectionManeger().callServerLogin(listener,str_user,str_pass);

        }

    }

    OnNetworkCallbackLoginListener listener = new OnNetworkCallbackLoginListener() {
        @Override
        public void onResponse(POJO_login loginRes) {


            try {

                editor.putInt(KEY_member_id, Integer.parseInt(loginRes.getMemberId()));
                editor.putString(KEY_member_firstname, loginRes.getMemberFirstname());
                editor.putString(KEY_member_lastname, loginRes.getMemberLastname());
                editor.putString(KEY_member_type, loginRes.getMemberType());
                editor.putString(KEY_term_num, loginRes.getTermNum());
                editor.putString(KEY_term_year, loginRes.getTermYear());
                editor.commit();

                String Member_Type = loginRes.getMemberType();

                if (Member_Type.equals("teacher")) {

                    Intent goMainApps = new Intent(MainActivity.this,MainApps.class);
                    startActivity(goMainApps);
                    finish();

                } else {
                    Toast.makeText(context, "คุณไม่สามารถเข้าสู่ระบบได้", Toast.LENGTH_SHORT).show();
                }

            }catch
                    (Exception e){
                Toast.makeText(context, "เข้าสู่ระบบ ล้มเหลว "+e.getMessage()+"    =========    "+loginRes.getMemberFirstname(), Toast.LENGTH_SHORT).show();

            }

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

//            Log.e("onBodyError",""+responseBodyError);
            Toast.makeText(context, "onBodyError", Toast.LENGTH_SHORT).show();
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
        }
        @Override
        public void onBodyErrorIsNull() {
            Log.e("onBodyErrorIsNull","Data is Null");
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            Toast.makeText(context, "onBodyErrorIsNull", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("onFailure", t.getMessage());
            Toast.makeText(context, "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }


        }
    };



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.btn_login:

                login();

                break;
        }


    }

}