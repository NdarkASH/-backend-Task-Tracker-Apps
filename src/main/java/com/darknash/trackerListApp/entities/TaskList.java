package com.darknash.trackerListApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskList {
    @Id
    private UUID id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Task> tasks;
}
