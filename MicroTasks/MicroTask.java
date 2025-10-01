package com.example.TaskManagement.MicroTasks;

import com.example.TaskManagement.Tasks.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MicroTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Task task;

    @NonNull
    private String description;

    @NonNull
    private Status status = Status.NOT_COMPLETED;


    public MicroTask(@NonNull String description, @NonNull Status status) {
        this.description = description;
    }
}
