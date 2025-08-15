package com.example.TaskManagement.Pomodoros;

import com.example.TaskManagement.Tasks.Task;
import com.example.TaskManagement.Tasks.TaskRepo;
import org.springframework.web.bind.annotation.*;

@RestController
public class PomodoroController {

    PomodoroService pomodoroService;
    TaskRepo taskRepo;
    PomodoroRepo pomodoroRepo;

    public PomodoroController(PomodoroService pomodoroService, TaskRepo taskRepo, PomodoroRepo pomodoroRepo )
        {
            this.pomodoroService = pomodoroService;
            this.taskRepo = taskRepo;
            this.pomodoroRepo = pomodoroRepo;
        }

    @PostMapping("/start/{taskId}")
    public PomodoroDTO startPomodoro(@PathVariable long taskId, @RequestParam int duration){
        Task foundTask = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return pomodoroService.startPomodoro(foundTask ,duration);
    }

    @PatchMapping("/end/{pomodoroId}")
    public String endPomodoro(@PathVariable long pomodoroId){
        return pomodoroService.stopPomodoro(pomodoroId);
    }

    @GetMapping("/studyTime/{taskId}")
    public String getStudyTime(@PathVariable long taskId){
        return pomodoroService.getPomodoros(taskId);
    }

}
