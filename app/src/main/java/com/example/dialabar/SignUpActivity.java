package com.example.dialabar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText username,useremail,userpassword,confirmpassword ;
    Button regbutton ;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.editName);
        useremail = findViewById(R.id.editEmail);
        userpassword=findViewById(R.id.editPass);
        confirmpassword=findViewById(R.id.confirmPass);
        regbutton=findViewById(R.id.buttonAcount);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),LoginUpActivity.class));
            finish();
        }

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString().trim();
                String uemail = useremail.getText().toString().trim();
                String upassword = userpassword.getText().toString().trim();
                String conpassword = confirmpassword.getText().toString().trim();
                if(TextUtils.isEmpty(uemail)){
                    useremail.setError("Email is required");

                }
                if(TextUtils.isEmpty(uname)){
                    username.setError("Name is required");
                }
                if(TextUtils.isEmpty(upassword)){
                    userpassword.setError("Please enter password");

                }
                if(upassword.length() < 6){
                    userpassword.setError("Password must be more then 6 characters");
                    return;

                }
                if(TextUtils.isEmpty(conpassword)){
                    confirmpassword.setError("Please enter password");

                }
                if(!upassword.equals(conpassword)){
                    confirmpassword.setError("Please enter same password");

                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(uemail, upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginUpActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
