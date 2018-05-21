package com.nontification.checknamestudent.checkstudent.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.nontification.checknamestudent.checkstudent.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Fragment_history_date extends Fragment {
    SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    Calendar myCalendar;
    TextView tv_date;
    Context context;
    String myFormat = "yyyy-MM-dd";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.histori_scan_layout,container,false);
        tv_date = view.findViewById(R.id.tv_date);
        tv_date.setText(datenow(myFormat));

        context = getContext();

        return view;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            //clear recycleview
//            Data_name = new ArrayList<>();
//            Data_ltname = new ArrayList<>();
//            Data_chan = new ArrayList<>();
//            Data_num = new ArrayList<>();
//            Data_score = new ArrayList<>();
//            Data_member_id = new ArrayList<>();

//            recycleViewAdapter5.clear();
//            sendData();
        }

    };

    public static String datenow(String Format) {

        DateFormat dateFormat = new SimpleDateFormat(Format);
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void sendData() {

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("th", "TH"));

        tv_date.setText(sdf.format(myCalendar.getTime()));
//       Log.d("Date tv",tv_date.toString());


    }
}
