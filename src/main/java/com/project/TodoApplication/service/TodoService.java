package com.project.TodoApplication.service;

import com.project.TodoApplication.model.Todo;
import com.project.TodoApplication.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }

    public void updateStatus(long id, int statusId) {
        todoRepository.updateStatus(id, statusId);
    }

    public void updateTitle(long id, String title) {
        todoRepository.updateTitle(id, title);
    }

    public void updateDescription(long id, String description) {
        todoRepository.updateDescription(id, description);
    }
}
