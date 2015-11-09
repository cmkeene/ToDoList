package com.example.ck.todolist;

/**
 * Created by CK on 11/9/2015.
 */
import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate(){
        super.onCreate();

        Parse.initialize(this, "TS7SDBiGiow535wARMOM72vEgIk9FAa8hjxrKV2s", "WGOgbTzVk94yh5KOuznAs7E0eYaEky1bTH1qensI");
    }
}
