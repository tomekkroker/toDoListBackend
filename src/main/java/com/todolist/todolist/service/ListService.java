package com.todolist.todolist.service;

import com.todolist.todolist.dto.todolist.ListRequest;
import com.todolist.todolist.dto.todolist.ListResponse;
import com.todolist.todolist.model.ListEntity;
import com.todolist.todolist.repository.ListRepository;
import com.todolist.todolist.repository.UserRepository;
import com.todolist.todolist.utils.BadRequestException;
import com.todolist.todolist.utils.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListService {

    private final ListRepository listRepository;
    private final UserRepository userRepository;

    public ListService(ListRepository listRepository, UserRepository userRepository) {
        this.listRepository = listRepository;
        this.userRepository = userRepository;
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
        if (listRepository.existsById(request.getId())) {
            throw new BadRequestException("Istnieje już lista o takim id");
        }
        if (!userRepository.existsByLogin(request.getUserLogin())) {
            throw new BadRequestException("Nie istnieje taki użytkownik o takim loginie");
        }
        return ListResponse.fromEntity(listRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public ListResponse editList(Integer id, ListRequest request) {
        if (!listRepository.existsById(id)) {
            throw new NotFoundException("List", "id", id);
        }
        if (!id.equals(request.getId())) {
            throw new BadRequestException("Id w urlu nie zgadza się z id w requeście");
        }
        request.setUserLogin(listRepository.findById(id).get().getUserLogin());
        return ListResponse.fromEntity(listRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public void deleteList(Integer id) {
        if (listRepository.existsById(id)) {
            listRepository.deleteById(id);
        } else {
            throw new NotFoundException("List", "id", id);
        }
    }

    public ListEntity fromSimpleDto(ListRequest dto) {
        return ListEntity.builder()
            .id(dto.getId())
                .name(dto.getName())
                .priority(dto.getPriority())
                .userLogin(dto.getUserLogin())
                .build();
    }
}
