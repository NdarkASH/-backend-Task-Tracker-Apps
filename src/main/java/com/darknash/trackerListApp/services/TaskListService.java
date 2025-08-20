package com.darknash.trackerListApp.services;

import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.UpdateTaskListRequest;
import com.darknash.trackerListApp.entities.TaskList;

import java.util.List;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> getTaksLists();
    TaskListResponse getCreateTaskListsRequest(CreateTaskListsRequest request);
    String findTaksListById(UUID id);
    void deleteTaksListById(UUID id);
    UpdateTaskListRequest getUpdateTaksListRequest(UUID id, UpdateTaskListRequest request);
}
