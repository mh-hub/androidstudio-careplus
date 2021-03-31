package com.example.Careplus.AddMedicin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddPeriodActivity extends AppCompatActivity {
    private static final String TAG = "AddPeriodActivity";
    Calendar myCalendar = Calendar.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //현재 유저 가져오기
    final FirebaseFirestore db = FirebaseFirestore.getInstance(); //초기화

    /*날짜 설정*/
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedicin_period);

        Intent intent = new Intent(this.getIntent());

        /*메뉴이동*/
        ImageButton Categorybtn2 = (ImageButton) findViewById(R.id.categorybtn2);
        Categorybtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddPeriodActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.period_ok).setOnClickListener(onClickListener);

        EditText et_Date = (EditText) findViewById(R.id.startday);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddPeriodActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        /*타임피커 설정*/
        final EditText et_time1 = (EditText) findViewById(R.id.mtime1);
        et_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPeriodActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time1.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*타임피커 설정*/
        final EditText et_time2 = (EditText) findViewById(R.id.mtime2);
        et_time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPeriodActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time2.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        /*타임피커 설정*/
        final EditText et_time3 = (EditText) findViewById(R.id.mtime3);
        et_time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPeriodActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "오전";
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "오후";
                        }
                        // EditText에 출력할 형식 지정
                        et_time3.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, hour, minute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy.MM.dd";    // 출력형식   2018.11.28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) findViewById(R.id.startday);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }
    /*버튼 클릭 이벤트*/
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.period_ok:
                    init();
                    break;
            }
        }
    };


    private void init(){
        //medicinperiod 컬렉션에 유저이메일 문서를 생성
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("medicinperiod").document(user.getEmail());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            medicinperiodUpdate();  //파이어베이스에 문서가 존재할 경우 호출
                        } else {
                            medicinperiodSet();  //파이어베이스에 문서가 존재하지 않을 경우 호출
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    /*주기별 약추가 수행*/
    private void medicinperiodUpdate() {
        final String 약종류 = ((Spinner) findViewById(R.id.select_spinner_p)).getSelectedItem().toString();
        final String 복용시작날짜 = ((EditText) findViewById(R.id.startday)).getText().toString();
        final String 복용주기 = ((EditText) findViewById(R.id.Doseperiod)).getText().toString();
        final String 약이름 = ((EditText) findViewById(R.id.periodname)).getText().toString();
        final String 시간1 = ((EditText) findViewById(R.id.mtime1)).getText().toString();
        final String 시간2 = ((EditText) findViewById(R.id.mtime2)).getText().toString();
        final String 시간3 = ((EditText) findViewById(R.id.mtime3)).getText().toString();
        if (약종류.length() > 0 && 복용시작날짜.length() > 0 && 복용주기.length() > 0 &&  약이름.length() > 0 && 시간1.length() > 0 || 시간2.length() > 0 || 시간3.length() > 0) {

            AddPeriodInfo periodInfo = new AddPeriodInfo(약이름, 약종류, 복용시작날짜, 복용주기, 시간1, 시간2, 시간3);
            if (user != null) {
                //add_period 필드를 생성하여 그 안에 배열형태로 값을 추가
                db.collection("medicinperiod").document(user.getEmail()).update("add_period", FieldValue.arrayUnion(periodInfo))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {  //성공 시
                                startToast("약이 추가되었습니다.");
                                myStartActivity(AddMedicin_Main_Activity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {  //실패 시
                                startToast("약추가에 실패하였습니다.");

                            }
                        });
                Log.e("로그","시간추가주기:"+시간1);

            } else {
                startToast("약 추가 양식을 작성해주세요.");
            }
        }

    }

    /*주기별 약추가 수행*/
    private void medicinperiodSet() {
        final String 약종류 = ((Spinner) findViewById(R.id.select_spinner_p)).getSelectedItem().toString();
        final String 복용시작날짜 = ((EditText) findViewById(R.id.startday)).getText().toString();
        final String 복용주기 = ((EditText) findViewById(R.id.Doseperiod)).getText().toString();
        final String 약이름 = ((EditText) findViewById(R.id.periodname)).getText().toString();
        final String 시간1 = ((EditText) findViewById(R.id.mtime1)).getText().toString();
        final String 시간2 = ((EditText) findViewById(R.id.mtime2)).getText().toString();
        final String 시간3 = ((EditText) findViewById(R.id.mtime3)).getText().toString();
        if (약종류.length() > 0 && 복용시작날짜.length() > 0 && 복용주기.length() > 0 &&  약이름.length() > 0 && 시간1.length() > 0 || 시간2.length() > 0 || 시간3.length() > 0) {
            final Map<String, Object> docData = new HashMap<>();
            final ArrayList<AddPeriodInfo> list = new ArrayList<>();

            AddPeriodInfo periodInfo = new AddPeriodInfo(약이름, 약종류, 복용시작날짜, 복용주기, 시간1, 시간2, 시간3);
            list.add(periodInfo);

            docData.put("add_period", list);
            if (user != null) {
                db.collection("medicinperiod").document(user.getEmail()).set(docData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {  //성공 시
                                startToast("약이 추가되었습니다.");
                                myStartActivity(AddMedicin_Main_Activity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {  //실패 시
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
