package com.darknash.trackerListApp.Controller;

import com.darknash.trackerListApp.Utils.TaskMapper;
import com.darknash.trackerListApp.dto.AppResponse;
import com.darknash.trackerListApp.dto.CreateTaskRequest;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.Task;
import com.darknash.trackerListApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/api/task-lists/{task_list_id}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @PostMapping
    private AppResponse<TaskResponse> createTask(@PathVariable UUID task_list_id, @RequestBody CreateTaskRequest request) {
        log.info("Create task: {}", request);

        Task task = TaskMapper.toEntity(request);
        TaskResponse taskResponse = taskService.createTaks(task_list_id, task);

        return AppResponse.<TaskResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskResponse)
                .build();

    }

    @GetMapping
    private AppResponse<List<TaskResponse>> getAllTask(@PathVariable UUID task_list_id) {
        List<TaskResponse> tasks = taskService.getTasks(task_list_id);

        return AppResponse.<List<TaskResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(tasks)
                .build();
    }
    @GetMapping("/{id}")
    private AppResponse<TaskResponse> findTaskByTaskId(@RequestParam UUID id) {
        TaskResponse taskResponse = taskService.findTaskById(id);

        return AppResponse.<TaskResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskResponse)
                .build();
    }

    @PutMapping
    private AppResponse<TaskResponse> updateTask(@RequestParam UUID id, @RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.updateTask(id, request);

        return AppResponse.<TaskResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskResponse)
                .build();
    }

    @DeleteMapping()
    private AppResponse<Void> deleteTask(@RequestParam UUID id) {
        taskService.deleteTaskById(id);

        return AppResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(null)
                .build();
    }



}
