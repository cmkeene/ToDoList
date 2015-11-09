package com.example.ck.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ck.todolist.model.Task;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class UpdateActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtDescription;
    private Intent intent;
    private Task task;
    private String name;
    private String description;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        intent = getIntent();

        task = new Task();
        task.setTaskId(intent.getStringExtra("objectId"));

        txtName = (TextView) findViewById(R.id.txtName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Task");
        query.getInBackground(task.getTaskId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject task, ParseException e) {
                if(e==null){
                    name = task.getString(name);
                    description = task. getString("description");

                    txtName.setText(name);
                    txtDescription.setText(description);
                }else {
                    Toast.makeText(getApplicationContext(), "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                name = txtName.getText().toString();
                description = txtDescription.getText().toString();

                if(name.equals("") && description.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter name and description",
                            Toast.LENGTH_LONG).show();
                }else {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Gamescore");

                    query.getInBackground(task.getTaskId(), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject task, ParseException e) {
                           if(e==null){
                               task.put("name", name);
                               task.put("description", description);
                               task.saveInBackground();

                               Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                               startActivity(listActivity);
                           }else{
                               Toast.makeText(getApplicationContext(), "Something went wrong",
                                       Toast.LENGTH_LONG).show();
                           }
                        }
                    });
                }
            }
        });

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("task");
                query.getInBackground(task.getTaskId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject task, ParseException e) {
                        if (e == null) {

                            task.deleteInBackground();

                            Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                            startActivity(listActivity);
                        }else {
                            Toast.makeText(getApplicationContext(), "Something went wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(listActivity);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
