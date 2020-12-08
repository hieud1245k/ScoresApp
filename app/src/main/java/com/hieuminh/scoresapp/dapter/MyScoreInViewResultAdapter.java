package com.hieuminh.scoresapp.dapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyScoreInViewResultHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrMatchScores.size();
    }

    public static class MyScoreInViewResultHolder extends RecyclerView.ViewHolder {
        public MyScoreInViewResultHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
