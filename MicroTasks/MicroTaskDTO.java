package com.example.TaskManagement.MicroTasks;

import lombok.Data;

@Data
public class MicroTaskDTO {
    private Long taskId;
    private String description;
    private Status status;

}
