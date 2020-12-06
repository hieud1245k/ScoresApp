package com.hieuminh.scoresapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hieuminh.scoresapp.R;

public class LoginFragment extends Fragment {

    private Button login, adapterRegistration;
    private EditText et_gmail, et_password;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private AppCompatCheckBox checkbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewConnection(view);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        adapterRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = LoginFragmentDirections.actionLoginFragment2ToRegisterFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gmail = et_gmail.getText().toString();
                String password = et_password.getText().toString();
                if(gmail.matches("\\s+")) {
                    Toast.makeText(getActivity(),"Gmail is not blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.matches("\\s+")) {
                    Toast.makeText(getActivity(),"Password is not blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AllowUserToLogin(gmail,password,view);
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void viewConnection(View view) {
        login = view.findViewById(R.id.bt_login);
        adapterRegistration = view.findViewById(R.id.bt_adapter_registration);
        et_gmail = view.findViewById(R.id.tv_gmail_login);
        et_password = view.findViewById(R.id.tv_password_login);
        checkbox = view.findViewById(R.id.checkbox);
    }

    private void AllowUserToLogin(String gmail,String password,final View view) {
        mAuth.signInWithEmailAndPassword(gmail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            NavDirections action = LoginFragmentDirections.actionLoginFragment2ToListFragment();
                            Navigation.findNavController(view).navigate(action);
                        } else {
                            Toast.makeText(getActivity(),"Login failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
