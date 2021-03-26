package com.rex.bkashliteclone.MainUI.FragmentUI.CashOut;

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
 * Use the {@link CashOutAmount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CashOutAmount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = "TAG";
    private OnClickedFragment listener;
    private View view;

    private TextView AgentNumber,AvailableBalance;
    private String ReceiverNumber, DatabaseAmount;
    private EditText Amount;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    // TODO: Rename and change types of parameters


    public CashOutAmount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ReceiverNumber Parameter 1.
     * @return A new instance of fragment CashOutAmount.
     */
    // TODO: Rename and change types and number of parameters
    public static CashOutAmount newInstance(String ReceiverNumber) {
        CashOutAmount fragment = new CashOutAmount();
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
        view = inflater.inflate(R.layout.fragment_cash_out_amount, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        FirebaseProcess();

        AgentNumber = view.findViewById(R.id.CashOutAmountAgent);
        AvailableBalance = view.findViewById(R.id.Availabe_cashoutAmount);
        Amount = view.findViewById(R.id.CashOut_Amount);

        AgentNumber.setText(ReceiverNumber);

        view.findViewById(R.id.next_CashoutFinalProcess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Amount.getText().toString().isEmpty()){
                    Amount.setError("Empty");
                    return;
                }else {
                    listener.OnCashOutAmountNextButtonClicked(DatabaseAmount,Amount.getText().toString(),ReceiverNumber);
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
                    AvailableBalance.setText(DatabaseAmount);
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
            throw new ClassCastException(context.toString()+"Must Implement Method");
        }
    }
}