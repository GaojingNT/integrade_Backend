package com.example.integradeproject.controllers;

import com.example.integradeproject.dtos.*;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.services.Task2Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/v2/tasks")


public class Task2Controller {
    @Autowired
    private Task2Service service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public ResponseEntity<List<Task2DTO>> getAllTasks(
            @RequestParam(required = false) String filterStatus,
            @RequestParam(required = false) String statusName
    ) {
        List<Task2DTO> tasks = service.getTask(filterStatus, statusName);
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Integer id){
        Task2 task2 = service.findById(id);
        Task2IdDTO task2IdDTO = modelMapper.map(task2 ,Task2IdDTO.class);
        return ResponseEntity.ok(task2IdDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<NewTask2DTO> removeTask (@PathVariable Integer id ){
        NewTask2DTO newTask2DTO = service.deleteById(id);
        return  ResponseEntity.ok(newTask2DTO);
    }
//    @PostMapping("")
//    public ResponseEntity<NewTask2DTO> createTask(@RequestBody NewTask2DTO newTask2DTO) {
//        NewTask2DTO createdTask = service.createTask(newTask2DTO);
//
//        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
//    }
@PostMapping("")
public ResponseEntity<NewTask2DTO> createTask(@RequestBody NewTask2DTO newTask2DTO) {
    NewTask2DTO createdTaskDTO = service.createTask(newTask2DTO);
    return new ResponseEntity<>(createdTaskDTO, HttpStatus.OK);
}
    @PutMapping("/{id}")
    public ResponseEntity<NewTask2DTO> updateTask(@PathVariable Integer id, @RequestBody NewTask2DTO newTask2DTO) {
        NewTask2DTO updatedTaskDTO = service.updateTask(id, newTask2DTO);
        return new ResponseEntity<>(updatedTaskDTO, HttpStatus.OK);
    }
}




