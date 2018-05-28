package com.nontification.checknamestudent.checkstudent.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nontification.checknamestudent.checkstudent.R;

public class Fragment_Mainapp2 extends Fragment implements View.OnClickListener{
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout_test,container,false);
        init(view);

        return view;
    }

    private void init(View v) {
        context = getContext();
        v.findViewById(R.id.card_naja).setOnClickListener(this);

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

    private void Goscan (){

        Intent intent = new Intent(getContext(), ScanClass.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_naja:
                Goscan();
                break;
        }
    }
}
