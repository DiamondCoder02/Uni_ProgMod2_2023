package com.lesson7;

import com.lesson7.Services.TodoServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Validated
// This class is used to handle requests
// Everything that comes to the server will be handled here and sent to the services
public class TodoController {
    @Autowired
    private TodoServices todoServices;

	// Get all tasks
    @GetMapping
    public Object getAllUsers() {
        return todoServices.findAllTasks();
    }

    // Create task
    @PostMapping
    public ResponseEntity<Todo> createTask(@RequestBody @Valid Todo todo) {
        todoServices.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    // Task update
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTask(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid Todo updatedUser) {
        return ResponseEntity.ok(todoServices.updateTaskById(id, updatedUser));
    }

    // Get task by id
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTask(@PathVariable("id") @Min(1) Long id) {
        return ResponseEntity.ok(todoServices.findTaskById(id));
    }

    // Task delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") @Min(1) Long id) {
        todoServices.deleteTaskById(id);
        return ResponseEntity.ok(todoServices.findAllTasks());
    }

	// Get all priority 1 tasks
	@GetMapping("/urgent")
	public ResponseEntity<Todo> getAllPriorityOneTasks() {
		return ResponseEntity.ok(todoServices.GetAllPriorityOneTasks());
	}
}
