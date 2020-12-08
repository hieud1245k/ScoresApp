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

public class MyScoreHistoryAdapter extends RecyclerView.Adapter<MyScoreHistoryAdapter.MyScoreHistoryHolder> {

    private List<MatchScores> matchScores;

    public MyScoreHistoryAdapter(List<MatchScores> matchScores) {
        this.matchScores = matchScores;
    }

    @NonNull
    @Override
    public MyScoreHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyScoreHistoryAdapter.MyScoreHistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_history,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyScoreHistoryHolder holder, int position) {
        MatchScores matchScore = matchScores.get(position);
        for(int i = 0; i < 4 ; i++) {
            holder.playersScore[i].setText(matchScore.marks[i]);
        }
        holder.match.setText(matchScore.id);
    }

    @Override
    public int getItemCount() {
        return matchScores.size();
    }

    class MyScoreHistoryHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView playersScore[] = new TextView[4];
        public TextView match;
        public MyScoreHistoryHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            playersScore[0] = view.findViewById(R.id.tv_player0_score_history);
            playersScore[1] = view.findViewById(R.id.tv_player1_score_history);
            playersScore[2] = view.findViewById(R.id.tv_player2_score_history);
            playersScore[3] = view.findViewById(R.id.tv_player3_score_history);

            match = view.findViewById(R.id.tv_match_score_history);
        }
    }
}
