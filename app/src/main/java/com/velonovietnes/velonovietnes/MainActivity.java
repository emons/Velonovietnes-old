package com.velonovietnes.velonovietnes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button bLogout;
    EditText etUsername, etEmail;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etEmail = (EditText) findViewById(R.id.etEmail);
        bLogout = (Button) findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {                                                  //On application start, checks if
        super.onStart();                                                        //user is logged in.
        if(authenticate())
        {
            displayUserDetails();                                      //šeit jābūt start activity uz google maps
        }
    }

    private boolean authenticate()                                              //Check if a user is logged in
    {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails()                                           //Parāda usera datus, probably ņemšu nost
    {
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etEmail.setText(user.email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bLogout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);



                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}
