package com.example.souqcom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Login<auth> extends AppCompatActivity {
    Button login, create;
    EditText Epass, Euser;
    private ProgressDialog mloading;



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        Epass = findViewById(R.id.Epass);
        Euser = findViewById(R.id.Euser);
        create = findViewById(R.id.Bcreate);
        mAuth = FirebaseAuth.getInstance();
        mloading=new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Euser.getText().toString().trim();
                String password = Epass.getText().toString().trim();
                if(email.isEmpty())
                {
                    Euser.setError("Email is empty");
                    Euser.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Euser.setError("Enter the valid email");
                    Euser.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    Epass.setError("Password is empty");
                    Epass.requestFocus();
                    return;
                }
                if(Epass.length()<6)
                {
                    Epass.setError("Length of password is more than 6");
                    Epass.requestFocus();
                    return;
                }


                mloading.setTitle("Entering");
                mloading.setMessage("Please wait,check your Email");
                mloading.setCanceledOnTouchOutside(false);
                mloading.show();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            getSharedPreferences("login", MODE_PRIVATE)
                                    .edit()
                                    .putBoolean("isLogin", true)
                                    .apply();
                            gotoLogin();

                        } else {
                            Toast.makeText(Login.this, "Your Email or Password Incorrect! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        create.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent homee = new Intent(Login.this, user_data.class);
                startActivity(homee);
                finish();
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
        boolean isLogin = getSharedPreferences("login", MODE_PRIVATE).getBoolean("isLogin", false);
        if (isLogin) {
            gotoLogin();
        }
    }
    void gotoLogin() {
        Intent home = new Intent(Login.this, Gategory.class);
        home.setFlags(home.FLAG_ACTIVITY_CLEAR_TASK | home.FLAG_ACTIVITY_NEW_TASK);
        startActivity(home);
        finish();
    }


}



