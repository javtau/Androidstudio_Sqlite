package com.javier_lozano.sqlite.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.javier_lozano.sqlite.R;
import com.javier_lozano.sqlite.Utilities.SqliteHelper;

public class MainActivity extends AppCompatActivity {
    private Button btn_insertar, btn_borrar, btn_modificar, btn_mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_insertar = (Button) findViewById(R.id.btn_insertar);
        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Insertar.class);
                startActivity(i);
            }
        });
        btn_borrar = (Button) findViewById(R.id.btn_borrar);
        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Borrar.class);
                startActivity(i);
            }
        });
        btn_mostrar = (Button) findViewById(R.id.btn_mostrar);
        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Mostrar.class);
                startActivity(i);
            }
        });
        btn_modificar = (Button) findViewById(R.id.btn_modificar);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Modificar.class);
                startActivity(i);
            }
        });

    }



}
