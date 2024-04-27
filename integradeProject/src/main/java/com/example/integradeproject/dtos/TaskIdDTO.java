package com.example.integradeproject.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
public class TaskIdDTO {
    private String title;
    private String description;
    private String assignees;
    private String status;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}



