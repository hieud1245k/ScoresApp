package com.hieuminh.scoresapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.internal.au;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hieuminh.scoresapp.R;

public class RegisterFragment extends Fragment {

    private Button adapterLogin, register;
    private EditText et_email, et_password, et_confirmPassword;

    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);

        auth = FirebaseAuth.getInstance();

        adapterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment2();
                Navigation.findNavController(view).navigate(action);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String confirmPassword = et_confirmPassword.getText().toString().trim();
                if (password.equals(confirmPassword)) {
                    registerUser(email, password,view);
                    return;
                }
            }
        });
    }

    private void viewConnection(View view) {
        adapterLogin = view.findViewById(R.id.bt_adapter_login);
        register = view.findViewById(R.id.bt_register);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_confirmPassword = view.findViewById(R.id.et_confirm_password);
    }

    private void registerUser(String email, String password, final View view) {
        try {
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getActivity(),"Register successful!",Toast.LENGTH_SHORT).show();
                                NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToListFragment();
                                Navigation.findNavController(view).navigate(action);
                            }
                            else {
                                Toast.makeText(getActivity(),"Register failed!",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}