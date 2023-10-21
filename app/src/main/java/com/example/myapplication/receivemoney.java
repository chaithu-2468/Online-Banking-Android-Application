package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class receivemoney extends AppCompatActivity {

    TextView tv;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivemoney);

        ed=findViewById(R.id.upiText);
        String useremail = getIntent().getStringExtra("username");
        ed.setText(useremail +"@oksbi");
        tv=findViewById(R.id.backtext);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(receivemoney.this , HomeActivity.class));
            }
        });

    }
}