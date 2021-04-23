package com.sssakib.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    TextView textView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView.findViewById(R.id.textView);
        String mobileFromMain = getIntent().getStringExtra("MOBILE");
        textView.setText("Welcome "+ mobileFromMain);


    }
}