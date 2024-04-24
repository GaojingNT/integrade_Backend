package com.example.integradeproject.services;

import com.example.integradeproject.entities.Task;
import com.example.integradeproject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class TaskService {
    @Autowired
    private TaskRepository repository ;

    public List<Task>  getTask(){
        return repository.findAll();
    }

    public Task findById(Integer id){
        return  repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "task " + id + " does not exist !! "));
    }

}
