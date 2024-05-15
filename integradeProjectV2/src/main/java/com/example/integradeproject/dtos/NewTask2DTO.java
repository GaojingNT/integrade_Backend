package com.example.integradeproject.dtos;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTask2DTO {
    private Integer id ;
    private String title ;
    private String description ;
    private String assignees ;
    private String  status ;

public void setTitle(String title){
    this.title = title;
}
    public void setDescription(String description) {
        description = description == "" ? null : description;
        this.description = description;
    }

    public void setAssignees(String assignees) {
        assignees = assignees == "" ? null : assignees;
        this.assignees = assignees;
    }


    public void setStatus(String status){
        this.status = status;
    }



}
