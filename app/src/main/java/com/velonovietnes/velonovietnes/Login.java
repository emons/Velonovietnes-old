package com.velonovietnes.velonovietnes;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends ActionBarActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById (R.id.etUsername);
        etPassword = (EditText) findViewById (R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bLogin:
                String username = etUsername.getText().toString();                           //User enters uname - stored
                String password = etPassword.getText().toString();                           //user enters pass - stored

                User user= new User(username, password);                                     //New user obj with prev details^

                authenticate(user);                                                          //Soft authenticates the user
                break;
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user)                                                     //Authenticating the user by fetching
    {                                                                                        //the data from the server
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser==null){                                                    //If no such user/pass combo is found
                    showErrorMessage();                                                     //error msg is displayed
                }else {
                    logUserIn(returnedUser);                                                //else Logs the user in
                }
            }
        });
    }

    private void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage(getString(R.string.incorrectUsernamePassword));
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser)                                               //If user is logged in, his data is
    {                                                                                       //stored on the device so he doesn't
        userLocalStore.storeUserData(returnedUser);                                         //need to log back in when launching
        userLocalStore.setUserLoggedIn(true);                                               //the app later.

        startActivity(new Intent(this, MainActivity.class));                                //Starting the main activity
    }
}

