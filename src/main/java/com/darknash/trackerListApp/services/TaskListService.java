package com.darknash.trackerListApp.services;

import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    List<TaskListResponse> getTaksLists();
    TaskListResponse getCreateTaskListsRequest(CreateTaskListsRequest request);
    TaskListResponse findTaksListById(UUID id);
    void deleteTaksListById(UUID id);
    TaskListResponse getUpdateTaksListRequest(UUID id, CreateTaskListsRequest request);
}
