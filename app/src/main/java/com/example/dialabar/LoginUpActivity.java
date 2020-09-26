package com.example.dialabar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUpActivity extends AppCompatActivity {
    TextView regLink ,useremail,userpassword;
    Button btnlogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_up);
        regLink = findViewById(R.id.createnewac);
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginUpActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });

        useremail = findViewById(R.id.loginemail);
        userpassword =findViewById(R.id.loginpassword);
        progressBar= findViewById(R.id.progressBar);
        fAuth= FirebaseAuth.getInstance();
        btnlogin =findViewById(R.id.buttonlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uemail = useremail.getText().toString().trim();
                String upassword = userpassword.getText().toString().trim();

                if(TextUtils.isEmpty(uemail)){
                    useremail.setError("Email is required");
                }

                if(TextUtils.isEmpty(upassword)){
                    userpassword.setError("Please enter password");
                }

                if(upassword.length() < 6){
                    userpassword.setError("Password must be more then 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(uemail,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginUpActivity.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                    }
                });

            }
        });

    }
}