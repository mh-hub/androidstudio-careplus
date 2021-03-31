package com.example.Careplus.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Login_Signup.LoginActivity;
import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = new Intent(this.getIntent());
        findViewById(R.id.Deletebtn).setOnClickListener(onClickListener);
        mAuth = FirebaseAuth.getInstance();

        ImageButton Categorybtn2 = (ImageButton) findViewById(R.id.categorybtn2);

        Categorybtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button Logoutbtn = (Button)findViewById(R.id.Logoutbtn);
        Button Question_btn = (Button)findViewById(R.id.Question_btn);
        Button help_btn = (Button)findViewById(R.id.help_btn);

        //로그아웃
        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        //1:!문의 메뉴로 이동
        Question_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, QuestionActivity.class);
                startActivity(intent);
            }
        });

        //도움말 메뉴로 이동
        help_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Deletebtn:
                    Delete();
                    break;
            }
        }
    };

    //회원탈퇴
    private void Delete() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mAuth.getCurrentUser().delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "User account deleted.");
                            startToast("계정이 삭제되었습니다.");
                            myStartActivity(LoginActivity.class);
                        }
                    }
                });

    }

    //토스트메시지 수행
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    //인텐트 수행
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
    }
}