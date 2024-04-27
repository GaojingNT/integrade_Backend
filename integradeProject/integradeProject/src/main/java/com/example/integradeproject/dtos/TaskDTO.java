package com.example.integradeproject.dtos;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskDTO {
    private int taskId;
    private String title ;
    private String assignees ;
    private String status ;

}
