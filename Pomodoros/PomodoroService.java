package com.example.TaskManagement.Pomodoros;

import com.example.TaskManagement.SimpleData.RawData;
import com.example.TaskManagement.SimpleData.RawDataRepo;
import com.example.TaskManagement.Tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PomodoroService {

    private final PomodoroRepo pomodoroRepo;
    private final PomodoroDTOConverter pomodoroDTOConverter;
    private final RawDataRepo rawDataRepo;

    @Autowired
    public PomodoroService(PomodoroRepo pomodoroRepo,
                           PomodoroDTOConverter pomodoroDTOConverter,
                           RawDataRepo rawDataRepo) {
        this.pomodoroRepo = pomodoroRepo;
        this.pomodoroDTOConverter = pomodoroDTOConverter;
        this.rawDataRepo = rawDataRepo;
    }

    // Start a new Pomodoro session
    public PomodoroDTO startPomodoro(Task task, int duration) {
        LocalDateTime now = LocalDateTime.now();

        // 1️⃣ Save Pomodoro entity
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setStartTime(now);
        pomodoro.setDuration(duration); // planned duration
        pomodoro.setStatus("IN_PROGRESS");
        pomodoro.setTask(task);
        Pomodoro savedPomodoro = pomodoroRepo.save(pomodoro);

        // 2️⃣ Save RawData entity for tracking
        RawData rawData = new RawData();
        rawData.setUserId(task.getUser());  // assign user ID
        rawData.setStartTime(now);
        rawData.setDuration(0);                    // actual duration starts at 0
        rawData.setCompleted(false);
        rawData.setAbandonedEarly(false);
        rawDataRepo.save(rawData);

        return pomodoroDTOConverter.toDTOConverter(savedPomodoro);
    }

    // Stop a Pomodoro session
    public String stopPomodoro(long id) {
        return pomodoroRepo.findById(id).map(pomodoro -> {

            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(pomodoro.getStartTime(), endTime);

            // 1️⃣ Update Pomodoro entity
            pomodoro.setEndTime(endTime);
            pomodoro.setDuration((int) duration.toMinutes());
            pomodoro.setStatus("COMPLETED");
            pomodoroRepo.save(pomodoro);

            // 2️⃣ Update RawData entity
            RawData rawData = rawDataRepo
                    .findTopByUserIdAndCompletedFalseOrderByStartTimeDesc(pomodoro.getTask().getUser())
                    .orElseThrow(() -> new RuntimeException("RawData session not found"));

            rawData.setDuration((int) duration.toMinutes());
            rawData.setCompleted(true);
            rawData.setAbandonedEarly(false);
            rawDataRepo.save(rawData);

            return "Stopped Successfully";
        }).orElseThrow(() -> new RuntimeException("Pomodoro Not Found"));
    }

    // Calculate total study time for a task
    AtomicInteger totalStudyTime = new AtomicInteger();
    public String getPomodoros(long taskId) {
        totalStudyTime.set(0); // reset counter
        pomodoroRepo.findAllByTaskId(taskId).forEach(pomodoro ->
                totalStudyTime.addAndGet(pomodoro.getDuration())
        );
        return totalStudyTime + " Study Time";
    }
}
