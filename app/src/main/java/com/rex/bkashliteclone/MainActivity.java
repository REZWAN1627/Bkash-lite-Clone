package com.rex.bkashliteclone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rex.bkashliteclone.Authenticate.Log_Registration;
import com.rex.bkashliteclone.MainUI.AppsMainUI;

import java.util.ArrayList;
import java.util.List;

import static com.rex.bkashliteclone.Pemission.Constant.ALL_PERMISSION_CODE;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "TAG";
    private List<SlideModel> sliderItems = new ArrayList<>();

    private ImageSlider imageSlider;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        imageSlider = findViewById(R.id.imageSlider);

        firebaseAuth = FirebaseAuth.getInstance();

        sliderItems.add(new SlideModel(R.drawable.a, ScaleTypes.FIT));
        sliderItems.add(new SlideModel(R.drawable.b, ScaleTypes.FIT));
        sliderItems.add(new SlideModel(R.drawable.c, ScaleTypes.FIT));

        imageSlider.setImageList(sliderItems, ScaleTypes.FIT);


        AskPermissionContacts();


    }

    private void AskPermissionContacts() {

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, ALL_PERMISSION_CODE);
            return;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case ALL_PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(this, "The app was not allowed to read your contact", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    public void registration_btn(View view) {
        startActivity(new Intent(MainActivity.this, Log_Registration.class));
        finish();
    }

    public void guest_account(View view) {
        Toast.makeText(MainActivity.this, "This Feature is Under develop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, "onStart: not Null");
            startActivity(new Intent(MainActivity.this, AppsMainUI.class));
            finish();
        } else {
            Log.d(TAG, "onStart: null");
        }
    }
}