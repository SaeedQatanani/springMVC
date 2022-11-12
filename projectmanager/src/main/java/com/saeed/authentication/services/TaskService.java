package com.saeed.authentication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saeed.authentication.models.Task;
import com.saeed.authentication.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
    private TaskRepository taskRepo;
    
    public List<Task> allTasks(){
    	return taskRepo.findAll();
    }
    public Task createTask(Task t) {
    	return taskRepo.save(t);
    }
}
