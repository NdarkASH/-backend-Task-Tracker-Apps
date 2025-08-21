package com.darknash.trackerListApp.services.Implement;

import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.dto.*;
import com.darknash.trackerListApp.entities.Task;
import com.darknash.trackerListApp.exceptions.NotFoundException;
import com.darknash.trackerListApp.repositories.TaskRepository;
import com.darknash.trackerListApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponse> getTasks() {
        List<Task> tasks =taskRepository.findAll();

        return tasks.stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse createTaks(CreateTaskRequest request) {
        Task task = new Task();
        TaskMapper.toEntity(task, request);
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public TaskResponse findTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        return TaskMapper.toResponse(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    @Override
    public TaskResponse updateTask(UUID id, CreateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        Task savedTask = taskRepository.save(task);
        return TaskMapper.toResponse(savedTask);
    }
}
