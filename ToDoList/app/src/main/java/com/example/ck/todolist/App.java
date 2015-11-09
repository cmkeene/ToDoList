package com.example.ck.todolist;

/**
 * Created by CK on 11/9/2015.
 */
import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate(){
        super.onCreate();

        Parse.initialize(this, ApplicationID, ClientKey);
    }
}
