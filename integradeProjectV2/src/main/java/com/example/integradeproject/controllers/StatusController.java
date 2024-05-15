package com.example.integradeproject.controllers;

import com.example.integradeproject.dtos.Task2DTO;
import com.example.integradeproject.dtos.Task2IdDTO;
import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.services.ListMapper;
import com.example.integradeproject.services.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/v2/statuses")
public class StatusController {
    @Autowired
    private StatusService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public List<Status> getALLStatus(){
        return service.findAllStatus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Integer id) {
        Status status = service.findById(id);
        return ResponseEntity.ok(status);
    }


    @PostMapping("")
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        Status newStatus = service.createNewStatus(status);
        return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public  ResponseEntity<Status> UpdateStatus (@RequestBody Status status , @PathVariable Integer id ){
        try{
            Status updateStatus  =service.updateStatus(id, status);
            return  ResponseEntity.ok(updateStatus);

        }catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeStatus(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.ok("{}");
    }

    @DeleteMapping("/{id}/{newId}")
    public ResponseEntity<Object> deleteStatusAndTransferTasks(@PathVariable int id, @PathVariable int newId) {
        service.deleteStatusAndTransferTasks(id, newId);
        return ResponseEntity.ok("{}");
    }


}



