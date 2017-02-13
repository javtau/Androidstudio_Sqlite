package com.javier_lozano.sqlite.UI;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.javier_lozano.sqlite.Objects.Contact;
import com.javier_lozano.sqlite.R;
import com.javier_lozano.sqlite.Utilities.ContactDataSource;

import java.util.ArrayList;

public class Borrar extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listview;
    private ArrayList<Contact> contactos;
    private ArrayList<String> contacts = new ArrayList<String>();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        listview = (ListView)findViewById(R.id.lv_list);
        ContactDataSource dataSource = new ContactDataSource(this);
        contactos = dataSource.readContacts();
        mostrar();
        listview.setOnItemClickListener(this);

    }

    private void mostrar() {
        String contact;
        for (Contact c : contactos){
            contact = c.getName()+ " " + c.getEmail();
            contacts.add(contact);
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contacts );
        listview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog d = new AlertDialog.Builder(this)
                .setMessage("¿seguro que desea borrar "+ contactos.get(position).getName()+" de la lista ?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactDataSource dataSource = new ContactDataSource(Borrar.this);
                        dataSource.borrarContacto(contactos.get(position).getId());
                        String contact = contactos.get(position).getName()+ " " + contactos.get(position).getEmail();
                        adapter.remove(contact);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No",null).show();

    }
}
