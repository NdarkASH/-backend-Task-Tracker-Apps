package com.darknash.trackerListApp.dto;

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
public class UpdateTaskListResponse {
    private UUID id;

    private String title;

    private String description;

    private List<UpdateTaskResponse> taks;
}
