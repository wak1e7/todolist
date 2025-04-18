package com.todolist.controlador;

import com.todolist.modelo.Tarea;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();
    private Long contadorId = 1L;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        return ResponseEntity.ok().body(tareas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Tarea tarea = tareas.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (tarea == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarea con ID " + id + " no encontrada.");
        }

        return ResponseEntity.ok().body(tarea);
    }

    @PostMapping
    public ResponseEntity<?> crearTarea(@RequestBody Tarea tarea) {
        tarea.setId(contadorId++);
        tareas.add(tarea);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarea creada exitosamente con ID " + tarea.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarea(@PathVariable Long id, @RequestBody Tarea nuevaTarea) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                tarea.setDescripcion(nuevaTarea.getDescripcion());
                tarea.setCompletado(nuevaTarea.isCompletado());
                return ResponseEntity.ok().body("Tarea con ID " + id + " actualizada correctamente.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró la tarea con ID " + id + " para actualizar.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarea(@PathVariable Long id) {
        boolean eliminada = tareas.removeIf(t -> t.getId().equals(id));
        if (eliminada) {
            return ResponseEntity.ok().body("Tarea con ID " + id + " eliminada exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la tarea con ID " + id + " para eliminar.");
        }
    }
}