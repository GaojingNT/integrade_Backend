package com.example.integradeproject.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class TaskIdDTO {
    private String title ;
    private String description ;
    private String assignees ;
    private String status ;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS ICT")
    private String createdOn ;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS ICT")
    private String updatedOn ;
}
