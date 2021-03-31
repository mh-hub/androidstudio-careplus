package com.example.Careplus.BestMedicin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;

public class BestMainActivity extends AppCompatActivity {

    final Button[] Virank = new Button[5];  //비타민 1~5순위
    final Button [] Eyerank = new Button[5]; //눈 영양제 1~5순위
    final Button [] Storank = new Button[5]; //유산균 1~5순위
    int i;
    ArrayAdapter<CharSequence> adapt1;  //어댑터 선언

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_main);

        Intent intent = new Intent(this.getIntent());

        final Spinner spinner = (Spinner) findViewById(R.id.Spinner1);
        Button Categorybtn4 = (Button) findViewById(R.id.menu1) ;

        //메뉴이동
        Categorybtn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(BestMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //위젯 대임
        for(i=0;i<5;i++)
            Virank[i]=(Button)findViewById(R.id.vitarank1+i);
        for(i=0;i<5;i++)
            Eyerank[i]=(Button)findViewById(R.id.eyerank1+i);
        for(i=0;i<5;i++)
            Storank[i]=(Button)findViewById(R.id.storank1+i);

        adapt1 = ArrayAdapter.createFromResource(this, R.array.약종류, android.R.layout.simple_spinner_dropdown_item);   //어댑터에 값을 넣는다. this는 현재class를 의미
        spinner.setAdapter(adapt1);  //어댑터에 값들을 spinner에 넣는다

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (adapt1.getItem(pos).equals("눈 영양제")) { //spinner 값을 가져와서 클릭 한 것이 눈인지 확인
                    //비타민, 장 안보이게 설정
                    for(i=0;i<5;i++){
                        Virank[i].setVisibility(View.INVISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Eyerank[i].setVisibility(View.VISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Storank[i].setVisibility(View.INVISIBLE);
                    }
                }
                if (adapt1.getItem(pos).equals("유산균")) { //spinner 값을 가져와서 클릭 한 것이 장인지 확인

                    for(i=0;i<5;i++){
                        Virank[i].setVisibility(View.INVISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Eyerank[i].setVisibility(View.INVISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Storank[i].setVisibility(View.VISIBLE);
                    }
                }
                if (adapt1.getItem(pos).equals("종합비타민")) { //spinner 값을 가져와서 클릭 한 것이 종합비타민인지 확인

                    for(i=0;i<5;i++){
                        Virank[i].setVisibility(View.VISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Eyerank[i].setVisibility(View.INVISIBLE);
                    }
                    for(i=0;i<5;i++){
                        Storank[i].setVisibility(View.INVISIBLE);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //각 버튼 클릭 시 성분 페이지로 이동
        Virank[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub1_vita_Activity.class);
                startActivity(intent);
            }
        });
        Virank[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub2_vita_Activity.class);
                startActivity(intent);
            }
        });

        Virank[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub3_vita_Activity.class);
                startActivity(intent);
            }
        });
        Virank[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub4_vita_Activity.class);
                startActivity(intent);
            }
        });
        Virank[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub5_vita_Activity.class);
                startActivity(intent);
            }
        });

        Eyerank[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub1_eye_Activity.class);
                startActivity(intent);
            }
        });
        Eyerank[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub2_eye_Activity.class);
                startActivity(intent);
            }
        });
        Eyerank[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub3_eye_Activity.class);
                startActivity(intent);
            }
        });
        Eyerank[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub4_eye_Activity.class);
                startActivity(intent);
            }
        });
        Eyerank[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub5_eye_Activity.class);
                startActivity(intent);
            }
        });

        Storank[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub1_sto_Activity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        Storank[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub2_sto_Activity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        Storank[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub3_sto_Activity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        Storank[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub4_sto_Activity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        Storank[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BestMainActivity.this, BestSub5_sto_Activity.class);
                //intent.putExtra("text",String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
    }
}
