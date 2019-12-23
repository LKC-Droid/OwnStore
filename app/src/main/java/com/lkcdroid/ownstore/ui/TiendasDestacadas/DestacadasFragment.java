package com.lkcdroid.ownstore.ui.TiendasDestacadas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lkcdroid.ownstore.AdapterTiendas;
import com.lkcdroid.ownstore.R;

public class DestacadasFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    ListView lista;

    String[][] datos = {
            {"Unravel Store", "Tienda de ropa juvenil de verano Tienda de ropa juvenil de veranoTienda de ropa juvenil de veranoTienda de ropa juvenil"},
            {"Go Shopping", "Accesorios, prendas y mas!"},
            {"TuTienda", "12345678910111213141516171819202122232425262728293031323334353637383940414243444546474849505152535455565758596061626364656667"}
    };


    int[] datosImg = {R.drawable.logo1,R.drawable.logo2,R.drawable.logo3};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
         View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        lista = (ListView) root.findViewById(R.id.listaTiendasDestacadas);
        lista.setAdapter(new AdapterTiendas(getContext(),datos,datosImg));

        return root;
    }

}