package com.example.myburger.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myburger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {
    TextView go_signUp_tv;

    MaterialButton loginBtn;
    TextInputEditText emailEt;
    TextInputEditText passwordEt;
    ProgressDialog dialog;
    EditText email_et;
    EditText password_et;
    Button sign_btn;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        go_signUp_tv=view.findViewById(R.id.go_signUp_tv);
        emailEt = view.findViewById(R.id.email_et);
        passwordEt = view.findViewById(R.id.password_et);
        loginBtn = view.findViewById(R.id.sign_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        go_signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_regsiterFragment);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


}