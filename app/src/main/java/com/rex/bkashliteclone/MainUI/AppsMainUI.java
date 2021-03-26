package com.rex.bkashliteclone.MainUI;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.rex.bkashliteclone.ClassInterface_Fragment.OnClickedFragment;
import com.rex.bkashliteclone.MainActivity;
import com.rex.bkashliteclone.MainUI.FragmentUI.CashOut.CashOut;
import com.rex.bkashliteclone.MainUI.FragmentUI.CashOut.CashOutAmount;
import com.rex.bkashliteclone.MainUI.FragmentUI.CashOut.CashOutFinalProcess;
import com.rex.bkashliteclone.MainUI.FragmentUI.Home;
import com.rex.bkashliteclone.MainUI.FragmentUI.Inbox.Inbox;
import com.rex.bkashliteclone.MainUI.FragmentUI.MobileRecharge.MobileRecharge;
import com.rex.bkashliteclone.MainUI.FragmentUI.MobileRecharge.MobileRechargeAmount;
import com.rex.bkashliteclone.MainUI.FragmentUI.MobileRecharge.MobileRechargeFinalProcess;
import com.rex.bkashliteclone.MainUI.FragmentUI.SendMoney.SendMoney;
import com.rex.bkashliteclone.MainUI.FragmentUI.SendMoney.SendMoneyAmount;
import com.rex.bkashliteclone.MainUI.FragmentUI.SendMoney.SendMoneyFinalProcess;
import com.rex.bkashliteclone.MainUI.FragmentUI.Settings.ChangeName;
import com.rex.bkashliteclone.MainUI.FragmentUI.Settings.ChangePicture;
import com.rex.bkashliteclone.MainUI.FragmentUI.Settings.Settings;
import com.rex.bkashliteclone.MainUI.FragmentUI.Statements.Statements;
import com.rex.bkashliteclone.MainUI.FragmentUI.Toolbar.EveryPageToolbar;
import com.rex.bkashliteclone.MainUI.FragmentUI.Toolbar.MainPageToolbar;
import com.rex.bkashliteclone.R;

public class AppsMainUI extends AppCompatActivity implements OnClickedFragment {

    public static final String TAG = "";

    private DrawerLayout drawerLayout;
    private Dialog dialog;
    private AnimationDrawable animationDrawable;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_main_u_i);

        drawerLayout = findViewById(R.id.MainDrawerLayout);

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new Home()).commit();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container, new MainPageToolbar()).commit();
    }

    @Override
    public void EveryPageNavClicked() {
        drawerLayout.openDrawer(GravityCompat.END);

    }

    @Override
    public void MainPageNavClicked() {

        drawerLayout.openDrawer(GravityCompat.END);

    }


    public void NavigationHome(View view){

        openDialog();
        drawerLayout.closeDrawer(GravityCompat.END);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new Home()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container, new MainPageToolbar()).commit();


            }
        },3000);


    }

    public void NavigationCoupons(View view){
        drawerLayout.closeDrawer(GravityCompat.END);
        Toast.makeText(this, "Under Develop", Toast.LENGTH_SHORT).show();
        return;
    }

    public void NavigationLimits(View view){
        drawerLayout.closeDrawer(GravityCompat.END);
        Toast.makeText(this, "Under Develop", Toast.LENGTH_SHORT).show();
        return;

    }

    public void NavigationLogout(View view){

        drawerLayout.closeDrawer(GravityCompat.END);

        firebaseAuth = FirebaseAuth.getInstance();

        startActivity(new Intent(AppsMainUI.this, MainActivity.class));
        firebaseAuth.signOut();
        finish();


    }

    public void NavigationSettings(View view){
        drawerLayout.closeDrawer(GravityCompat.END);

        openDialog();
        drawerLayout.closeDrawer(GravityCompat.END);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new Settings()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Settings")).commit();


            }
        },3000);



    }

    public void NavigationStatements(View view){
        openDialog();
        drawerLayout.closeDrawer(GravityCompat.END);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new Statements()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Statements")).commit();


            }
        },3000);

    }


    @Override
    public void OnInboxClicked() {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new Inbox()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Inbox")).commit();

            }
        },3000);

    }

    @Override
    public void OnSendMoneyClicked() {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new SendMoney()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Send Money")).commit();

            }
        },3000);

    }

    @Override
    public void OnCashOutClicked() {
        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new CashOut()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Cash Out")).commit();

            }
        },3000);



    }

    @Override
    public void OnMobileRechargedClicked() {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new MobileRecharge()).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Mobile Recharge")).commit();

            }
        },3000);



    }

    @Override
    public void OnPayBillClicked() {

    }

    @Override
    public void OnSendMoneyNextButtonClicked(String ReceiverNumber) {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new SendMoneyAmount().newInstance(ReceiverNumber)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Send Money")).commit();

            }
        },3000);



    }

    @Override
    public void OnSendMoneyAmountNextButtonClicked(String DataBaseAmount, String Amount, String ReceiverNumber) {
        Log.d(TAG, "OnSendMoneyAmountNextButtonClicked: amount "+Amount);
        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new SendMoneyFinalProcess().newInstance(ReceiverNumber,Amount,DataBaseAmount)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Send Money")).commit();

            }
        },3000);






    }

    @Override
    public void OnCashOutNextButtonClicked(String AgentNumber) {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new CashOutAmount().newInstance(AgentNumber)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Cash Out")).commit();


            }
        },3000);



    }

    @Override
    public void OnCashOutAmountNextButtonClicked(String DataBaseAmount, String Amount, String ReceiverNumber) {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new CashOutFinalProcess().newInstance(ReceiverNumber,Amount,DataBaseAmount)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Cash Out")).commit();

            }
        },3000);





    }

    @Override
    public void OnMobileRechargeNextButtonClicked(String ReceiverNumber) {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new MobileRechargeAmount().newInstance(ReceiverNumber)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Mobile Recharge")).commit();

            }
        },3000);





    }

    @Override
    public void OnMobileRechargeAmountNextButtonClicked(String databaseAmount, String Amount, String receiverNumber) {

        openDialog();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment,
                        new MobileRechargeFinalProcess().newInstance(receiverNumber,Amount,databaseAmount)).commit();
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                        new EveryPageToolbar().newInstance("Mobile Recharge")).commit();

            }
        },3000);



    }

    @Override
    public void OnSettingNameChangeClicked() {

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new ChangeName()).commit();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                new EveryPageToolbar().newInstance("Settings")).commit();


    }

    @Override
    public void OnSettingPictureChangeClicked() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container_All_fragment, new ChangePicture()).commit();
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.Toolbar_fragment_container,
                new EveryPageToolbar().newInstance("Settings")).commit();

    }

    private void openDialog() {



        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_loadinganimation);
        ImageView imageView = dialog.findViewById(R.id.DialogImageView);
        imageView.setBackgroundResource(R.drawable.animation_of_loadingscreen);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        dialog.show();
    }

}