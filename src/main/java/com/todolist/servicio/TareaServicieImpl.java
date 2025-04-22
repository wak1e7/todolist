package com.todolist.servicio;

import com.todolist.modelo.Tarea;
import com.todolist.repositorio.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicieImpl implements TareaService {

    @Autowired
    private TareaRepository repositorio;

    @Override
    public List<Tarea> obtenerTodas() {
        return repositorio.findAll();
    }

    @Override
    public Tarea obtenerPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Tarea crear(Tarea tarea) {
        return repositorio.save(tarea);
    }

    @Override
    public Tarea actualizar(Long id, Tarea tareaNueva) {
        Tarea tareaExistente = repositorio.findById(id).orElse(null);
        if (tareaExistente != null) {
            tareaExistente.setDescripcion(tareaNueva.getDescripcion());
            tareaExistente.setCompletado(tareaNueva.isCompletado());
            return repositorio.save(tareaExistente);
        }
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }
}

