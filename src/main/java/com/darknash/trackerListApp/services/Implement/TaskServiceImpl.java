package com.darknash.trackerListApp.services.Implement;

import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.constants.TaksPriority;
import com.darknash.trackerListApp.constants.TaskStatus;
import com.darknash.trackerListApp.dto.*;
import com.darknash.trackerListApp.entities.Task;
import com.darknash.trackerListApp.entities.TaskList;
import com.darknash.trackerListApp.exceptions.NotFoundException;
import com.darknash.trackerListApp.repositories.TaskListRepository;
import com.darknash.trackerListApp.repositories.TaskRepository;
import com.darknash.trackerListApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskResponse> getTasks(UUID taskListId) {
        List<Task> tasks = taskRepository.findByTaskListId(taskListId);

        return tasks.stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse createTaks(UUID taskListId, Task task) {
       if (task.getId() != null) {
           throw new NotFoundException("Task with id " + taskListId + " already exists");
       }
       if (task.getTitle() == null || task.getDescription() == null) {
           throw new NotFoundException("Task list with id " + taskListId + " not found");
       }

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new NotFoundException("Task list with id " + taskListId + " not found"));


        TaksPriority taksPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaksPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;
        task.setStatus(taskStatus);
        task.setPriority(taksPriority);
        task.setTaskList(taskList);
        task.setDueDate(LocalDateTime.now());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }

    @Override
    public TaskResponse findTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        return TaskMapper.toResponse(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    @Override
    public TaskResponse updateTask(UUID id, CreateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found"));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(LocalDateTime.now());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        taskRepository.save(task);
        return TaskMapper.toResponse(task);
    }
}
