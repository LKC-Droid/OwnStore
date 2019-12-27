package com.lkcdroid.ownstore.ui.VistaTiendaYProducto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkcdroid.ownstore.AdaptadorProducto;
import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.ProductoDAL;
import com.lkcdroid.ownstore.dto.Producto;

import java.util.ArrayList;

public class VistaTiendaActivity extends AppCompatActivity {



    private TextView nombre;
    private  TextView desc;

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager;

    private ArrayList<Producto> lista;


    private int codPosicion;
    private ArrayList<Producto> lp;
    private ProductoDAL productoDAL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_tienda);
        //Recordar de cambiar los ID de los elementos, los dejé así para que los nombres a tu gusto

        nombre = (TextView) findViewById(R.id.nombreTienda);
        desc =(TextView) findViewById(R.id.dscTienda);


        String nom = getIntent().getStringExtra("nombre");
        String des = getIntent().getStringExtra("descripcion");

        lp = new ProductoDAL(getApplicationContext()).seleccionar();

        this.nombre.setText(nom);
        desc.setText(des);

        //Lista

        rv = (RecyclerView) findViewById(R.id.rvProductos);

        rv.setHasFixedSize(true);

        LayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(LayoutManager);


        llenarLista();

        AdaptadorProducto mAdapter = new AdaptadorProducto(lista);

        //Menu en pantalla
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea borrar el producto?");
        builder.setPositiveButton("Si, borrar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                int id = ((Producto) lp.get(codPosicion)).getId();
                boolean r = productoDAL.eliminar(id);
                if(r){
                    Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "No se ha podido eliminar la serie", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();
        //Fin dialogo en pantalla

        mAdapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(),"seleccion: "+
                        lista.get(rv.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_LONG).show();
                codPosicion = rv.getChildAdapterPosition(v);
                dialog.show();
                return false;
            }
        });
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"seleccion: "+
                        lista.get(rv.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_LONG).show();

                Producto t = (Producto) lista.get(rv.getChildAdapterPosition(v));
                abrirProducto(t);

            }
        });


        rv.setAdapter(mAdapter);


    }

    private void abrirProducto(Producto t) {

        Intent intento = new Intent(this, VistaProductoActivity.class);

        intento.putExtra("nombre", t.getNombre());
        intento.putExtra("descripcion", t.getDescripcion());

        startActivityForResult(intento, 100);
    }

    private void llenarLista() {
        this.lista = new ProductoDAL(getBaseContext()).seleccionar();
    }
}
