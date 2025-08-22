package com.darknash.trackerListApp.services.Implement;


import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.constants.TaskStatus;
import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.Task;
import com.darknash.trackerListApp.entities.TaskList;
import com.darknash.trackerListApp.repositories.TaskListRepository;
import com.darknash.trackerListApp.repositories.TaskRepository;
import com.darknash.trackerListApp.services.TaskListService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskRepository taskRepository;

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
        taskList.setTasks(setTaskToTaskList(taskList, request));
        taskListRepository.save(taskList);


        return TaskListResponse.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .taks(setTaskToTaskList(taskList))
                .progress(calculateProgressTask(taskList.getTasks()))
                .build();

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
        TaskList taskList = taskListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("TaskList with id " + id + " not found"));
        taskList.setTitle(request.getTitle());
        taskList.setDescription(request.getDescription());
        taskList.setTasks(setTaskToTaskList(taskList, request));
        taskListRepository.save(taskList);

        return toResponseTaskList(taskList);
    }

    private List<TaskResponse> setTaskToTaskList(TaskList taskList) {
        return  taskList.getTasks()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
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


    private List<Task> setTaskToTaskList(TaskList taskList, CreateTaskListsRequest request) {
        List<Task> tasks = Optional.ofNullable(request.getTaks())
                .map(taskId -> taskRepository.findAllById(request.getTaks()))
                .orElseThrow(()-> new RuntimeException("TaskList with id " + request.getTaks() + " not found"));
        taskList.setTasks(tasks);
        return tasks;
    }

    private TaskListResponse toResponseTaskList (TaskList taskList) {
        return TaskListResponse.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .taks(setTaskToTaskList(taskList))
                .progress(calculateProgressTask(taskList.getTasks()))
                .build();
    }


}
