package com.todolist.todolist.controller;

import com.todolist.todolist.dto.task.TaskRequest;
import com.todolist.todolist.dto.task.TaskResponse;
import com.todolist.todolist.service.TaskService;
import com.todolist.todolist.utils.BasicResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Integer id) {
        return ResponseEntity.ok(TaskResponse.fromEntity(taskService.getTask(id)));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<TaskResponse>> getListTasks(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getListTasks(id));
    }


    @PostMapping
    public ResponseEntity<BasicResponse> createTask(@Valid @RequestBody TaskRequest request) {
        var dto = taskService.createTask(request);
        return ResponseEntity.created(UriBuilder.getUri("tasks/{id}", dto.getId())).body(
                new BasicResponse(true, "Pomyślnie utworzono zadanie", dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicResponse> editTask(@PathVariable Integer id,
            @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(new BasicResponse(
                true,
                "Pomyślnie edytowano zadanie",
                taskService.editTask(id, request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new BasicResponse(true, "Pomyślnie usunięto zadanie"));
    }
}
