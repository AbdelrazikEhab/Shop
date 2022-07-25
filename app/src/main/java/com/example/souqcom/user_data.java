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

public class user_data extends AppCompatActivity {



    Button create;
    TextView name,adress,pass;
    EditText Ename,Eadress,Epass;
    private ProgressDialog mloading;
    FirebaseAuth mAuth;
    String getname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
//        name = findViewById(R.id.namee);
//        adress = findViewById(R.id.adress);
//        pass = findViewById(R.id.pass);
        Ename = findViewById(R.id.Ename);
        Eadress = findViewById(R.id.Eadrss);
        Epass = findViewById(R.id.Epass);
        create = findViewById(R.id.create);
        mAuth=FirebaseAuth.getInstance();
        mloading=new ProgressDialog(this);



        create.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                 getname = Ename.getText().toString().trim();
                String getadress = Eadress.getText().toString().trim();
                String getpassword = Epass.getText().toString().trim();
                if(getname.isEmpty())
                {
                    Ename.setError("Email is empty");
                    Ename.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(getname).matches())
                {
                    Ename.setError("Enter the valid email address");
                    Ename.requestFocus();
                    return;
                }
                if(getpassword.isEmpty())
                {
                    Epass.setError("Enter the password");
                    Epass.requestFocus();
                    return;
                }
                if(getpassword.length()<6)
                {
                    Epass.setError("Length of the password should be more than 6");
                    Epass.requestFocus();
                    return;
                }
                mloading.setTitle("Regester");
                mloading.setMessage("Please wait,check your Email");
                mloading.setCanceledOnTouchOutside(false);
                mloading.show();

                mAuth.createUserWithEmailAndPassword(getname, getpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(user_data.this, "You are successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(user_data.this, mobile_product.class);
                            home.setFlags(home.FLAG_ACTIVITY_CLEAR_TASK | home.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(home);


                        } else {
                            Toast.makeText(user_data.this, "You are not Registered! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });








    }


}
