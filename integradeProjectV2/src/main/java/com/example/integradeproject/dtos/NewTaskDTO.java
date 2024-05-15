package com.example.integradeproject.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class NewTaskDTO {
    private Integer taskId ;
    private String title ;
    private String description ;
    private String assignees ;
    private String  status ;



}
