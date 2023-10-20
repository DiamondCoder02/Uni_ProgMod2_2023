package com.lesson7.Services;

import com.lesson7.Todo;

// This interface is implemented in TodoServicesImplementation.java
public interface TodoServices {
	Object findAllTasks();

    Todo findTaskById(Long id);

    void save(Todo task);

    Todo updateTaskById(Long id, Todo updatedTask);

    void deleteTaskById(Long id);
}
