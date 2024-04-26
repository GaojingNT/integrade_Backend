package com.example.integradeproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskIdDTO {
    private String title ;
    private String description ;
    private String assignees ;
    private String status ;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss z " , timezone = "Asia/Bangkok")
    private LocalDateTime createdOn ;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss z " , timezone = "Asia/Bangkok")
    private LocalDateTime updatedOn ;

}
