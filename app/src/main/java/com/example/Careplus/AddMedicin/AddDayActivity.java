package com.example.Careplus.AddMedicin;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/*요일별 약 추가 액티비티*/
public class AddDayActivity extends AppCompatActivity {
    private static final String TAG = "AddDayActivity";
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //현재 유저 가져오기
    final FirebaseFirestore db = FirebaseFirestore.getInstance(); //초기화


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedicin_day);

        Intent intent = new Intent(this.getIntent());

        /*메뉴이동*/
        ImageButton Categorybtn2 = (ImageButton) findViewById(R.id.categorybtn2);
        Categorybtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddDayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.day_ok).setOnClickListener(onClickListener);
        final EditText et_time4 = (EditText) findViewById(R.id.mtime4);

        /*타임피커 설정*/
        et_time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddDayActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time4.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*타임피커 설정*/
        final EditText et_time5 = (EditText) findViewById(R.id.mtime5);
        et_time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddDayActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time5.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*타임피커 설정*/
        final EditText et_time6 = (EditText) findViewById(R.id.mtime6);
        et_time6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddDayActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time6.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }
    /*버튼 클릭 이벤트*/
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.day_ok:
                    init();
                    break;
            }
        }
    };


    private void init(){
        //medicinday컬렉션에 유저이메일 문서를 쿼리
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("medicinday").document(user.getEmail());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            medicindayUpdate();  //파이어베이스에 문서가 존재할 경우 호출
                        } else {
                            medicindaySet();  //파이어베이스에 문서가 존재하지 않을 경우 호출
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    /*요일별 약추가 수행*/
    private void medicindayUpdate() {
        final String 약종류 = ((Spinner) findViewById(R.id.select_spinner_d)).getSelectedItem().toString();
        final String 복용요일 = ((EditText) findViewById(R.id.day)).getText().toString();
        final String 약이름 = ((EditText) findViewById(R.id.dayname)).getText().toString();
        final String 시간1 = ((EditText) findViewById(R.id.mtime4)).getText().toString();
        final String 시간2 = ((EditText) findViewById(R.id.mtime5)).getText().toString();
        final String 시간3 = ((EditText) findViewById(R.id.mtime6)).getText().toString();

        if (약종류.length() > 0 && 복용요일.length() > 0 && 약이름.length() > 0 && 시간1.length() > 0 || 시간2.length() > 0 || 시간3.length() > 0) {
            AddDayInfo dayInfo = new AddDayInfo(약이름, 약종류, 복용요일, 시간1, 시간2, 시간3);

            if (user != null) {
                //add_day필드를 생성하여 그 안에 배열형태로 값을 추가
                db.collection("medicinday").document(user.getEmail()).update("add_day", FieldValue.arrayUnion(dayInfo))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {  //성공 시
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("약이 추가되었습니다.");
                                myStartActivity(AddMedicin_Main_Activity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {  //실패 시
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("약추가에 실패하였습니다.");

                            }
                        });


                Log.e("로그","시간추가:"+시간1);


            } else {
                startToast("약 추가 양식을 작성해주세요.");
            }

        }
    }

    /*요일별 약추가 수행*/
    private void medicindaySet() {
        final String 약종류 = ((Spinner) findViewById(R.id.select_spinner_d)).getSelectedItem().toString();
        //final String 복용요일 =((EditText) findViewById(R.id.day)).getText().toString();
        final String 복용요일 = ((EditText) findViewById(R.id.day)).getText().toString();
        final String 약이름 = ((EditText) findViewById(R.id.dayname)).getText().toString();
        final String 시간1 = ((EditText) findViewById(R.id.mtime4)).getText().toString();
        final String 시간2 = ((EditText) findViewById(R.id.mtime5)).getText().toString();
        final String 시간3 = ((EditText) findViewById(R.id.mtime6)).getText().toString();


        if (약종류.length() > 0 && 복용요일.length() > 0 && 약이름.length() > 0 && 시간1.length() > 0 || 시간2.length() > 0 || 시간3.length() > 0) {
            final Map<String, Object> docData = new HashMap<>();
            final ArrayList<AddDayInfo> list = new ArrayList<>();

            AddDayInfo dayInfo = new AddDayInfo(약이름, 약종류, 복용요일, 시간1, 시간2, 시간3);
            list.add(dayInfo);
            docData.put("add_day", list);
            if (user != null) {
                db.collection("medicinday").document(user.getEmail()).set(docData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {  //성공 시
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("약이 추가되었습니다.");
                                myStartActivity(AddMedicin_Main_Activity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {  //실패 시
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("약추가에 실패하였습니다.");

                            }
                        });
            } else {
                startToast("약 추가 양식을 작성해주세요.");
            }

        }
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