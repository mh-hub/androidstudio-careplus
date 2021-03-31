package com.example.Careplus.Login_Signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
   private FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPasswordResetButton).setOnClickListener(onClickListener);
        findViewById(R.id.signupButton).setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginButton:  //로그인 버튼 클릭 시
                    login();  //로그인함수 호출
                    break;
                case R.id.gotoPasswordResetButton:  //비밀번호 재설정 버튼 클릭 시
                    myStartActivity(PasswordResetActivity.class);  //비밀번호 재설정 화면으로 이동
                    break;
                case R.id.signupButton:  //회원가입 버튼 클릭 시
                    myStartActivity(SignUpActivity.class);  //회원가입 화면으로 이동
                    break;
            }
        }
    };
    //로그아웃 안했으면, 즉 로그인 되어있으면 자동으로 메인페이지로 이동시키기
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){  //유저가 null이 아니라면
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    //로그인 수행
    private void login() {
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하였습니다.");
                                myStartActivity(MainActivity.class);
                            } else {
                                if (task.getException() != null) {
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
