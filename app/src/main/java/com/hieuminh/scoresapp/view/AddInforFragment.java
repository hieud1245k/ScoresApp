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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hieuminh.scoresapp.R;
import com.hieuminh.scoresapp.model.Records;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddInforFragment extends Fragment {

    private Button clear, next;
    private EditText[] playersName = new EditText[4];
    private Records record = new Records();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_infor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);
    }

    private void viewConnection(View view) {
        clear = view.findViewById(R.id.bt_clean_add_infor);
        next = view.findViewById(R.id.bt_next_add_infor);

        playersName[0] = view.findViewById(R.id.tv_name_add_player_name_0);
        playersName[1] = view.findViewById(R.id.tv_name_add_player_name_1);
        playersName[2] = view.findViewById(R.id.tv_name_add_player_name_2);
        playersName[3] = view.findViewById(R.id.tv_name_add_player_name_3);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(EditText p : playersName) {
                    p.setText("");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < playersName.length ; i++) {
                    if(playersName[i].getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Please enter name of player " + (i + 1), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                for(int i = 0; i < playersName.length;  i++) {
                    record.players.add(playersName[i].getText().toString());
                }

                NavDirections action = AddInforFragmentDirections.actionAddInforFragmentToAddFragment(record);
                Navigation.findNavController(view).navigate(action);
            }
        });
    }
}