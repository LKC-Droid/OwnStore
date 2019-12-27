package com.lkcdroid.ownstore.ui.VistaTiendaYProducto;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lkcdroid.ownstore.R;

public class VistaProductoActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView Descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_producto);
        //Agregar los ID!

        this.nombre = (TextView) findViewById(R.id.txtNombreProducto);
        this.Descripcion = (TextView) findViewById(R.id.txtDescrpcionProducto);

        String nom = getIntent().getStringExtra("nombre");
        String des = getIntent().getStringExtra("descripcion");

        nombre.setText(nom);
        Descripcion.setText(des);

    }
}
