package com.example.integradeproject.repositories;

import com.example.integradeproject.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository  <Status , Integer>{
    Optional<Status> findByName(String name);

    boolean existsByName(String name);

    Optional<Status> findById(Integer id) ;

}
