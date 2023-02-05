package com.todolist.todolist.service;

import com.todolist.todolist.dto.todolist.ListRequest;
import com.todolist.todolist.dto.todolist.ListResponse;
import com.todolist.todolist.model.ListEntity;
import com.todolist.todolist.repository.ListRepository;
import com.todolist.todolist.repository.TaskRepository;
import com.todolist.todolist.utils.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ListService(ListRepository listRepository,
            TaskRepository taskRepository) {
        this.listRepository = listRepository;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public ListEntity getList(Integer id) {
        return listRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ToDoList", "id", id));
    }

    @Transactional
    public List<ListResponse> getLists() {
        return listRepository.findAll().stream()
                .map(ListResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public ListResponse createList(ListRequest request) {
        return ListResponse.fromEntity(listRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public ListResponse editList(Integer id, ListRequest request) {
        if (!listRepository.existsById(id)) {
            throw new NotFoundException("List", "id", id);
        }
        return ListResponse.fromEntity(listRepository.save(fromSimpleDtoEdit(request, id)));
    }

    @Transactional
    public void deleteList(Integer id) {
        if (listRepository.existsById(id)) {

            taskRepository.deleteAllByListId(id);
            listRepository.deleteById(id);
        } else {
            throw new NotFoundException("List", "id", id);
        }
    }

    public ListEntity fromSimpleDtoEdit(ListRequest dto, Integer id) {
        return ListEntity.builder()
                .id(id)
                .name(dto.getName())
                .priority(dto.getPriority())
                .build();
    }

    public ListEntity fromSimpleDto(ListRequest dto) {
        return ListEntity.builder()
                .name(dto.getName())
                .priority(dto.getPriority())
                .build();
    }
}
