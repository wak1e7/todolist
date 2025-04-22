package com.todolist.servicio;

import com.todolist.modelo.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TareaService {
    List<Tarea> obtenerTodas();
    Tarea obtenerPorId(Long id);
    Tarea crear(Tarea tarea);
    Tarea actualizar(Long id, Tarea tarea);
    boolean eliminar(Long id);
}
