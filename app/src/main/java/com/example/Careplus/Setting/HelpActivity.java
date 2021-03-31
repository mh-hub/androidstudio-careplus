package com.example.Careplus.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;
import com.google.firebase.auth.FirebaseAuth;

public class HelpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = new Intent(this.getIntent());

        Button OkBtn = (Button) findViewById(R.id.Okbtn);
        OkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}