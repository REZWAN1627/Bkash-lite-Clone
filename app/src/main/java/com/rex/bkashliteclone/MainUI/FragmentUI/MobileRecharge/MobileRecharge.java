package com.rex.bkashliteclone.MainUI.FragmentUI.MobileRecharge;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;

public class MobileRecharge extends Fragment {

    private View view;
    private OnClickedFragment listener;
    private EditText ReceiverNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mobile_recharge, container, false);

        ReceiverNumber = view.findViewById(R.id.ReceiverNumberMobileRecharge);

        ReceiverNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 11) {
                    closeKeyboard(view);
                }

            }
        });


        view.findViewById(R.id.MobileRecharge_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ReceiverNumber.getText().toString().isEmpty()) || (ReceiverNumber.getText().toString().length() != 11)) {
                    ReceiverNumber.setError("Invalid or Empty");
                    return;
                }else {
                    listener.OnMobileRechargeNextButtonClicked(ReceiverNumber.getText().toString());
                }
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

    private void closeKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            ReceiverNumber.clearFocus();
        }
    }
}