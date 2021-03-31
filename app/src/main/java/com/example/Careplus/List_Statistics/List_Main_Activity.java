package com.example.Careplus.List_Statistics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;

public class List_Main_Activity extends AppCompatActivity {

    SharedPreferences sf;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicin_dayperiod_main_list);
        Button Daylist = (Button)findViewById(R.id.daylist_main);
        Button Periodlist= (Button)findViewById(R.id.periodlist) ;

        Intent intent = new Intent(this.getIntent());

        ImageButton Categorybtn7 = (ImageButton) findViewById(R.id.categorybtn7);

        Categorybtn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(List_Main_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //요일별 복용 목록으로 이동
        Daylist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(List_Main_Activity.this, ListDayActivity.class);
                startActivity(intent);

            }
        });

        //주기별 복용 목록 으로 이동
        Periodlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(List_Main_Activity.this, ListPeriodActivity.class);
                startActivity(intent);
            }
        });

    }

}
