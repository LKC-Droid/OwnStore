package com.lkcdroid.ownstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lkcdroid.ownstore.dto.Tienda;

import java.util.ArrayList;

public class AdaptadorTienda extends RecyclerView.Adapter<AdaptadorTienda.MyViewHolder> implements View.OnClickListener {

    private ArrayList<Tienda> lista;
    private View.OnClickListener listener;


    public AdaptadorTienda(ArrayList<Tienda> t) {
        this.lista = t;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemanto_lista_tienda,null,false);

        view.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.nombre.setText(lista.get(position).getNombre());
        holder.desc.setText(lista.get(position).getDescripcion());

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

        if (listener!=null){
            listener.onClick(v);
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView desc;
        public ImageView img;

        private MyViewHolder( View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            desc =(TextView) itemView.findViewById(R.id.txtDsc);
            img = (ImageView) itemView.findViewById(R.id.imageView7);
        }

        private void asignarDatos(Tienda tienda) {

            nombre.setText(tienda.getNombre());
            desc.setText(tienda.getDescripcion());
            img.setImageResource(Integer.parseInt(tienda.getImagen()));


        }
    }
}
