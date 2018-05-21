package com.nontification.checknamestudent.checkstudent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.nontification.checknamestudent.checkstudent.Fragment.Fragment_select_mode;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;
import com.nontification.checknamestudent.checkstudent.Retrofit.NetworkConnectionManeger;
import com.nontification.checknamestudent.checkstudent.Retrofit.OnNetworkCallbackLoginListener;

import okhttp3.ResponseBody;

public class Fragment_login extends Fragment implements View.OnClickListener {
    EditText et_user,et_pass;
    String str_user,str_pass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
//    ProgressDialog progressDialog;
    Context context;

    public static String BASE_URL = "http://webapp.ctn-phrae.com/";

    public static final String MyPer = "myPer";
    public static final String KEY_member_type = "member_type";
    public static final String KEY_member_id = "member_id";
    public static final String KEY_member_firstname = "member_firstname";
    public static final String KEY_member_lastname = "member_lastname";
    public static final String KEY_term_num = "term_num";
    public static final String KEY_term_year = "term_year";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout,container,false);
        context = getContext();
        et_user = view.findViewById(R.id.et_user);
        et_pass = view.findViewById(R.id.et_pass);

        sharedPreferences = getActivity().getSharedPreferences(MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        view.findViewById(R.id.btn_login).setOnClickListener(this);

        
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return view;
    }


    public void replaceFragment(Fragment fragment, Bundle bundle) {
        if (bundle != null)
            fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment oldFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());

        //if oldFragment already exits in fragmentManager use it
        if (oldFragment != null) {
            fragment = oldFragment;
        }

        fragmentTransaction.replace(R.id.content, fragment, fragment.getClass().getName());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(R.id.content,fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void login(){

        str_user = et_user.getText().toString().trim();
        str_pass = et_pass.getText().toString().trim();


        if (TextUtils.isEmpty(et_user.getText().toString().trim())|| TextUtils.isEmpty(et_pass.getText().toString().trim())){
            et_user.setError("โปรดกรอกให้ถูกต้อง");
            et_pass.setError("โปรดกรอกรหัสผ่านให้ถูกต้อง");

//            if (progressDialog.isShowing()) {
//                progressDialog.dismiss();
//            }

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

//                    Log.d("type user",""+Member_Type);

            if (Member_Type.equals("teacher")) {

                    Fragment_select_mode sec = new Fragment_select_mode();
                    replaceFragment(sec, null);

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
//            Log.e("onBodyErrorIsNull","Data is Null");
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }
            Toast.makeText(context, "onBodyErrorIsNull", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Throwable t) {
            Log.e("onFailure", t.getMessage());
            Toast.makeText(getContext(), "ชื่อผู้ใช้งานหรือรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
//            if(progressDialog.isShowing()){
//                progressDialog.dismiss();
//            }


        }
    };




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_login:

//                showLoading();
                login();

//                Intent intent = new Intent(getActivity() , QrCodeScannerActivity.class);
//                startActivity(intent);

                break;
        }


    }
}
