package com.example.TaskManagement.Pomodoros;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PomodoroDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PomodoroDTO toDTOConverter(Pomodoro pomodoro){
        return modelMapper.map(pomodoro, PomodoroDTO.class);
    }
    public Pomodoro toEntityConverter(PomodoroDTO dto){
        return modelMapper.map(dto, Pomodoro.class);
    }
}
