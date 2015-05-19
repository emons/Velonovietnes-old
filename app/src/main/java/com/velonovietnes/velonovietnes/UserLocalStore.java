package com.velonovietnes.velonovietnes;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;                                        //Creating a sharedpref local database, so the
                                                                                //application remembers the user logged in
    public UserLocalStore(Context context)                                      //and the user doesnt need to log in every time he laucnhes the application.
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user)                                        //Storing the user data on a local db
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("username", user.username);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser()                                               //Getting and returning the logged in user details
    {
        String username = userLocalDatabase.getString("username", "");
        String email = userLocalDatabase.getString("email", "");
        String password = userLocalDatabase.getString("password", "");

        User storedUser = new User(username, email, password);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn)                               //Setting the state of the user logged in or not
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public void clearUserData()                                                 //Clearing the user data
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
