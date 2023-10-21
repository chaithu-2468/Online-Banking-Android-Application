package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class transcationHistory extends AppCompatActivity {

    private ListView transactionListView;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_history);

        transactionListView = findViewById(R.id.transactionListView);
        String useremail = getIntent().getStringExtra("username");

        dbHelper = new Database(this ,"myapplication" ,null,1); // Initialize your database helper
        Cursor cursor = dbHelper.getdata(useremail); // Retrieve the transaction data using your method

        // Define the columns to be displayed
        String[] fromColumns = {"phone", "type", "amount"};

        // Define the view IDs to populate data into
        int[] toViews = {R.id.phoneTextView, R.id.typeTextView, R.id.amountTextView};

        // Create a SimpleCursorAdapter to display the data
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.transaction_list_item, // Layout for each list item
                cursor,
                fromColumns,
                toViews,
                0); // Flags (0 here)

        transactionListView.setAdapter(adapter);


    }
}