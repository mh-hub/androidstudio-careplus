package com.example.Careplus.AddMedicin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;

public class AddMedicin_Main_Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmedicin_main);
        Button Dayselect = (Button)findViewById(R.id.dayselect);
        Button Periodselect = (Button)findViewById(R.id.periodselect) ;

        Intent intent = new Intent(this.getIntent());

        /*메뉴이동*/
        ImageButton Categorybtn2 = (ImageButton) findViewById(R.id.categorybtn2);
        Categorybtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicin_Main_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //요일별 복용으로 이동
        Dayselect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicin_Main_Activity.this, AddDayActivity.class);
                startActivity(intent);
            }
        });

        //주기별 복용으로 이동
        Periodselect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddMedicin_Main_Activity.this, AddPeriodActivity.class);
                startActivity(intent);
            }
        });

    }

}
