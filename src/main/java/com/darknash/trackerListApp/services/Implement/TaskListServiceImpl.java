package com.darknash.trackerListApp.services.Implement;


import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.constants.TaskStatus;
import com.darknash.trackerListApp.dto.CreateTaskRequest;
import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.entities.Task;
import com.darknash.trackerListApp.entities.TaskList;
import com.darknash.trackerListApp.repositories.TaskListRepository;
import com.darknash.trackerListApp.services.TaskListService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskListResponse> getTaksLists() {
        List<TaskList> taskLists = taskListRepository.findAll();

        return taskLists.stream()
                .map(this::toResponseTaskList)
                .toList();
    }


    @Override
    public TaskListResponse getCreateTaskListsRequest(CreateTaskListsRequest request) {

        TaskList taskList = new TaskList();
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        taskList.setTasks(
        Optional.ofNullable(request.getTaks())
                .map(tasks -> tasks
                        .stream()
                        .map(TaskMapper::toEntity)
                        .toList())
                .orElse(null)
        );
        taskListRepository.save(taskList);

        return toResponseTaskList(taskList);

    }

    @Override
    public TaskListResponse findTaksListById(UUID id) {
        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskList with id " + id + " not found"));

        return toResponseTaskList(taskList);
    }

    @Override
    public void deleteTaksListById(UUID id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public TaskListResponse getUpdateTaksListRequest(UUID id, CreateTaskListsRequest request) {

        TaskList taskList = taskListRepository.findById(id).orElseThrow(() -> new RuntimeException("TaskList with id " + id + " not found"));
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        taskList.setTasks(setTaskToTaskList(request.getTaks()));

        if (taskList.getTasks().isEmpty()) {
            throw new EntityNotFoundException("TaskList with id " + id + " not found");
        }

        if (!Objects.equals(taskList.getId(), id)) {
            throw new IllegalArgumentException("TaskList with id " + id + " is not the same");
        }

        TaskList existingTaskList = taskListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskList with id " + id + " not found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setTasks(taskList.getTasks());
        taskListRepository.save(existingTaskList);

        return toResponseTaskList(taskList);
    }

    private List<Task> setTaskToTaskList(List<CreateTaskRequest> request) {
       return request.stream()
               .map(TaskMapper::toEntity)
               .collect(Collectors.toList());
    }


    private Double calculateProgressTask(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return 0.0;
        }

        long closedTasksCount = tasks
                .stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus()).count();

        return (double) closedTasksCount / tasks.size();
    }


    private TaskListResponse toResponseTaskList (TaskList taskList) {
        return TaskListResponse.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .taks(taskList.getTasks())
                .progress(calculateProgressTask(taskList.getTasks()))
                .build();
    }


}
