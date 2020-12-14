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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.Records;

public class WinFragment extends Fragment {

    private TextView title;
    private TextView playersScore[] = new TextView[4];
    private TextView playersName[] = new TextView[4];
    private LinearLayout ll_players[] = new LinearLayout[4];
    private Animation topAnim, bottomAnim , bottomAnimSlow;
    private Records record;
    private Button returnMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_win, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);
        setAnimation();
        if(getArguments() != null) {
            WinFragmentArgs args = WinFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
        }
        for(int i = 0; i < 4; i++) {
            playersName[i].setText("" + record.playersMap.keySet().toArray()[i]);
            playersScore[i].setText("" + record.playersMap.values().toArray()[i]);
        }

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = WinFragmentDirections.actionWinFragmentToListFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    public void viewConnection(View view) {
        ll_players[0] = view.findViewById(R.id.ll_win_player_0);
        ll_players[1] = view.findViewById(R.id.ll_win_player_1);
        ll_players[2] = view.findViewById(R.id.ll_win_player_2);
        ll_players[3] = view.findViewById(R.id.ll_win_player_3);

        playersName[0] = view.findViewById(R.id.tv_win_player_0);
        playersName[1] = view.findViewById(R.id.tv_win_player_1);
        playersName[2] = view.findViewById(R.id.tv_win_player_2);
        playersName[3] = view.findViewById(R.id.tv_win_player_3);

        playersScore[0] = view.findViewById(R.id.tv_win_player_score_0);
        playersScore[1] = view.findViewById(R.id.tv_win_player_score_1);
        playersScore[2] = view.findViewById(R.id.tv_win_player_score_2);
        playersScore[3] = view.findViewById(R.id.tv_win_player_score_3);

        returnMain = view.findViewById(R.id.bt_win_return_main);

        title = view.findViewById(R.id.tv_win_title);
    }

    public void setAnimation() {
        topAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_animation);
        bottomAnimSlow = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_animation_slow);

        title.setAnimation(topAnim);

        for(LinearLayout l : ll_players) {
            l.setAnimation(bottomAnim);
        }

        returnMain.setAnimation(bottomAnimSlow);
    }
}