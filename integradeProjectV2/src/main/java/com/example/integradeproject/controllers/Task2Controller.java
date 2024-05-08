package com.example.integradeproject.controllers;

import com.example.integradeproject.dtos.Task2DTO;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.services.Task2Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
