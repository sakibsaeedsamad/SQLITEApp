package com.sssakib.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText nameRegistrationET, mobileRegistrationET, passwordRegistrationET, addressRegistrationET, emailRegistrationET;

    User user;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        user = new User();
        databaseHelper = new DatabaseHelper(this);

        nameRegistrationET = findViewById(R.id.nameRegistrationET);
        mobileRegistrationET = findViewById(R.id.mobileRegistrationET);
        passwordRegistrationET = findViewById(R.id.passwordRegistrationET);
        addressRegistrationET = findViewById(R.id.addressRegistrationET);
        emailRegistrationET = findViewById(R.id.emailRegistrationET);


        Button registerButton = findViewById(R.id.registerButton);
        Button goBackButton = findViewById(R.id.goBackButton);
        registerButton.setOnClickListener(this);
        goBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.registerButton){
            doInsert();


        }
        else if(v.getId()==R.id.goBackButton){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
    public void doInsert(){
        String name = nameRegistrationET.getText().toString();
        String mobile = mobileRegistrationET.getText().toString().trim();
        String email = emailRegistrationET.getText().toString().trim();
        String password = passwordRegistrationET.getText().toString().trim();
        String address = addressRegistrationET.getText().toString().trim();
        if(name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fill up all values",Toast.LENGTH_LONG).show();
        }
        else{

            if(!databaseHelper.checkUser(mobile)){
                user.setName(name);
                user.setMobile(mobile);
                user.setEmail(email);
                user.setPassword(password);
                user.setAddress(address);
                databaseHelper.insertData(user);


                Toast.makeText(getApplicationContext(), "Row is Successfully inserted", Toast.LENGTH_LONG).show();


            }
            else{
                Toast.makeText(getApplicationContext(), "Record Already Exists!", Toast.LENGTH_LONG).show();

            }




            }




        }

    }

