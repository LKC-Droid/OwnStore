package com.lkcdroid.ownstore.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lkcdroid.ownstore.dto.Producto;
import com.lkcdroid.ownstore.helpers.DatabaseHelper;

import java.util.ArrayList;

public class ProductoDAL {
    private DatabaseHelper dbHelper;
    private Producto producto;

    public ProductoDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.producto = new Producto();
        // Testing
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public ProductoDAL(Context context, Producto producto) {
        this.dbHelper = new DatabaseHelper(context);
        this.producto = producto;
    }

    public boolean insertar() {
        return this.tryInsert();
    }

    public boolean insertar(String nombre, String descripcion)
    {
        this.producto.setNombre(nombre);
        this.producto.setDescripcion(descripcion);

        return this.tryInsert();
    }

    public ArrayList<Producto> seleccionar()
    {
        ArrayList<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM producto", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String nombre = consulta.getString(1);
                String descripcion = consulta.getString(2);

                Producto producto = new Producto(id,nombre,descripcion);
                lista.add(producto);

            } while(consulta.moveToNext());

        }

        return lista;
    }

    public boolean actualizar(int id, Producto producto)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre_producto", producto.getNombre());
        c.put("descripcion_producto", producto.getDescripcion());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "producto",
                    c,
                    "id_producto = ?",
                    new String[] { String.valueOf(id) }
            );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean actualizar(Producto producto)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre_producto", producto.getNombre());
        c.put("descripcion_producto", producto.getDescripcion());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "producto",
                    c,
                    "id_producto = ?",
                    new String[] { String.valueOf(producto.getId()) }
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
            filasAfectadas = db.delete("producto","id_producto = ?",
                    new String[] { String.valueOf(id) });
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);

    }

    private boolean tryInsert() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        ContentValues c = new ContentValues();
        c.put("nombre_producto", this.producto.getNombre());
        c.put("descripcion_producto", this.producto.getDescripcion());

        try {
            db.insert("producto", null, c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
