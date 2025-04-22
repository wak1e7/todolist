package com.todolist;

import com.todolist.modelo.Tarea;
import com.todolist.repositorio.TareaRepository;
import com.todolist.servicio.TareaServicieImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodolistApplicationTests  {

    @Mock
    private TareaRepository tareaRepository;

    @InjectMocks
    private TareaServicieImpl tareaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodas() {
        List<Tarea> tareasMock = Arrays.asList(
                new Tarea(1L, "Tarea 1", false),
                new Tarea(2L, "Tarea 2", true)
        );
        when(tareaRepository.findAll()).thenReturn(tareasMock);
        List<Tarea> resultado = tareaService.obtenerTodas();
        assertEquals(2, resultado.size());
        verify(tareaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Tarea tarea = new Tarea(1L, "Tarea de prueba", false);
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tarea));
        Tarea resultado = tareaService.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals("Tarea de prueba", resultado.getDescripcion());
    }

    @Test
    void testCrearTarea() {
        Tarea tarea = new Tarea(null, "Nueva tarea", false);
        Tarea tareaGuardada = new Tarea(1L, "Nueva tarea", false);
        when(tareaRepository.save(tarea)).thenReturn(tareaGuardada);
        Tarea resultado = tareaService.crear(tarea);
        assertNotNull(resultado.getId());
        assertEquals("Nueva tarea", resultado.getDescripcion());
    }

    @Test
    void testActualizarTarea() {
        Tarea tareaExistente = new Tarea(1L, "Tarea vieja", false);
        Tarea tareaActualizada = new Tarea(null, "Tarea nueva", true);
        when(tareaRepository.findById(1L)).thenReturn(Optional.of(tareaExistente));
        when(tareaRepository.save(any(Tarea.class))).thenReturn(tareaExistente);
        Tarea resultado = tareaService.actualizar(1L, tareaActualizada);
        assertNotNull(resultado);
        assertEquals("Tarea nueva", resultado.getDescripcion());
        assertTrue(resultado.isCompletado());
    }

    @Test
    void testEliminarTarea() {
        when(tareaRepository.existsById(1L)).thenReturn(true);
        boolean resultado = tareaService.eliminar(1L);
        assertTrue(resultado);
        verify(tareaRepository).deleteById(1L);
    }
}

