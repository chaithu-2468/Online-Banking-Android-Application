package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class bills extends AppCompatActivity {

    TextView tv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);


        tv=findViewById(R.id.backtext);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bills.this , HomeActivity.class));
            }
        });


        CardView rechargeBill = findViewById(R.id.rechargebill);
        rechargeBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "Recharge Bills");
                startActivity(it);
            }
        });

        CardView electricityBill = findViewById(R.id.electricitybill);
        electricityBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "Electricity Bills");
                startActivity(it);
            }
        });

        CardView cylinderBill = findViewById(R.id.cylinderbill);
        cylinderBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "LPG Cylinder Bills");
                startActivity(it);
            }
        });

        CardView waterBill = findViewById(R.id.waterbill);
        waterBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "Water Bills");
                startActivity(it);
            }
        });

        CardView dishBill = findViewById(R.id.dishbill);
        dishBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "DTH Cable Bills");
                startActivity(it);
            }
        });

        CardView policyBill = findViewById(R.id.policybill);
        policyBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(bills.this,billdetails.class);
                it.putExtra("title" , "Insurance Bills");
                startActivity(it);
            }
        });

    }
}