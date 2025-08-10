package com.example.TaskManagement.Pomodoros;


import com.example.TaskManagement.Tasks.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pomodoro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @NonNull
    private LocalDateTime startTime;
    @NonNull
    private LocalDateTime endTime;
    @NonNull
    private int duration;
    @NonNull
    private String status;

}
