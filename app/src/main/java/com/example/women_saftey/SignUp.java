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

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText name;
    EditText password;
    EditText repassword;
    EditText number;
    Button signup;
    FirebaseAuth mAuth;
    TextView signinbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        email=findViewById(R.id.signup_mail);
        name=findViewById(R.id.signup_name);
        number=findViewById(R.id.signup_number);
        password=findViewById(R.id.signup_password);
        repassword=findViewById(R.id.signup_repassword);
        signup=findViewById(R.id.signup_btn);
        signinbtn=findViewById(R.id.signin_btn1);

        mAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();
            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,SignIn.class));
            }
        });
    }
    private void createuser(){
        String Email = email.getText().toString();
        String Password= password.getText().toString();
        String Repassword= repassword.getText().toString();
        String Number = number.getText().toString();
        if(TextUtils.isEmpty(Email)){
            email.setError("Email can't be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(Password)){
            password.setError("Password can't be empty");
            password.requestFocus();
        }
        else if(TextUtils.isEmpty(Number)){
            number.setError("Number can't be empty");
            number.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this,SignIn.class));
                    }
                    else{
                        Toast.makeText(SignUp.this, "Unsuccessful Registration", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}