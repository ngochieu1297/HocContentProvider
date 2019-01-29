package com.example.admin.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    ListView lvSMS;
    ArrayAdapter<String> adapterSMS;
    ArrayList<String> dsSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        addControls();
        addEvents();

        showAllSMS();
    }

    private void showAllSMS() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        dsSMS.clear();
        while (cursor.moveToNext()) {
            int indexPhoneNumber = cursor.getColumnIndex("address");
            int indexTimeStamp = cursor.getColumnIndex("date");
            int indexBody = cursor.getColumnIndex("body");

            String phoneNumber = cursor.getString(indexPhoneNumber);
            String timeStamp = cursor.getString(indexTimeStamp);
            String body = cursor.getString(indexBody);

            dsSMS.add(phoneNumber + "\n" + timeStamp + "\n" + body);
        }
        cursor.close();
        adapterSMS.notifyDataSetChanged();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvSMS = findViewById(R.id.lvSMS);
        dsSMS = new ArrayList<>();
        adapterSMS = new ArrayAdapter<>(Main3Activity.this, android.R.layout.simple_list_item_1, dsSMS);
        lvSMS.setAdapter(adapterSMS);
    }
}
