
package com.emergentes.modelo;

public class Tarea {
    private int id;
    private String nombre;
    private String estado;

    public Tarea() {
        this.id = 0;
        this.nombre = "";
        this.estado = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
