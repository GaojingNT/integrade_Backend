package com.example.integradeproject.controllers;
import com.example.integradeproject.dtos.NewTaskDTO;
import com.example.integradeproject.dtos.TaskIdDTO;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.dtos.TaskDTO;
import com.example.integradeproject.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
    @PostMapping("")
    public ResponseEntity<NewTaskDTO> createTask(@RequestBody NewTaskDTO newTask) {
        // เรียกใช้ service เพื่อสร้างงานใหม่
        NewTaskDTO createdTask = service.createTask(newTask);

        // ส่งกลับ ResponseEntity ที่มีสถานะ 201 Created พร้อมงานที่ถูกสร้าง
        return ResponseEntity.status(201).body(createdTask);
    }
    @DeleteMapping("/{id}")
    public  void  removeTask (@PathVariable Integer id ){
        service.deleteById(id);
    }



}
