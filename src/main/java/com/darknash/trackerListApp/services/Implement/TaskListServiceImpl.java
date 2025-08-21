package com.darknash.trackerListApp.services.Implement;


import com.darknash.trackerListApp.Utils.TaskListMapper;
import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.TaskList;
import com.darknash.trackerListApp.repositories.TaskListRepository;
import com.darknash.trackerListApp.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskListResponse> getTaksLists() {
        List<TaskList> taskLists = taskListRepository.findAll();

        return taskLists.stream()
                .map(taskList -> {
                    TaskListResponse taskListResponse = new TaskListResponse();
                    taskListResponse.setId(taskList.getId());
                    taskListResponse.setTitle(taskList.getTitle());
                    taskListResponse.setDescription(taskList.getDescription());
                    taskListResponse.setTaks(getTaskInTaskList(taskList));
                    return taskListResponse;
                })
                .toList();
    }

    @Override
    public TaskListResponse getCreateTaskListsRequest(CreateTaskListsRequest request) {
        TaskList taskList = new TaskList();
        TaskListResponse taskListResponse =
    }

    @Override
    public TaskListResponse findTaksListById(UUID id) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskList with id " + id + " not found"));
        List<TaskResponse> taskResponseList = getTaskInTaskList(taskList);
        return TaskListMapper::toTaskListResponse(taskList);
    }

    @Override
    public void deleteTaksListById(UUID id) {

    }

    @Override
    public TaskListResponse getUpdateTaksListRequest(UUID id, CreateTaskListsRequest request) {
        return null;
    }

    private List<TaskResponse> getTaskInTaskList(TaskList taskList) {
        return  taskList.getTasks()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }
}
