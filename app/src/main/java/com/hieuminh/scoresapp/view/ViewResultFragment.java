package com.hieuminh.scoresapp.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.dapter.MyScoreInViewResultAdapter;
import com.hieuminh.scoresapp.model.Records;

public class ViewResultFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private Records record;
    private Button playersName[] = new Button[4];
    private Button match;
    private MyScoreInViewResultAdapter myScoreInViewResultAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_result, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);
        if (getArguments() != null) {
            ViewResultFragmentArgs args = ViewResultFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
        }

        for (int i = 0; i < playersName.length; i++) {
            playersName[i].setText(record.players[i]);
        }

        myScoreInViewResultAdapter = new MyScoreInViewResultAdapter(record.scoresMatrix);
        recyclerView.setAdapter(myScoreInViewResultAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view_result, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_caculate_result) {
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
            b.setTitle("Notice!");
            b.setMessage("Are you sure that you want to end game?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    NavDirections action = ViewResultFragmentDirections.actionViewResultFragmentToCountDownFragment(record);
                    Navigation.findNavController(getView()).navigate(action);
                }
            });
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog al = b.create();
            al.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewConnection(View view) {
        playersName[0] = view.findViewById(R.id.bt_view_result_player_name_0);
        playersName[1] = view.findViewById(R.id.bt_view_result_player_name_1);
        playersName[2] = view.findViewById(R.id.bt_view_result_player_name_2);
        playersName[3] = view.findViewById(R.id.bt_view_result_player_name_3);

        recyclerView = view.findViewById(R.id.rv_view_result_items_list);

        match = view.findViewById(R.id.bt_view_result_match);

        floatingActionButton = view.findViewById(R.id.fab_view_result_add_score);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ViewResultFragmentDirections.actionViewResultFragmentToAddFragment(record);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}