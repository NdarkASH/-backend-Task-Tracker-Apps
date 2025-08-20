package com.darknash.trackerListApp.services.Implement;

import com.darknash.trackerListApp.dto.CreateTaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.UpdateTaskListRequest;
import com.darknash.trackerListApp.entities.TaskList;
import com.darknash.trackerListApp.repositories.TaskListRepository;
import com.darknash.trackerListApp.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> getTaksLists() {
        return taskListRepository.findAll();
    }

    @Override
    public CreateTaskListResponse getCreateTaskListsRequest(CreateTaskListsRequest request) {
        TaskList taskList = new TaskList();
        taskList.setId(UUID.randomUUID());
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        taskList.setTasks(request.getTaks());

        return null;
    }

    @Override
    public String findTaksListById(UUID id) {
        return "";
    }

    @Override
    public void deleteTaksListById(UUID id) {

    }

    @Override
    public UpdateTaskListRequest getUpdateTaksListRequest(UUID id, UpdateTaskListRequest request) {
        return null;
    }
}
