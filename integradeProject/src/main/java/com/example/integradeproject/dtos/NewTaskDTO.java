package com.example.integradeproject.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NewTaskDTO {
    private String title ;
    private String description ;
    private String assignees ;
    private String  status ;

}
