package com.example.integradeproject.services;

import com.example.integradeproject.dtos.NewTaskDTO;
import com.example.integradeproject.dtos.TaskDTO;
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

    @Transactional
    public NewTaskDTO createTask(NewTaskDTO newTask) {
        if(newTask.getTitle() == null || newTask.getTitle().isEmpty()){
            throw new IllegalArgumentException("Title is required");
        }
        Task task = mapper.map(newTask, Task.class);
        Task savedTask = repository.saveAndFlush(task);
        return mapper.map(savedTask, NewTaskDTO.class);
    }

    @Transactional
    public NewTaskDTO deleteById( Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "ID " + id + " DOES NOT EXIST !!!"));
        repository.deleteById(task.getTaskId());
        NewTaskDTO deletedTaskDTO = mapper.map(task, NewTaskDTO.class);

        return deletedTaskDTO;
    }

    @Transactional
    public NewTaskDTO updateTask(NewTaskDTO newTaskDTO, Integer id) {
        if (newTaskDTO == null || newTaskDTO.getTitle() == null || newTaskDTO.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus
                        .NOT_FOUND, "ID " + id + " DOES NOT EXIST !!!"));

        Integer originalId = existingTask.getTaskId();
        mapper.map(newTaskDTO, existingTask);
        existingTask.setTaskId(originalId);
        Task updatedTask = repository.saveAndFlush(existingTask);
        NewTaskDTO updatedTaskDTO = mapper.map(updatedTask, NewTaskDTO.class);

        return updatedTaskDTO;
    }

}
