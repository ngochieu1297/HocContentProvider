package com.example.admin.hoccontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.hoccontentprovider.model.Contact;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView lvContact;
    ArrayList<Contact> dsDanhBa;
    ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addControls();
        addEvents();

        showAllContactFromDevices();
    }

    private void showAllContactFromDevices() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; //truy xuat danh ba tu dien thoai
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        dsDanhBa.clear();
        while(cursor.moveToNext()) {
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vtTenCotName = cursor.getColumnIndex(tenCotName);
            int vtCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vtTenCotName);
            String phone = cursor.getString(vtCotPhone);
            Contact contact = new Contact(name, phone);
            dsDanhBa.add(contact);
        }
        cursor.close();
        contactAdapter.notifyDataSetChanged();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvContact = findViewById(R.id.lvContact);
        dsDanhBa = new ArrayList<>();
        contactAdapter = new ArrayAdapter<>(Main2Activity.this, android.R.layout.simple_list_item_1, dsDanhBa);
        lvContact.setAdapter(contactAdapter);
    }
}
