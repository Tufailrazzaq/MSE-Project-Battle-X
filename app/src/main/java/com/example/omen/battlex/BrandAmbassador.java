package com.example.omen.battlex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BrandAmbassador extends Fragment {

    private EditText emailTextBox, passwordTextBox;
    private FirebaseAuth mAuth;
    private Button loginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_ambassadors, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        emailTextBox = (EditText) getActivity().findViewById(R.id.ambassador_emailTextbox);
        passwordTextBox = (EditText) getActivity().findViewById(R.id.ambassador_passwordTextbox);
        loginBtn = (Button) getActivity().findViewById(R.id.brandAmbassadorLoginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });

    }

    public void validateLogin(){
        if(emailTextBox.getText().toString().isEmpty() || passwordTextBox.getText().toString().isEmpty()){
            Snackbar.make(getView(),"Email and Password is Required",Snackbar.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailTextBox.getText().toString(), passwordTextBox.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(getView(), "Login Successful.",
                                    Snackbar.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getActivity(),AmbassadorListView.class);
                            intent.putExtra("uid",user.getUid());
                            startActivity(intent);
                        } else {
                            Snackbar.make(getView(), "Authentication failed.",
                                    Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
