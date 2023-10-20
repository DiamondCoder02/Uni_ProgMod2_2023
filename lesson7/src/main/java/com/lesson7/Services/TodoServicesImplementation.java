package com.lesson7.Services;

import com.lesson7.Todo;
import com.lesson7.Exceptions.TodoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j(topic = "fileLogger")
public class TodoServicesImplementation implements TodoServices {
	private final List<Todo> todos = new ArrayList<>();

    // Find all tasks
    @Override
    public Object findAllTasks() {
        return (todos.isEmpty()) ? "The task list is empty" : todos;
    }

    // Find task by ID
    @Override
    public Todo findTaskById(Long id) {
        String errorMessage = "Task by ID=" + id + " cannot be found.";
        return getTaskById(id, errorMessage);
    }

    // Save task
    @Override
    public void save(Todo todo) {
        todo.setId(generateTaskId());
        todos.add(todo);
    }

    // Generate task ID
    // Increment the maximum ID value by 1 and return it
    private Long generateTaskId() {
        return todos.stream().mapToLong(Todo::getId).max().orElse(0) + 1;
    }

    // Update task by ID
    @Override
    public Todo updateTaskById(Long id, Todo updatedTask) {
        String errorMessage = "Update error. Task by ID=" + id + " cannot be found.";
        Todo todo = getTaskById(id, errorMessage);
        todo.setName(updatedTask.getName());
        todo.setDescription(updatedTask.getDescription());
        return todo;
    }

    // Delete task by ID
    @Override
    public void deleteTaskById(Long id) {
        String errorMessage = "Delete error. Task by ID=" + id + " cannot be found.";
        Todo todo = getTaskById(id, errorMessage);
        todos.remove(todo);
    }

    // Get task by ID
    private Todo getTaskById(Long id, String errorMessage) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst().orElseThrow(() -> {
            log.error(errorMessage);
            return new TodoNotFoundException(errorMessage);
        });
    }

    @Override
    public Todo GetAllPriorityOneTasks() {
        return todos.stream().filter(todo -> todo.getPriority().equals(1)).findFirst().orElseThrow(() -> {
            log.error("There are no priority one tasks");
            return new TodoNotFoundException("There are no priority one tasks");
        });
    }
}
