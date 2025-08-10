package com.example.TaskManagement.Pomodoros;

import org.springframework.stereotype.Component;

@Component
public class PomodoroDTOConverter {

    public PomodoroDTO toDTOConverter(Pomodoro pomodoro){
        return PomodoroDTO;
    }
    public Pomodoro toEntityConverter(PomodoroDTO dto){
        return dto;
    }
}
