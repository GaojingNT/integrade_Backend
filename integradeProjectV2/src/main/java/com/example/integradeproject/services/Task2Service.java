package com.example.integradeproject.services;

import com.example.integradeproject.dtos.Task2DTO;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.entities.Status;
import com.example.integradeproject.repositories.Task2Repository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task2Service {

    @Autowired
    private Task2Repository repository;

    @Autowired
    private ModelMapper mapper;

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


}
