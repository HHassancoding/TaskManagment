package com.example.TaskManagement.Pomodoros;

import com.example.TaskManagement.Tasks.Task;
import com.example.TaskManagement.Tasks.TaskRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PomodoroService {

    @Autowired
    private PomodoroRepo pomodoroRepo;
    @Autowired
    private PomodoroDTOConverter pomodoroDTOConverter;


    public String startPomodoro(Task task, int duration){
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setStartTime(LocalDateTime.now());
        pomodoro.setDuration(duration);
        pomodoro.setStatus("IN_PROGRESS");
        pomodoro.setTask(task);
        pomodoroRepo.save(pomodoro);
        return "STARTED";
    }

    public String stopPomodoro(long id){
        return pomodoroRepo.findById(id).map(pomodoro -> {
            pomodoro.setEndTime(LocalDateTime.now());
            Duration duration = Duration.between(pomodoro.getStartTime(), pomodoro.getEndTime());
            pomodoro.setStatus("COMPLETED");
            pomodoro.setDuration((int) duration.toMinutes());
            pomodoroRepo.save(pomodoro);
            return "Stopped Successfully";
        }).orElseThrow(() -> new RuntimeException("Pomodoro Not Found"));
    }


    AtomicInteger totalStudyTime = new AtomicInteger();
    public String getPomodoros(long taskId){
        pomodoroRepo.findAllByTaskId(taskId).forEach(pomodoro -> {
            totalStudyTime.addAndGet(pomodoro.getDuration());
        });
        return totalStudyTime + " Study Time";
    }

}
