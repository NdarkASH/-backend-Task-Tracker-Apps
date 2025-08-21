package com.darknash.trackerListApp.Utils;

import com.darknash.trackerListApp.dto.CreateTaskListsRequest;
import com.darknash.trackerListApp.dto.TaskListResponse;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.TaskList;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class TaskListMapper {
    public TaskListResponse toTaskListResponse(TaskList taskLit, List<TaskResponse> taskResponses) {
        return TaskListResponse.builder()
                .id(taskLit.getId())
                .title(taskLit.getTitle())
                .description(taskLit.getDescription())
                .taks(taskResponses)
                .build();
    }
}
