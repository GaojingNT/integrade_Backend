package com.example.integradeproject.services;

import com.example.integradeproject.dtos.NewTaskDTO;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ListMapper listMapper;

    public List<Task> getTask() {
        return repository.findAll();
    }

    public Task findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "task " + id + " does not exist !! "));
    }

    public NewTaskDTO createTask(NewTaskDTO newTask) {

        Task task = mapper.map(newTask, Task.class);
        Task savedTask = repository.saveAndFlush(task);
        return mapper.map(savedTask, NewTaskDTO.class);
    }
     public void  deleteById(Integer id){
        Task task  = repository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND , "ID " + id + " DOES NOT EXIST !!!"));
         repository.delete(task);
     }


}
