package com.darknash.trackerListApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTaskListsRequest {

    private String title;

    private String description;

    @Builder.Default
    private List<CreateTaskRequest> taks = new ArrayList<>();
}
