package com.project.TodoApplication.controller;

import com.project.TodoApplication.dto.FilterDto;
import com.project.TodoApplication.dto.TodoDto;
import com.project.TodoApplication.enums.TodoStatus;
import com.project.TodoApplication.service.FilterService;
import com.project.TodoApplication.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/todo")
public class TodoApiController {
    private final TodoService todoService;
    private final FilterService filterService;
    private static final Logger logger = Logger.getLogger(TodoApiController.class.getName());

    public TodoApiController(TodoService todoService, FilterService filterService) {
        this.todoService = todoService;
        this.filterService = filterService;
    }

    @PatchMapping("/update-title-description/{id}")
    public ResponseEntity<?> changeTitle(@PathVariable long id, @RequestBody TodoDto todoDto) {
        var title = todoDto.getTitle();
        if (title != null)
            todoService.updateTitle(id, title);

        var description = todoDto.getDescription();
        if (description != null)
            todoService.updateDescription(id, description);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/apply-filter")
    public void applyFilter(@RequestBody FilterDto filterDto) {
        filterService.setFilter(filterDto);
    }

    @PostMapping("/reset-filter")
    public void resetFilter() {
        filterService.resetFilter();
    }
}
