package com.javier_lozano.sqlite.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.javier_lozano.sqlite.Objects.Contact;
import com.javier_lozano.sqlite.R;
import com.javier_lozano.sqlite.Utilities.ContactDataSource;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    ListView listview;
    private ArrayList<Contact> contactos;
    private ArrayList<String> contacts = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        listview = (ListView)findViewById(R.id.lv_list);
        ContactDataSource dataSource = new ContactDataSource(this);
        contactos = dataSource.readContacts();
        String contact;
        for (Contact c : contactos){
            contact = c.getName()+ " " + c.getEmail();
            contacts.add(contact);
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contacts );
        listview.setAdapter(adapter);
     }
}
