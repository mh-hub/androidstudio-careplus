package com.example.Careplus.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        ImageButton categorybtnq = (ImageButton) findViewById(R.id.categorybtnq);
        findViewById(R.id.btnOK).setOnClickListener(onClickListener);
        findViewById(R.id.btnCancel).setOnClickListener(onClickListener);


        // 카테고리 버튼 선택 시
        categorybtnq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnOK:  //확인버튼 클릭 시 init()함수 호출
                    init();
                    break;
                case R.id.btnCancel:  //취소버튼 클릭 시 메인엑티비티로 돌아가기
                    myStartActivity(MainActivity.class);
                    break;
            }
        }
    };
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private void init() {
        //question 컬렉션에서 유저이메일 문서의 필드를 쿼리
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("question").document(user.getEmail());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            questionUpdate();
                        } else {
                            questionSet();
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void questionUpdate() {
        final String name = ((EditText) findViewById(R.id.edit_name)).getText().toString();
        final String email = ((EditText) findViewById(R.id.edit_email)).getText().toString();
        final String spinner_question = ((Spinner) findViewById(R.id.spinner_question)).getSelectedItem().toString();
        final String title = ((EditText) findViewById(R.id.edit_title)).getText().toString();
        final String content = ((EditText) findViewById(R.id.edit_content)).getText().toString();

        if (name.length() > 0 && email.length() > 5 && spinner_question.length() > 0 && title.length() > 0 && content.length() > 10) {

            QuestionInfo questionInfo = new QuestionInfo(name, email, spinner_question, title, content);
            if (user != null) {
                //add_question 필드를 생성하여 그 안에 배열형태로 값을 추가
                db.collection("question").document(user.getEmail()).update("add_question", FieldValue.arrayUnion(questionInfo))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("문의 등록을 성공하셨습니다.");
                                myStartActivity(MainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("문의 등록을 실패하였습니다.");

                            }
                        });
            } else {
                startToast("문의내역을 입력해주세요.");
            }
        }
    }

    private void questionSet() {

        final String name = ((EditText) findViewById(R.id.edit_name)).getText().toString();
        final String email = ((EditText) findViewById(R.id.edit_email)).getText().toString();
        final String spinner_question = ((Spinner) findViewById(R.id.spinner_question)).getSelectedItem().toString();
        final String title = ((EditText) findViewById(R.id.edit_title)).getText().toString();
        final String content = ((EditText) findViewById(R.id.edit_content)).getText().toString();

        if (name.length() > 0 && email.length() > 5 && spinner_question.length() > 0 && title.length() > 0 && content.length() > 10) {
            final Map<String, Object> docData = new HashMap<>();
            final ArrayList<QuestionInfo> list = new ArrayList<>();

            QuestionInfo questionInfo = new QuestionInfo(name, email, spinner_question, title, content);
            list.add(questionInfo);

            docData.put("add_question", list);
            if (user != null) {
                db.collection("question").document(user.getEmail()).set(docData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("문의 등록을 성공하셨습니다.");
                                myStartActivity(MainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("문의 등록을 실패하였습니다.");

                            }
                        });
            } else {
                startToast("문의내역을 입력해주세요.");
            }

        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivityForResult(intent, 0);
    }
}
