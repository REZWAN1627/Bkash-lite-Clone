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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rex.bkashliteclone.R;


import java.util.concurrent.TimeUnit;

public class Mobile_OTP_verification extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Boolean otpValid = true;
    private TextView ownerNumber;
    private String PhoneNumber;
    private FirebaseAuth mAuth;
    private String verificationId;
    private PhoneAuthProvider.ForceResendingToken token;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private boolean otpConfirm = false;

    private EditText otpNumberOne, getOtpNumberTwo, getOtpNumberThree, getOtpNumberFour, getOtpNumberFive, otpNumberSix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile__o_t_p_verification);

        mAuth = FirebaseAuth.getInstance();
        PhoneNumber = getIntent().getStringExtra("Phone");

        ownerNumber = findViewById(R.id.textView_ownerNumber);
        ownerNumber.setText(PhoneNumber);

        otpNumberOne = findViewById(R.id.otpNumberOne);
        getOtpNumberTwo = findViewById(R.id.optNumberTwo);
        getOtpNumberThree = findViewById(R.id.otpNumberThree);
        getOtpNumberFour = findViewById(R.id.otpNumberFour);
        getOtpNumberFive = findViewById(R.id.otpNumberFive);
        otpNumberSix = findViewById(R.id.optNumberSix);




        otpNumberOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                otpNumberOne.clearFocus();
                getOtpNumberTwo.requestFocus();
                getOtpNumberTwo.setCursorVisible(true);

            }
        });

        getOtpNumberTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getOtpNumberTwo.clearFocus();
                getOtpNumberThree.requestFocus();
                getOtpNumberThree.setCursorVisible(true);

            }
        });

        getOtpNumberThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getOtpNumberThree.clearFocus();
                getOtpNumberFour.requestFocus();
                getOtpNumberFour.setCursorVisible(true);

            }
        });

        getOtpNumberFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getOtpNumberFour.clearFocus();
                getOtpNumberFive.requestFocus();
                getOtpNumberFive.setCursorVisible(true);


            }
        });

        getOtpNumberFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                getOtpNumberFive.clearFocus();
                otpNumberSix.requestFocus();
                otpNumberSix.setCursorVisible(true);

            }
        });

        otpNumberSix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                closeKeyboard();

            }
        });













        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                verifyAuthentication(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d(TAG, "onVerificationFailed: " + e.getMessage());
                Toast.makeText(Mobile_OTP_verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                token = forceResendingToken;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
            }
        };

        verifyPhoneNumber(PhoneNumber);
    }

    private void verifyAuthentication(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Toast.makeText(Mobile_OTP_verification.this, "OTP Received press next", Toast.LENGTH_SHORT).show();
                otpConfirm = true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                Toast.makeText(Mobile_OTP_verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verifyPhoneNumber(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+88" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    public void validateField(EditText field) {
        Log.d(TAG, "validateField: is called");
        if (field.getText().toString().isEmpty()) {
            field.setError("Required");
            otpValid = false;
        } else {
            Log.d(TAG, "validateField: is true for otp");
            otpValid = true;
        }
    }

    public void change_number_clicked(View view) {
        startActivity(new Intent(Mobile_OTP_verification.this, Log_Registration.class));
        finish();
    }

    public void next_btn(View view) {

        if (otpConfirm){
            Intent i = new Intent(Mobile_OTP_verification.this, Login_withPassword.class);
            i.putExtra("PhoneNumber", PhoneNumber);
            startActivity(i);
            finish();
        }else{

            validateField(otpNumberOne);
            validateField(getOtpNumberTwo);
            validateField(getOtpNumberThree);
            validateField(getOtpNumberFour);
            validateField(getOtpNumberFive);
            validateField(otpNumberSix);

            if (otpValid) {

                String otp = otpNumberOne.getText().toString() + getOtpNumberTwo.getText().toString() + getOtpNumberThree.getText().toString() + getOtpNumberFour.getText().toString() +
                        getOtpNumberFive.getText().toString() + otpNumberSix.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                verifyAuthentication(credential);
            }

        }


    }

    public void next(View view) {

        if (otpConfirm){
            Intent i = new Intent(Mobile_OTP_verification.this, Login_withPassword.class);
            i.putExtra("PhoneNumber", PhoneNumber);
            startActivity(i);
            finish();
        }else{

            validateField(otpNumberOne);
            validateField(getOtpNumberTwo);
            validateField(getOtpNumberThree);
            validateField(getOtpNumberFour);
            validateField(getOtpNumberFive);
            validateField(otpNumberSix);

            if (otpValid) {

                String otp = otpNumberOne.getText().toString() + getOtpNumberTwo.getText().toString() + getOtpNumberThree.getText().toString() + getOtpNumberFour.getText().toString() +
                        getOtpNumberFive.getText().toString() + otpNumberSix.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                verifyAuthentication(credential);
            }else {
                Toast.makeText(this, "OTP not matched", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            otpNumberSix.clearFocus();

        }
    }
}