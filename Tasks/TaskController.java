package com.example.TaskManagement.Tasks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get")
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/add")
    public String addTask(@RequestBody Task task){
        taskService.addTask(task);
        return "Added task";
    }
    @PutMapping("/update/{id}")
    public String updateTask(@RequestBody Task updatedTask, @PathVariable long id){
        taskService.updateTask(updatedTask, id);
        return "Updated task";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
        return "Deleted task";
    }

    @PatchMapping("/{id}/complete")
    public String completeTask(@PathVariable long id){
        taskService.markAsDone(id);
        return "Marked as Done";
    }
    @PatchMapping("{id}/fields")
    public String changeField(@PathVariable long id, @RequestBody Map<String, Object> fields){
        taskService.fieldChange(id, fields);
        return "Fields changed";
    }





}
