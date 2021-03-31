package com.example.Careplus.List_Statistics;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Careplus.AddMedicin.AddDayInfo;
import com.example.Careplus.R;

import java.util.ArrayList;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.MainViewHolder> {
    private ArrayList<AddDayInfo> mDataset;
    private Activity activity;

    class MainViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        MainViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    public DayListAdapter(Activity activity, ArrayList<AddDayInfo> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DayListAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_day, parent, false);
        final MainViewHolder mainViewHolder = new MainViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
        CardView cardView = holder.cardView;

        TextView nameTextView = cardView.findViewById(R.id.name_medicin_text);
        nameTextView.setText(mDataset.get(position).get약이름());


        TextView kindTextView = cardView.findViewById(R.id.kind_medicin_text);
        kindTextView.setText(mDataset.get(position).get약종류());

        TextView wayTextView = cardView.findViewById(R.id.way_medicin_text);
        wayTextView.setText(mDataset.get(position).get복용요일());

        TextView timeTextView1 = cardView.findViewById(R.id.medicin_time_text1);
        timeTextView1.setText(mDataset.get(position).get시간1());

        Log.e("로그","시간테스트데이:"+mDataset.get(position).get시간1());

        TextView timeTextView2 = cardView.findViewById(R.id.medicin_time_text2);
        timeTextView2.setText(mDataset.get(position).get시간2());
        Log.e("로그","시간테스트데이2:"+mDataset.get(position).get시간2());

        TextView timeTextView3 = cardView.findViewById(R.id.medicin_time_text3);
        timeTextView3.setText(mDataset.get(position).get시간3());

    }
    @Override
    public int getItemCount() {
        return mDataset.size();

    }
}