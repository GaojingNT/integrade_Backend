package com.example.integradeproject.controllers;

import com.example.integradeproject.dtos.Task2DTO;
import com.example.integradeproject.dtos.Task2IdDTO;
import com.example.integradeproject.dtos.TaskIdDTO;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.services.Task2Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Task2DTO>> getAllTasks() {
        List<Task2DTO> tasks = service.getTask();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Integer id){
        Task2 task2 = service.findById(id);
        Task2IdDTO task2IdDTO = modelMapper.map(task2 ,Task2IdDTO.class);
        return ResponseEntity.ok(task2IdDTO);
    }
}
