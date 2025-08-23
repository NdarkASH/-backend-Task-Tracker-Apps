package com.darknash.trackerListApp.dto;

import com.darknash.trackerListApp.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskListResponse {

    private UUID id;

    private String title;

    private String description;

    private Double progress;

    private List<Task> taks;
}
