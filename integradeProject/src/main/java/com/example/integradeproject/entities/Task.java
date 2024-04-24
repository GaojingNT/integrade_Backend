package com.example.integradeproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private Integer taskId;
    private String title ;
    private String description ;
    private String assignees ;
    private String status ;
    private String createdOn ;
    private String updatedOn ;



}
