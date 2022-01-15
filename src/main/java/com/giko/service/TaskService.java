package com.giko.service;

import com.giko.model.Task;
import com.giko.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
}
