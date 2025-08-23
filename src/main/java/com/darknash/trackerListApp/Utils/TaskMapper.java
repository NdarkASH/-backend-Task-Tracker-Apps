package com.darknash.trackerListApp.Utils;

import com.darknash.trackerListApp.dto.CreateTaskRequest;
import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.Task;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskMapper {
    public Task toEntity(CreateTaskRequest request) {
        Task task = new Task();

        if (request.getId() != null) {
            task.setId(request.getId());
        }

        task.setTitle(request.getTitle());
        task.setStatus(request.getStatus());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setPriority(request.getPriority());
        task.setCreatedAt(request.getCreatedAt());
        task.setUpdatedAt(request.getUpdatedAt());
        return task;
    }

    public TaskResponse toResponse (Task task) {

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(task.getStatus())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();

    }
}
