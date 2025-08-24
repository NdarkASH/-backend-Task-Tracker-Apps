package com.darknash.trackerListApp.services;

import com.darknash.trackerListApp.dto.*;
import com.darknash.trackerListApp.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponse> getTasks(UUID taskListId);
    TaskResponse createTaks(UUID taskListId, Task task);
    TaskResponse findTaskById(UUID id);
    void deleteTaskById(UUID id);
    TaskResponse updateTask(UUID id, CreateTaskRequest request);
}
