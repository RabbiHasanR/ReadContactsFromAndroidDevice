package com.example.readcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.read_contacts)
    void readContacts(){
        // Start the Signup activity
        Log.d("Click","yes");
        Intent intent = new Intent(getApplicationContext(), ContactsListActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.read_sms)
    void readSms(){
        // Start the Signup activity
        Log.d("Click2","yes");
        Intent intent = new Intent(getApplicationContext(), SmsListActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.read_call_logs)
    void readCallLogs(){
        // Start the Signup activity
        Log.d("Click3","yes");
        Intent intent = new Intent(getApplicationContext(), CallLogListActivity.class);
        startActivity(intent);
        finish();
    }
}
