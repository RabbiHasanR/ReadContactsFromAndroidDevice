package com.example.readcontacts;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class CallLogListActivity extends AppCompatActivity {
    private ListView listView;
    private CallLogsAdapter callLogsAdapter;
    private ArrayList<CallLogsModel> callLogsModelArrayList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_list);
        if (checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        listView = (ListView) findViewById(R.id.listViewCallLogs);

        callLogsModelArrayList = new ArrayList<>();
        Uri contacts = CallLog.Calls.CONTENT_URI;
        Cursor callLogs = getContentResolver().query(contacts, null, null, null, android.provider.CallLog.Calls.DATE+ " DESC" );
        int number = callLogs.getColumnIndex(CallLog.Calls.NUMBER);
        int type = callLogs.getColumnIndex(CallLog.Calls.TYPE);
        int date = callLogs.getColumnIndex(CallLog.Calls.DATE);
        int duration = callLogs.getColumnIndex(CallLog.Calls.DURATION);
        Log.d("CallLogs cont>>", String.valueOf(callLogs.getCount()));
        while (callLogs.moveToNext())
        {
            String phNumber = callLogs.getString(number);
            String callType = callLogs.getString(type);
            String callDate = callLogs.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = callLogs.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }


            CallLogsModel callLogsModel=new CallLogsModel();
            callLogsModel.setNumber(phNumber);
            callLogsModel.setType(dir);
            callLogsModel.setDate(callDayTime);
            callLogsModel.setDuration(callDuration);
            callLogsModelArrayList.add(callLogsModel);
            Log.d("CallLogs>>",phNumber+"  "+callType);
        }
        callLogs.close();

        callLogsAdapter = new CallLogsAdapter(this,callLogsModelArrayList);
        listView.setAdapter(callLogsAdapter);

    }
}
