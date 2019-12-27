package com.lkcdroid.ownstore.ui.Tiendas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkcdroid.ownstore.AdaptadorTienda;
import com.lkcdroid.ownstore.AdapterTiendas;
import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.ProductoDAL;
import com.lkcdroid.ownstore.dal.TiendaDAL;
import com.lkcdroid.ownstore.dto.Producto;
import com.lkcdroid.ownstore.dto.Tienda;
import com.lkcdroid.ownstore.ui.VistaTiendaYProducto.VistaTiendaActivity;

import java.util.ArrayList;

public class TiendasFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager LayoutManager;
    private ProductoDAL productoDAL;
    private ArrayList<Tienda> lista;

    private TiendaDAL tiendaDAL;



    int[] datosImg = {R.drawable.logo1,R.drawable.logo2,R.drawable.logo3};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        rv = (RecyclerView) root.findViewById(R.id.rvTiendas);

        //rv.setHasFixedSize(true);

        LayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(LayoutManager);

        //
        llenarLista();

        AdaptadorTienda mAdapter = new AdaptadorTienda(lista);

        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"seleccion: "+
                        lista.get(rv.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_LONG).show();

                Tienda t = (Tienda) lista.get(rv.getChildAdapterPosition(v));
                abrirTienda(t);

            }
        });

        rv.setAdapter(mAdapter);

        //agregarProducto();



        return root;
    }

    private void agregarProducto() {
        Producto s = new Producto("UnderStore", "Vintage");
        this.productoDAL = new ProductoDAL(getContext(), s);
        if(productoDAL.insertar()) {
            Toast.makeText(getContext(), "OK! Insertó", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "MAL! NO Insertó", Toast.LENGTH_LONG).show();
        }
    }

    private void abrirTienda(Tienda i) {

        Intent intento = new Intent(getContext(), VistaTiendaActivity.class);

        intento.putExtra("nombre", i.getNombre());
        intento.putExtra("descripcion", i.getDescripcion());

        startActivityForResult(intento, 100);

    }

    private void llenarLista() {
        this.lista = new TiendaDAL(getContext()).seleccionar();
    }

}