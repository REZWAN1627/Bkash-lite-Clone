package com.rex.bkashliteclone.MainUI.FragmentUI.CashOut;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rex.bkashliteclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CashOutFinalProcess#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CashOutFinalProcess extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    public static final String TAG = "TAG";
    private View view;
    private TextView ReceiverNumber, ReceiverNumber1, Amount, Total;
    private String receiverNumber, receiverAmount, DataBaseAmount;
    private EditText PIN;

    private FirebaseFirestore firebaseFirestore;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private int dataBaseAmount = 0, amountReceiver = 0;
    private Dialog dialog;

    // TODO: Rename and change types of parameters


    public CashOutFinalProcess() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param receiverNumber Parameter 1.
     * @param receiverAmount Parameter 2.
     * @param DataBaseAmount Parameter 3.
     * @return A new instance of fragment CashOutFinalProcess.
     */
    // TODO: Rename and change types and number of parameters
    public static CashOutFinalProcess newInstance(String receiverNumber, String receiverAmount, String DataBaseAmount) {
        CashOutFinalProcess fragment = new CashOutFinalProcess();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, receiverNumber);
        args.putString(ARG_PARAM2, receiverAmount);
        args.putString(ARG_PARAM3, DataBaseAmount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            receiverNumber = getArguments().getString(ARG_PARAM1);
            receiverAmount = getArguments().getString(ARG_PARAM2);
            DataBaseAmount = getArguments().getString(ARG_PARAM3);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cash_out_final_process, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        ReceiverNumber = view.findViewById(R.id.cashoutfinalnumber);
        ReceiverNumber1 = view.findViewById(R.id.cashoutfinalnumber1);
        PIN = view.findViewById(R.id.cashoutfinalPIN);
        Amount = view.findViewById(R.id.finalcashoutAmount);
        Total = view.findViewById(R.id.finaltotalcashout);

        PIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 5) {
                    closeKeyboard(view);

                }

            }
        });

        dataBaseAmount = Integer.parseInt(DataBaseAmount);
        amountReceiver = Integer.parseInt(receiverAmount);

        ReceiverNumber.setText(receiverNumber);
        ReceiverNumber1.setText(receiverNumber);

        Amount.setText("$ "+receiverAmount);
        Total.setText("$ "+receiverAmount);


        view.findViewById(R.id.FinalCashOut_Done_CashOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((PIN.getText().toString().isEmpty()) || (PIN.getText().toString().length() < 5)) {
                    PIN.setError("Error! The PIN is 12345");
                    return;
                } else {


                    openDialog();
                }

            }
        });

        return view;
    }

    private void openDialog() {

        dataBaseAmount -= amountReceiver;

        dialog = new Dialog(getContext(), android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirm_all_transaction);

        TextView HeadLine = dialog.findViewById(R.id.textView8);
        TextView ReceiverNumber = dialog.findViewById(R.id.dialogReceiverNumber);
        TextView TotalAmount = dialog.findViewById(R.id.dialog_total);
        TextView NewBalance = dialog.findViewById(R.id.dialogNewBalance);
        RelativeLayout relativeLayout = dialog.findViewById(R.id.dialogRelativeLAyout);
        relativeLayout.setVisibility(View.GONE);

        TotalAmount.setText("$ "+receiverAmount);
        NewBalance.setText("$ "+String.valueOf(dataBaseAmount));
        ReceiverNumber.setText(receiverNumber);
        HeadLine.setText("CASH OUT");

        dialog.findViewById(R.id.dialogConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // slash screen
                FirebaseProcess(dataBaseAmount);

            }
        });

        dialog.findViewById(R.id.dialogCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseAmount = Integer.parseInt(DataBaseAmount);;
                amountReceiver = Integer.parseInt(receiverAmount);
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void FirebaseProcess(int finalAmount) {

        Log.d(TAG, "onCreate: " + firebaseUser.getPhoneNumber());

        String StringConvertedAmount = String.valueOf(finalAmount);

        firebaseFirestore.collection("USER PROFILE").document(firebaseUser.getPhoneNumber())
                .update("Amount",StringConvertedAmount).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Transferred", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+e.getMessage());
            }
        });

    }

    private void closeKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            PIN.clearFocus();
        }
    }
}