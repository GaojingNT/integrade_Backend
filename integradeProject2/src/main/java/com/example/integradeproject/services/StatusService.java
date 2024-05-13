package com.example.integradeproject.services;

import com.example.integradeproject.dtos.NewTaskDTO;
import com.example.integradeproject.entities.Status;
import com.example.integradeproject.entities.Task;
import com.example.integradeproject.entities.Task2;
import com.example.integradeproject.repositories.StatusRepository;
import com.example.integradeproject.repositories.Task2Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private Task2Repository task2Repository ;
    @Autowired
    ModelMapper mapper;
    @Autowired
    ListMapper listMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Status> findAllStatus() {
        return statusRepository.findAll();
    }

    public Status createNewStatus(Status status) {
        return statusRepository.saveAndFlush(status);
    }



    public Status updateByStatusId(Status status, Integer statusId) {
        if (status == null || status.getStatusName() == null || status.getStatusName().isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }
        Status existingStatus = statusRepository.findById(statusId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus
                        .NOT_FOUND, "ID " + status + " DOES NOT EXIST !!!"));
        Integer originalId = existingStatus.getStatusId();
        mapper.map(status, existingStatus);
        existingStatus.setStatusId(originalId);

        return statusRepository.saveAndFlush(existingStatus);
    }
    @Transactional
    public Status deleteById(Integer statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status with ID " + statusId + " does not exist"));

        statusRepository.delete(status);
        return status;
    }
    @Transactional
    public void deleteStatusAndTransferTasks(int id, int newStatusId) {
        Status currentStatus = statusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status with ID " + id + " does not exist"));

        Status newStatus = statusRepository.findById(newStatusId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status with ID " + newStatusId + " does not exist"));

        List<Task2> tasksWithCurrentStatus = task2Repository.findByStatusId(currentStatus);
        tasksWithCurrentStatus.forEach(task -> task.setStatusId(newStatus));
        task2Repository.saveAll(tasksWithCurrentStatus);

        statusRepository.delete(currentStatus);
    }
}
