package com.darknash.trackerListApp.repositories;

import com.darknash.trackerListApp.dto.TaskResponse;
import com.darknash.trackerListApp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);

}
