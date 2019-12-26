package com.lkcdroid.ownstore.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lkcdroid.ownstore.dto.Tienda;
import com.lkcdroid.ownstore.helpers.DatabaseHelper;

import java.util.ArrayList;

public class TiendaDAL {
    private DatabaseHelper dbHelper;
    private Tienda tienda;

    public TiendaDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.tienda = new Tienda();
        // Testing
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public TiendaDAL(Context context, Tienda tienda) {
        this.dbHelper = new DatabaseHelper(context);
        this.tienda = tienda;
    }

    public boolean insertar() {
        return this.tryInsert();
    }

    public boolean insertar(String nombre, String descripcion, String imagen)
    {
        this.tienda.setNombre(nombre);
        this.tienda.setDescripcion(descripcion);
        this.tienda.setImagen(imagen);

        return this.tryInsert();
    }

    public ArrayList<Tienda> seleccionar()
    {
        ArrayList<Tienda> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM tienda", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String nombre = consulta.getString(1);
                String descripcion = consulta.getString(2);
                String imagen = consulta.getString(3);
                String direccion=consulta.getString(4);

                Tienda tienda = new Tienda(id,nombre,descripcion,imagen,direccion);
                lista.add(tienda);

            } while(consulta.moveToNext());

        }

        return lista;
    }

    public boolean actualizar(int id, Tienda tienda)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre_tienda", tienda.getNombre());
        c.put("descripcion_tienda", tienda.getDescripcion());
        c.put("imagen_tienda", tienda.getImagen());
        c.put("direccion_tienda",tienda.getDireccion());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "tienda",
                    c,
                    "id_tienda = ?",
                    new String[] { String.valueOf(id) }
            );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean actualizar(Tienda tienda)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre_tienda", tienda.getNombre());
        c.put("descripcion_tienda", tienda.getDescripcion());
        c.put("imagen_tienda", tienda.getImagen());
        c.put("direccion_tienda",tienda.getDireccion());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "tienda",
                    c,
                    "id_tienda = ?",
                    new String[] { String.valueOf(tienda.getId()) }
            );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasAfectadas;


        try {
            filasAfectadas = db.delete("tienda","id_tienda = ?",
                    new String[] { String.valueOf(id) });
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);

    }

    private boolean tryInsert() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ContentValues c = new ContentValues();
        c.put("nombre_tienda", this.tienda.getNombre());
        c.put("descripcion_tienda", this.tienda.getDescripcion());
        c.put("imagen_tienda", this.tienda.getImagen());
        c.put("direccion_tienda",tienda.getDireccion());

        try {
            db.insert("tienda", null, c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
