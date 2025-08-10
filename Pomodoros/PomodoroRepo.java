package com.example.TaskManagement.Pomodoros;

import com.example.TaskManagement.Tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PomodoroRepo extends JpaRepository<Pomodoro, Long> {
    void deleteAllByTask(Task task);
    List<Pomodoro> findAllByTaskId(long taskId);
}
