package com.hieuminh.scoresapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

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
import com.hieuminh.scoresapp.utils.SessionUtil;

public class LoginFragment extends Fragment {

    private Button login, adapterRegistration;
    private EditText et_gmail, et_password;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private AppCompatCheckBox checkBox_showPassword, checkBox_rememberMe;

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
                if(gmail.equals("")) {
                    Toast.makeText(getActivity(),"Gmail is not blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals("")) {
                    Toast.makeText(getActivity(),"Password is not blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AllowUserToLogin(gmail,password,view);
            }
        });

        checkBox_showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        checkBox_rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()) {
                    SharedPreferences preferences = getContext().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(getContext(), "Checked", Toast.LENGTH_SHORT).show();
                } else if(!compoundButton.isChecked()) {
                    SharedPreferences preferences = getContext().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(getContext(), "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SharedPreferences preferences = getContext().getSharedPreferences("checkbox", Context.MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")) {
            NavDirections action = LoginFragmentDirections.actionLoginFragment2ToListFragment();
            Navigation.findNavController(view).navigate(action);
        } else if(checkbox.equals("false")) {
            Toast.makeText(getActivity(),"Please sign in!", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewConnection(View view) {
        login = view.findViewById(R.id.bt_login);
        adapterRegistration = view.findViewById(R.id.bt_adapter_registration);
        et_gmail = view.findViewById(R.id.tv_gmail_login);
        et_password = view.findViewById(R.id.tv_password_login);
        checkBox_showPassword = view.findViewById(R.id.checkbox_show_password);
        checkBox_rememberMe = view.findViewById(R.id.checkbox_remember_me);
    }

    private void AllowUserToLogin(String gmail,String password,final View view) {
        mAuth.signInWithEmailAndPassword(gmail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();
                            new SessionUtil(getActivity()).setUserId(userId);
                            NavDirections action = LoginFragmentDirections.actionLoginFragment2ToListFragment();
                            Navigation.findNavController(view).navigate(action);
                        } else {
                            Toast.makeText(getActivity(),"Gmail or password is incorrect!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
