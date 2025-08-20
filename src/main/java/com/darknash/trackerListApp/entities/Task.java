package com.darknash.trackerListApp.entities;

import com.darknash.trackerListApp.constants.TaksPriority;
import com.darknash.trackerListApp.constants.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    private UUID id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaksPriority priority;

    private TaskStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taks_list_id")
    private TaskList taskList;
}
