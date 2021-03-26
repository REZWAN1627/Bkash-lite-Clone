package com.rex.bkashliteclone.MainUI.FragmentUI.MobileRecharge;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MobileRechargeAmount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileRechargeAmount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "TAG";
    private View view;
    private OnClickedFragment listener;
    private String ReceiverNumber;
    private TextView ReceiverNumberTV;

    // TODO: Rename and change types of parameters
    public MobileRechargeAmount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ReceiverNumber Parameter 1.
     * @return A new instance of fragment MobileRechargeAmount.
     */
    // TODO: Rename and change types and number of parameters
    public static MobileRechargeAmount newInstance(String ReceiverNumber) {
        MobileRechargeAmount fragment = new MobileRechargeAmount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, ReceiverNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ReceiverNumber = getArguments().getString(ARG_PARAM1);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mobile_recharge_amount, container, false);


        ReceiverNumberTV = view.findViewById(R.id.MobileRechargeReceiverNumber);
        ReceiverNumberTV.setText(ReceiverNumber);


        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickedFragment) {
            listener = (OnClickedFragment) context;
        } else {
            throw new ClassCastException(context.toString() + "must Implements Method");
        }
    }
}