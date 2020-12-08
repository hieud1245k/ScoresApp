package com.hieuminh.scoresapp.dapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.Records;
import com.hieuminh.scoresapp.model.Time;
import com.hieuminh.scoresapp.view.ListFragmentDirections;
import com.hieuminh.scoresapp.view.RegisterFragmentDirections;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MyScoreAdapter extends RecyclerView.Adapter<MyScoreAdapter.MyScoresHolder> {

    private List<Records> records;

    public MyScoreAdapter(List<Records> records) {
        this.records = records;
    }

    @NonNull
    @Override
    public MyScoresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyScoresHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyScoresHolder holder, int position) {
        Records record = records.get(position);
        for(int i = 0; i < 4; i++) {
            holder.players[i].setText(record.players[i]);
        }
        String h = (record.startTime.hour < 10)?("" + record.startTime.hour):(record.startTime.hour + "");
        String m = (record.startTime.min < 10)?("" + record.startTime.min):(record.startTime.min + "");
        holder.saveDate.setText(h + ":" + m);
//        holder.dateHistory.setText(setTime(record.time));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class MyScoresHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView players[] = new TextView[4];
        public TextView saveDate, dateHistory;
        public LinearLayout linearLayoutRecordScore;
        public MyScoresHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            players[0] = view.findViewById(R.id.tv_player0_name);
            players[1] = view.findViewById(R.id.tv_player1_name);
            players[2] = view.findViewById(R.id.tv_player2_name);
            players[3] = view.findViewById(R.id.tv_player3_name);

            saveDate = view.findViewById(R.id.tv_date_score);
            dateHistory = view.findViewById(R.id.tv_date_history);

            linearLayoutRecordScore = view.findViewById(R.id.ll_record_item);
            linearLayoutRecordScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavDirections action = ListFragmentDirections.actionListFragmentToDetailFragment();
                    Navigation.findNavController(view).navigate(action);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String setTime(Time time) {
        if(time.month != LocalDate.now().getMonthValue()) {
            return LocalDate.now().getMonthValue() - time.month + " month ago";
        } else if(time.day != LocalDate.now().getDayOfMonth()) {
            return LocalDate.now().getDayOfMonth() - time.day + " day ago";
        } else if(time.hour != LocalTime.now().getHour()) {
            return LocalTime.now().getHour() - time.hour + " hour ago";
        } else if(time.min != LocalTime.now().getMinute()) {
            return LocalTime.now().getMinute() - time.min + " minute ago";
        } else if(time.second != LocalTime.now().getSecond()) {
            return LocalTime.now().getSecond() - time.second + " second ago";
        }
        return null;
    }
}
