package com.example.TaskManagement.MicroTasks;

import com.example.TaskManagement.Tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MicroTaskRepo extends JpaRepository<MicroTask, Long> {
    List<MicroTask> findAllByTask(Task task);
}
