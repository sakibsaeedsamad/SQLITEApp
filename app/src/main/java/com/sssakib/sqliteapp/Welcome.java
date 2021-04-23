package com.sssakib.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
    TextView nameTV,mobileTV,emailTV,addressTV;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    String mobile="";
    ArrayList<String> list=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        mobile = intent.getExtras().getString("mobile");

        list = databaseHelper.getUser(mobile);

        String name= list.get(0);
        String mobile= list.get(1);
        String email= list.get(2);
        String address= list.get(3);

        nameTV = findViewById(R.id.nameTV);
        mobileTV = findViewById(R.id.mobileTV);
        emailTV = findViewById(R.id.emailTV);
        addressTV = findViewById(R.id.addressTV);

        nameTV.setText(name);
        mobileTV.setText(mobile);
        emailTV.setText(email);
        addressTV.setText(address);


    }



}