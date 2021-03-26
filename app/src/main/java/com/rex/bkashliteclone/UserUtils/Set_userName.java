package com.rex.bkashliteclone.UserUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rex.bkashliteclone.R;


public class Set_userName extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText firstName, lastName;
    private RelativeLayout relativeLayout;

    private String PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_name);

        PhoneNumber = getIntent().getStringExtra("PhoneNumber");
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);

        relativeLayout = findViewById(R.id.setname_layout);

        lastName.addTextChangedListener(new TextWatcher() {
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
                } else {
                    return;
                }

            }
        });
    }

    public void next_btn(View view) {
        if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()) {
            lastName.setError("Field is empty");
            firstName.setError("Field is empty");
            return;
        } else {
            Intent i = new Intent(Set_userName.this, Set_userProfile.class);
            i.putExtra("FirstName", firstName.getText().toString());
            i.putExtra("lastname", lastName.getText().toString());
            i.putExtra("PhoneNumber",PhoneNumber);
            startActivity(i);
            finish();
        }

    }

    public void skip_btn(View view) {

        if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()) {
            Intent i = new Intent(Set_userName.this, Set_userProfile.class);
            i.putExtra("Anonymous", firstName.getText().toString());
            i.putExtra("Guest", lastName.getText().toString());
            startActivity(i);
            finish();

        } else {
            Intent i = new Intent(Set_userName.this, Set_userProfile.class);
            i.putExtra("FirstName", firstName.getText().toString());
            i.putExtra("lastname", lastName.getText().toString());
            startActivity(i);
            finish();
        }
    }

    public void next(View view) {

        if (firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty()) {
            lastName.setError("Field is empty");
            firstName.setError("Field is empty");
            return;
        } else {
            Intent i = new Intent(Set_userName.this, Set_userProfile.class);
            i.putExtra("FirstName", firstName.getText().toString());
            i.putExtra("lastname", lastName.getText().toString());
            i.putExtra("PhoneNumber",PhoneNumber);
            startActivity(i);
            finish();
        }
    }
}