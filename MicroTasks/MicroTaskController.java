package com.example.TaskManagement.MicroTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/microtasks")
public class MicroTaskController {

    private final MicroTaskService microTaskService;

    @Autowired
    public MicroTaskController(MicroTaskService microTaskService) {
        this.microTaskService = microTaskService;
    }

    // 1. Add a micro-task
    @PostMapping("/add")
    public MicroTask addMicroTask(@RequestBody MicroTaskDTO microTaskDTO) {
        return microTaskService.addMicroTask(
                microTaskDTO.getTaskId(),
                microTaskDTO.getDescription()
        );
    }

    // 2. Get all micro-tasks for a task
    @GetMapping("/task/{taskId}")
    public List<MicroTask> getMicroTasks(@PathVariable Long taskId) {
        return microTaskService.getMicroTasks(taskId);
    }

    // 3. Update description
    @PutMapping("/update/{microTaskId}")
    public MicroTask updateDescription(@PathVariable Long microTaskId, @RequestBody MicroTaskDTO microTaskDTO) {
        return microTaskService.updateDescription(microTaskId, microTaskDTO.getDescription());
    }

    // 4. Change status
    @PatchMapping("/status/{microTaskId}")
    public MicroTask changeStatus(@PathVariable Long microTaskId, @RequestBody MicroTaskDTO microTaskDTO) {
        return microTaskService.changeStatus(microTaskId, microTaskDTO.getStatus());
    }

    // 5. Delete a micro-task
    @DeleteMapping("/delete/{microTaskId}")
    public String deleteMicroTask(@PathVariable Long microTaskId) {
        microTaskService.deleteMicroTask(microTaskId);
        return "Deleted micro-task successfully";
    }
}
