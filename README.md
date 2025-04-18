# ToDoListApp

## Descripción
Esta API permite gestionar tareas pendientes, permitiendo operaciones de creación, lectura, actualización y eliminación (CRUD) usando Spring Boot.

## Cómo ejecutar
1. Clonar repositorio.
2. Abrir en IntelliJ IDEA.
3. Ejecutar la clase `ToDoApplication.java`.
4. Acceder a la API en: `http://localhost:8080/api/tareas`

## Endpoints
### GET /api/tareas
Obtiene todas las tareas.

### POST /api/tareas
Crea una nueva tarea.

```json
{
  "descripcion": "Ejemplo de tarea",
  "completado": false
}
```

### GET /api/tareas/{id}
Obtiene una tarea por su ID.

```json
{
    "id": 1,
    "descripcion": "Ejemplo de tarea",
    "completado": false
}
```

### PUT /api/tareas/{id}
Actualiza una tarea.

```json
{
  "descripcion": "Ejemplo de tarea actualizada",
  "completado": true
}
```

### DELETE /api/tareas/{id}
Elimina una tarea.
