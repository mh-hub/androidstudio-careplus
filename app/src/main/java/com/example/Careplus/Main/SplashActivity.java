package com.example.Careplus.Main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.Careplus.Login_Signup.LoginActivity;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
