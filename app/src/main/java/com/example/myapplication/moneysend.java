package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class moneysend extends AppCompatActivity {
    Button send , back;

    EditText mobno , amnt , pi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneysend);

        mobno=findViewById(R.id.mobileno);
        amnt=findViewById(R.id.amount);
        pi=findViewById(R.id.pin);

        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fromphone=getIntent().getStringExtra("username");
                //String fromPhone=getIntent().getStringExtra("userid");
                String tophone = mobno.getText().toString();
                String Amount = amnt.getText().toString();
                String type = "SEND";
                String pin = pi.getText().toString();
                int amount = 0;
                try {
                     amount =Integer.parseInt(Amount);
                }
                catch (NumberFormatException e){
                    //hello
                }
                Database db = new Database(getApplicationContext(),"myapplication",null,1);
                if(db.pinvalidation(pin , fromphone) == 1){
                    db.addTranscation(fromphone , type , amount ,tophone);
                    startActivity(new Intent(moneysend.this,paymentsuccess.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "INVALID PIN ", Toast.LENGTH_SHORT).show();
                }
                /*db.addTranscation(fromphone , type , amount ,tophone);
                startActivity(new Intent(moneysend.this,paymentsuccess.class));*/
            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(moneysend.this , HomeActivity.class));
            }
        });
    }
}