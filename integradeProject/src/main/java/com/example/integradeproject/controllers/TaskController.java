package com.example.integradeproject.controllers;

import com.example.integradeproject.dtos.TaskIdDTO;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.dtos.TaskDTO;
import com.example.integradeproject.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/tasks")

public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

//    @GetMapping("")
//    public List<Task> getAllTasks(){
//        return service.getTask();
//    }
    @GetMapping("")
    public ResponseEntity<Object> getTasks(){
        return ResponseEntity.ok(listMapper.mapList(service.getTask(), TaskDTO.class,modelMapper));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Integer id){
        Task task = service.findById(id);
        TaskIdDTO  taskIdDTO = modelMapper.map(task ,TaskIdDTO.class);
        return ResponseEntity.ok(taskIdDTO);
    }



}
