package com.darknash.trackerListApp.dto;

import com.darknash.trackerListApp.constants.TaksPriority;
import com.darknash.trackerListApp.constants.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {
    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaksPriority priority;

    private TaskStatus status;

    private LocalDateTime updatedAt;
}
