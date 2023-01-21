package com.todolist.todolist.service;

import com.todolist.todolist.dto.task.TaskRequest;
import com.todolist.todolist.dto.task.TaskResponse;
import com.todolist.todolist.model.TaskEntity;
import com.todolist.todolist.repository.TaskRepository;
import com.todolist.todolist.utils.BadRequestException;
import com.todolist.todolist.utils.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskEntity getTask(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task", "id", id));
    }

    @Transactional
    public List<TaskResponse> getTasks() {
        return taskRepository.findAll().stream()
                .map(TaskResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        if (taskRepository.existsById(request.getId())) {
            throw new BadRequestException("Istnieje już zadanie o takim id");
        }
        return TaskResponse.fromEntity(taskRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public TaskResponse editTask(Integer id, TaskRequest request) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task", "id", id);
        }
        if (!id.equals(request.getId())) {
            throw new BadRequestException("Id w urlu nie zgadza się z id w requeście");
        }
        return TaskResponse.fromEntity(taskRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public void deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new NotFoundException("Task", "id", id);
        }
    }

    public TaskEntity fromSimpleDto(TaskRequest dto) {
        return TaskEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .deadline(dto.getDeadline())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .listId(dto.getListId())
                .build();
    }
}
