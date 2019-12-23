package com.lkcdroid.ownstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterTiendas extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context contexto;
    String[][]datos;
    int[] datosImagen;


    public AdapterTiendas(Context contexto, String[][] datos, int[] datosImg) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosImagen = datosImg;
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return datosImagen.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.lista_tiendas,null);
        TextView TituloTienda = (TextView) vista.findViewById(R.id.textNombreTienda);
        TextView DescripcionTienda = (TextView) vista.findViewById(R.id.textDesc);
        ImageView LogoTienda = (ImageView) vista.findViewById(R.id.imageLogo);
        ImageView ProductoTienda1 = (ImageView) vista.findViewById(R.id.imageProducto1);
        ImageView ProductoTienda2 = (ImageView) vista.findViewById(R.id.imageProducto2);
        ImageView ProductoTienda3 = (ImageView) vista.findViewById(R.id.imageProducto3);

        TituloTienda.setText(datos[i][0]);
        DescripcionTienda.setText(datos[i][1]);
        LogoTienda.setImageResource(datosImagen[i]);
        ProductoTienda1.setImageResource(datosImagen[i]);
        ProductoTienda2.setImageResource(datosImagen[i]);
        ProductoTienda3.setImageResource(datosImagen[i]);

        return vista;
    }
}
