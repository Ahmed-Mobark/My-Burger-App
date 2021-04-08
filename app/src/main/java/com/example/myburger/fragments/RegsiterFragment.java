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

public class RegsiterFragment extends Fragment {

    TextView gotoLogin_tv;
    MaterialButton signUpBtn;
    TextInputEditText nameEt;
    TextInputEditText emailEt;
    TextInputEditText passwordEt;
    ProgressDialog dialog;

    private FirebaseAuth mAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_regsiter, container, false);

        gotoLogin_tv=view.findViewById(R.id.go_signUp_tv);
        nameEt=view.findViewById(R.id.name_et);
        emailEt=view.findViewById(R.id.email_et);
        passwordEt=view.findViewById(R.id.password_et);
        signUpBtn=view.findViewById(R.id.SignUp_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(requireContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        setUpClickListeners();

    }
    private void setUpClickListeners() {

        gotoLogin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_regsiterFragment_to_profileFragment);
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                String name = nameEt.getText().toString();
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                dialog.show();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) requireContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {dialog.dismiss();
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {dialog.dismiss();
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(requireContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });

            }
        });
    }
}