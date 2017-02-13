package com.javier_lozano.sqlite.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javier_lozano.sqlite.Objects.Contact;
import com.javier_lozano.sqlite.R;
import com.javier_lozano.sqlite.Utilities.ContactDataSource;

public class Insertar extends AppCompatActivity {
    EditText txt_name;
    EditText txt_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_mail = (EditText) findViewById(R.id.txt_mail);


    }

    public void insertar(View v){
        String nombre = txt_name.getText().toString();
        String mail = txt_mail.getText().toString();

        if (nombre.equals("") || mail.equals("")){
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT);
        } else {
            ContactDataSource dataSource = new ContactDataSource(this);
            dataSource.insertContact(new Contact(-1,nombre,mail));
            Toast.makeText(this,"Contacto "+ nombre+", guardado",Toast.LENGTH_SHORT);
            txt_name.setText("");
            txt_mail.setText("");
        }
    }
}
