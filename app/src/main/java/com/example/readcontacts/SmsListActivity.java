package com.example.readcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class SmsListActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter customAdapter;
    private ArrayList<SmsModel> smsModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);

        listView = (ListView) findViewById(R.id.listView);

        smsModelArrayList = new ArrayList<SmsModel>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");

        Cursor sms = getContentResolver().query(uriSMSURI, null,null,null,null);
        while (sms.moveToNext())
        {
            String address = sms.getString(sms.getColumnIndex("address"));
            String body = sms.getString(sms.getColumnIndexOrThrow("body"));

            SmsModel smsModel = new SmsModel();
            smsModel.setAddress(address);
            smsModel.setMessage(body);
            smsModelArrayList.add(smsModel);
            Log.d("Address>>",address+"  "+body);
        }
        sms.close();

        customAdapter = new CustomAdapter(this,null,smsModelArrayList);
        listView.setAdapter(customAdapter);

    }
}
