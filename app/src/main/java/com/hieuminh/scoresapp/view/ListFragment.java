package com.hieuminh.scoresapp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.dapter.MyScoreAdapter;
import com.hieuminh.scoresapp.model.Records;
import com.hieuminh.scoresapp.utils.SessionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListFragment extends Fragment {

    private List<Records> records;
    private RecyclerView recyclerView;
    private MyScoreAdapter myScoreAdapter;
    private DatabaseReference mData;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mData = FirebaseDatabase.getInstance().getReference();
        records = new ArrayList<>();
        viewConnection(view);

        String userId = new SessionUtil(getActivity()).getUserId();

        mData.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Records record =   ds.getValue(Records.class);
                    records.add(record);
                }
                Collections.reverse(records);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(null));
                myScoreAdapter = new MyScoreAdapter(records);
                recyclerView.setAdapter(myScoreAdapter);
                myScoreAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = ListFragmentDirections.actionListFragmentToAddInforFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private void viewConnection(View view) {
        recyclerView = view.findViewById(R.id.rv_score);
        imageView = view.findViewById(R.id.iv_add);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout) {

            AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
            b.setTitle("Notice!");
            b.setMessage("Are you sure that you want to log out?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences preferences = getContext().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();

                    NavDirections action = ListFragmentDirections.actionListFragmentToLoginFragment2();
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
}