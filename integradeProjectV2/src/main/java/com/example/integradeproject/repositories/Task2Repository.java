package com.example.integradeproject.repositories;

import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Task2Repository extends JpaRepository<Task2 , Integer> {
    List<Task2> findByStatus(Status string);


}
