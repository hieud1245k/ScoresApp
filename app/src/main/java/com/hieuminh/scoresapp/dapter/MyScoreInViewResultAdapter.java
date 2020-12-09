package com.hieuminh.scoresapp.dapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.MatchScores;

import java.util.List;

import static com.hieuminh.scoresapp.dapter.MyScoreInViewResultAdapter.*;

public class MyScoreInViewResultAdapter extends RecyclerView.Adapter<MyScoreInViewResultHolder> {

    private List<MatchScores> arrMatchScores;

    public MyScoreInViewResultAdapter(List<MatchScores> arrMatchScores) {
        this.arrMatchScores = arrMatchScores;
    }

    @NonNull
    @Override
    public MyScoreInViewResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyScoreInViewResultAdapter.MyScoreInViewResultHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_result,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyScoreInViewResultHolder holder, int position) {
        MatchScores matchScore = arrMatchScores.get(position);
        for(int i = 0; i < matchScore.marks.length; i++) {
            holder.playersScore[i].setText("" + matchScore.marks[i]);
        }
        holder.match.setText("" + position);
    }

    @Override
    public int getItemCount() {
        return arrMatchScores.size();
    }

    public static class MyScoreInViewResultHolder extends RecyclerView.ViewHolder {
        public TextView playersScore[] = new TextView[4];
        public View view;
        public TextView match;
        public MyScoreInViewResultHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            playersScore[0] = view.findViewById(R.id.tv_view_result_player_score_0);
            playersScore[1] = view.findViewById(R.id.tv_view_result_player_score_1);
            playersScore[2] = view.findViewById(R.id.tv_view_result_player_score_2);
            playersScore[3] = view.findViewById(R.id.tv_view_result_player_score_3);

            match = view.findViewById(R.id.tv_view_result_item_match);
        }
    }
}
