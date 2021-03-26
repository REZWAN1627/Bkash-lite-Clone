package com.rex.bkashliteclone.MainUI.FragmentUI.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.R;


public class MainPageToolbar extends Fragment {
    public static final String TAG = "TAG";

    private OnClickedFragment listener;

    private String FirstName, LastName, Amount, PicVisibility;

    private TextView UserName, UserAmount;

    private FirebaseFirestore firebaseFirestore;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private ImageView UserProfileImage;

    private ImageView tabForbalance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page_toolbar, container, false);

        tabForbalance = view.findViewById(R.id.tabAnimation);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        UserName = view.findViewById(R.id.MainPageToolbar_Username);
        UserAmount = view.findViewById(R.id.MainPage_UserAmount);
        UserProfileImage = view.findViewById(R.id.MainPage_UserprofilePicture);


        UserAmount.setVisibility(View.INVISIBLE);
        UserName.setVisibility(View.INVISIBLE);

        FirebaseProcess();


        tabForbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: is clicked for tab");

                tabForbalance.animate().translationX(330);
                tabForbalance.animate().setDuration(700);
                UserAmount.setText("");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UserAmount.setTextSize(16);
                        UserAmount.setText("$ " + Amount);
                    }
                }, 1000);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        UserAmount.setText("");
                        tabForbalance.animate().translationX(0);
                        tabForbalance.animate().setDuration(700);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                UserAmount.setTextSize(15);
                                UserAmount.setText("Tab for balance");
                            }
                        }, 1000);

                    }
                }, 3000);


            }
        });


        view.findViewById(R.id.mainpage_nav_open_indicator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MainPageNavClicked();
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
                    Log.d(TAG, "onComplete: " + task.getResult().getData().get("FirstName"));

                    FirstName = (String) task.getResult().getData().get("FirstName");
                    LastName = (String) task.getResult().getData().get("LastName");
                    Amount = (String) task.getResult().getData().get("Amount");

                    PicVisibility = (String) task.getResult().getData().get("PICTURE_VISIBILITY");

                    UserName.setVisibility(View.VISIBLE);
                    UserAmount.setVisibility(View.VISIBLE);
                    UserName.setText(FirstName + " " + LastName);

                    String image = (String) task.getResult().getData().get("ImageURI");
                    Log.d(TAG, "onComplete: " + image);
                    if (PicVisibility.equals("YES")) {
                        Glide.with(getActivity()).load(image).into(UserProfileImage);
                    } else {
                        Log.d(TAG, "onComplete: Database NO");
                    }


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
        if (context instanceof OnClickedFragment) {
            listener = (OnClickedFragment) context;
            return;
        } else {
            throw new ClassCastException(context.toString() + "Must Implements Interface Method");

        }

    }


}