package com.lkcdroid.ownstore.dto;

public class Imagen {
    private int id_imagen;
    private String recurso;

    public Imagen(int id_imagen, String recurso) {
        this.id_imagen = id_imagen;
        this.recurso = recurso;
    }

    public Imagen(String recurso) {
        this.recurso = recurso;
    }

    public Imagen() {
    }

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id_imagen=" + id_imagen +
                ", recurso='" + recurso + '\'' +
                '}';
    }
}
