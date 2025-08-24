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
@RequestMapping(path = "/api/task-lists")
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

    @GetMapping(path = "/{task_list_id}")
    private AppResponse<TaskListResponse> getTaskListResponseById(@PathVariable UUID task_list_id) {
        TaskListResponse taskListResponse = taskListService.findTaksListById(task_list_id);

        return AppResponse.<TaskListResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @PutMapping(path = "/{taks_list_id}")
    private AppResponse<TaskListResponse> updateTaskList(@PathVariable UUID taks_list_id, CreateTaskListsRequest request) {
        TaskListResponse taskListResponse = taskListService.getUpdateTaksListRequest(taks_list_id, request);

        return AppResponse.<TaskListResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(taskListResponse)
                .build();
    }

    @DeleteMapping(path = "{task_list_id}")
    private AppResponse<Void> deleteTaskList(@PathVariable UUID task_list_id) {
        taskListService.deleteTaksListById(task_list_id);

        return AppResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .data(null)
                .build();
    }



}
