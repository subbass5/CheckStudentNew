package com.nontification.checknamestudent.checkstudent.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nontification.checknamestudent.checkstudent.Fragment_login;
import com.nontification.checknamestudent.checkstudent.R;

public class Fragment_select_mode extends Fragment {

    Context context;
    ArrayAdapter ListViewAdapter;
    String [] Mode ={ "การเช็คชื่อหน้าเสาธง","การเช็คชื่อกิจกรรมโฮมรูม" };
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String term_num = "";
    String term_year = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.select_mode_layout,container,false);
        ListView listView =(ListView) view.findViewById(R.id.list_selectmode);



        ArrayAdapter ListViewAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,Mode);
        listView.setAdapter(ListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Fragment_showlist showlist = new Fragment_showlist();
                replaceFragment(showlist, null);

            }
        });

        context = getContext();



        sharedPreferences = getActivity().getSharedPreferences(Fragment_login.MyPer, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        term_num = sharedPreferences.getString(Fragment_login.KEY_term_num,null);
        term_year = sharedPreferences.getString(Fragment_login.KEY_term_year,null);


        FloatingActionButton fab = view.findViewById(R.id.fab_louout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.fab_louout:
                        Logout();
                        break;
                }
            }

        });

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
    private void Logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("คำเตือน");
        builder.setMessage("คุณต้องการออกจากระบบ ?");

        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);


                Fragment_login fragment_login = new Fragment_login();
                replaceFragment(fragment_login,null);



                Toast.makeText(context, "คุณได้ออกสู่ระบบแล้ว", Toast.LENGTH_SHORT).show();

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


}
