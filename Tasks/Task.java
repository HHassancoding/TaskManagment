package com.example.TaskManagement.Tasks;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String taskName;
    @NonNull
    private String taskDescription;
    @NonNull
    private LocalDate dueDate;
    @NonNull
    private Boolean completed;


    public void updateTask(Task updateTask){
        this.taskName = updateTask.getTaskName();
        this.taskDescription = updateTask.getTaskDescription();
        this.dueDate = updateTask.getDueDate();
        this.completed = updateTask.getCompleted();
    }



}
