package com.hieuminh.scoresapp.view;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.MatchScores;
import com.hieuminh.scoresapp.model.Records;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CountDownFragment extends Fragment {

    private Button countdown;
    private Records record;
    private int count;
    private TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_count_down, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            CountDownFragmentArgs args = CountDownFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
        }
        count = 10;
        viewConnection(view);
    }

    public void viewConnection(View view) {
        countdown = view.findViewById(R.id.bt_count_down);
        title = view.findViewById(R.id.tv_count_down_title);

        countdown.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                new CountDownTimer(11000, 1000) {
                    @Override
                    public void onTick(long l) {
                        countdown.setText("" + count);
                        count--;
                    }

                    @Override
                    public void onFinish() {
                        NavDirections action = CountDownFragmentDirections.actionCountDownFragmentToWinFragment(record);
                        Navigation.findNavController(getView()).navigate(action);
                    }
                }.start();
                scoresCalculator();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scoresCalculator() {
        int[] scores = new int[4];
        for (MatchScores m : record.scoresMatrix) {
            for (int i = 0; i < 4; i++) {
                scores[i] += m.marks[i];
            }
        }
        for (int i = 0; i < 4; i++) {
            record.playersMap.put(i, scores[i]);
        }
        record.playersMap = sortByComparator(record.playersMap, false);
    }

    private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}