package com.example.TaskManagement.MicroTasks;

import com.example.TaskManagement.Tasks.Task;
import com.example.TaskManagement.Tasks.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MicroTaskService {
    private final MicroTaskRepo microTaskRepo;
    private final TaskRepo taskRepo;

    @Autowired
    public MicroTaskService(MicroTaskRepo microTaskRepo, TaskRepo taskRepo) {
        this.microTaskRepo = microTaskRepo;
        this.taskRepo = taskRepo;
    }

    // 1. Add micro-task
    public MicroTask addMicroTask(Long taskId, String description) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        MicroTask microTask = new MicroTask(description, Status.NOT_COMPLETED);
        microTask.setTask(task);
        return microTaskRepo.save(microTask);
    }

    @Transactional
    // 2. Get all micro-tasks for a task
    public List<MicroTask> getMicroTasks(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return microTaskRepo.findAllByTask(task);
    }
    @Transactional
    // 3. Update description
    public MicroTask updateDescription(Long id, String newDescription) {
        MicroTask microTask = microTaskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Micro-task not found"));
        microTask.setDescription(newDescription);
        return microTaskRepo.save(microTask);
    }

    // 4. Change status
    public MicroTask changeStatus(Long id, Status newStatus) {
        MicroTask microTask = microTaskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Micro-task not found"));
        microTask.setStatus(newStatus);
        return microTaskRepo.save(microTask);
    }

    // 5. Delete micro-task
    public void deleteMicroTask(Long id) {
        if (!microTaskRepo.existsById(id)) {
            throw new RuntimeException("Micro-task not found");
        }
        microTaskRepo.deleteById(id);
    }
}
