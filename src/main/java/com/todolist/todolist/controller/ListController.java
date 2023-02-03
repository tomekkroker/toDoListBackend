package com.todolist.todolist.controller;

import com.todolist.todolist.dto.todolist.ListRequest;
import com.todolist.todolist.dto.todolist.ListResponse;
import com.todolist.todolist.service.ListService;
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
@RequestMapping("/lists")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListResponse> getList(@PathVariable Integer id) {
        return ResponseEntity.ok(ListResponse.fromEntity(listService.getList(id)));
    }

    @GetMapping
    public ResponseEntity<List<ListResponse>> getLists() {
        return ResponseEntity.ok(listService.getLists());
    }

    @PostMapping
    public ResponseEntity<BasicResponse> createList(@Valid @RequestBody ListRequest request) {
        var dto = listService.createList(request);
        return ResponseEntity.created(UriBuilder.getUri("/lists", dto.getId())).body(
                new BasicResponse(true, "Pomyślnie utworzono listę", dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicResponse> editList(@PathVariable Integer id,
            @Valid @RequestBody ListRequest request) {
        return ResponseEntity.ok(new BasicResponse(
                true,
                "Pomyślnie edytowano listę",
                listService.editList(id, request)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> deleteListTask(@PathVariable Integer id) {
        listService.deleteList(id);
        return ResponseEntity.ok(new BasicResponse(true, "Pomyślnie usunięto listę"));
    }
}
