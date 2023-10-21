package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username" , " ").toString();
        Toast.makeText(getApplicationContext(),"Welcome " + username,Toast.LENGTH_SHORT).show();

        CardView more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                startActivity(new Intent(HomeActivity.this , moreoptions.class));
            }
        });

        CardView bills = findViewById(R.id.bills);
        bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this , com.example.myapplication.bills.class));
            }
        });

        CardView receive = findViewById(R.id.receiveMoney);
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = getIntent().getStringExtra("username");
                Intent intent = new Intent(HomeActivity.this, receivemoney.class);
                intent.putExtra("username", useremail);
                startActivity(intent);
                //startActivity(new Intent(HomeActivity.this , com.example.myapplication.receivemoney.class));
            }
        });

        CardView send = findViewById(R.id.sendMoney);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = getIntent().getStringExtra("username");
                Intent intent = new Intent(HomeActivity.this, moneysend.class);
                intent.putExtra("username", useremail);
                startActivity(intent);
                //startActivity(new Intent(HomeActivity.this , com.example.myapplication.moneysend.class));
            }
        });

        CardView balance = findViewById(R.id.bankBalance);
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(HomeActivity.this , com.example.myapplication.moneysend.class));

                Database db = new Database(getApplicationContext(),"myapplication",null,1);
                String useremail = getIntent().getStringExtra("username");
                int bal = db.displaybalance(useremail);
                Toast.makeText(getApplicationContext(), "Balance:  "+ bal, Toast.LENGTH_SHORT).show();
            }
        });


        /*CardView history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = getIntent().getStringExtra("username");
                Intent intent = new Intent(HomeActivity.this, transcationHistory.class);
                intent.putExtra("username", useremail);
                startActivity(intent);
               // startActivity(new Intent(HomeActivity.this , com.example.myapplication.transcationHistory.class));
            }
        });*/
    }
}

