package com.example.integradeproject.repositories;

import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Task2Repository extends JpaRepository<Task2 , Integer> {
    List<Task2> findByStatusId(Status string);
    List<Task2> findByStatusId_StatusNameOrderByStatusId_StatusNameAsc(String statusName);
    List<Task2> findByStatusId_StatusNameOrderByStatusId_StatusNameDesc(String statusName);
    List<Task2> findAllByOrderByStatusId_StatusNameAsc();
    List<Task2> findAllByOrderByStatusId_StatusNameDesc();

    List<Task2> findByStatusId_StatusNameInOrderByStatusId_StatusNameAsc(List<String> statusNames);

    List<Task2> findByStatusId_StatusNameInOrderByStatusId_StatusNameDesc(List<String> statusNames);
}
