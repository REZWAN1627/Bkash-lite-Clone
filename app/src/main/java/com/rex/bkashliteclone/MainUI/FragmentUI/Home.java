package com.rex.bkashliteclone.MainUI.FragmentUI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;


public class Home extends Fragment {

    private View view;

    private OnClickedFragment listener;

    public static final String TAG = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.home_inbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnInboxClicked();
            }
        });

        view.findViewById(R.id.sendMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.OnSendMoneyClicked();

            }
        });

        view.findViewById(R.id.MobileRecharge_Home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnMobileRechargedClicked();
            }
        });

        view.findViewById(R.id.CashOut_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnCashOutClicked();
            }
        });

        view.findViewById(R.id.Paybill_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.OnPayBillClicked();

            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnClickedFragment) {
            listener = (OnClickedFragment) context;
        } else {
            throw new ClassCastException(context.toString() + "Must implements listener");
        }
    }
}