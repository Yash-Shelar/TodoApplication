package com.project.TodoApplication.controller;

import com.project.TodoApplication.dto.TodoDto;
import com.project.TodoApplication.enums.TodoStatus;
import com.project.TodoApplication.model.Todo;
import com.project.TodoApplication.service.FilterService;
import com.project.TodoApplication.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    private final FilterService filterService;

    public TodoController(TodoService todoService, FilterService filterService) {
        this.todoService = todoService;
        this.filterService = filterService;
    }

    @GetMapping
    public String todos(Model model) {
        var todos = ((Collection<Todo>) todoService.getAllTodos())
                .stream()
                .sorted(Comparator.comparing(Todo::getCreatedAt))
                .filter(todo -> {
                    boolean allow = true;

                    if (filterService.getStatus().isPresent())
                        allow = filterService.getStatus().get() == TodoStatus.fromInteger(todo.getStatus_id());
                    if (filterService.getDate().isPresent())
                        allow = allow && (filterService.getDate().get().isEqual(todo.getCreatedAt().toLocalDate()));
                    if (filterService.getTimeFrom().isPresent())
                        allow = allow && (todo.getCreatedAt().toLocalTime().isAfter(
                                filterService.getTimeFrom().get().minusSeconds(1)
                        ));
                    if (filterService.getTimeTo().isPresent())
                        allow = allow && (todo.getCreatedAt().toLocalTime().isBefore(
                                filterService.getTimeTo().get().plusSeconds(1)
                        ));

                    return allow;
                })
                .toList();

        model.addAttribute("todos", todos);
        model.addAttribute("TodoStatus", TodoStatus.class);

        return "todo";
    }

    @PostMapping("/add")
    public String addTodo(TodoDto todoDto, Model model) {
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setStatus_id(TodoStatus.CREATED.ordinal());
        todo.setCreatedAt(LocalDateTime.now());

        todoService.saveTodo(todo);

        return "redirect:/todo";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable long id, Model model) {
        todoService.deleteTodo(id);

        return "redirect:/todo";
    }

    @PostMapping("/status-change/{id}")
    public String changeStatus(
            @PathVariable long id,
            @RequestParam TodoStatus newStatus,
            Model model) {

        todoService.updateStatus(id, newStatus.ordinal());
        return "redirect:/todo";
    }
}
