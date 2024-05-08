package com.example.integradeproject.repositories;

import com.example.integradeproject.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository <Task , Integer> {

}
