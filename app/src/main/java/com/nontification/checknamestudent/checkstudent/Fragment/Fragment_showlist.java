package com.nontification.checknamestudent.checkstudent.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nontification.checknamestudent.checkstudent.Fragment_login;
import com.nontification.checknamestudent.checkstudent.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_showlist extends Fragment implements View.OnClickListener{

    Context context;
    ArrayAdapter ListViewAdapter;
//    String [] Class ={ "สแกน QR","แก้ไขวันมา"};
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showlist_layout,container,false);
//        ListView listView = (ListView) view.findViewById(R.id.list_showlist);

        context = getContext();




        view.findViewById(R.id.buttonbarcode).setOnClickListener(this);
        view.findViewById(R.id.buttonhistory).setOnClickListener(this);


        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        ArrayAdapter ListViewAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,Class);
//        listView.setAdapter(ListViewAdapter);

//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//                Fragment_inshowlist fragment_inshowlist = new Fragment_inshowlist();
//                replaceFragment(fragment_inshowlist, null);
//
//            }
//        });


        return view;
    }

    public void replaceFragment(Fragment fragment, Bundle bundle) {

        if (bundle != null)
            fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction frgTran = fragmentManager.beginTransaction();
        frgTran.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        frgTran.replace(R.id.content,fragment).addToBackStack(null).commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonhistory:

                Fragment_history_date fragment_history_date = new Fragment_history_date();
                replaceFragment(fragment_history_date,null);
                break;
            case  R.id.buttonbarcode:
                Intent intent = new Intent(getActivity() , ScanClass.class);
                startActivity(intent);
                break;
        }
    }
}
