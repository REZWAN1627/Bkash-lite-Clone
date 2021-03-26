package com.rex.bkashliteclone.MainUI.FragmentUI.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.internal.$Gson$Preconditions;
import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;


public class Settings extends Fragment {

    private View view;
    private OnClickedFragment listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_settings, container, false);


        view.findViewById(R.id.changeName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.OnSettingNameChangeClicked();

            }
        });


        view.findViewById(R.id.changePicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnSettingPictureChangeClicked();

            }
        });



        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickedFragment){
            listener = (OnClickedFragment) context;
        }else {
            throw new ClassCastException(context.toString()+"Must Implements Method");
        }
    }
}