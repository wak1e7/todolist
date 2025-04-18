package com.todolist.modelo;

public class Tarea {
    private Long id;
    private String descripcion;
    private boolean completado;

    public Tarea() {
    }

    public Tarea(Long id, String descripcion, boolean completado) {
        this.id = id;
        this.descripcion = descripcion;
        this.completado = completado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}

