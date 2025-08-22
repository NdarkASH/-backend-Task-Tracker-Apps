package com.darknash.trackerListApp.Controller;

import com.darknash.trackerListApp.dto.AppResponse;
import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/task-list")
public class TaskListController {

    private final TaskListService taskListService;


    @PostMapping
    private AppResponse<TaskListResponse> createTaskListResponse(@RequestBody CreateTaskListsRequest request) {
        TaskListResponse taskListResponse = taskListService.getCreateTaskListsRequest(request);

        return AppResponse.<TaskListResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @GetMapping
    private AppResponse<List<TaskListResponse>> getTaskListResponse() {
        List<TaskListResponse> taskListResponse = taskListService.getTaksLists();

        return AppResponse.<List<TaskListResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @GetMapping("/{id}")
    private AppResponse<TaskListResponse> getTaskListResponseById(@RequestParam UUID id) {
        TaskListResponse taskListResponse = taskListService.findTaksListById(id);

        return AppResponse.<TaskListResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @PutMapping(path = "/{id}")
    private AppResponse<TaskListResponse> updateTaskList(@PathVariable UUID id, CreateTaskListsRequest request) {
        TaskListResponse taskListResponse = taskListService.getUpdateTaksListRequest(id, request);

        return AppResponse.<TaskListResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @DeleteMapping
    private AppResponse<Void> deleteTaskList(@RequestParam UUID id) {
        taskListService.deleteTaksListById(id);

        return AppResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .data(null)
                .build();
    }



}
