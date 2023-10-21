package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class paymentActivity extends AppCompatActivity {

    EditText ed1 ,ed2 ,ed3 , ed4 ;
    Button bt , prcd;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        bt=findViewById(R.id.ExitButton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(paymentActivity.this , bills.class));
            }
        });

        tv1=findViewById(R.id.paymenttitle);
        ed1=findViewById(R.id.BillName);
        ed2=findViewById(R.id.BillNo);
        ed3=findViewById(R.id.BillAmount);
        ed4=findViewById(R.id.PasswordToPay);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String name = it.getStringExtra("text2");
        String num = it.getStringExtra("text3");
        String amount = it.getStringExtra("text4");

        tv1.setText(title);
        ed1.setText(name);
        ed2.setText(num);
        ed3.setText(amount+"/-");

        prcd=findViewById(R.id.ProceedButton);
        String pin = prcd.getText().toString();
        prcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(paymentActivity.this, paymentsuccess.class));
                Toast.makeText(getApplicationContext(), "Payment success ", Toast.LENGTH_SHORT).show();

            }
        });

    }
}