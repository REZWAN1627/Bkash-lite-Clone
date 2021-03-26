package com.rex.bkashliteclone.UserUtils;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rex.bkashliteclone.MainUI.AppsMainUI;
import com.rex.bkashliteclone.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.rex.bkashliteclone.Pemission.Constant.CAMERA_REQUEST_CODE;
import static com.rex.bkashliteclone.Pemission.Constant.CAMERA_REQUEST_PERMISSION_CODE;


public class Set_userProfile extends AppCompatActivity {

    public static final String TAG = "TAG";
    private String FirstName, LastName, PhoneNumber;

    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    private String ImageUri;

    private ImageView profilePic;

    private CheckBox checkBox;
    private String flag;

    private Map<String, Object> hashMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_profile);

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        FirstName = getIntent().getStringExtra("FirstName");
        LastName = getIntent().getStringExtra("lastname");


        PhoneNumber = getIntent().getStringExtra("PhoneNumber");

        profilePic = findViewById(R.id.camerapic);
        checkBox = findViewById(R.id.profileCheck);

        if (checkBox.isChecked()) {
            flag = "YES";
        } else {
            flag = "NO";
        }


    }

    public void skip_btn(View view) {
        Intent i = new Intent(Set_userProfile.this, AppsMainUI.class);
        i.putExtra("FirstName", FirstName);
        i.putExtra("LastName", LastName);
        startActivity(i);
    }

    public void profile_picture_upload_btn(View view) {
        askCameraPermission();


    }

    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_PERMISSION_CODE);
            return;
        } else {
            Log.d(TAG, "askCameraPermission: camera open");
            openCamera();
        }
    }

    private void openCamera() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        profilePic.setImageBitmap(bitmap);
        profilePic.setVisibility(View.VISIBLE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] imageData = byteArrayOutputStream.toByteArray();

        final StorageReference image = storageReference.child("+88" + PhoneNumber);

        image.putBytes(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                if (taskSnapshot.getMetadata() != null) {
                    if (taskSnapshot.getMetadata().getReference() != null) {
                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                ImageUri = uri.toString();
                                Log.d(TAG, "onSuccess: uri " + uri.toString());
                                Log.d(TAG, "onSuccess: Checked ---> image  ---> " + ImageUri);

                            }
                        });
                    }
                }

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {

                Toast.makeText(Set_userProfile.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void next_btn(View view) {
        Log.d(TAG, "next_btn: ---> image ---> " + ImageUri);
        hashMap.put("FirstName", FirstName);
        hashMap.put("LastName", LastName);
        hashMap.put("PhoneNumber", PhoneNumber);
        hashMap.put("ImageURI", ImageUri);
        hashMap.put("Amount", "5000");
        hashMap.put("PICTURE_VISIBILITY", flag);


        Log.d(TAG, "next_btn: is clicked");

        firebaseFirestore.collection("USER PROFILE").document("+88" + PhoneNumber).set(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        if (ImageUri != null) {

                            Log.d(TAG, "onSuccess: " + ImageUri);

                            Toast.makeText(Set_userProfile.this, "Set Information ", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Set_userProfile.this, AppsMainUI.class));
                            finish();
                        } else {
                            Toast.makeText(Set_userProfile.this, "Wait 5 second and press next arrow Again", Toast.LENGTH_SHORT).show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Set_userProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: camera open");
        }
    }

    public void back_btn(View view) {

        startActivity(new Intent(Set_userProfile.this, Set_userName.class));
        finish();

    }
}