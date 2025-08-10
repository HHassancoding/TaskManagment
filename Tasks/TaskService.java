package com.example.TaskManagement.Tasks;

import com.example.TaskManagement.Pomodoros.PomodoroRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TaskService{

    private final TaskRepo repository;
    private final PomodoroRepo pomodoroRepo;

    public TaskService(TaskRepo repository, PomodoroRepo pomodoroRepo){
        this.repository = repository;
        this.pomodoroRepo = pomodoroRepo;
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task addTask(Task task){
        return repository.save(task);
    }

    @Transactional
    public String deleteTask(long id){
        Task task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        pomodoroRepo.deleteAllByTask(task);
        repository.deleteById(id);
        return "Deleted";
    }
    public void updateTask(Task task, long id){
        Task updating  = repository.findById(id).get();
        updating.updateTask(task);
        repository.save(updating);
    }

   public Task markAsDone(long id){
        Task task = repository.findById(id).get();
        task.setCompleted(true);
        return repository.save(task);
   }


    public void fieldChange(long id, Map<String, Object> fields) {
        Task task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if(fields.containsKey("taskName")){task.setTaskName(fields.get("taskName").toString());}
        if(fields.containsKey("taskDescription")){task.setTaskDescription(fields.get("taskDescription").toString());}
        if(fields.containsKey("dueDate")) {
            String datastr = fields.get("dueDate").toString();
            task.setDueDate(LocalDate.parse(datastr));
        }
        if(fields.containsKey("completed")){task.setCompleted((Boolean) fields.get("completed"));}

        repository.save(task);
    }

}
