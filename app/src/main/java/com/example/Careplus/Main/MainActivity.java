package com.example.Careplus.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.AddMedicin.AddMedicin_Main_Activity;
import com.example.Careplus.BestMedicin.BestMainActivity;
import com.example.Careplus.List_Statistics.List_Main_Activity;
import com.example.Careplus.Login_Signup.LoginActivity;
import com.example.Careplus.Map.MapActivity;
import com.example.Careplus.R;
import com.example.Careplus.Setting.SerialNbInfo;
import com.example.Careplus.Setting.SettingActivity;
import com.example.Careplus.Statistics.StatisticsMain_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private long time= 0;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private TextView myemail;
    int iValue;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //현재 유저 가져오기
    final FirebaseFirestore db = FirebaseFirestore.getInstance();  //초기화

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //고유번호 난수 생성
        for(int i = 0; i < 100; i++) {
            double dValue = Math.random();
            iValue = (int)(dValue * 100);
        }

        FirebaseMessaging.getInstance().subscribeToTopic(String.format("%d",iValue));
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser(); //유저가 있다면, null이 아니면 계속 진행
        mAuth = FirebaseAuth.getInstance();
        myemail = (TextView) findViewById(R.id.myemail);

        //유저의 이메일 메인화면에서 보여준다.
        myemail.setText(user.getEmail());

        Intent intent = new Intent(this.getIntent());

        Button Addmedicine_btn=(Button)findViewById(R.id.addmedicine_btn);
        Button Best_btn = (Button)findViewById(R.id.best_btn) ;
        Button Map_btn = (Button)findViewById(R.id.map_btn);
        Button List_btn = (Button)findViewById(R.id.list_btn);
        Button statistics_btn = (Button)findViewById(R.id.statistics_btn);
        ImageButton setting_btn = (ImageButton) findViewById(R.id.setting_btn);

        init();  //고유번호 db에 저장

        //설정 메뉴로 이동
        setting_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        //약추가 메뉴로 이동
        Addmedicine_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMedicin_Main_Activity.class);
                startActivity(intent);
            }
        });

        //BEST약 메뉴로 이동
        Best_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BestMainActivity.class);
                startActivity(intent);
            }
        });

        //약국찾기 메뉴로 이동
        Map_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


        //약목록 메뉴로 이동
        List_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Main_Activity.class);
                startActivity(intent);
            }
        });

        //통계 메뉴로 이동
        statistics_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatisticsMain_Activity.class);
                startActivity(intent);
            }
        });

    }
    private void init(){
        //SerialNumber컬렉션에 유저이메일 문서를 쿼리
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("SerialNumber").document(user.getEmail());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            //파이어베이스에 문서가 존재할 경우 호출
                        } else {
                            SerialNbSet();  //파이어베이스에 문서가 존재하지 않을 경우 호출
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }


    /*고유번호 db에 저장*/
    private void SerialNbSet() {
        final String topic = Integer.toString(iValue);
        if (topic.length() > 0) {
            SerialNbInfo serialNbInfo = new SerialNbInfo(topic);
            if (user != null) {
                //SerialNumber 컬렉션에 유저이메일 문서를 생성 후 고유번호 저장
                db.collection("SerialNumber").document(user.getEmail()).set(serialNbInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {  //성공 시
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("고유번호가 생성되었습니다.");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {  //실패 시
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("고유번호 생성에 실패하였습니다.");

                            }
                        });
            }
        }
    }

    //토스트메시지 수행
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //뒤로가기 두번 눌러서 종료
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }

    //로그인 되어있으면 currentUser 변수에 유저정보 할당. 아닌경우 login 페이지로 이동!
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}