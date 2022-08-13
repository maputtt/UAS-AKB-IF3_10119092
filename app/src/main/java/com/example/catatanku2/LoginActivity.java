package com.example.catatanku2;

//Mahfudz Abdulloh
//10119092
//IF3

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText inputEmail, inputPassword;
    private Button  btnSignIn;
    private TextView btnSignUp,btnForgotPass;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail  = (EditText) findViewById(R.id.email);
        inputPassword = (EditText)  findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (TextView) findViewById(R.id.sign_up_button);
        btnForgotPass =(TextView) findViewById(R.id.forgotPassword);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    inputEmail.setError("Email Harus Diisi!");
                }else if(TextUtils.isEmpty(password)){
                    inputPassword.setError("Password Harus Diisi!");
                }else if (password.length() < 6){
                    inputPassword.setError("Password minimal 6 karakter!");
                }else{
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Sign In Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            }else {
                                Toast.makeText(LoginActivity.this, "Sign In Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.catatanku2.RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.catatanku2.ForgotPassActivity.class);
                startActivity(intent);
            }
        });
    }
}