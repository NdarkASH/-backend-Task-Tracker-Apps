package com.darknash.trackerListApp.services;

import com.darknash.trackerListApp.dto.*;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponse> getTasks();
    TaskResponse createTaks(CreateTaskRequest request);
    TaskResponse findTaskById(UUID id);
    void deleteTaskById(UUID id);
    TaskResponse updateTask(UUID id, UpdateTaskRequest request);
}
