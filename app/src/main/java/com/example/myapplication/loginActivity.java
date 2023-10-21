package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    EditText eduserid , edpassword ;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eduserid = findViewById(R.id.editTextLoginUserID);
        edpassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.Loginbutton);
        tv = findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = eduserid.getText().toString();
                String password = edpassword.getText().toString();

                Database db = new Database(getApplicationContext(),"myapplication",null,1);

                if (userid.length() == 0 || password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fill All the Details", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(userid, password) == 1) {
                        Toast.makeText(getApplicationContext(), "logging IN ", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences = getSharedPreferences("Shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor  = sharedpreferences.edit();
                        editor.putString("username" , userid);
                        editor.apply();

                        Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                        intent.putExtra("username", userid);
                        startActivity(intent);


                        //startActivity(new Intent(loginActivity.this,HomeActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "INVALID Details ", Toast.LENGTH_SHORT).show();
                    }





                }
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,RegisterActivity.class));
            }
        });
    }
}