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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rex.bkashliteclone.R;
import com.rex.bkashliteclone.UserUtils.Set_userName;


public class Login_withPassword extends AppCompatActivity {

    public static final String TAG = "TAG";
    private TextView ownerNumber;
    private String phoneNumber;
    private EditText password;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_password);
        ownerNumber = findViewById(R.id.textView9);
        password = findViewById(R.id.edittextpassword);
        relativeLayout = findViewById(R.id.relativeLayout2passwrd);

       phoneNumber = getIntent().getStringExtra("PhoneNumber");


        ownerNumber.setText(phoneNumber);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.d(TAG, "afterTextChanged: is called");
                if (s.length() == 5) {
                    relativeLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.color_base_UI));
                    closeKeyboard();
                } else {
                    return;
                }

            }
        });
    }

    public void next_btn(View view) {
        Log.d(TAG, "next_btn: is called  "+password.getText().toString());
        if ((password.getText().toString().equals("12345")) && (!password.getText().toString().isEmpty())){

           Intent i = new Intent(Login_withPassword.this, Set_userName.class);
           i.putExtra("PhoneNumber",phoneNumber);
            Log.d(TAG, "next_btn: if called");
            startActivity(i);

        }else{
            password.setError("Empty or wrong password");
        }
    }

    public void back_btn(View view) {
        startActivity(new Intent(Login_withPassword.this,Log_Registration.class));
        finish();
    }

    public void next(View view) {

        if ((password.getText().toString().equals("12345")) && (!password.getText().toString().isEmpty())){

            Intent i = new Intent(Login_withPassword.this, Set_userName.class);
            i.putExtra("PhoneNumber",phoneNumber);
            Log.d(TAG, "next_btn: if called");
            startActivity(i);
            finish();

        }else{
            password.setError("Empty or wrong password");
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            password.clearFocus();

        }
    }
}