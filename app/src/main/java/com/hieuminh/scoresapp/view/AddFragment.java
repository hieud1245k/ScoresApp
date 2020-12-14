package com.hieuminh.scoresapp.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.MatchScores;
import com.hieuminh.scoresapp.model.Records;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {

    private Button reset, save;
    private TextView playersName[] = new TextView[4];
    private TextView match;
    private Records record;
    private LinearLayout ll_add_scores[] = new LinearLayout[4];
    private LinearLayout ll_add_score_feature;
    private TextView playersScore[] = new TextView[4];
    private List<Integer> indexs = new ArrayList<>();
    private Animation topAnim, bottomAnim, rightAnim, leftAnim;

    private int mark = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mark = 3;
        viewConnection(view);
        animation();
        if (getArguments() != null) {
            AddFragmentArgs args = AddFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
            for (int i = 0; i < playersName.length; i++) {
                playersName[i].setText(record.players[i]);
            }
            match.setText("Match: " + record.scoresMap.size());
        }

    }

    private void viewConnection(View view) {
        playersName[0] = view.findViewById(R.id.tv_add_score_player_name_0);
        playersName[1] = view.findViewById(R.id.tv_add_score_player_name_1);
        playersName[2] = view.findViewById(R.id.tv_add_score_player_name_2);
        playersName[3] = view.findViewById(R.id.tv_add_score_player_name_3);

        playersScore[0] = view.findViewById(R.id.tv_add_score_0);
        playersScore[1] = view.findViewById(R.id.tv_add_score_1);
        playersScore[2] = view.findViewById(R.id.tv_add_score_2);
        playersScore[3] = view.findViewById(R.id.tv_add_score_3);

        ll_add_scores[0] = view.findViewById(R.id.ll_add_score_0);
        ll_add_scores[1] = view.findViewById(R.id.ll_add_score_1);
        ll_add_scores[2] = view.findViewById(R.id.ll_add_score_2);
        ll_add_scores[3] = view.findViewById(R.id.ll_add_score_3);

        match = view.findViewById(R.id.tv_add_score_match);
        ll_add_score_feature = view.findViewById(R.id.ll_add_score_feature);

        save = view.findViewById(R.id.bt_add_score_save);
        reset = view.findViewById(R.id.bt_add_score_reset);

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                int markSum = 0;
                int[] marks = new int[4];
                for(int i = 0; i < marks.length; i ++) {
                    marks[i] = Integer.parseInt(playersScore[i].getText().toString());
                    markSum += marks[i];
                }
                if(markSum != 6) {
                    Toast.makeText(getActivity(), "You have not finished entering points!\nThe player's total score must be 6!", Toast.LENGTH_LONG).show();
                    return;
                }
//                record.scoresMatrix.add(new MatchScores(marks));
                MatchScores matchScore = new MatchScores(marks);
                record.scoresMap.put("" + matchScore.id, matchScore);
                reset();
                match.setText("Match: " + record.scoresMap.size());
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        for (int i = 0; i < ll_add_scores.length; i++) {
            final int index = i;
            ll_add_scores[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll_add_scores[index].setEnabled(false);
                    indexs.add(index);
                    mark--;
                    for (int j = 0; j < playersScore.length; j++) {
                        if (!checkIdExistInList(indexs,j)) {
                            playersScore[j].setText("" + mark);
                        }
                    }
                }
            });
        }
    }

    public void animation() {
        topAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.top_animation);
        leftAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.left_animation);
        rightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.right_animation);
        bottomAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_animation);

        ll_add_scores[0].setAnimation(leftAnim);
        ll_add_scores[3].setAnimation(leftAnim);

        ll_add_scores[1].setAnimation(rightAnim);
        ll_add_scores[2].setAnimation(rightAnim);
    }

    public boolean checkIdExistInList(List<Integer> list, int i) {
        for (Integer index : list)
            if (i == index)
                return true;
        return false;
    }

    public void reset() {
        mark = 3;
        for (int i = 0; i < playersScore.length; i++) {
            playersScore[i].setText("" + mark);
            ll_add_scores[i].setEnabled(true);
            indexs.removeAll(indexs);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_views) {
            NavDirections action = AddFragmentDirections.actionAddFragmentToViewResultFragment(record);
            Navigation.findNavController(getView()).navigate(action);
        }
        return super.onOptionsItemSelected(item);
    }
}