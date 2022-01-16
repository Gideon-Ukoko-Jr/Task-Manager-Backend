package com.giko.controller;

import com.giko.dto.CountType;
import com.giko.model.Task;
import com.giko.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping("/task/vData/percentcounttype")
    public List<CountType> getPercentageGroupByType(){
        return taskService.getPercentageGroupByType();
    }

    @GetMapping("/task")
    public List<Task> getTask(){
        return taskService.getTasks();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById(@PathVariable Long id){
        return taskService.getTaskById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requested Task Not Found"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> addTask(@RequestBody Task taskParam, @PathVariable Long id){
        if (taskService.existsById(id)){
            Task task = taskService.getTaskById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Requested Task Not Found"));

            task.setTitle(taskParam.getTitle());
            task.setType(taskParam.getType());
            task.setDueDate(taskParam.getDueDate());
            task.setDescription(taskParam.getDescription());

            taskService.save(task);
            return ResponseEntity.ok().body(task);

        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        if (taskService.existsById(id)){
            taskService.deleteTask(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

}
