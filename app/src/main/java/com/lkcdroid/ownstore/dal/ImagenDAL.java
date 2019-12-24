package com.lkcdroid.ownstore.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lkcdroid.ownstore.dto.Imagen;
import com.lkcdroid.ownstore.helpers.DatabaseHelper;

import java.util.ArrayList;

public class ImagenDAL {
    private DatabaseHelper dbHelper;
    private Imagen imagen;

    public ImagenDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.imagen = new Imagen();
        // Testing
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public ImagenDAL(Context context, Imagen imagen) {
        this.dbHelper = new DatabaseHelper(context);
        this.imagen = imagen;
    }

    public boolean insertar() {
        return this.tryInsert();
    }

    public boolean insertar(String recurso)
    {
        this.imagen.setRecurso(recurso);

        return this.tryInsert();
    }

    public ArrayList<Imagen> seleccionar()
    {
        ArrayList<Imagen> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM tienda", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String recurso = consulta.getString(1);


                Imagen imagen = new Imagen(id,recurso);
                lista.add(imagen);

            } while(consulta.moveToNext());

        }

        return lista;
    }

    public boolean actualizar(int id, Imagen imagen)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("recurso_imagen", imagen.getRecurso());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "imagen",
                    c,
                    "id_imagen = ?",
                    new String[] { String.valueOf(id) }
            );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean actualizar(Imagen imagen)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("recurso_imagen", imagen.getRecurso());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "imagen",
                    c,
                    "id_imagen = ?",
                    new String[] { String.valueOf(imagen.getId_imagen()) }
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
        c.put("recurso_imagen", this.imagen.getRecurso());

        try {
            db.insert("tienda", null, c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
