package com.lkcdroid.ownstore.helpers;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Definimos los atributos para el nombre de la BD
    // y su versión de nuestra aplicación
    public static final String DATABASE_NAME = "ownstore.db";
    public static final int DATABASE_VERSION = 1;

    // Elaboramos el constructor en base a los parámetros necesarios
    // por la superclase (padre)
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Método obligatorio de la interfaz para crear la base de datos
    // en caso de que la app ejecutada en el teléfono móvil
    // no la tenga creada.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tienda(id_tienda INTEGER PRIMARY KEY AUTOINCREMENT, nombre_tienda TEXT, descripcion_tienda TEXT,imagen_tienda text);");
        db.execSQL("CREATE TABLE producto(id_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre_producto TEXT, descripcion_producto TEXT);");
        db.execSQL("CREATE TABLE tienda(id_imagen INTEGER PRIMARY KEY AUTOINCREMENT, recurso_imagen TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
