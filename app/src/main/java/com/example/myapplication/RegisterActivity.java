package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {

    EditText etname,etemail,etphone,etpassword,etcpassword;
    Button bt;
    TextView tv;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etname = findViewById(R.id.BillName);
        etemail = findViewById(R.id.editTextEmail);
        etphone=findViewById(R.id.BillNo);
        etpassword = findViewById(R.id.BillAmount);
        etcpassword = findViewById(R.id.PasswordToPay);

        bt = findViewById(R.id.buttoncreate);
        tv = findViewById(R.id.textViewForLogin);

        auth=FirebaseAuth.getInstance();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,loginActivity.class));
            }
        });

       bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString();
                String email = etemail.getText().toString();
                String phone = etphone.getText().toString();
                String password = etpassword.getText().toString();
                String cpassword = etcpassword.getText().toString();
                Database db = new Database(getApplicationContext(),"myapplication",null,1);
                if(name.length()==0 || email.length()==0 || phone.length()==0 || password.length()==0 || cpassword.length()==0){
                    Toast.makeText(getApplicationContext(), "Kindly fill \nAll Details ", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(cpassword)) {
                        //registerUser(email , password);
                        Toast.makeText(getApplicationContext(), "AccountCreated\nPlease Login", Toast.LENGTH_SHORT).show();
                        db.register(name, email, phone ,password);
                        startActivity(new Intent(RegisterActivity.this, loginActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords doesn't MATCH", Toast.LENGTH_SHORT).show();
                         }
                    }
                }

        });



    }

    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error while Registering", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
