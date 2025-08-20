package com.darknash.trackerListApp.services.Implement;

import com.darknash.trackerListApp.dto.*;
import com.darknash.trackerListApp.repositories.TaskRepository;
import com.darknash.trackerListApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
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
    public List<Task> getTasks() {
        return List.of();
    }

    @Override
    public CreateTaksResponse createTaks(CreateTaskRequest request) {
        return null;
    }

    @Override
    public CreateTaskListResponse findTaskById(UUID id) {
        return null;
    }

    @Override
    public void deleteTaskById(UUID id) {

    }

    @Override
    public UpdateTaskResponse updateTask(UUID id, UpdateTaskRequest request) {
        return null;
    }
}
