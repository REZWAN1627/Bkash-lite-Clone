package com.rex.bkashliteclone.MainUI.FragmentUI.SendMoney;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CursorAdapter;
import android.widget.EditText;

import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;

public class SendMoney extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private View view;
    private OnClickedFragment listener;
    private EditText ReceiverNumber;

    private CursorAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_send_money, container, false);
        ReceiverNumber = view.findViewById(R.id.ReceiverNumber);

        ReceiverNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 11){
                    closeKeyboard(view);
                }

            }
        });

        view.findViewById(R.id.sendMoney_next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ReceiverNumber.getText().toString().isEmpty()) || (ReceiverNumber.getText().toString().length() !=11)){
                    ReceiverNumber.setError("Invalid or Empty");
                    return;
                }else {
                    listener.OnSendMoneyNextButtonClicked(ReceiverNumber.getText().toString());
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
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            ReceiverNumber.clearFocus();
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}