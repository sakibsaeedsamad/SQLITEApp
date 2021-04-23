package com.sssakib.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;


    private EditText mobileLogin, passwordLogin;
    private Button loginButton;
    private TextView registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        mobileLogin = findViewById(R.id.mobileLogIn);
        passwordLogin = findViewById(R.id.passwordLogIn);

        loginButton = findViewById(R.id.logInButton);
        registerId = findViewById(R.id.registerId);

        loginButton.setOnClickListener(this);
        registerId.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String mobile = mobileLogin.getText().toString().trim();
        String password = passwordLogin.getText().toString().trim();


        if(v.getId()==R.id.logInButton){
            if(mobile.isEmpty() || password.isEmpty() ){
                Toast.makeText(getApplicationContext(),"Please give Mobile and Password",Toast.LENGTH_LONG).show();
            }
            if(databaseHelper.checkUser(mobile,password)){


                   Intent i = new Intent(getApplicationContext(),Welcome.class);
                   i.putExtra("MOBILE",mobile);
                   startActivity(i);
               }
               else{
                   Toast.makeText(getApplicationContext(),"Password and Mobile does not match",Toast.LENGTH_LONG).show();
               }





        }
        else if(v.getId()==R.id.registerId){
        startActivity(new Intent(getApplicationContext(),Registration.class));
        }


    }
}