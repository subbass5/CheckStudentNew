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
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nontification.checknamestudent.checkstudent.MainApps;
import com.nontification.checknamestudent.checkstudent.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class FragmentMainApps extends Fragment implements View.OnClickListener {
    Context context;
    ZXingScannerView scannerView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_layout,container,false);
        init(v);
        return v;
    }

    private void init(View v){
        context = getContext();
        v.findViewById(R.id.card_setShowList).setOnClickListener(this);
        v.findViewById(R.id.card_setType).setOnClickListener(this);
    }

    private void doScan(){

        scannerView = new ZXingScannerView(context);
        scannerView.startCamera();
        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                scannerView.stopCamera();
                scannerView.startCamera();
                Toast.makeText(context, ""+result.getText(), Toast.LENGTH_SHORT).show();
            }
        });

//            IntentIntegrator integrator = new IntentIntegrator(getActivity());
//            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//            integrator.setPrompt("Scan!!");
//            integrator.setCameraId(0);
//            integrator.setBeepEnabled(true);
//            integrator.setBarcodeImageEnabled(true);
//            integrator.initiateScan();

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Toast.makeText(context, "scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

        if (result != null) {
            if (result.getContents() == null) {
                System.out.println("Cancelled");
                Toast.makeText(context, "You cancelled the scanning!", Toast.LENGTH_LONG).show();
            } else {
                System.out.println("Worked: " + result.getContents());
                Toast.makeText(context, "scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_setType:
                doScan();
                break;
        }
    }
}
