package com.lesson7.Services;

import com.lesson7.Todo;

public interface TodoServices {
	Object findAllTasks();

    Todo findTaskById(Long id);

    void save(Todo task);

    Todo updateTaskById(Long id, Todo updatedTask);

    void deleteTaskById(Long id);
}
