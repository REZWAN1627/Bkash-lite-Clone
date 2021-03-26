package com.rex.bkashliteclone.MainUI.FragmentUI.SendMoney;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendMoneyAmount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendMoneyAmount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String receiverNumber = "ReceiverNumber";
    public static final String TAG = "TAG";
    private View view;
    private OnClickedFragment listener;
    private EditText Amount;
    private TextView ReceiverNumberTV,AvailableMoney;

    private FirebaseFirestore firebaseFirestore;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    // TODO: Rename and change types of parameters
    private String ReceiverNumber;
    private String DatabaseAmount;

    public SendMoneyAmount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ReceiverNumber Parameter 1.
     * @return A new instance of fragment SendMoneyAmount.
     */
    // TODO: Rename and change types and number of parameters
    public static SendMoneyAmount newInstance(String ReceiverNumber) {
        SendMoneyAmount fragment = new SendMoneyAmount();
        Bundle args = new Bundle();
        args.putString(receiverNumber, ReceiverNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ReceiverNumber = getArguments().getString(receiverNumber);
        }
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_send_money_amount, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();



        Amount = view.findViewById(R.id.ammount_sendmoneyPage);
        ReceiverNumberTV = view.findViewById(R.id.receiverNumbersendamount);
        AvailableMoney = view.findViewById(R.id.availableBalance_sendMoney);

        FirebaseProcess();
        ReceiverNumberTV.setText(ReceiverNumber);
        AvailableMoney.setText(DatabaseAmount);


        view.findViewById(R.id.next_success_sendMoney).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Amount.getText().toString().isEmpty()){
                    Amount.setError("Empty");
                    return;
                }else {
                    listener.OnSendMoneyAmountNextButtonClicked(DatabaseAmount,Amount.getText().toString(),ReceiverNumber);
                }

            }
        });

        return view;
    }


    private void FirebaseProcess() {

        Log.d(TAG, "onCreate: " + firebaseUser.getPhoneNumber());

        firebaseFirestore.collection("USER PROFILE").document(firebaseUser.getPhoneNumber()).
                get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DatabaseAmount = (String) task.getResult().getData().get("Amount");
                    AvailableMoney.setText(DatabaseAmount);
                    return;

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickedFragment){
            listener = (OnClickedFragment) context;
        }else {
            throw new ClassCastException(context.toString()+"must Implements Method");
        }
    }
}