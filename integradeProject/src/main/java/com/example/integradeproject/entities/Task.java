package com.example.integradeproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss z " , timezone = "Asia/Bangkok")
    private LocalDateTime createdOn ;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss z " , timezone = "Asia/Bangkok")
    private LocalDateTime updatedOn ;



}
