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
import java.util.Optional;

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

    @Transactional
    public Status findById(Integer id){
        return statusRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"task " + id + " does not exist !! "));
    }

    public Status createNewStatus(Status status) {

        boolean exists = statusRepository.existsByName(status.getName());
        if (exists) {
            throw new IllegalArgumentException("A Status with the name '" + status.getName() + "' already exists.");
        }

        return statusRepository.saveAndFlush(status);
    }






    public Status updateStatus(Integer id, Status updatedStatus) {
        if (id == 1 || updatedStatus.getName() == null || updatedStatus.getName().trim().isEmpty()) {
            throw new IllegalStateException("Cannot edit this status");
        }
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isPresent()) {
            Status existingStatus = optionalStatus.get();
            existingStatus.setName(updatedStatus.getName());
            existingStatus.setDescription(updatedStatus.getDescription());
            return statusRepository.save(existingStatus);
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Status not found with id: " + id);
        }
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
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Status with ID " + id + " does not exist"));
        Status newStatus = statusRepository.findById(newStatusId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Status with ID " + newStatusId + " does not exist"));

        List<Task2> tasksWithCurrentStatus = task2Repository.findByStatus(currentStatus);
        tasksWithCurrentStatus.forEach(task -> task.setStatus(newStatus));
        task2Repository.saveAll(tasksWithCurrentStatus);

        statusRepository.delete(currentStatus);
    }
}
