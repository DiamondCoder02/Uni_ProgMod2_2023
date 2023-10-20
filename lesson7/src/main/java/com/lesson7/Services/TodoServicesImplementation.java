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

    @Override
    public Object findAllTasks() {
        return (todos.isEmpty()) ? "The task list is empty" : todos;
    }

    @Override
    public Todo findTaskById(Long id) {
        String errorMessage = "Task by ID=" + id + " cannot be found.";
        return getTaskById(id, errorMessage);
    }

    @Override
    public void save(Todo todo) {
        todo.setId(generateTaskId());
        todos.add(todo);
    }

    private Long generateTaskId() {
        return todos.stream().mapToLong(Todo::getId).max().orElse(0) + 1;
    }

    @Override
    public Todo updateTaskById(Long id, Todo updatedTask) {
        String errorMessage = "Update error. Task by ID=" + id + " cannot be found.";
        Todo todo = getTaskById(id, errorMessage);
        todo.setName(updatedTask.getName());
        todo.setDescription(updatedTask.getDescription());
        return todo;
    }

    @Override
    public void deleteTaskById(Long id) {
        String errorMessage = "Delete error. Task by ID=" + id + " cannot be found.";
        Todo todo = getTaskById(id, errorMessage);
        todos.remove(todo);
    }

    private Todo getTaskById(Long id, String errorMessage) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst().orElseThrow(() -> {
            log.error(errorMessage);
            return new TodoNotFoundException(errorMessage);
        });
    }
}
