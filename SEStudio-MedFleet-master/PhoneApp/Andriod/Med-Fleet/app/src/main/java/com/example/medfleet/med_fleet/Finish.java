package com.example.medfleet.med_fleet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Retrieve the request result from the previous Activity
        Intent intent = getIntent();
        String message = intent.getStringExtra("result");

        TextView text = (TextView) findViewById(R.id.finish);

        if(message.equals("true"))
        {
            text.setText(R.string.success);
        }
        else if(message.equals("false"))
        {
            text.setText(R.string.failure);
        }
    }
}
