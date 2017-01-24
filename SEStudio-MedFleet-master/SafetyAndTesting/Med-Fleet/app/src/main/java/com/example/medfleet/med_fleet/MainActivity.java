package com.example.medfleet.med_fleet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.medfleet.med_fleet";
    int severity = 0;
    StringBuffer response = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void severityClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_btn_1:
                if (checked)
                    severity = 1;
                break;
            case R.id.radio_btn_2:
                if (checked)
                    severity = 2;
                break;
            case R.id.radio_btn_3:
                if (checked)
                    severity = 3;
                break;
            case R.id.radio_btn_4:
                if (checked)
                    severity = 4;
                break;
            case R.id.radio_btn_5:
                if (checked)
                    severity = 5;
                break;
        }
    }


    public void reqClicked(View view) throws MalformedURLException {

        Intent intent = new Intent(this, OpenMap.class);

        intent.putExtra(EXTRA_MESSAGE, Integer.toString(severity));
        startActivity(intent);
    }
}
