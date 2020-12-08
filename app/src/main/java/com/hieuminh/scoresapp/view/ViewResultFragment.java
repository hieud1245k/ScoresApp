package com.hieuminh.scoresapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.Records;

public class ViewResultFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private Records record;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);
        if(getArguments() != null) {
            AddFragmentArgs args = AddFragmentArgs.fromBundle(getArguments());
            record = args.getRecord();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view_result,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_caculate_result) {

        }
        return super.onOptionsItemSelected(item);
    }

    public void viewConnection(View view) {
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