package com.example.TaskManagement.Tasks;

import com.example.TaskManagement.MicroTasks.MicroTask;
import com.example.TaskManagement.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder({
        "id", "taskName", "taskDescription", "dueDate", "completed" , "microTaskList"
})

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor

@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "task")
    @JsonManagedReference
    private List<MicroTask> microTaskList = new ArrayList<>();


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
