package com.rex.bkashliteclone.Authenticate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rex.bkashliteclone.MainActivity;
import com.rex.bkashliteclone.R;


public class Log_Registration extends AppCompatActivity {

    public static final String TAG = "TAG";
    private EditText PhoneNumber;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__registration);

        PhoneNumber = findViewById(R.id.editText_registartion);
        relativeLayout = findViewById(R.id.relative_next_registration);

        PhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: is called");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Log.d(TAG, "onTextChanged: is called");
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, "afterTextChanged: is called");
                if (s.length() == 11) {
                    relativeLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_base_UI));
                    closeKeyboard();
                } else {
                    return;
                }
            }
        });

    }



    public void next_btn(View view) {
        Log.d(TAG, "next_btn: is clicked");

        if (PhoneNumber.getText().toString().isEmpty() || PhoneNumber.getText().toString().length() != 11) {

            Toast.makeText(Log_Registration.this, "Error!", Toast.LENGTH_SHORT).show();
            PhoneNumber.setError("Field is Empty or Invalid phone number");
            return;
        } else {

            Intent i = new Intent(Log_Registration.this, Mobile_OTP_verification.class);
            i.putExtra("Phone", PhoneNumber.getText().toString());
            startActivity(i);
            finish();
        }
    }

    public void back_btn(View view) {
        startActivity(new Intent(Log_Registration.this, MainActivity.class));
        finish();
    }

    public void next(View view) {
        if (PhoneNumber.getText().toString().isEmpty() || PhoneNumber.getText().toString().length() != 11) {

            Toast.makeText(Log_Registration.this, "Error!", Toast.LENGTH_SHORT).show();
            PhoneNumber.setError("Field is Empty or Invalid phone number");
            return;
        } else {

            Intent i = new Intent(Log_Registration.this, Mobile_OTP_verification.class);
            i.putExtra("Phone", PhoneNumber.getText().toString());
            startActivity(i);
            finish();
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            PhoneNumber.clearFocus();
        }
    }
}