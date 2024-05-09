package com.example.integradeproject.services;

import com.example.integradeproject.dtos.NewTask2DTO;
import com.example.integradeproject.dtos.Task2DTO;
import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.repositories.StatusRepository;
import com.example.integradeproject.repositories.Task2Repository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Task2Service {

    @Autowired
    private Task2Repository repository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    private StatusRepository statusRepository;

    public List<Task2DTO> getTask() {
        List<Task2> tasks = repository.findAll();

        return tasks.stream()
                .map(task -> {
                    Task2DTO task2DTO = mapper.map(task, Task2DTO.class);
                    task2DTO.setStatusName(task.getStatusId().getStatusName());
                    return task2DTO;
                })
                .collect(Collectors.toList());
    }
    @Transactional
    public Task2 findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"task " + id + " does not exist !! "));
    }
    @Transactional
    public NewTask2DTO deleteById(Integer id) {
        Task2 task2 = repository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "ID " + id + " DOES NOT EXIST !!!"));
        repository.deleteById(task2.getTaskId());
        NewTask2DTO deletedTask2DTO = mapper.map(task2, NewTask2DTO.class);

        return deletedTask2DTO;
    }

    public NewTask2DTO createTask(NewTask2DTO newTask2DTO) {
        Task2 task = new Task2();
        task.setTitle(newTask2DTO.getTitle());
        task.setDescription(newTask2DTO.getDescription());
        task.setAssignees(newTask2DTO.getAssignees());

        // Find the Status entity by name
        Optional<Status> status = statusRepository.findByStatusName(newTask2DTO.getStatusName());
        status.ifPresent(task::setStatusId);

        Task2 savedTask = repository.save(task);

        NewTask2DTO createdTaskDTO = mapper.map(savedTask, NewTask2DTO.class);
        createdTaskDTO.setStatusName(savedTask.getStatusId().getStatusName()); // Set the status name
        return createdTaskDTO;
    }

    public NewTask2DTO updateTask(Integer taskId, NewTask2DTO newTask2DTO) {
        Task2 existingTask = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        existingTask.setTitle(newTask2DTO.getTitle());
        existingTask.setDescription(newTask2DTO.getDescription());
        existingTask.setAssignees(newTask2DTO.getAssignees());

        // Find the Status entity by name
        Optional<Status> status = statusRepository.findByStatusName(newTask2DTO.getStatusName());
        status.ifPresent(existingTask::setStatusId);

        Task2 updatedTask = repository.save(existingTask);

        NewTask2DTO updatedTaskDTO = mapper.map(updatedTask, NewTask2DTO.class);
        updatedTaskDTO.setStatusName(updatedTask.getStatusId().getStatusName()); // Set the status name
        return updatedTaskDTO;
    }
}
