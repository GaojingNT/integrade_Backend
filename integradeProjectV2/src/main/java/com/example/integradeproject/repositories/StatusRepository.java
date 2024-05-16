package com.example.integradeproject.repositories;

import com.example.integradeproject.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository  <Status , Integer>{
    Optional<Status> findByStatusName(String statusName);

    List<Status> findByStatusNameContains( String Name );

}
