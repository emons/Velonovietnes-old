package com.velonovietnes.velonovietnes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    EditText etUsername, etEmail, etPassword, etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {                                                   //What happends why listener receives a click
        switch (v.getId())
        {
            case R.id.bRegister:                                                    //Register button clicked
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(username, email, password);

                registerUser(user);                                                 //Calling the registration activity

                break;
        }
    }

    private void registerUser(User user)                                            //Registering the user
    {
        ServerRequests serverRequests = new ServerRequests(this);                   //new server request
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {      //Store the user data in background
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}
