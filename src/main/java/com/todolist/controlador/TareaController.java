package com.todolist.controlador;

import com.todolist.modelo.Tarea;
import com.todolist.servicio.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService servicio;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        return ResponseEntity.ok(servicio.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Tarea tarea = servicio.obtenerPorId(id);
        if (tarea == null) return ResponseEntity.status(404).body("No encontrada.");
        return ResponseEntity.ok(tarea);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Tarea tarea) {
        Tarea creada = servicio.crear(tarea);
        return ResponseEntity.status(201).body("Tarea creada con ID " + creada.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Tarea tarea) {
        Tarea actualizada = servicio.actualizar(id, tarea);
        if (actualizada == null) return ResponseEntity.status(404).body("No se pudo actualizar.");
        return ResponseEntity.ok("Tarea actualizada.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminada = servicio.eliminar(id);
        if (!eliminada) return ResponseEntity.status(404).body("No se encontró para eliminar.");
        return ResponseEntity.ok("Tarea eliminada.");
    }
}