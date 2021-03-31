package com.example.Careplus.List_Statistics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Careplus.AddMedicin.AddDayInfo;
import com.example.Careplus.Main.MainActivity;
import com.example.Careplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListDayActivity extends AppCompatActivity {
    private static final String TAG = "ListDayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recycler);
        Intent intent = new Intent(this.getIntent());


        ImageButton Categorybtn6 = (ImageButton) findViewById(R.id.categorybtn6);

        Categorybtn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ListDayActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("medicinday").document(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    ArrayList<AddDayInfo> addDayInfos = new ArrayList<>();
                    if (document != null) {
                        if (document.exists()) {
                            List list = (List) document.getData().get("add_day");
                            for (int i = 0; i < list.size(); i++) {
                                Log.i("TEST", "data[" + i + "] > " + list.get(i).toString());
                                HashMap map = (HashMap) list.get(i);
                                addDayInfos.add(new AddDayInfo(
                                        map.get("약이름").toString(),
                                        map.get("약종류").toString(),
                                        map.get("복용요일").toString(),
                                        map.get("시간1").toString(),
                                        map.get("시간2").toString(),
                                        map.get("시간3").toString()));
                                //document.getId()));

                                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ListDayActivity.this));

                                RecyclerView.Adapter mAdapter = new DayListAdapter(ListDayActivity.this,addDayInfos);
                                recyclerView.setAdapter(mAdapter);

                                Log.e("로그","데이터이름:"+map.get("약이름"));
                                Log.e("로그","데이터종류:"+map.get("약종류"));
                                Log.e("로그","데이터요일:"+map.get("복용요일"));
                                Log.e("로그","데이터아침:"+map.get("시간1"));
                                Log.e("로그","데이터점심:"+map.get("시간2"));
                                Log.e("로그","데이터저녁:"+map.get("시간3"));

                            }

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }

}