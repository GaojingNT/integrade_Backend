package com.example.integradeproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class TaskIdDTO {
    private String title ;
    private String description ;
    private String assignees ;
    private String status ;
    private ZonedDateTime createdOn ;
    private ZonedDateTime updatedOn ;

}
