package com.darknash.trackerListApp.Controller;

import com.darknash.trackerListApp.dto.AppResponse;
import com.darknash.trackerListApp.dto.CreateTaskRequest;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    private AppResponse<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        TaskResponse taskResponse = taskService.createTaks(request);

        return AppResponse.<TaskResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskResponse)
                .build();

    }

    @GetMapping
    private AppResponse<List<TaskResponse>> getTask() {
        List<TaskResponse> tasks = taskService.getTasks();

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
