package com.darknash.trackerListApp.services;

import com.darknash.trackerListApp.dto.*;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getTasks();
    CreateTaksResponse createTaks(CreateTaskRequest request);
    CreateTaskListResponse findTaskById(UUID id);
    void deleteTaskById(UUID id);
    UpdateTaskResponse updateTask(UUID id, UpdateTaskRequest request);
}
