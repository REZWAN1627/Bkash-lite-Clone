package com.rex.bkashliteclone.MainUI.FragmentUI.CashOut;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;


public class CashOut extends Fragment {

   private View view;
    public static final String TAG = "TAG";
    private EditText AgentNumber;
    private OnClickedFragment listener;

    private TextView AgentList, inputAgentNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_cash_out, container, false);

        AgentNumber = view.findViewById(R.id.CashOut_Client_Number);
        AgentList = view.findViewById(R.id.youhavenoagent);
        inputAgentNumber = view.findViewById(R.id.inputNumber);

        AgentNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 11) {
                    Log.d(TAG, "afterTextChanged: in if statements");
                    closeKeyboard(view);

                }
                inputAgentNumber.setText(AgentNumber.getText().toString().trim());
                inputAgentNumber.setVisibility(View.VISIBLE);

            }
        });

        view.findViewById(R.id.CashOut_Next_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!AgentNumber.getText().toString().isEmpty()) && (AgentNumber.getText().toString().length() == 11)) {

                    listener.OnCashOutNextButtonClicked(AgentNumber.getText().toString());

                } else {
                    AgentNumber.setError("Empty or Invalid Number");
                }

            }
        });

        view.findViewById(R.id.continue_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!AgentNumber.getText().toString().isEmpty()) && (AgentNumber.getText().toString().length() == 11)) {

                    listener.OnCashOutNextButtonClicked(AgentNumber.getText().toString());

                } else {
                    AgentNumber.setError("Empty or Invalid Number");
                }

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

    private void closeKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            AgentNumber.clearFocus();
        }
    }
}