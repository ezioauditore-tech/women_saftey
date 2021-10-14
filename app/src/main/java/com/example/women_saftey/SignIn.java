package com.example.women_saftey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button signin;
    EditText mail;
    EditText password;
    TextView signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        mAuth=FirebaseAuth.getInstance();
        signin=findViewById(R.id.signin_btn);
        mail=findViewById(R.id.signin_email);
        password=findViewById(R.id.signin_password);
        signupbtn=findViewById(R.id.signup_btn1);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
            }
        });

    }
    private void loginuser(){
        String Email = mail.getText().toString();
        String Password= password.getText().toString();
        if(TextUtils.isEmpty(Email)){
            mail.setError("Email can't be empty");
            mail.requestFocus();
        }
        else if(TextUtils.isEmpty(Password)){
            password.setError("Password can't be empty");
            password.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(SignIn.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}