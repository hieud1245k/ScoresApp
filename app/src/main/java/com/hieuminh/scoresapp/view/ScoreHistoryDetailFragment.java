package com.hieuminh.scoresapp.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.dapter.MyScoreHistoryAdapter;
import com.hieuminh.scoresapp.model.Records;

public class ScoreHistoryDetailFragment extends Fragment {

    private RecyclerView rv_ScoreHistory;
    private TextView tv_match_total;
    private TextView tv_player_score_totals[] = new TextView[4];
    private Button bt_player_names[] = new Button[4];
    private Records record;
    private MyScoreHistoryAdapter myScoreHistoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score_history_detail, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectView(view);
        if(getArguments() != null) {
            ScoreHistoryDetailFragmentArgs args = ScoreHistoryDetailFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
            tv_match_total.setText("" + record.matchTotal);
            for(int i = 0; i < 4; i++) {
                bt_player_names[i].setText(record.players.get(i));
                tv_player_score_totals[i].setText("" + record.playersMap.get(record.players.get(i)));
            }
            rv_ScoreHistory.setHasFixedSize(true);
            rv_ScoreHistory.setLayoutManager(new LinearLayoutManager(null));
            myScoreHistoryAdapter = new MyScoreHistoryAdapter(record.scoresMatrix);
            rv_ScoreHistory.setAdapter(myScoreHistoryAdapter);
        }
    }

    private void connectView(View view) {
        rv_ScoreHistory = view.findViewById(R.id.rv_score_history_scores_list);
        tv_match_total = view.findViewById(R.id.tv_score_history_match_total);

        tv_player_score_totals[0] = view.findViewById(R.id.tv_score_history_player_score_total_0);
        tv_player_score_totals[1] = view.findViewById(R.id.tv_score_history_player_score_total_1);
        tv_player_score_totals[2] = view.findViewById(R.id.tv_score_history_player_score_total_2);
        tv_player_score_totals[3] = view.findViewById(R.id.tv_score_history_player_score_total_3);

        bt_player_names[0] = view.findViewById(R.id.bt_score_history_player_name_0);
        bt_player_names[1] = view.findViewById(R.id.bt_score_history_player_name_1);
        bt_player_names[2] = view.findViewById(R.id.bt_score_history_player_name_2);
        bt_player_names[3] = view.findViewById(R.id.bt_score_history_player_name_3);
    }
}