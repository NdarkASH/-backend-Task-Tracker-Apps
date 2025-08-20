package com.darknash.trackerListApp.dto;

import com.darknash.trackerListApp.constants.TaksPriority;
import com.darknash.trackerListApp.constants.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskResponse {
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaksPriority priority;

    private TaskStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
