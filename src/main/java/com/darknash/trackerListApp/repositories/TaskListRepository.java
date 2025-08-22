package com.darknash.trackerListApp.repositories;

import com.darknash.trackerListApp.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
    TaskList findByIdContaining(UUID id);
}
